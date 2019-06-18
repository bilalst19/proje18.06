package com.example.a90535.letgo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Popular> mPopulars;

    public PopularAdapter(Context context,List<Popular> populars)
    {
        mContext=context;
        mPopulars=populars;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.popular_item,viewGroup,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        Popular popularCur=mPopulars.get(i);
        imageViewHolder.prod_name.setText(popularCur.getProduct_title());
        imageViewHolder.prod_price.setText(popularCur.getProduct_price());

        Picasso.with(mContext)
                .load(popularCur.getProduct_image())
                .placeholder(R.drawable.img_placeholder)
                .fit()
                .centerCrop()
                .into((Target)imageViewHolder.prod_img);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView prod_name,prod_price,prod_img;


        public ImageViewHolder(@NonNull View itemView) {

            super(itemView);
            prod_name=itemView.findViewById(R.id.prodName);
            prod_price=itemView.findViewById(R.id.prodPrice);
            prod_img=itemView.findViewById(R.id.prodImage);
        }
    }
}

