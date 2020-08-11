package com.xk.myblogs.client.entity.ryvue;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * TABLE  qrtz_fired_triggers
 * MyBatis Generator Create
 */
public class QrtzFiredTriggersKey implements Serializable {
    private String schedName;

    private String entryId;

    private static final long serialVersionUID = 1L;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", schedName=").append(schedName);
        sb.append(", entryId=").append(entryId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}