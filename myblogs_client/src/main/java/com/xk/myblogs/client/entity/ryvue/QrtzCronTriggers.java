package com.xk.myblogs.client.entity.ryvue;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * TABLE  qrtz_cron_triggers
 * MyBatis Generator Create
 */
public class QrtzCronTriggers extends QrtzCronTriggersKey implements Serializable {
    private String cronExpression;

    private String timeZoneId;

    private static final long serialVersionUID = 1L;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cronExpression=").append(cronExpression);
        sb.append(", timeZoneId=").append(timeZoneId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}