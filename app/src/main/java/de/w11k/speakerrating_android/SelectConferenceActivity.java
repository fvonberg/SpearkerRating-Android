package de.w11k.speakerrating_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import de.w11k.speakerrating_android.services.ConferenceService;
import de.w11k.speakerrating_android.services.ConferenceServiceFactory;
import de.w11k.speakerrating_android.view.ConferenceAdapter;

public class SelectConferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_conference);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.conferenceCardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ConferenceService conferenceService = ConferenceServiceFactory.getConferenceService();
        ConferenceAdapter conferenceAdapter = new ConferenceAdapter(conferenceService.getAllConferences());

        recyclerView.setAdapter(conferenceAdapter);
    }
}
