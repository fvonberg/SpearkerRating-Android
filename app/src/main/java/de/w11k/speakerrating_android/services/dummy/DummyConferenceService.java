package de.w11k.speakerrating_android.services.dummy;

import android.content.res.AssetManager;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.w11k.speakerrating_android.data.Conference;
import de.w11k.speakerrating_android.data.Rating;
import de.w11k.speakerrating_android.data.Talk;
import de.w11k.speakerrating_android.services.ConferenceService;

public class DummyConferenceService implements ConferenceService {

    private class JsonConferenceData {
        List<Conference> data;
    }

    private class JsonTalkData {
        List<JsonConferenceToTalkList> data;
    }

    private class JsonConferenceToTalkList {
        Integer conferenceId;
        List<Talk> talks;
    }

    @Override
    public List<Conference> getAllConferences(AssetManager assets) {

        String json = loadJSONFromAsset(assets, "conferencesData.json");

        Gson gson = new GsonBuilder().create();
        JsonConferenceData jsonConferenceData = gson.fromJson(json, JsonConferenceData.class);

        return jsonConferenceData.data;
    }

    @Override
    public List<Talk> getAllTalksForConferenceWith(final Integer conferenceId, AssetManager assets) {

        String json = loadJSONFromAsset(assets, "talksData.json");

        Gson gson = new GsonBuilder().create();
        JsonTalkData jsonTalkData = gson.fromJson(json, JsonTalkData.class);

        List<Talk> talks = FluentIterable.from(jsonTalkData.data).filter(new Predicate<JsonConferenceToTalkList>() {
            @Override
            public boolean apply(JsonConferenceToTalkList input) {
                return input.conferenceId.equals(conferenceId);
            }
        }).transformAndConcat(new Function<JsonConferenceToTalkList, Iterable<Talk>>() {
            @Override
            public Iterable<Talk> apply(JsonConferenceToTalkList input) {
                return input.talks;
            }
        }).toList();

        return talks;
    }

    @Override
    public boolean sendRating(Rating customerRating) {
        /* store the rating somewhere, or not ;-)*/
        return true;
    }

    private String loadJSONFromAsset(AssetManager assets, String jsonFileName) {
        String json = null;
        try {
            InputStream is = assets.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
