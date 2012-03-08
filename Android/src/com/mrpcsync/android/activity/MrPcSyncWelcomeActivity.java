package com.mrpcsync.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MrPcSyncWelcomeActivity extends Activity{
    
    private WebView web_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        
        web_url = (WebView) findViewById(R.id.web_url);
    }
    
}