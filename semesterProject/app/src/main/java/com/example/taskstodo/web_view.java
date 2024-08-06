package com.example.taskstodo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web_view extends AppCompatActivity {

    private WebView mWebView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Find the WebView in the layout
        mWebView = findViewById(R.id.web_view);

        // Retrieve the URL passed from the previous activity
        String url = getIntent().getStringExtra("url");

        // Enable JavaScript (if needed)
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Load the passed URL
        mWebView.loadUrl(url);

        // Set a WebViewClient to handle page navigation events
        mWebView.setWebViewClient(new WebViewClient());
    }
}
