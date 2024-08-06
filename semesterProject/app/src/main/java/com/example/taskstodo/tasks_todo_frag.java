package com.example.taskstodo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tasks_todo_frag extends Fragment implements TaskAdapter.OnTaskDeleteListener {

    private FloatingActionButton fab;
    private LinearLayout formLayout;
    private EditText taskNameEditText;
    private Button saveButton;
    private Button logout;

    private DatabaseReference tasksRef;
    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    public tasks_todo_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks_todo, container, false);

        // Initialize Firebase
        tasksRef = FirebaseDatabase.getInstance().getReference("tasks");

        // Initialize views
        fab = view.findViewById(R.id.fab);
        formLayout = view.findViewById(R.id.formLayout);
        taskNameEditText = view.findViewById(R.id.taskNameEditText);
        saveButton = view.findViewById(R.id.saveButton);
        logout = view.findViewById(R.id.logout);

        // Initialize RecyclerView
        tasksRecyclerView = view.findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setHasFixedSize(true);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize task list
        taskList = new ArrayList<>();

        // Initialize adapter with delete listener
        taskAdapter = new TaskAdapter(taskList, this); // Pass 'this' as the delete listener
        tasksRecyclerView.setAdapter(taskAdapter);

        saveButton.setEnabled(false);

        // Set up click listener for FloatingActionButton
        fab.setOnClickListener(view1 -> toggleFormVisibility());

        // Add TextChangedListener to EditText
        taskNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // Enable or disable the Save button based on EditText's text
                saveButton.setEnabled(!taskNameEditText.getText().toString().trim().isEmpty());
            }
        });

        // Set up click listener for Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean completed = false; // Assuming initially task is not completed
                addTaskToFirebase(taskNameEditText.getText().toString().trim(), completed);
                // Hide the form after saving
                hideForm();
            }
        });

        // Retrieve tasks from Firebase
        retrieveTasksFromFirebase();

        // Set up click listener for logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear login state
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                // Sign out from Firebase
                FirebaseAuth.getInstance().signOut();

                // Navigate to LandingScreen
                Intent intent = new Intent(getActivity(), LoginSignup.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    private void toggleFormVisibility() {
        if (formLayout.getVisibility() == View.VISIBLE) {
            hideForm();
        } else {
            showForm();
        }
    }

    private void showForm() {
        formLayout.setVisibility(View.VISIBLE);
        Animation slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        formLayout.startAnimation(slideUp);
    }

    private void hideForm() {
        Animation slideDown = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                formLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        formLayout.startAnimation(slideDown);
    }

    // Add task to Firebase under the current user's ID
    private void addTaskToFirebase(String taskName, boolean completed) {
        // Get the ID of the currently logged-in user
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Generate a unique ID for the task
        String taskId = tasksRef.child(userId).push().getKey();

        // Create a Task object with completion status
        Task task = new Task(taskId, taskName);
        task.setCompleted(completed);

        // Push the task to Firebase under the user's node
        tasksRef.child(userId).child(taskId).setValue(task);
    }

    // Retrieve tasks from Firebase
    private void retrieveTasksFromFirebase() {
        // Get the ID of the currently logged-in user
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Retrieve tasks for the current user from Firebase
        tasksRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskList.clear();
                for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                    Task task = taskSnapshot.getValue(Task.class);
                    taskList.add(task);
                }
                taskAdapter.notifyDataSetChanged(); // Notify adapter of data change
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    // Implement the onTaskDelete method from OnTaskDeleteListener interface
    @Override
    public void onTaskDelete(Task task) {
        // Handle the delete action here
        // For example, you can remove the task from the Firebase database
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        tasksRef.child(userId).child(task.getId()).removeValue();
    }
}
