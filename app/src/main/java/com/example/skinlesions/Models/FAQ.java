package com.example.skinlesions.Models;

public class FAQ {
    private String ask;
    private String answer;

    public FAQ(String question, String response) {
        this.ask = question;
        this.answer = response;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
