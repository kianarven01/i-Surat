package com.example.gca2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class PptViewFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_ppt_view, container, false);
        WebView webView = rootView.findViewById(R.id.webView);
        ImageButton backButton = rootView.findViewById(R.id.from_ppt);
        webView.setWebViewClient(new WebViewClient()); // Ensures links open in WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed
        Bundle bundle = getArguments();
        String pptLink = bundle.getString("link", "No message received");

        webView.loadUrl(pptLink);

        backButton.setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
        });


        return rootView;
    }
}