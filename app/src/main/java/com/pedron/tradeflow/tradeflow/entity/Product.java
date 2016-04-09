package com.pedron.tradeflow.tradeflow.entity;

/**
 * Created by leocg on 12/03/2016.
 */
public class Product {
    private String id_producto;
    private String id_marca;
    private String producto;

    public Product(){
    }

    public Product(String id_producto, String id_marca, String producto){
        this.producto = producto;
        this.id_producto = id_producto;
        this.id_marca = id_marca;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getId_marca() {
        return id_marca;
    }

    public void setId_marca(String id_marca) {
        this.id_marca = id_marca;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
