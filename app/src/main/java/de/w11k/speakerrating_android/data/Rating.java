package de.w11k.speakerrating_android.data;

public class Rating {

    private final Integer id;
    private final Integer conferenceId;
    private final Integer talkId;
    private final Integer rating;
    private final String feedback;

    public Rating(Integer id, Integer conferenceId, Integer talkId, Integer rating, String feedback) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.talkId = talkId;
        this.rating = rating;
        this.feedback = feedback;
    }

    public Integer getId() {
        return id;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public Integer getTalkId() {
        return talkId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }
}
