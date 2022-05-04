package com.gdev.quotes;

public class ResultsModal {
    String author;
    String text;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return text;
    }

    public ResultsModal(String author, String content) {
        this.author = author;
        this.text = content;
    }
}
