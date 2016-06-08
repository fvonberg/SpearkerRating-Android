package de.w11k.speakerrating_android.data;

import com.google.common.base.Optional;

public class Conference {

    private final Integer id;
    private final Optional<String> optionalImageUrl;
    private final String title;
    private final String description;

    public Conference(Integer id, Optional<String> optionalImageUrl, String title, String description) {
        this.id = id;
        this.optionalImageUrl = optionalImageUrl;
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

    public Optional<String> getOptionalImageUrl() {
        return optionalImageUrl;
    }
}
