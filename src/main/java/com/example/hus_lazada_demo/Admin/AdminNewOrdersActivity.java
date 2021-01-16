package com.example.hus_lazada_demo.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hus_lazada_demo.Model.AdminOrders;
import com.example.hus_lazada_demo.Model.History;
import com.example.hus_lazada_demo.Prevalent.Prevalent;
import com.example.hus_lazada_demo.R;
import com.example.hus_lazada_demo.User.HistoryActivity;
import com.example.hus_lazada_demo.User.MainActivity;
import com.example.hus_lazada_demo.User.SettinsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.hus_lazada_demo.Prevalent.Prevalent;
import com.example.hus_lazada_demo.Model.History;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class AdminNewOrdersActivity extends AppCompatActivity {

    private RecyclerView ordersList;
    private DatabaseReference ordersRef;
    private History history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_orders);


        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList = findViewById(R.id.orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options = new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(ordersRef, AdminOrders.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull @NotNull AdminNewOrdersActivity.AdminOrdersViewHolder adminOrdersViewHolder, final int position, @NonNull @NotNull AdminOrders adminOrders) {
                        adminOrdersViewHolder.userName.setText("Name: " + adminOrders.getName());
                        adminOrdersViewHolder.userPhoneNumber.setText("Phone: " + adminOrders.getPhone());
                        adminOrdersViewHolder.userTotalPrice.setText("Total Amount = $ " + adminOrders.getTotalAmount());
                        adminOrdersViewHolder.userDateTime.setText("Order at: " + adminOrders.getDate() + " " + adminOrders.getTime());
                        adminOrdersViewHolder.userShippingAddress.setText("Shipping Address: " + adminOrders.getAddress() + ", " + adminOrders.getCity());
                        adminOrdersViewHolder.state.setText("State : " + adminOrders.getState());

                        adminOrdersViewHolder.showOrdersBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String uID = getRef(position).getKey();

                                Intent intent = new Intent(AdminNewOrdersActivity.this, AdminUserProductsActivity.class);
                                intent.putExtra("uid", uID);
                                startActivity(intent);
                            }
                        });
                        adminOrdersViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence options[] = new CharSequence[] {
                                        "YES",
                                        "NO"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminNewOrdersActivity.this);
                                builder.setTitle("Have you shipped this order products ?");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(i == 0) {
                                            String uID = getRef(position).getKey();
                                            adminOrdersViewHolder.state.setText("ALREADY SHIPPED");
                                            adminOrders.setState("ALREADY SHIPPED");


                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("History");

                                            HashMap<String, Object> orderMap = new HashMap<>();
                                            orderMap. put("name", adminOrders.getName());
                                            orderMap. put("address", adminOrders.getAddress());
                                            orderMap. put("phoneOrder", adminOrders.getPhone());
                                            orderMap.put("state", "shipped");
                                            orderMap.put("time", adminOrders.getTime());
                                            orderMap.put("date", adminOrders.getDate());
                                            orderMap.put("city", adminOrders.getCity());
                                            orderMap.put("totalAmount", adminOrders.getTotalAmount());


                                            ref.child(adminOrders.getPhone()).updateChildren(orderMap);

                                            adminOrdersViewHolder.state.setText("ALREADY SHIPPED");

                                            //adminOrdersViewHolder.state.setText("State : " + History.getState());








                                            RemoveOrder(uID);
                                        }
                                        else {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @NotNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                        return new AdminOrdersViewHolder(view);
                    }
                };

        ordersList.setAdapter(adapter);
        adapter.startListening();

    }



    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userPhoneNumber, userTotalPrice, userDateTime, userShippingAddress,state;
        public Button showOrdersBtn;


        public AdminOrdersViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.order_user_name);
            userPhoneNumber = itemView.findViewById(R.id.order_phone_number);
            userDateTime = itemView.findViewById(R.id.order_date_time);
            userTotalPrice = itemView.findViewById(R.id.order_total_price);
            userShippingAddress = itemView.findViewById(R.id.order_address_city);
            showOrdersBtn = itemView.findViewById(R.id.show_all_products);
            state = itemView.findViewById(R.id.order_status);
        }
    }

    private void RemoveOrder(String uID) {
        ordersRef.child(uID).removeValue();
    }
}