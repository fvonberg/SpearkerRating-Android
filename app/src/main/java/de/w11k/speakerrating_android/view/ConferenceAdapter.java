package de.w11k.speakerrating_android.view;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Optional;

import java.util.List;

import de.w11k.speakerrating_android.R;
import de.w11k.speakerrating_android.SelectTalkActivity;
import de.w11k.speakerrating_android.data.Conference;

public class ConferenceAdapter extends RecyclerView.Adapter<ConferenceAdapter.ConferenceViewHolder> {

    public final static String EXTRA_CONFERENCE_ID = "extra_conference_id";

    private final List<Conference> conferenceList;

    public ConferenceAdapter(List<Conference> conferenceList) {
        this.conferenceList = conferenceList;
    }

    @Override
    public ConferenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.conference_card_layout, parent, false);
        return new ConferenceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConferenceViewHolder holder, int position) {
        Conference conference = conferenceList.get(position);

        Optional<String> optImageUrl = conference.getOptionalImageUrl();
        if (optImageUrl.isPresent()) {
            new DownloadImageTask(holder.thumbnailImage).execute(optImageUrl.get());
        }

        holder.conferenceName.setText(conference.getTitle());
        holder.conferenceDescription.setText(conference.getDescription());

        holder.conferenceRateButton.setOnClickListener(new ConferenceButtonOnClickListener(conference));
    }

    @Override
    public int getItemCount() {
        return conferenceList.size();
    }

    public static class ConferenceViewHolder extends RecyclerView.ViewHolder {

        protected ImageView thumbnailImage;
        protected TextView conferenceName;
        protected TextView conferenceDescription;
        protected Button conferenceRateButton;

        public ConferenceViewHolder(View itemView) {
            super(itemView);

            this.thumbnailImage = (ImageView) itemView.findViewById(R.id.conferenceThumbnail);
            this.conferenceName = (TextView) itemView.findViewById(R.id.conferenceTitle);
            this.conferenceDescription = (TextView) itemView.findViewById(R.id.conferenceText);
            this.conferenceRateButton = (Button) itemView.findViewById(R.id.conferenceButton);
        }
    }

    private class ConferenceButtonOnClickListener implements View.OnClickListener {

        private final Conference conference;

        public ConferenceButtonOnClickListener(Conference conference) {
            this.conference = conference;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), SelectTalkActivity.class);

            intent.putExtra(EXTRA_CONFERENCE_ID, conference.getId());

            view.getContext().startActivity(intent);
        }
    }
}
