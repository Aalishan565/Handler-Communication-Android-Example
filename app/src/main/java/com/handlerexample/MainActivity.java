package com.handlerexample;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private Button btnStop;
    private TextView tvResult;
    Handler handler = new Handler();
    Thread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        tvResult = findViewById(R.id.tvResult);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                t = new Thread(new Task());
                t.start();
                break;
            case R.id.btnStop:
                t.interrupt();
                break;
        }
    }

    class Task implements Runnable {
        int i;

        @Override
        public void run() {
            for (i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

               /* tvResult.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("" + i);
                    }
                });*/

               /* runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("" + i);
                    }
                });*/
               /* handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("" + i);
                    }
                });*/
                Handler handler = new Handler(getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvResult.setText("" + i);
                    }
                });

            }
        }
    }
}
