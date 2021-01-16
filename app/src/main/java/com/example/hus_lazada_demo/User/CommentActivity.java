package com.example.hus_lazada_demo.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.hus_lazada_demo.Model.Comment;
import com.example.hus_lazada_demo.R;
import com.example.hus_lazada_demo.ViewHolder.CommentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;


public class CommentActivity extends AppCompatActivity {

    private DatabaseReference CommentRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        CommentRef = FirebaseDatabase.getInstance().getReference().child("Rating");

        recyclerView = findViewById(R.id.comments_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Comment> options =
                new FirebaseRecyclerOptions.Builder<Comment>()
                .setQuery(CommentRef, Comment.class)
                .build();

        FirebaseRecyclerAdapter<Comment, CommentViewHolder > adapter =
                new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull @NotNull CommentViewHolder commentViewHolder, int i, @NonNull @NotNull Comment comment) {
                        commentViewHolder.userPhone.setText("User: "+comment.getUserPhone());
                        commentViewHolder.comment.setText("Comment: " + comment.getComment());

                    }

                    @NonNull
                    @NotNull
                    @Override
                    public CommentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent, false);

                        CommentViewHolder holder = new CommentViewHolder(view);
                        return holder;
                    };
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}