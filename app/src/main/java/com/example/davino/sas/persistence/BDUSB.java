package com.example.davino.sas.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Davino on 24/05/2015.
 */
public class BDUSB extends SQLiteOpenHelper{

    private static final String NOME = "BD_H";
    private static final int VERSAO = 12;

    public BDUSB(Context c){
          super(c,NOME,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        Log.i("BDHN", "CRIOU A DB");
        bd.execSQL("create table us(id integer primary key, nome text not null, lat double precision not null, long double precision not null, " +
                "tipo int not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table us");
        onCreate(bd);
    }
}
