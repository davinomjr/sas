package com.example.davino.sas.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.davino.sas.R;
import com.example.davino.sas.model.ArrayAdapterList;
import com.example.davino.sas.model.ObjectList;
import com.example.davino.sas.persistence.DadosUS;
import com.example.davino.sas.persistence.FormarBDUS;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

public class Home extends Activity {
    protected static String[] menuitems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTitle=mDrawerTitle=getTitle();
        inicializararray();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.listviewitems,
                R.id.textViewMain, menuitems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // Set the drawer toggle as the DrawerListener
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
            //    R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.abrir,
                R.string.fechar
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void inicializararray() {
        menuitems = new String[4];
        menuitems[0] = "Home";
        menuitems[1] = "Telefones Úteis";
        menuitems[2] = "Configurações";
        menuitems[3] = "About";
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

      return true;
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);

        }
    }


    private void selectItem(int pos) {
        //imprimir telefone?
        //about?

        if (pos==0) { //home
            //voltando
        } else if (pos==1) { //telefones
            Dialog d = new Dialog(this);
            ObjectList[] obj = new ObjectList[6];
            obj[0]=new ObjectList(1,"Disque Denuncia");
            obj[1]=new ObjectList(2,"Policia Rodoviaria");
            obj[2]=new ObjectList(3,"Policia Federal");
            obj[3]=new ObjectList(4,"Policia Civil");
            obj[4]=new ObjectList(1,"DETRAN");
            obj[5]=new ObjectList(2,"Defesa Civil");
             d.setTitle("TELEFONES");


            d.setContentView(R.layout.activity_list_users);

            mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.listviewitems,
                    R.id.textViewMain, menuitems));

            // Set the list's click listener
            ArrayAdapterList adapter = new ArrayAdapterList(this,R.layout.listviewtel,obj);
            ListView list = (ListView) d.findViewById(R.id.listview);

            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    if (position == 0) {
                        String telefone = "181";
                        Uri uri = Uri.parse("tel:" + telefone);
                        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intencao);
                    } else if (position == 1) {
                        Log.i("HOME", "LIGANDO 191");
                        String telefone = "191";
                        Uri uri = Uri.parse("tel:" + telefone);
                        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intencao);
                    } else if (position == 2) {
                        Log.i("HOME", "LIGANDO 194");
                        String telefone = "194";
                        Uri uri = Uri.parse("tel:" + telefone);
                        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intencao);
                    } else if(position == 3) {
                        Log.i("HOME", "LIGANDO 197");
                        String telefone = "197";
                        Uri uri = Uri.parse("tel:" + telefone);
                        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intencao);
                    }
                    else if(position == 4){
                        String telefone = "08002840194";
                        Uri uri = Uri.parse("tel:" + telefone);
                        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intencao);
                    }
                    else{
                        String telefone = "199";
                        Uri uri = Uri.parse("tel:" + telefone);
                        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                        startActivity(intencao);
                    }
                }
            });
            d.show();
        } else if(pos == 2) {
            Intent i = new Intent(this,Config.class);
            startActivity(i);

        }
        else{
            //TODO IMPLEMENTAR COMO FRAGMENT
            Intent i2 = new Intent(this,About.class);
            startActivity(i2);

        }
        //seta titulo e fecha o menu
        mDrawerList.setItemChecked(pos, true);
        setTitle(menuitems[pos]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void listarlist(View v) {
        Intent i = new Intent(this, DadosUS.class);
        startActivity(i);

    }

    public void addbd(View v) {
        Intent i = new Intent(this, FormarBDUS.class);
        startActivity(i);
    }

    public void deletebd(View v) {
        Intent i = new Intent(this, FormarBDUS.class);
        i.putExtra("cod", 2);
        startActivity(i);
    }

    public void ligarsamu(View v) {
        String telefone = "192";
        Uri uri = Uri.parse("tel:" + telefone);
        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intencao);
    }

    public void ligarbombeiro(View v) {
        String telefone = "193";
        Uri uri = Uri.parse("tel:" + telefone);
        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intencao);
    }

    public void ligarpolicia(View v) {
        String telefone = "190";
        Uri uri = Uri.parse("tel:" + telefone);
        Intent intencao = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intencao);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void buscar(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View vi = inflater.inflate(R.layout.dialoglayout,null);
        EditText et = (EditText)vi.findViewById(R.id.rangetxt);
        et.setHint("Informe o que deseja buscar");
        et.setInputType(InputType.TYPE_CLASS_TEXT);
                dialog.setView(vi);
        dialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText et = (EditText)vi.findViewById(R.id.rangetxt);
                et.setHint("Informe o que deseja buscar");
                String bu = et.getText().toString();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.valueOf(Uri.parse("http://maps.google.com/maps/search/" + bu))));

                startActivity(intent);
                dialog.dismiss();
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        dialog.show();




    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //voltando da lst pegando coords
        ArrayList<Double> lats, longs;
        ArrayList<Integer> ids;
        LatLng loc;
        Log.i("TAG", "VOLTOU");
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Log.i("TAG", "VAI EXIBIR");
                ids = (ArrayList<Integer>) data.getSerializableExtra("ids");
                lats = (ArrayList<Double>) data.getSerializableExtra("lats");
                longs = (ArrayList<Double>) data.getSerializableExtra("longs");

                for (int i = 0; i < ids.size(); i++) {
                    loc = new LatLng(lats.get(i), longs.get(i));
                    Log.i("MapF", "Myloc nao foi null");
                    Location loc2 = new Location("");
                    loc2.setLatitude(loc.latitude);
                    loc2.setLongitude(loc.longitude);
                    String var = String.valueOf(loc.latitude);
                    Log.i("MapF DISTANCIA = ", var);
                }
            }
            if (resultCode == RESULT_CANCELED) {
            }
        }
    }


    public void mapaEMERGENCIA(View v) {
        Intent i = new Intent(this, MapF.class);
        i.putExtra("tipo",1);
        startActivity(i);
    }

    public void mapaHOSPITAIS(View v) {
        Intent i = new Intent(this, MapF.class);
        i.putExtra("tipo",2);
        startActivity(i);
    }

    public void mapaUPA(View v) {
        Intent i = new Intent(this, MapF.class);
        i.putExtra("tipo",3);
        startActivity(i);
    }

    public void mapaOUTROS(View v) {
        Intent i = new Intent(this,MapF.class);
        i.putExtra("tipo",4);
        startActivity(i);
    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}
