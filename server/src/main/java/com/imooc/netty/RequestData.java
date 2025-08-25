package com.imooc.netty;

import java.io.Serializable;

public class RequestData implements Serializable {
    private int id;
    private String message;
    private byte[] attachment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
