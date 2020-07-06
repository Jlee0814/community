package com.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"Topic not found"),
    TARGET_PARAM_NOT_FOUND(2002,"TARGET_PARAM_NOT_FOUND"),
    NO_LOGIN(2003,"PLEASE LOGIN AND TRY IT AGAIN"),
    SYS_ERROR(2004, "SERVER ERROR "),
    TYPE_PARAM_WRONG(2005,"TYPE_PARAM_WRONG"),
    COMMENT_NOT_FOUND(2006,"COMMENT_NOT_FOUND")
    ;
    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

}
