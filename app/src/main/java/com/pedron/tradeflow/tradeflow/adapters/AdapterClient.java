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
 * Created by leocg on 17/03/2016.
 */
public class AdapterClient extends RecyclerView.Adapter<AdapterClient.ViewHolder> {

    /**
     * Variables
     */
    private Context context;
    private List<String> clientList;
    private OnItemClickListener listener;

    /**
     * Contructor
     * @param context
     * @param clientList
     */
    public AdapterClient(Context context, List<String> clientList) {
        this.context = context;
        this.clientList = clientList;
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
        private AdapterClient adapt = null;

        public ViewHolder(View v, AdapterClient adapt) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image_logo_client);
            this.adapt = adapt;
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            final OnItemClickListener listener = adapt.getOnItemClickListener();
//            String i = v.findViewById(R.id.image_logo_client).getTag().toString();
//            Log.i("Leobas", "id de la imagen: " + i);

            if (listener != null) {
                listener.onItemClick(this, getAdapterPosition());
            }
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clients_logo_card, viewGroup, false);

        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String urlImage = clientList.get(i);

        if(null != urlImage) {
            Glide.with(context)
                    .load(urlImage)
                    //.centerCrop()
                    .override(700,300)
//                .placeholder(R.drawable.loading_spinner) aqui se agrega lo que se va mostrar cuando una de las imagenes no esta disponible
                    .into(viewHolder.image);

        } else {
            Log.e("Error", "No Items on the List");
        }
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return listener;
    }
}
