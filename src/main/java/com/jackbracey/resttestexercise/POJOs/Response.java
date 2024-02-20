package com.jackbracey.resttestexercise.POJOs;

public class Response {

    private Object object;

    private int code;

    private String message;

    public Response(Object object, int code, String message) {
        this.object = object;
        this.code = code;
        this.message = message;
    }

    public Response() {
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Response Success(Object object) {
        return new Response(object, 200, "");
    }

    public static Response Success(Object object, String message) {
        return new Response(object, 200, message);
    }

}
