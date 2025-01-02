package com.example.gca2;

import android.content.SharedPreferences;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VideoViewFragment extends Fragment {
    private ExoPlayer player;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video_view, container, false);
        PlayerView videoView = rootView.findViewById(R.id.playerView);
        ImageButton from_video = rootView.findViewById(R.id.from_video);
        EditText editText = rootView.findViewById(R.id.editTextTextMultiLine2);
        Bundle bundle = getArguments();

        player = new ExoPlayer.Builder(getContext()).build();
        videoView.setPlayer(player);

        // Set the path to the video (from raw folder or URL)
        String videoTitle = bundle.getString("title", "No title received");
        String videoPath = bundle.getString("link", "No message received"); // From raw folder
        // String videoPath = "https://www.example.com/sample_video.mp4"; // External URL

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoPath));
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        from_video.setOnClickListener(v -> {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("learnings");

            SharedPreferences credentialPreferences = requireActivity().getSharedPreferences("UserLogs", getContext().MODE_PRIVATE);

            String name = credentialPreferences.getString("name", "null");
            String username =  credentialPreferences.getString("username", "null");
            String date = getCurrentDateTime();
            String learning = editText.getText().toString();

            AnotherClass anotherClass = new AnotherClass(name, videoTitle, learning, date);
            reference.child(username + "_" + date).setValue(anotherClass);
            Toast.makeText(getContext(), "Matagumpay na nairecord ang iyong sagot.", Toast.LENGTH_SHORT).show();
            getParentFragmentManager().popBackStack();
            getParentFragmentManager().popBackStack();
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
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