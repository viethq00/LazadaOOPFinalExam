package com.example.hus_lazada_demo.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hus_lazada_demo.Model.Cart;
import com.example.hus_lazada_demo.Prevalent.Prevalent;
import com.example.hus_lazada_demo.R;
import com.example.hus_lazada_demo.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class AdminUserProductsActivity extends AppCompatActivity {

    private RecyclerView productsList;
    private DatabaseReference cartListRef;
    private String userID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_products);

        userID = getIntent().getStringExtra("uid");

        productsList = findViewById(R.id.products_list);
        productsList.setLayoutManager(new LinearLayoutManager(this));


        cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List")
                .child("Admin View").child(userID).child("Products");
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef, Cart.class)
                .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, final int position, @NonNull @NotNull Cart cart) {
                holder.txtProductName.setText(cart.getPname());
                holder.txtProductPrice.setText("Price " + cart.getPrice() +"$");
                holder.txtProductQuantity.setText("Quantity = " +cart.getQuantity());
            }

            @NonNull
            @NotNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        productsList.setAdapter(adapter);
        adapter.startListening();
    }
}