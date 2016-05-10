package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class Visit {
    private String usuario;
    private String fecha;
    private String ubicacion;
    private String id_tienda;
    private String estatus;
    private String etapa;

    public Visit(){
    }

    public Visit(String usuario, String fecha, String ubicacion, String id_tienda, String estatus, String etapa){
        this.usuario = usuario;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.id_tienda = id_tienda;
        this.estatus = estatus;
        this.etapa = etapa;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(String id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
}
