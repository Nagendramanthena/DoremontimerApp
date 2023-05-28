package eu.tutorials.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    CountDownTimer cdt;
    boolean isRunning = false;
    public void Gobutton(View view){
        SeekBar sb = (SeekBar) findViewById(R.id.seekBar);

        if(isRunning){
            isRunning = false;
            sb.setProgress(30);
            sb.setEnabled(true);
            cdt.cancel();
            TextView tv = findViewById(R.id.textView7);
            tv.setText("00:30");
            Button bb = findViewById(R.id.button);
            bb.setText("GO");

        }
        else {
            isRunning = true;
            sb.setEnabled(false);
            Button bb = findViewById(R.id.button);
            bb.setText("STOP");
            cdt = new CountDownTimer(sb.getProgress() * 1000, 1000) {
                public void onTick(long secleft) {
                    update(secleft / 1000);

                }

                public void onFinish() {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.ni);
                    mp.start();
                    isRunning = false;
                    sb.setProgress(30);
                    sb.setEnabled(true);
                    cdt.cancel();
                    TextView tv = findViewById(R.id.textView7);
                    tv.setText("00:30");
                    Button bb = findViewById(R.id.button);
                    bb.setText("GO");

                }

            }.start();
        }
    }
    public void update(long secleft){
        long minleft = secleft/60;
        long sec = secleft-minleft*60;

        String part1 = Long.toString(minleft);
        String part2  =Long.toString(sec);
        if(minleft<10)part1 = "0"+minleft;
        if(sec<10)part2 = "0"+sec;

        TextView tv = findViewById(R.id.textView7);
        tv.setText(part1+":"+part2);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar sb  = (SeekBar) findViewById(R.id.seekBar);
        TextView tv = (TextView) findViewById(R.id.textView7
        );

        sb.setMax(600);
        sb.setProgress(30);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int minutes = i/60;
                int seconds = i-(minutes*60);
                String secstring = Integer.toString(seconds);
                String minstring = Integer.toString(minutes);
                if(minutes<10)minstring = "0"+minstring;
                if(seconds < 10)secstring = "0"+secstring;
                tv.setText(minstring+":"+secstring);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

