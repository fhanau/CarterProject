package com.example.felixhanau.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        android.webkit.WebView wv = (android.webkit.WebView)findViewById(R.id.wView);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("Sitename");
            String value2 = extras.getString("Filename");
            setTitle(value);
            wv.loadUrl(value2);
        }
        else {
            //wv.loadUrl("file:///android_asset/myweb.html");
            //wv.loadUrl("file:///android_asset/index1.html");
            wv.loadUrl("file:///android_asset/impressum.html");
            //wv.loadUrl("http://x.org/");
        }
    }

}
