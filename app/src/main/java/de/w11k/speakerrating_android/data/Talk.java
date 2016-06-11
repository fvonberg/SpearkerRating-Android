package de.w11k.speakerrating_android.data;

import java.io.Serializable;

public class Talk implements Serializable{

    private final Integer id;
    private final Integer conferenceId;
    private final String title;
    private final String description;
    private final String author;
    private final String imageUrl;
    private final String presentationDate;

    public Talk(Integer id, Integer conference_id, String title, String description, String speaker, String optionalImageUrl, String presentationDate) {
        this.id = id;
        this.conferenceId = conference_id;
        this.title = title;
        this.description = description;
        this.author = speaker;
        this.imageUrl = optionalImageUrl;
        this.presentationDate = presentationDate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPresentationDate() {
        return presentationDate;
    }
}
