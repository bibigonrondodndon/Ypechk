package com.example.ypechk

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

const val BLACK = 0xff000000.toInt()
const val TIMEOUT_LOOP = 500.toLong()

class MainActivity : AppCompatActivity() {
    private lateinit var lampa1: LinearLayout;
    private lateinit var lampa2: LinearLayout;
    private lateinit var lampa3: LinearLayout;
    private lateinit var knopkaStart: Button;
    private var isClickStart = false;
    private var counter = 0;
    private val a = hashMapOf(
        0 to ::setLampa1Green,
        1 to ::setLampa2Orange,
        2 to ::setLampa3Red
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lampa1 = findViewById(R.id.lampa1);
        lampa2 = findViewById(R.id.lampa2);
        lampa3 = findViewById(R.id.lamapa3);
        knopkaStart = findViewById(R.id.knopkaStart);
    }

     fun setLampa1Green() {
        lampa1.setBackgroundColor(Color.GREEN);
        lampa2.setBackgroundColor(BLACK);
        lampa3.setBackgroundColor(BLACK);
    }

    fun setLampa2Orange() {
        lampa1.setBackgroundColor(BLACK);
        lampa2.setBackgroundColor(Color.YELLOW);
        lampa3.setBackgroundColor(BLACK);
    }

    fun setLampa3Red() {
        lampa1.setBackgroundColor(BLACK);
        lampa2.setBackgroundColor(BLACK);
        lampa3.setBackgroundColor(Color.RED);
    }

    fun onClickStart(view: View) {
        if (!isClickStart) {
            isClickStart = true;
            Thread(Runnable {
                while (isClickStart) {
                    a[counter%a.size]?.let { it() };
                    counter++;
                    Thread.sleep(TIMEOUT_LOOP);
                }
            }).start()
            knopkaStart.setText("Stop");
        } else {
            isClickStart = false;
            knopkaStart.setText("Start");
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isClickStart = true;
    }
}




