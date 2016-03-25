package com.pedron.tradeflow.tradeflow.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.activities.NewsActivity;
import com.pedron.tradeflow.tradeflow.activities.TrademarkActivity;

import java.util.List;


/**
 * Created by Exchange on 10/5/2015.
 */
public class AdapterTrademark extends RecyclerView.Adapter<AdapterTrademark.ViewHolder> {
    /**
     * Variables
     */
    private Context context;
    private List<String> trademarkList;
    private OnItemClickListener listener;

    /**
     * Contructor
     * @param context
     * @param trademarkList
     */
    public AdapterTrademark(Context context, List<String> trademarkList) {
        this.context = context;
        this.trademarkList = trademarkList;
    }

    /**
     * Interfaces
     */
    public interface OnItemClickListener {
        void onItemClick(ViewHolder item, int position);
    }

    /**
     * Metodos
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        ImageView image;
        private AdapterTrademark adapt = null;

        public ViewHolder(View v, AdapterTrademark adapt) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);
            this.adapt = adapt;
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            final OnItemClickListener listener = adapt.getOnItemClickListener();

            if (listener != null) {
                Log.i("Leobas", "entro1");
                listener.onItemClick(this, getAdapterPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trademark_logo_card, viewGroup, false);

        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String urlImage = trademarkList.get(i);

        if(null != urlImage) {
            Glide.with(context)
                .load(urlImage)

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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }
}
