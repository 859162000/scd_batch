#!/usr/bin/env bash

set -x

export PATH=$JAVA_HOME/bin:$PATH

LOG_PATH=$HOME/var/log/credit

# BatchLauncher
BATCH_HOME=$(cd $(dirname $0);cd ..;pwd)

# BatchLauncher/lib
LIB_ROOT=$BATCH_HOME/lib

# BatchLauncher/conf
CONFIG_ROOT=$BATCH_HOME/conf

CLASSPATH=$CLASSPATH:$CONFIG_ROOT:$LIB_ROOT/*

JAVA_OPTS="-Xms4096m -Xmx4096m -XX:NewSize=512m -server -XX:+DisableExplicitGC -Dlog.path=$LOG_PATH -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:$LOG_PATH/logs/gc.log"

java -classpath $CLASSPATH $JAVA_OPTS com.scd.batch.executor.job.BatchLauncher -p classpath:META-INF/ApplicationContext-executor.xml -f $1 $2 $3 $4 $5 $6 $7 $8 $9

RETVAL=$?

exit $RETVAL