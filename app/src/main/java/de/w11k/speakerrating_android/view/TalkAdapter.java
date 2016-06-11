package de.w11k.speakerrating_android.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.w11k.speakerrating_android.R;
import de.w11k.speakerrating_android.RatingActivity;
import de.w11k.speakerrating_android.data.Talk;

public class TalkAdapter extends RecyclerView.Adapter<TalkAdapter.TalkViewHolder>{

    public final static String EXTRA_TALK_DATA = "extra_talk";

    private final List<Talk> talkList;
    private final Typeface typeFace;

    public TalkAdapter(List<Talk> talkList, Typeface typeFace) {
        this.talkList = talkList;
        this.typeFace = typeFace;
    }

    @Override
    public TalkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.talk_card_layout, parent, false);
        return new TalkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TalkViewHolder holder, int position) {
        Talk talk = talkList.get(position);

//        Optional<String> optImageUrl = talk.getImageUrl();
//        if (optImageUrl.isPresent()) {
//            new DownloadImageTask(holder.thumbnailImage).execute(optImageUrl.get());
//        }

        holder.talkTitle.setText(talk.getTitle());
        holder.talkSpeaker.setTypeface(typeFace);
        holder.talkSpeaker.setText("\uf007 " + talk.getAuthor());
        holder.talkDate.setTypeface(typeFace);
        holder.talkDate.setText("\uf073 " + talk.getPresentationDate());
        holder.talkText.setText(talk.getDescription());

        holder.talkRateButton.setOnClickListener(new TalkButtonOnClickListener(talk));
    }

    @Override
    public int getItemCount() {
        return talkList.size();
    }

    public static class TalkViewHolder extends RecyclerView.ViewHolder {

        protected final ImageView thumbnailImage;
        protected final TextView talkTitle;
        protected final TextView talkSpeaker;
        protected final TextView talkDate;
        protected final TextView talkText;
        protected final Button talkRateButton;

        public TalkViewHolder(View itemView) {
            super(itemView);

            this.thumbnailImage= (ImageView) itemView.findViewById(R.id.talkThumbnail);
            this.talkTitle = (TextView) itemView.findViewById(R.id.talkTitle);
            this.talkSpeaker = (TextView) itemView.findViewById(R.id.talkSpeaker);
            this.talkDate = (TextView) itemView.findViewById(R.id.talkDate);
            this.talkText = (TextView) itemView.findViewById(R.id.talkText);
            this.talkRateButton = (Button) itemView.findViewById(R.id.talkButton);

        }
    }

    private class TalkButtonOnClickListener implements View.OnClickListener {

        private final Talk talk;

        public TalkButtonOnClickListener(Talk talk) {
            this.talk = talk;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), RatingActivity.class);

            intent.putExtra(EXTRA_TALK_DATA, talk);

            view.getContext().startActivity(intent);
        }
    }
}
