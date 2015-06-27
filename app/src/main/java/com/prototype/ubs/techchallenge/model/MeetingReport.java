package com.prototype.ubs.techchallenge.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 27/6/2015.
 */
public class MeetingReport {

    private Integer id;
    private DateTime meetingDate;
    private DateTime verifiedDate;
    private String topic;
    private List<String> tags;
    private Status status;
    private String author;
    private List<String> summary;
    private List<String> agreement;
    
    public MeetingReport() {
        tags = new ArrayList<>();
        summary = new ArrayList<>();
        agreement = new ArrayList<>();
    }
    
    public DateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(DateTime meetingDate) {
        this.meetingDate = meetingDate;
    }

    public DateTime getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(DateTime verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTags(String tag) {
        this.tags.add(tag);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getSummary() {
        return summary;
    }

    public void addSummary(String summary) {
        this.summary.add(summary);
    }

    public List<String> getAgreement() {
        return agreement;
    }

    public void addAgreement(String agreement) {
        this.agreement.add(agreement);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public enum Status {
        VERIFIED, UNVERIFIED;
    }


}
