package com.example.hus_lazada_demo.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hus_lazada_demo.Interface.ItemClickListner;
import com.example.hus_lazada_demo.R;

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView userPhone, comment;
    public ItemClickListner listner;


    public CommentViewHolder(View itemView)
    {
        super(itemView);


        userPhone = (TextView) itemView.findViewById(R.id.username);
        comment = (TextView) itemView.findViewById(R.id.comment);

    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
