package de.w11k.speakerrating_android.services;

import android.content.res.AssetManager;

import java.util.List;

import de.w11k.speakerrating_android.data.Conference;
import de.w11k.speakerrating_android.data.Rating;
import de.w11k.speakerrating_android.data.Talk;

public interface ConferenceService {

    public List<Conference> getAllConferences(AssetManager assets);
    public List<Talk> getAllTalksForConferenceWith(Integer id, AssetManager assets);
    public boolean sendRating(Rating customerRating);
}
