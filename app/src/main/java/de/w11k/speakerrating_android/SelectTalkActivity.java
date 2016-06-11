package de.w11k.speakerrating_android;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import de.w11k.speakerrating_android.data.Talk;
import de.w11k.speakerrating_android.services.ConferenceServiceFactory;
import de.w11k.speakerrating_android.view.ConferenceAdapter;
import de.w11k.speakerrating_android.view.TalkAdapter;

public class SelectTalkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_talk);

        Intent intent = getIntent();
        Integer conferenceId = (Integer) intent.getSerializableExtra(ConferenceAdapter.EXTRA_CONFERENCE_ID);

        List<Talk> conferenceTalks = ConferenceServiceFactory.getConferenceService().getAllTalksForConferenceWith(conferenceId);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.talkCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );

        TalkAdapter talkAdapter = new TalkAdapter(conferenceTalks, font);

        recyclerView.setAdapter(talkAdapter);
    }
}
