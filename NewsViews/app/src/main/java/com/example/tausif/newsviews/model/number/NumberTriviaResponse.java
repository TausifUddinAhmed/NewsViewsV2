package com.example.tausif.newsviews.model.number;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NumberTriviaResponse {

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("found")

    @Expose
    private Boolean found;

    @SerializedName("type")
    @Expose
    private String type;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NumberTriviaResponse{" +
                "text='" + text + '\'' +
                ", number=" + number +
                ", found=" + found +
                ", type='" + type + '\'' +
                '}';
    }
}
