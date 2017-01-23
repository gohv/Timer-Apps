package xyz.georgyhristov.residentevilcountdowntimer;


import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    private TextView countDownTextView;
    private Handler handler;
    private Runnable runnable;
    private StringBuilder stringBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTextView = (TextView) findViewById(R.id.countDownTextView);
        countDownStart();

 }

    public void countDownStart() {
        stringBuilder = new StringBuilder();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");

                    Date futureDate = dateFormat.parse("2017-01-24");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;

                        countDownTextView.setText(String.format("%2d", days)+" Days" + "\n" +
                                        String.format("%02d", hours) + " Hours" + "\n" +
                                        String.format("%02d", minutes) + " Minutes" + "\n" +
                                        String.format("%02d", seconds)
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }
}
