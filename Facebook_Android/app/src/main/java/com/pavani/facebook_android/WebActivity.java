package com.pavani.facebook_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.facebook.FacebookSdk;

public class WebActivity extends AppCompatActivity {
    private WebView wv1;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_web);
        url="https://www.facebook.com/profile.php?&sk=wall";
        wv1 = (WebView) findViewById(R.id.page);
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.getSettings().setDomStorageEnabled(true);
        wv1.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        wv1.setWebViewClient(new MyBrowser());
        wv1.loadUrl(url);
        wv1.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
    }

    @Override
    public void onBackPressed() {
         {
             super.onBackPressed();
            wv1.goBack();
        }


    }
}

   class MyBrowser extends WebViewClient {
        private final String log_txt="In WebView Client";
    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i(log_txt,"In shouldOverrideUrlLoading");
        view.loadUrl(url);
        return true;
    }
}
