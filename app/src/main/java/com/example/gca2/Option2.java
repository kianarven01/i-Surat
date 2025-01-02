package com.example.gca2;


import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Option2 extends Fragment {
    public Option2() {
        super(R.layout.fragment_option2); // The layout for this fragment
    }

    private int score = 0;
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<String> correctAnswers = new ArrayList<>(Arrays.asList("con-fe-ti", "es-kwe-la", "ja-ni-tor", "ma-ney-dyer"));
    Button submit;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        submit = view.findViewById(R.id.submitButton);

        // Setting up drag listeners for each option with their corresponding category
        setUpDragAndDrop(view, R.id.confettiOptionA, R.id.blankConfetti, "CONFETTI");
        setUpDragAndDrop(view, R.id.confettiOptionB, R.id.blankConfetti, "CONFETTI");
        setUpDragAndDrop(view, R.id.confettiOptionC, R.id.blankConfetti, "CONFETTI");
        setUpDragAndDrop(view, R.id.confettiOptionD, R.id.blankConfetti, "CONFETTI");

        setUpDragAndDrop(view, R.id.escuelaOptionA, R.id.blankEscuela, "ESCUELA");
        setUpDragAndDrop(view, R.id.escuelaOptionB, R.id.blankEscuela, "ESCUELA");
        setUpDragAndDrop(view, R.id.escuelaOptionC, R.id.blankEscuela, "ESCUELA");
        setUpDragAndDrop(view, R.id.escuelaOptionD, R.id.blankEscuela, "ESCUELA");

        setUpDragAndDrop(view, R.id.janitorOptionA, R.id.blankJanitor, "JANITOR");
        setUpDragAndDrop(view, R.id.janitorOptionB, R.id.blankJanitor, "JANITOR");
        setUpDragAndDrop(view, R.id.janitorOptionC, R.id.blankJanitor, "JANITOR");
        setUpDragAndDrop(view, R.id.janitorOptionD, R.id.blankJanitor, "JANITOR");

        setUpDragAndDrop(view, R.id.managerOptionA, R.id.blankManager, "MANAGER");
        setUpDragAndDrop(view, R.id.managerOptionB, R.id.blankManager, "MANAGER");
        setUpDragAndDrop(view, R.id.managerOptionC, R.id.blankManager, "MANAGER");
        setUpDragAndDrop(view, R.id.managerOptionD, R.id.blankManager, "MANAGER");

        submit.setOnClickListener(new View.OnClickListener() {
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
    }

    private void updateScore() {
        Bundle bundle = new Bundle();
        bundle.putString("score", String.valueOf(score));
        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(bundle);
        navigateToFragment(fragment);
    }

    private void setUpDragAndDrop(View parentView, int dragViewId, int dropTargetId, final String category) {
        TextView dragView = parentView.findViewById(dragViewId);
        TextView dropTarget = parentView.findViewById(dropTargetId);

        // Set the tag for dragView to identify its category
        dragView.setTag(category);


        // Set touch listener for the draggable view
        dragView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // Start the drag operation when the user taps
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(null, shadowBuilder, v, 0);
                //Toast.makeText(getContext(), "Drag started for: " + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                return true;  // Return true to indicate that the touch event is being consumed
            }
            return false;
        });

        // Set drag event listener for the target
        dropTarget.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true; // Accept drag
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light)); // Highlight drop area
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(getResources().getColor(android.R.color.transparent)); // Remove highlight
                    return true;
                case DragEvent.ACTION_DROP:
                    // Perform the drop
                    TextView draggedView = (TextView) event.getLocalState();
                    String draggedWord = draggedView.getText().toString();
                    String draggedCategory = (String) draggedView.getTag();  // Retrieve the category tag from the dragged view

                    if(category.equals("CONFETTI")){
                        answers.add(0, draggedWord);
                    }else if(category.equals("ESCUELA")){
                        answers.add(1, draggedWord);
                    }else if(category.equals("JANITOR")){
                        answers.add(2, draggedWord);
                    }else if(category.equals("MANAGER")){
                        answers.add(3, draggedWord);
                    }

                    // Only allow drops from the matching category
                    if (!draggedCategory.equals(category)) {
                        Toast.makeText(getContext(), "Invalid category drop!", Toast.LENGTH_SHORT).show();
                        return false; // Do not accept the drag if categories do not match
                    }

                    String existingText = dropTarget.getText().toString();

                    // Ensure we replace only the placeholder "_______________" and avoid any remaining underscores
                    String updatedText = existingText.replace("_______________", draggedView.getText().toString());

                    // Trim any trailing underscores from the resulting text
                    updatedText = updatedText.replaceAll("_+$", "").trim();

                    dropTarget.setText(updatedText); // Update the TextView with the new text
                    dropTarget.setEnabled(false);
                    dropTarget.setBackgroundColor(Color.BLACK);

                    v.setBackgroundColor(getResources().getColor(android.R.color.transparent)); // Remove highlight
                    //Toast.makeText(getContext(), "Word placed: " + draggedView.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(getResources().getColor(android.R.color.transparent)); // Clean up
                    return true;
                default:
                    return false;
            }
        });
    }

    private void navigateToFragment(Fragment fragment) {
        if (getActivity() != null) {
            // Replace the current fragment and add the transaction to the back stack
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
