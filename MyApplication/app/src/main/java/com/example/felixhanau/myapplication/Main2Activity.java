package com.example.felixhanau.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.view.View.OnKeyListener;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        android.webkit.WebView wv = (android.webkit.WebView)findViewById(R.id.wView);


        wv.setWebViewClient(new android.webkit.WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                setTitle(view.getTitle());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(android.net.Uri.parse(url).getHost().length() == 0) {
                    return false;
                }

                android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }
        });
        wv.getSettings().setBuiltInZoomControls(true);


        wv.setOnKeyListener(new OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, android.view.KeyEvent event)
            {
                if(event.getAction() == android.view.KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case android.view.KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });

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
