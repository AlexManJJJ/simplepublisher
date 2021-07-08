package com.example.demo.model;

public class Publication {

    private String header;
    private String body;

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
