package com.gabriellewhite.cryptock.ui.news;

import java.util.HashMap;
import java.util.Map;

public class NewsModel {

    public String category;
    public Integer datetime;
    public String headline;
    private Integer id;
    private String image;
    private String related;
    private String source;
    private String summary;
    public  String url;

    public NewsModel(){}

    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNewsUrl() {
        return url;
    }

    public void setNewsUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}