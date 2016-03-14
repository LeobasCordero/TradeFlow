package com.pedron.tradeflow.tradeflow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pedron.tradeflow.tradeflow.R;

import java.util.List;


/**
 * Created by Exchange on 10/5/2015.
 */
public class AdapterTrademark extends RecyclerView.Adapter<AdapterTrademark.ViewHolder> {
    private Context context;
    private List<String> trademarkList;

    public AdapterTrademark(Context context, List<String> trademarkList) {
        this.context = context;
        this.trademarkList = trademarkList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);

        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trademark_logo_card, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String urlImage = trademarkList.get(i);

        if(null != urlImage) {
            Glide.with(context)
                .load(urlImage)
                .centerCrop()
                        .override(700,300)
//                .placeholder(R.drawable.loading_spinner) aqui se agrega lo que se va mostrar cuando una de las imagenes no esta disponible
                .into(viewHolder.image);

        } else {
            Log.e("Error", "No Items on the List");
        }
    }

    @Override
    public int getItemCount() {
        return trademarkList.size();
    }

}
