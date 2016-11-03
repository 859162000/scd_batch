package com.scd.batch.common.logger;

import java.text.MessageFormat;

/**
 * Format the log arguments to message argument and last throwable
 */
public class FormatedLogMessage {

    public final String formatedMsg;
    public final Object[] formatedArgs;
    public final Throwable t;

    public FormatedLogMessage(String message, Object[] args) {
        if (args == null || args.length == 0) {
            this.formatedArgs = null;
            this.t = null;
        } else {
            if (args[args.length - 1] instanceof Throwable) {
                this.t = (Throwable) args[args.length - 1];
                if (args.length > 1) {
                    this.formatedArgs = new Object[args.length - 1];
                    System.arraycopy(args, 0, this.formatedArgs, 0, this.formatedArgs.length);
                } else {
                    this.formatedArgs = null;
                }
            } else {
                this.formatedArgs = args;
                this.t = null;
            }
        }

        if (this.formatedArgs != null) {
            for (int i = 0; i < formatedArgs.length; i++) {
                Object arg = formatedArgs[i];
                if (arg instanceof Number) {
                    formatedArgs[i] = arg.toString();
                }
                message = message.replace("{" + i + "_sec}", "{" + i + "}");
            }
            message = message.replace("_sec", "");
        }

        this.formatedMsg = MessageFormat.format(message, formatedArgs);
    }
}
