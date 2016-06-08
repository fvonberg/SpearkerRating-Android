package de.w11k.speakerrating_android.data;

import com.google.common.base.Optional;

import java.io.Serializable;

public class Talk implements Serializable{

    private final Integer id;
    private final Integer conferenceId;
    private final String title;
    private final String description;
    private final String speaker;
    private final Optional<String> optionalImageUrl;

    public Talk(Integer id, Integer conference_id, String title, String description, String speaker, Optional<String> optionalImageUrl) {
        this.id = id;
        this.conferenceId = conference_id;
        this.title = title;
        this.description = description;
        this.speaker = speaker;
        this.optionalImageUrl = optionalImageUrl;
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

    public String getSpeaker() {
        return speaker;
    }

    public Optional<String> getOptionalImageUrl() {
        return optionalImageUrl;
    }
}
