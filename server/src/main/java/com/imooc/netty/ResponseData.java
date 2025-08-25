package com.imooc.netty;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private int id;
    private String message;

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

    @Override
    public String toString() {
        return "ResponseData{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
