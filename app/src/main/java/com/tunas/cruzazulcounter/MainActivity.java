package com.tunas.cruzazulcounter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static TextView countdownTimerText;
    private static Button startTimer, resetTimer;
    private static CountDownTimer countDownTimer;
    int puntuacion = 0;
    int puntuacionb = 0;
    int fallas = 0;
    int fallasb = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdownTimerText = (TextView) findViewById(R.id.countdownText);
        startTimer = (Button) findViewById(R.id.startTimer);
        resetTimer = (Button) findViewById(R.id.resetTimer);

        setListeners();

    }


    //Set Listeners over button
    private void setListeners() {
        startTimer.setOnClickListener(this);
        resetTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startTimer:

                int noOfMinutes = 90 * 60 * 1000;//Convert minutes into milliseconds

                startTimer(noOfMinutes);//start countdown
                startTimer.setText(getString(R.string.stop_timer));//Change Text

                break;
            case R.id.resetTimer:
                stopCountdown();//stop count down
                startTimer.setText(getString(R.string.start_timer));//Change text to Start Timer
                countdownTimerText.setText(getString(R.string.timer));//Change Timer text
                puntuacionb = 0;
                puntuacion = 0;
                fallas = 0;
                fallasb = 0;
                ver(puntuacionb);
                display(puntuacion);
                verX(fallas);
                displayX(fallasb);
                break;
        }


    }

    //Stop Countdown method
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    //Start Countodwn method
    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                countdownTimerText.setText(hms);//set text
            }

            public void onFinish() {

                countdownTimerText.setText("TIME'S UP!!"); //On finish change timer text
                countDownTimer = null;//set CountDownTimer to null
                startTimer.setText(getString(R.string.start_timer));//Change button text
            }
        }.start();

    }

    public void goalA(View view) {
        puntuacion = puntuacion + 1;
        display(puntuacion);

    }

    public void yellowA(View view) {
        fallas = fallas + 1;
        displayX(fallas);

    }

    public void redA(View view) {
        fallas = fallas + 1;
        displayX(fallas);

    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.team_a_goals);
        quantityTextView.setText("" + number);
    }

    private void displayX(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.team_a_failures);
        quantityTextView.setText("" + number);
    }

    public void goalB(View view) {
        puntuacionb = puntuacionb + 1;
        ver(puntuacionb);

    }

    public void yellowB(View view) {
        fallasb = fallasb + 1;
        verX(fallasb);

    }

    public void redB(View view) {
        fallasb = fallasb + 1;
        verX(fallasb);

    }

    private void ver(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.team_b_goals);
        quantityTextView.setText("" + number);
    }

    private void verX(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.team_b_failures);
        quantityTextView.setText("" + number);
    }


}