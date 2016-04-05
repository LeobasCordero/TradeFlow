package com.pedron.tradeflow.tradeflow.util;

import java.util.HashMap;

/**
 * Created by leocg on 31/03/2016.
 */
public class HelperUtilities {

    HashMap<Integer, String> dias = new HashMap<Integer, String>();
    HashMap<Integer, String> meses = new HashMap<Integer, String>();

    public HelperUtilities(){
        dias.put(1, "Domingo");
        dias.put(2, "Lunes");
        dias.put(3, "Martes");
        dias.put(4, "Miercoles");
        dias.put(5, "Jueves");
        dias.put(6, "Viernes");
        dias.put(7, "Sabado");

        meses.put(0, "Enero");
        meses.put(1, "Febrero");
        meses.put(2, "Marzo");
        meses.put(3, "Abril");
        meses.put(4, "Mayo");
        meses.put(5, "Junio");
        meses.put(6, "Julio");
        meses.put(7, "Agosto");
        meses.put(8, "Septiembre");
        meses.put(9, "Octubre");
        meses.put(10, "Noviembre");
        meses.put(11, "Diciembre");
    }

    public String getDia(int day){
        return dias.get(day);
    }

    public String getMes(int month){
        return meses.get(month);
    }
}
