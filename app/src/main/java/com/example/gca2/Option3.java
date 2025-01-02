package com.example.gca2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class Option3 extends Fragment {
    Button continueBtn;
    private int score = 0;
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<String> correctAnswers = new ArrayList<>(Arrays.asList("bakasyon,", "Baguio,", "saging.", "araw-araw", "\"Patawad, hindi na ito mauulit\"", "ito!", "yun?"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_option3, container, false);
        continueBtn = view.findViewById(R.id.continueButton);

        // Get references to the punctuation and words
        TextView punctuation1 = view.findViewById(R.id.punctuation1);
        TextView punctuation2 = view.findViewById(R.id.punctuation2);
        TextView punctuation3 = view.findViewById(R.id.punctuation3);
        TextView punctuation4 = view.findViewById(R.id.punctuation4);
        TextView punctuation5 = view.findViewById(R.id.punctuation5);
        TextView punctuation6 = view.findViewById(R.id.punctuation6);

        // Question1
        TextView q1word2 = view.findViewById(R.id.q1word2);
        TextView q1word4 = view.findViewById(R.id.q1word4);
        // Question2
        TextView q2word2 = view.findViewById(R.id.q2word2);
        // Question3
        TextView q3word2 = view.findViewById(R.id.q3word2);
        // Question4
        TextView q4word2 = view.findViewById(R.id.q4word2);
        // Question5
        TextView q5word2 = view.findViewById(R.id.q5word2);
        // Question6
        TextView q6word2 = view.findViewById(R.id.q6word2);

        // Set up drag listeners for punctuation
        setDragListener(punctuation1);
        setDragListener(punctuation2);
        setDragListener(punctuation3);
        setDragListener(punctuation4);
        setDragListener(punctuation5);
        setDragListener(punctuation6);

        // Set up drop listeners for words

        // Question1
        q1word2.setOnDragListener(new WordDropListener(q1word2));
        q1word4.setOnDragListener(new WordDropListener(q1word4));
        // Question2
        q2word2.setOnDragListener(new WordDropListener(q2word2));
        // Question3
        q3word2.setOnDragListener(new WordDropListener(q3word2));
        // Question4
        q4word2.setOnDragListener(new WordDropListener(q4word2));
        // Question5
        q5word2.setOnDragListener(new WordDropListener(q5word2));
        // Question6
        q6word2.setOnDragListener(new WordDropListener(q6word2));

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String item : correctAnswers) {
                    if (answers.contains(item)) {
                        score+=10;  // Increment score for each match
                    }
                }

                updateScore();
            }
        });


        return view;
    }

    private void updateScore() {
        Bundle bundle = new Bundle();
        bundle.putString("score", String.valueOf(score));
        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(bundle);
        navigateToFragment(fragment);
    }

    // Helper function to make punctuation marks draggable
    @SuppressLint("ClickableViewAccessibility")
    private void setDragListener(final TextView punctuation) {
        punctuation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Start the drag operation
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(punctuation);
                    punctuation.startDragAndDrop(null, shadowBuilder, punctuation, 0);
                    return true;
                }
                return false;
            }
        });
    }

    // Custom DragListener for words
    private class WordDropListener implements View.OnDragListener {
        TextView wordView;

        WordDropListener(TextView wordView) {
            this.wordView = wordView;
        }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Log to check if drag has started
                    System.out.println("Drag started");
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Log to check if the drag has entered the drop area
                    System.out.println("Drag entered word area");
                    return true;

                case DragEvent.ACTION_DROP:
                    // Get the dragged punctuation mark
                    TextView draggedView = (TextView) event.getLocalState();
                    String punctuation = draggedView.getText().toString();
                    String currentWord = wordView.getText().toString();

                    // Check if the word already has punctuation at the end
                    if (punctuation.equals("\"")) {
                        // Handle quotation marks
                        if (currentWord.startsWith("\"") && currentWord.endsWith("\"")) {
                            // Remove quotation marks if already present
                            currentWord = currentWord.substring(1, currentWord.length() - 1);
                        } else {
                            // Enclose in quotation marks, removing existing punctuation if needed
                            if (currentWord.matches(".*[.,!?-]$")) {
                                currentWord = currentWord.substring(0, currentWord.length() - 1);
                            }
                            currentWord = "\"" + currentWord + "\"";
                        }
                    } else {
                        // Handle other punctuation
                        if (currentWord.startsWith("\"") && currentWord.endsWith("\"")) {
                            // Remove quotation marks if present
                            currentWord = currentWord.substring(1, currentWord.length() - 1);
                        }
                        // Replace existing punctuation (including quotation marks)
                        if (currentWord.matches(".*[.,!?-]$")) {
                            currentWord = currentWord.substring(0, currentWord.length() - 1);
                        }
                        currentWord = currentWord + punctuation;
                    }

                    if(currentWord.contains("bakasyon")){
                        answers.add(0, currentWord);
                    }else if(currentWord.contains("Baguio")){
                        answers.add(1, currentWord);
                    }else if(currentWord.contains("saging")){
                        answers.add(2, currentWord);
                    }else if(currentWord.contains("araw")){
                        answers.add(3, currentWord);
                    }else if(currentWord.contains("Patawad")){
                        answers.add(4, currentWord);
                    }else if(currentWord.contains("ito")){
                        answers.add(5, currentWord);
                    }else if(currentWord.contains("yun")) {
                        answers.add(6, currentWord);
                    }


                    wordView.setText(currentWord);
                    wordView.setEnabled(false);
                    wordView.setTextColor(Color.BLACK);
                    // ... (Toast and log) ...
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                    // Log to check if the drag has ended
                    System.out.println("Drag ended");
                    return true;

                default:
                    return false;
            }
        }
    }

    private void navigateToFragment(Fragment fragment) {
        if (getActivity() != null) {
            // Replace the current fragment and add the transaction to the back stack
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
