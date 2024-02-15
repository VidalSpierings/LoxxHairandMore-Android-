package com.entecmedia.loxxhairandmore;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.MailTo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.google.android.material.snackbar.Snackbar;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    ImageView imageView_home,
    imageView_refresh;
    ProgressBar progressBar;
    String url;
    RelativeLayout webViewActivtyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        imageView_home = (ImageView) findViewById(R.id.imageView_home);

        webViewActivtyLayout = (RelativeLayout) findViewById(R.id.webViewActivtyLayout);

        imageView_refresh = (ImageView) findViewById(R.id.imageView2);

        webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        //**enabled dom storage**
        webSettings.setDomStorageEnabled(true);
        //enabling javascript
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //database enabled
        webSettings.setDatabaseEnabled(true);


        webView.setWebViewClient(new MyWebViewClient());

        url = getString(R.string.webview_url_string);

        webView.loadUrl(getString(R.string.webview_url_string));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        imageView_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WebViewActivity.this, MainActivity2.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Pair<View, String> pair1 = Pair.create((View)imageView_home, imageView_home.getTransitionName());
                    Pair<View, String> pair2 = Pair.create((View)imageView_refresh, imageView_refresh.getTransitionName());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(WebViewActivity.this, pair1, pair2);
                    startActivity(intent, options.toBundle());
                }
                else {
                    finish();
                }
            }
        });

        // set OnClickListener for regresh button.
        // When button is pressed, a loading icon will appear visible until the page is completely reloaded

        imageView_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }

                progressBar.setProgress(progress);
                if(progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });

    }


    private class MyWebViewClient extends WebViewClient {

        // remove progress bar when page is done loading

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (url.startsWith("mailto:" + getString(R.string.e_mail_string))){
                MailTo mt = MailTo.parse(url);
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
                i.putExtra(Intent.EXTRA_CC, mt.getCc());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(i);
                webView.reload();
            }

            if (url.startsWith("tel:0135114279")){
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse(getString(R.string.action_call_phone_number_string)));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(i);
                webView.reload();
            }

            if (url.startsWith("http://maps.google.com/maps?q=Burgemeester+Brokxlaan+1708a%2C+5041+SH%2C+Tilburg")){

                Uri uri = Uri.parse(getString(R.string.google_maps_location_url_string));

                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(i);
                webView.reload();
            }

            if (url.startsWith("http://loxxhairandmore.nl")){

                Uri uri = Uri.parse("http://www.loxxhairandmore.nl");

                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(i);
                webView.reload();
            }

            return false;
        }
    }

    // return to previous page when onBackPressed() is instantiated.
    // if no previous pages exist, use super method

    @Override
    public void onBackPressed() {

        if (webView.canGoBack())
            webView.goBack();
        else {
            super.onBackPressed();
        }
    }

    public void isOnline() {

        // Check if user has an active internet connection.
        // If not, the user will be told they have no internet connection by a snackbar

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo == null){

            Snackbar.make(webViewActivtyLayout, getString(R.string.internet_not_detected), 2000).show();

        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        isOnline();
    }

}

