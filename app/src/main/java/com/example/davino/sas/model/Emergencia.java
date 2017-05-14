package com.example.davino.sas.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

/**
 * Created by Davino on 23/05/2015.
 */
public class Emergencia extends UnidadeSaude implements ClusterItem, Serializable {

    public Emergencia(int id, String nome, LatLng loc,int tipo){
       super(id,nome,loc,tipo);
    }
    public Emergencia(){
    }

}
