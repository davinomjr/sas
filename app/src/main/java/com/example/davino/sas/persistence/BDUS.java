package com.example.davino.sas.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.davino.sas.model.UnidadeSaude;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class BDUS {
    private SQLiteDatabase bd;

    public BDUS(Context c){
        BDUSB b=new BDUSB(c);
        bd=b.getWritableDatabase();
    }

    public void inserir(UnidadeSaude us){
        ContentValues values = new ContentValues();
        values.put("id",us.getId());
        values.put("nome",us.getNome());
        values.put("lat",us.getPosition().latitude);
        values.put("long",us.getPosition().longitude);
        values.put("tipo",us.getTipo());
        bd.insert("us", null, values);
    }

    public void update(UnidadeSaude us){
        ContentValues values = new ContentValues();
        values.put("id",us.getId());
        values.put("nome",us.getNome());
        values.put("lat",us.getPosition().latitude);
        values.put("long",us.getPosition().longitude);
        values.put("tipo",us.getTipo());
        bd.update("us",values,"id = ?", new String[]{""+us.getId()});
    }

    public void delete(UnidadeSaude us){
        bd.delete("us","id = "+us.getId(),null);
    }

    public ArrayList<UnidadeSaude> buscar(int tipo) {
        ArrayList<UnidadeSaude> uss = new ArrayList<>();
        String[] col = new String[]{"id", "nome","lat","long","tipo"};
        Cursor cursor = bd.query("us", col, null, null, null, null, "nome ASC");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                if(cursor.getInt(4) == tipo) {
                    UnidadeSaude h = new UnidadeSaude();
                    h.setId(cursor.getInt(0));
                    h.setNome(cursor.getString(1));
                    Double lat = cursor.getDouble(2);
                    Double longi = cursor.getDouble(3);
                    LatLng loc = new LatLng(lat, longi);
                    h.setLoc(loc);
                    uss.add(h);
                }
            } while (cursor.moveToNext());
        }
        return uss;
    }


    public void limpar() {
        bd.execSQL("drop table us");
    }

    public void close() {
        bd.close();
    }
}
