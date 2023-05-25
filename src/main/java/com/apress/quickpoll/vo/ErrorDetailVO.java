package com.apress.quickpoll.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetailVO {
    private final String title;
    private final int status;
    private final String detail;
    private final long timeStamp;
    private final String message;

    private Map<String, List<ValidationErrorVO>> errors = new HashMap<String,List<ValidationErrorVO>>();

    public ErrorDetailVO(String title, int status, String detail, long timeStamp, String message, Map<String, List<ValidationErrorVO>> errors) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.message = message;
        this.errors = errors;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, List<ValidationErrorVO>> getErrors() {
        return errors;
    }
}
