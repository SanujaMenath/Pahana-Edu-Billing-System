package com.pahanaedu.model;

public class HelpTopic {
    private String title;
    private String description;

    public HelpTopic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
