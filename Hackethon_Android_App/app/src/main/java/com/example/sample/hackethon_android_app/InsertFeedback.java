package com.example.sample.hackethon_android_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bhushan.Jagtap on 7/21/2016.
 */
public class InsertFeedback extends Activity {

    RatingBar ratingBar;
    TextView rateMessage;
    String ratedValue;
    int like_disklike;
    CheckBox check;
    String id, title, survey_txt;
    String vote = "1.0", like = "like", priv = "no", commnt = "No Comments", tag = "NoTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_feedback);

        TextView ttl1 = (TextView) findViewById(R.id.take_a_survey_title);
        TextView txt2 = (TextView) findViewById(R.id.take_a_survey_question);

        id = getIntent().getExtras().getString("id");
        title = getIntent().getExtras().getString("title");
        survey_txt = getIntent().getExtras().getString("text");

        ttl1.setText(title);
        txt2.setText(survey_txt);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        rateMessage = (TextView) findViewById(R.id.rate);
        check = (CheckBox) findViewById(R.id.checkBox);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                vote = String.valueOf(ratingBar.getRating());
            }
        });
    }


    public void onCheckboxClicked(View view) {


        EditText ttl = (EditText) findViewById(R.id.titleTxt);
        EditText txt = (EditText) findViewById(R.id.Survey_txt);

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    Toast.makeText(InsertFeedback.this, "You have made your comments private", Toast.LENGTH_LONG).show();
                    priv = "yes";
                } else {
                    priv = "no";
                }
        }
    }

    public void finishServey(View v) {


        EditText comments, tags;
        comments = (EditText) findViewById(R.id.take_a_survey_comments);
        tags = (EditText) findViewById(R.id.take_a_survey_tags);

        // generate value

        commnt = comments.getText().toString();
        tag = tags.getText().toString();

        //Toast.makeText(InsertFeedback.this, "Finish comments" + comments.getText().toString() + "tags" + tags.getText().toString(), Toast.LENGTH_SHORT).show();

        String method = "insertCommnet";

        // get element


        BackgroudTask bt = new BackgroudTask(this);
        bt.execute(method, vote, like, priv, commnt, tag, id, MainActivity.name);
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean like1 = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.take_a_survey_like:
                if (like1)
                    like = "like";
                Toast.makeText(InsertFeedback.this, "Liked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.take_a_survey_dislike:
                if (like1)
                    like = "dislike";
                Toast.makeText(InsertFeedback.this, "Unliked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

