package com.example.sonlethanh.hellotwitter.model;

import java.io.Serializable;

/**
 * Created by sonlethanh on 20/7/14.
 */
public class Tweet implements Serializable {
    private String title;
    private String body;
    private String id;

    private static final long serialVersionUID = 1L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
