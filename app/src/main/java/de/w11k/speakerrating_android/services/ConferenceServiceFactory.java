package de.w11k.speakerrating_android.services;

import de.w11k.speakerrating_android.services.dummy.DummyConferenceService;

public class ConferenceServiceFactory {

    private static ConferenceService conferenceService;

    public static ConferenceService getConferenceService() {

        if (conferenceService == null) {
            conferenceService = new DummyConferenceService();
        }

        return conferenceService;
    }

}
