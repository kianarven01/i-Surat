package com.example.gca2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PptViewFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_ppt_view, container, false);
        WebView webView = rootView.findViewById(R.id.webView);
        ImageButton backButton = rootView.findViewById(R.id.from_ppt);
        EditText editText = rootView.findViewById(R.id.editTextTextMultiLine);
        webView.setWebViewClient(new WebViewClient()); // Ensures links open in WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if needed
        Bundle bundle = getArguments();
        String pptTitle =  bundle.getString("title", "No title received");
        String pptLink = bundle.getString("link", "No message received");


        webView.loadUrl(pptLink);

        backButton.setOnClickListener(v -> {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("learnings");

            SharedPreferences credentialPreferences = requireActivity().getSharedPreferences("UserLogs", getContext().MODE_PRIVATE);

            String name = credentialPreferences.getString("name", "null");
            String username =  credentialPreferences.getString("username", "null");
            String date = getCurrentDateTime();
            String learning = editText.getText().toString();

            AnotherClass anotherClass = new AnotherClass(name, pptTitle, learning, date);
            reference.child(username + "_" + date).setValue(anotherClass);
            Toast.makeText(getContext(), "Matagumpay na nairecord ang iyong sagot.", Toast.LENGTH_SHORT).show();
            getParentFragmentManager().popBackStack();
            getParentFragmentManager().popBackStack();
        });


        return rootView;
    }

    public static String getCurrentDateTime() {
        // Define the date format
        SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy:HH:mm:ss", Locale.getDefault());

        // Get the current date and time
        Date now = new Date();

        // Format the current date and time
        return sdf.format(now);
    }
}