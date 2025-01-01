package com.example.gca2;

import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;

public class VideoViewFragment extends Fragment {
    private ExoPlayer player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video_view, container, false);
        PlayerView videoView = rootView.findViewById(R.id.playerView);
        Bundle bundle = getArguments();

        player = new ExoPlayer.Builder(getContext()).build();
        videoView.setPlayer(player);

        // Set the path to the video (from raw folder or URL)
        String videoPath = bundle.getString("link", "No message received");; // From raw folder
        // String videoPath = "https://www.example.com/sample_video.mp4"; // External URL

        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoPath));
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();


        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }

}