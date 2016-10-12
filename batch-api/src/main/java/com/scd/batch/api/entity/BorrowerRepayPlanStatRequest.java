package com.scd.batch.api.entity;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by liyankai on 2016/10/9.
 */
public class BorrowerRepayPlanStatRequest implements Serializable {

    private static final long serialVersionUID = 5894050827680521125L;

    @Min(value = 1, message = "页码必须大于0!")
    public final int pno;

    @Min(value = 1, message = "每页记录数必须大于0!")
    public final int psize;

    public BorrowerRepayPlanStatRequest(int pno, int psize) {
        this.pno = pno;
        this.psize = psize;
    }
}
