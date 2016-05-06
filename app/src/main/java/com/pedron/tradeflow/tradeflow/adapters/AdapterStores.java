package com.pedron.tradeflow.tradeflow.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.entity.Store;
import com.pedron.tradeflow.tradeflow.util.Constant;

import java.util.List;

/**
 * Created by leocg on 13/03/2016.
 */
public class AdapterStores extends ArrayAdapter<Store> {

    public AdapterStores(Context context, List<Store> storeList) {
        super(context, 0, storeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(
                    R.layout.list_item_stores,
                    parent,
                    false);
        }

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
//        if(store.getUsuario().equals()){
//
//        }
        //Devolver al ListView la fila creada
        return listItemView;
    }

}
