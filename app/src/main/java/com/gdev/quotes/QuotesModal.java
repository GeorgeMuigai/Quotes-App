package com.gdev.quotes;

public class QuotesModal {
    String author;
    String content;

    public QuotesModal(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
