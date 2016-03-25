package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class Alert {
    private String id_tienda;
    private String alerta;
    private String perfil;

    public Alert(){
    }

    public Alert(String id_tienda, String alerta, String perfil){
        this.id_tienda = id_tienda;
        this.alerta = alerta;
        this.perfil = perfil;
    }

    public String getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(String id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
