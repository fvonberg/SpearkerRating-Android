package de.w11k.speakerrating_android.data;

public class Conference {

    private final Integer id;
    private final String imageUrl;
    private final String title;
    private final String description;

    public Conference(Integer id, String imageUrl, String title, String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
