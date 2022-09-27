package com.gabriellewhite.cryptock.ui.news.WebView;

import static com.gabriellewhite.cryptock.ui.news.ForexNewsFragment.forexNewsUrl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gabriellewhite.cryptock.R;

public class ForexNewsWebView extends Fragment {

    WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.forex_news_webview, container, false);
        webView = (WebView) v.findViewById(R.id.ForexNewsWebViewLayout);


        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());


        webView.loadUrl(forexNewsUrl);
        System.out.println("NEW WEBVIEW: " + forexNewsUrl);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());

        return v;
    }

}