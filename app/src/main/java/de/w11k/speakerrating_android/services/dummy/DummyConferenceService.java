package de.w11k.speakerrating_android.services.dummy;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

import de.w11k.speakerrating_android.data.Conference;
import de.w11k.speakerrating_android.data.Rating;
import de.w11k.speakerrating_android.data.Talk;
import de.w11k.speakerrating_android.services.ConferenceService;

public class DummyConferenceService implements ConferenceService {
    @Override
    public List<Conference> getAllConferences() {

        Conference karlsruhe = new Conference(0, Optional.of("https://placekitten.com/g/500/300"), "Karlsruher Entwicklertag 2016", "Agilität, Qualität, Innovation");
        Conference nuernberg = new Conference(1, Optional.<String>absent(),
                "Herbstcampus 2016", "Dieses Jahr findet der Herbstcampus in Nürnberg bereits zum neunten Mal statt. Vom 30. August bis 1. September 2016 veranstaltet die MATHEMA – nunmehr mit tatkräftiger Unterstützung von dpunkt.verlag, heise Developer und iX – diese technologieorientierte Konferenz für Software-Entwickler, Architekten und Projektleiter aus dem Enterprise-Bereich. Wieder gibt es ein abwechslungsreiches und spannendes Programm zur Software-Entwicklung mit den Technologie-Schwerpunkten .NET und Java geben, aber auch JavaScript spielt eine Rolle.\n" +
                "\n" +
                "Erstmalig dauert der Herbstcampus drei Tage. Am Dienstag wird die Veranstaltung mit den Tutorien eröffnet, während am Mittwoch und Donnerstag die Vorträge der Hauptkonferenz stattfinden.");
        Conference frankfurt = new Conference(2, Optional.of("https://placekitten.com/g/300/200"), "IT-Tage 2016", "IT-Management- und Entwickler-Konferenz");


        List<Conference> conferenceList = new ArrayList<>();
        conferenceList.add(karlsruhe);
        conferenceList.add(nuernberg);
        conferenceList.add(frankfurt);

        return conferenceList;
    }

    @Override
    public List<Talk> getAllTalksForConferenceWith(Integer id) {

        List<Talk> talkList = new ArrayList<>();

        talkList.add(new Talk(0, id, "Talk 1", "Lorem ipsum", "Frederik von Berg", Optional.of("https://placekitten.com/g/900/400"), "heute"));
        talkList.add(new Talk(1, id, "Talk 2", "Lorem ipsum", "Dragan Zuvic", Optional.<String>absent(), "heute"));
        talkList.add(new Talk(2, id, "Talk 3", "Lorem ipsum", "Philipp Burgmer", Optional.<String>absent(), "heute"));

        return talkList;
    }

    @Override
    public boolean sendRating(Rating customerRating) {
        /* store the rating somewhere, or not ;-)*/
        return true;
    }
}
