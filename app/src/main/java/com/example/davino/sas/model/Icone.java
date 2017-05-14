package com.example.davino.sas.model;

import android.content.Context;

import com.example.davino.sas.R;
import com.example.davino.sas.model.Hospital;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by Davino on 24/05/2015.
 */
public class Icone extends DefaultClusterRenderer<UnidadeSaude> {

    public Icone(Context context, GoogleMap map, ClusterManager<UnidadeSaude> clusterManager) {
        super(context, map, clusterManager);
    }

    //@Override
    protected void onBeforeClusterItemRendered(UnidadeSaude item, MarkerOptions markerOptions) {
        markerOptions.title(item.getNome());
        if(item.getTipo()==1){
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.emergencia));
        }
        else if(item.getTipo()==2) {
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital));
        }
        else{
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.upa));
        }
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

}
