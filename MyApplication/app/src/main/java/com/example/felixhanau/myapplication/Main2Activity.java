package com.example.felixhanau.myapplication;

import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.view.View.OnKeyListener;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        WebView wv = (WebView)findViewById(R.id.wView);
        wv.setWebViewClient(new android.webkit.WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("gmaps:")){
                    int num = Integer.parseInt(url.substring(6));
                    String[] address = {"Wallstraße+11", "Kaiser-Friedrich-Promenade+11", "Kaiser-Friedrich-Promenade+14", "Obergasse+15", "Kaiser-Friedrich-Promenade+74",
                            "Kaiser-Friedrich-Promenade+76", "Georg-Speyer-Straße+4", "Kisseleffstraße+14",
                            "Louisenstraße+97", "Schöne Aussicht+24", "Schwedenpfad+22"};
                    Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=" + address[num] + ",+Bad+Homburg"); // Too aggressive
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    return true;
                }
                if(url.startsWith("mailto:")){
                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                    startActivity(i);
                    return true;
                }
                if(Uri.parse(url).getHost().length() == 0) {
                    return false;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            }
        });
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.setOverScrollMode(1);//OVER_SCROLL_IF_CONTENT_SCROLLS

        wv.setOnKeyListener(new OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, android.view.KeyEvent event)
            {
                if(event.getAction() == android.view.KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    if(keyCode == android.view.KeyEvent.KEYCODE_BACK)
                    {
                        if(webView.canGoBack())
                        {
                            webView.goBack();
                            return true;
                        }
                    }
                }
                return false;
            }
        });
        wv.loadUrl("file:///android_asset/menu.html");
    }
}
