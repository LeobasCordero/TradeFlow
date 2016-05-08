package com.pedron.tradeflow.tradeflow.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.activities.StoresActivity;
import com.pedron.tradeflow.tradeflow.entity.Store;
import com.pedron.tradeflow.tradeflow.util.Constant;

import java.util.List;

/**
 * Created by leocg on 13/03/2016.
 */
public class AdapterStores extends ArrayAdapter<Store> {

    /*ImageView imR;
    ImageView imA;
    ImageView imC;*/

    public int pos;

    public AdapterStores(Context context, List<Store> storeList) {
        super(context, 0, storeList);
        Context con = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

          final View listItemView = inflater.inflate(
                    R.layout.list_item_stores,
                    parent,
                    false);
        pos = position;

        //Obteniendo instancias de los text views
        TextView nombreTienda = (TextView) listItemView.findViewById(R.id.nombre_tienda);
//        TextView idTienda = (TextView) listItemView.findViewById(R.id.id_tienda);
        TextView cadena = (TextView) listItemView.findViewById(R.id.cadena_tienda);
        TextView formato = (TextView) listItemView.findViewById(R.id.formato_tienda);
//        TextView calle = (TextView) listItemView.findViewById(R.id.calle_tienda);
//        TextView noExt = (TextView) listItemView.findViewById(R.id.numero_tienda);
        TextView colonia = (TextView) listItemView.findViewById(R.id.direccion_tienda);

        //Obteniendo instancia de la Tarea en la posición actual
        Store store = (Store) getItem(position);

        nombreTienda.setText(store.getNombreTienda());
//        idTienda.setText(store.getIdTienda());
        cadena.setText(store.getCadena());
        formato.setText(store.getFormato());
//        calle.setText(store.getCalle());
//        noExt.setText(store.getNumTiendas());
        colonia.setText(store.getDireccion());
//        Log.i("Leobas", "position: " + position + " cruz: " + store.getCruz());
        if(store.getCruz().equals(Constant.CRUZ_TRUE)){
            if(convertView != null) {
                Log.i("Leobas", "position: " + position + " nombre: " + store.getNombreTienda());
                if(listItemView.findViewById(R.id.img_cruz).getVisibility() != View.VISIBLE)
                    listItemView.findViewById(R.id.img_cruz).setVisibility(View.VISIBLE);
            }else{
                Log.i("Leobas", "nulo");
            }
        }

        //Handle buttons and add onClickListeners
        ImageView imgCruz = (ImageView)listItemView.findViewById(R.id.img_cruz);
        LinearLayout linearLis = (LinearLayout)listItemView.findViewById(R.id.element_list_store);
        RelativeLayout relativeLayout = (RelativeLayout)listItemView.findViewById(R.id.rel_layout);
        TableLayout tabLay = (TableLayout)listItemView.findViewById(R.id.table_list);

        /*imA = (ImageView)listItemView.findViewById(R.id.sem_ama);
        imR = (ImageView)listItemView.findViewById(R.id.sem_rojo);
        imC = (ImageView)listItemView.findViewById(R.id.img_cruz);*/

        imgCruz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StoresActivity a = (StoresActivity) getContext();
                a.removeStoreFromList(pos);
            }
        });
        linearLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoresActivity a = (StoresActivity) getContext();
                a.nextTask(listItemView, pos);

            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoresActivity a = (StoresActivity) getContext();
                a.nextTask(listItemView, pos);

            }
        });
        tabLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StoresActivity a = (StoresActivity) getContext();
                a.nextTask(listItemView, pos);
            }
        });

        //Devolver al ListView la fila creada
        return listItemView;
    }
}
