package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class Store {
    private String numTiendas;
    private String nombreTienda;
    private String cadena;
    private String formato;
    private String calle;
    private String idTienda;
    private String colonia;

    public Store(){
    }

    public Store(String numTiendas, String nombreTienda, String cadena, String formato,
                 String calle, String idTienda, String colonia){
        this.numTiendas = numTiendas;
        this.nombreTienda = nombreTienda;
        this.cadena = cadena;
        this.formato = formato;
        this.calle = calle;
        this.idTienda = idTienda;
        this.colonia = colonia;
    }

    public String getNumTiendas() {
        return numTiendas;
    }

    public void setNumTiendas(String numTiendas) {
        this.numTiendas = numTiendas;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
}
