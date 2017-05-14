package com.example.davino.sas.persistence;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.example.davino.sas.model.UnidadeSaude;
import com.example.davino.sas.view.MapF;
import com.example.davino.sas.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Davino on 24/05/2015.
 */
public class DadosUS extends Activity implements Serializable{
    public BDUS bd;
    public String[] nomes;
    public ArrayList<Integer> ids;
    public ArrayList<Double> lats ;
    public ArrayList<Double> longs;
    public int tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        //buscando coordenadas no BD
        bd = new BDUS(this);
        tipo = getIntent().getExtras().getInt("tipo");
        ArrayList<UnidadeSaude> list = bd.buscar(tipo);

        ids= new ArrayList<>();
        nomes = new String[list.size()];
        lats = new ArrayList<>();
        longs=new ArrayList<>();

        for(int i=0;i<list.size();i++){
            ids.add(i,list.get(i).getId());
            nomes[i]=list.get(i).getNome();
            lats.add(i,list.get(i).getPosition().latitude);
            longs.add(i, list.get(i).getPosition().longitude);
        }


        //pegando localizacao e devolvendo pro mapa as unidades de saude
        Location loc=getIntent().getParcelableExtra("location");
        Intent i = new Intent(this,MapF.class);
        i.putExtra("ids",ids);
        i.putExtra("nomes",nomes);
        i.putExtra("lats",lats);
        i.putExtra("longs",longs);
        i.putExtra("loc",loc);
        i.putExtra("tipo",tipo);
        setResult(RESULT_OK, i);
        bd.close();
        finish();
    }
}