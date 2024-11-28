package com.darkube.server.types;

public class Err {

    public int code = 400;
    public String error = "Bad Request";

    public Err(String error, int code) {
        this.error = error;
        this.code = code;
    }

    public Err(String error) {
        this.error = error;
    }

}
