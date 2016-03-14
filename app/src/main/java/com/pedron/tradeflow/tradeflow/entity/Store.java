package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class Store {
    private String nombreTienda;
    private String idTienda;
    private String cadena;
    private String formato;
    private String calle;
    private String noExt;
    private String colonia;

    public Store(){
    }

    public Store(String nombreTienda, String idTienda, String cadena, String formato,
                 String calle, String noExt, String colonia){
        this.nombreTienda = nombreTienda;
        this.idTienda = idTienda;
        this.cadena = cadena;
        this.formato = formato;
        this.calle = calle;
        this.noExt = noExt;
        this.colonia = colonia;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
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

    public String getNoExt() {
        return noExt;
    }

    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
}
