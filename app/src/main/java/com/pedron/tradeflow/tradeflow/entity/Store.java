package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class Store {
    private String numTiendas;
    private String nombreTienda;
    private String cadena;
    private String formato;
//    private String calle;
    private String idTienda;
//    private String colonia;
    private String direccion;
    private String usuario;
    private String cruz;


    public Store(){
    }

    public Store(String numTiendas, String nombreTienda, String cadena, String formato,
                 String idTienda, String direccion, String usuario, String cruz){
        this.numTiendas = numTiendas;
        this.nombreTienda = nombreTienda;
        this.cadena = cadena;
        this.formato = formato;
        this.idTienda = idTienda;
        this.direccion = direccion;
        this.usuario = usuario;
        this.cruz = cruz;
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

    public String getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(String idTienda) {
        this.idTienda = idTienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCruz() {
        return cruz;
    }

    public void setCruz(String cruz) {
        this.cruz = cruz;
    }
}
