package com.company.project.core;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 */
public class BusinessException extends RuntimeException {
    private Object messages;

    private Object extra;
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public  BusinessException(Object messages, Object extra) {

        super();

        this.messages = messages;

        this.extra = extra;
    }

    public Object getMessages() {
        return messages;
    }

    public void setMessages(Object messages) {
        this.messages = messages;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
