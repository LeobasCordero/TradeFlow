package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class User {
    private String usuario;
    private String contrasena;
    private String perfil;
    private String estatus;

    public User(){
    }

    public User(String usuario, String contrasena, String perfil, String estatus){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.perfil = perfil;
        this.estatus = estatus;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
