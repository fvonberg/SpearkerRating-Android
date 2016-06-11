package de.w11k.speakerrating_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.common.base.Optional;

import de.w11k.speakerrating_android.data.Rating;
import de.w11k.speakerrating_android.data.Talk;
import de.w11k.speakerrating_android.services.ConferenceServiceFactory;
import de.w11k.speakerrating_android.view.DownloadImageTask;
import de.w11k.speakerrating_android.view.TalkAdapter;

public class RatingActivity extends AppCompatActivity {

    private Typeface typeFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        this.typeFace = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );

        Intent intent = getIntent();
        Talk talk = (Talk) intent.getSerializableExtra(TalkAdapter.EXTRA_TALK_DATA);

        ImageView thumbnailImage = (ImageView) findViewById(R.id.ratingThumbnail);

//        Optional<String> optImageUrl = talk.getImageUrl();
//        if (optImageUrl.isPresent()) {
//            new DownloadImageTask(thumbnailImage).execute(optImageUrl.get());
//        }

        TextView ratingTalkTitle = (TextView) findViewById(R.id.ratingTalkTitle);
        ratingTalkTitle.setText(talk.getTitle());

        Button rateButton = (Button) findViewById(R.id.ratingButton);
        rateButton.setOnClickListener(new RateButtonOnClickListener(talk));

    }

    private class RateButtonOnClickListener implements View.OnClickListener {

        private final Talk talk;

        public RateButtonOnClickListener(Talk talk) {
            this.talk = talk;
        }

        @Override
        public void onClick(final View view) {
            RatingBar ratingCustomerRating = (RatingBar) findViewById(R.id.ratingCustomerRating);
            EditText ratingCustomerFeedback = (EditText) findViewById(R.id.ratingCustomerFeedback);

            Float rating = ratingCustomerRating.getRating();
            Editable ratingCustomerFeedbackText = ratingCustomerFeedback.getText();

            Rating customerRating = new Rating(0, talk.getConferenceId(), talk.getId(), rating.intValue(), ratingCustomerFeedbackText.toString());

            boolean result = ConferenceServiceFactory.getConferenceService().sendRating(customerRating);

            if (result) {
                AlertDialog.Builder confirmDialogBuilder = new AlertDialog.Builder(view.getContext());

                String message = "Vielen Dank für Ihre Bewertung mit " + rating.intValue() + " \uf005";

                confirmDialogBuilder.setMessage(message).setTitle("Bewertung");

                confirmDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(view.getContext(), SelectConferenceActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });

                AlertDialog dialog = confirmDialogBuilder.show();
                TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                textView.setTypeface(typeFace);

            } else {
                AlertDialog.Builder errorDialogBuilder = new AlertDialog.Builder(view.getContext());
                errorDialogBuilder.setMessage("Ups! Something went wrong, please try again.").setTitle("We are sorry!");

                errorDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                errorDialogBuilder.show();
            }
        }
    }
}
