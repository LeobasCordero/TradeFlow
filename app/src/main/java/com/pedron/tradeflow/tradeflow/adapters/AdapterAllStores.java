package com.pedron.tradeflow.tradeflow.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.entity.Store;

import java.util.List;

/**
 * Created by leocg on 13/03/2016.
 */
public class AdapterAllStores extends ArrayAdapter<Store> {

    public AdapterAllStores(Context context, List<Store> storeList) {
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
                    R.layout.list_item_all_stores,
                    parent,
                    false);
        }

        //Obteniendo instancias de los text views
        TextView nombreTienda = (TextView) listItemView.findViewById(R.id.nombre_tienda_s);
//        TextView cadena = (TextView) listItemView.findViewById(R.id.cadena_tienda_s);
//        TextView formato = (TextView) listItemView.findViewById(R.id.formato_tienda_s);
//        TextView colonia = (TextView) listItemView.findViewById(R.id.direccion_tienda_s);

        //Obteniendo instancia de la Tarea en la posición actual
        Store store = (Store) getItem(position);

        nombreTienda.setText(store.getNombreTienda());
//        cadena.setText(store.getCadena());
//        formato.setText(store.getFormato());
//        colonia.setText(store.getDireccion());

        //Devolver al ListView la fila creada
        return listItemView;
    }

}
