package com.example.davino.sas.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

/**
 * Created by Davino on 23/05/2015.
 */
public class UnidadeSaude implements ClusterItem, Serializable {

    private int id;
    private String nome;
    private LatLng loc;
    public int tipo; //1-emergencia 2-hospital 3-UPA 4-Lafepe

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLoc(LatLng loc) {
        this.loc = loc;
    }

    public UnidadeSaude(int id, String nome, LatLng loc, int tipo){
        this.id=id;
        this.nome=nome;
        this.loc=loc;
        this.tipo=tipo;
    }
    public UnidadeSaude(){
    }

    @Override
    public LatLng getPosition() {
       return this.loc;
    }

    public String getNome(){
        return this.nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
