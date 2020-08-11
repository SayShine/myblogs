package com.xk.myblogs.client.entity.ryvue;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * TABLE  qrtz_simple_triggers
 * MyBatis Generator Create
 */
public class QrtzSimpleTriggers extends QrtzSimpleTriggersKey implements Serializable {
    private Long repeatCount;

    private Long repeatInterval;

    private Long timesTriggered;

    private static final long serialVersionUID = 1L;

    public Long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Long repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Long getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", repeatCount=").append(repeatCount);
        sb.append(", repeatInterval=").append(repeatInterval);
        sb.append(", timesTriggered=").append(timesTriggered);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}