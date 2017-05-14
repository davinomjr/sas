package com.example.davino.sas.view;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;

import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.davino.sas.R;
import com.example.davino.sas.model.UnidadeSaude;
import com.example.davino.sas.model.Icone;
import com.example.davino.sas.persistence.DadosUS;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.io.Serializable;
import java.util.ArrayList;


public class MapF extends Activity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, LocationListener,
        GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener,DialogInterface.OnClickListener,ClusterManager.OnClusterClickListener<UnidadeSaude>,
        ClusterManager.OnClusterInfoWindowClickListener<UnidadeSaude>,
        ClusterManager.OnClusterItemClickListener<UnidadeSaude>,
        ClusterManager.OnClusterItemInfoWindowClickListener<UnidadeSaude>, Serializable {

    public static long range = 5000;
    public static final int TWO_MINUTES = 1000 * 60 * 2;
    public final String TAG = "MapF";
    private GoogleMap mapG;
    private MapFragment mapa;
    private GoogleApiClient gapi;
    private boolean gps_enabled, network_enabled;
    private LocationRequest locR;
    private ClusterManager<UnidadeSaude> cmH;
    public static long lastTime;
    public int tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_f);
        mapa = (MapFragment) getFragmentManager().findFragmentById(R.id.map); //criando fragmento
        mapa.getMapAsync(this); //criando mapa (funcao retorna nao null)
        lastTime = System.currentTimeMillis() - TWO_MINUTES; // tempo minimo para atualizar markers
        tipo = getIntent().getExtras().getInt("tipo");
    }


    public void onMapReady(GoogleMap map) { //chamado em getMapAsync
        map.setMyLocationEnabled(true); //enable bussola
        buildGoogleApiClient(); //tentando conectar a api client
    }

    private void initLocationRequest() {
        locR = new LocationRequest();
        locR.setInterval(8000); //8 sec
        locR.setFastestInterval(4000); //tempo mais rapido(ao ser iniciado, por exemplo)
        locR.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void onResume() {
        super.onResume();
        if (gapi != null && gapi.isConnected()) {
            startLocationUpdate();
        }
    }

    public void onPause() {
        super.onPause();
        if (gapi != null) {
            stopLocationRequest();
        }
    }

    private void startLocationUpdate() {
        initLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(gapi, locR, MapF.this);
        Location loc = LocationServices.FusedLocationApi.getLastLocation(gapi);
        if (loc != null) {
            Log.i(TAG, "DEU");
            getMyLoc(loc);
        } else {
            Log.i(TAG, "loc ta nulo");
            gapi.connect();
        }

    }

    private void stopLocationRequest() {
        LocationServices.FusedLocationApi.removeLocationUpdates(gapi, MapF.this);
    }


    private synchronized void buildGoogleApiClient() { //instancia uma nova apiclient e tenta conectar -> onConnected
        gapi = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        gapi.connect();
    }

    public void getMyLoc(Location myLoc) { //carrega camera ao iniciar
        LatLng loc = new LatLng(myLoc.getLatitude(), myLoc.getLongitude()); //pega coords
        mapG = mapa.getMap();
        if (mapG != null) {
            CameraUpdate cupdate = CameraUpdateFactory.newLatLngZoom(loc, 15); //update camera
            mapG.animateCamera(cupdate);

        } else {
            mapa.getMapAsync(this);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        setClusters(mapa.getMap());
        String[] nomes;
        ArrayList<Double> lats, longs;
        ArrayList<Integer> ids;
        LatLng loc;
        Location myLoc;
        Float dist;
        int id, tipo;
        if (resultCode == RESULT_OK) {

            ids = (ArrayList<Integer>) data.getSerializableExtra("ids");
            nomes = data.getExtras().getStringArray("nomes");
            lats = (ArrayList<Double>) data.getSerializableExtra("lats");
            longs = (ArrayList<Double>) data.getSerializableExtra("longs");
            tipo = data.getExtras().getInt("tipo");
            if (nomes.length == 0) {
                Log.i("MapF", "Como assim tamanho 0 vei?");
            }
            for (int i = 0; i < nomes.length; i++) {
                loc = new LatLng(lats.get(i), longs.get(i));
                id = ids.get(i);
                myLoc = data.getParcelableExtra("loc");
                if (myLoc != null) {
                    Log.i("MapF", "Myloc nao foi null");
                    Location loc2 = new Location("");
                    loc2.setLatitude(loc.latitude);
                    loc2.setLongitude(loc.longitude);
                    dist = myLoc.distanceTo(loc2);
                    String var = dist.toString();
                    Log.i("MapF DISTANCIA = ", var);
                    if (dist < range) { //adiciona markers apenas num range minimo
                        cmH.addItem(new UnidadeSaude(id, nomes[i], loc, tipo));
                        Log.i("MapF", "BOTOU HOSPITAL PQ EH PERTO");
                    }
                }
            }
        } else {
            Log.i("TAG", "Erro result");
        }
    }

    private synchronized void addItems(Location loc, int tipo) {
        Log.i("MapF", "Indo pra listHospitals");
        Intent i = new Intent(this, DadosUS.class);
        i.putExtra("location", loc);
        i.putExtra("tipo", tipo);
        startActivityForResult(i, 1);
    }

    public void setClusters(GoogleMap mapG) {
        cmH = new ClusterManager<>(this, mapG);
        cmH.setRenderer(new Icone(this, mapG, cmH));
        mapG.setOnCameraChangeListener(cmH);
        mapG.setOnMarkerClickListener(this);
        /*
        cmH.getMarkerCollection().setOnInfoWindowAdapter(new MarkerInfoWindowAdapter());
        cmH.getClusterMarkerCollection().setOnInfoWindowAdapter(new ClusterInfoWindow());
        */
    }

    @Override
    public synchronized void onConnected(Bundle bundle) { //chamado ao conectar
        Location loc = LocationServices.FusedLocationApi.getLastLocation(gapi);
        if (loc != null) {  //conectado e location atual, seta clusters, comeca a pedir att de loc
            startLocationUpdate();
            Log.i(TAG, "FUNFOU ALOU");
            mapG = mapa.getMap();
            setClusters(mapG);

        } else {  //testando recurso de localizacao
            Log.i(TAG, "nao conectou.......");
            LocationManager locationManager;
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            try {
                gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            } catch (Exception ex) {
            }

            try {
                network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            } catch (Exception ex) {
            }

            if (!gps_enabled && !network_enabled) { //pede ao usuario p/ ligar gps e/ou internet
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Ative um dos recursos de localização");
                dialog.setPositiveButton("Ir para opções", this);
                dialog.setNegativeButton("Cancel", this);
                dialog.show();
            } else {
                startLocationUpdate();
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) { //tenta reconectar
        Log.i(TAG, "Conexao SUSPENSA!");
        if (gapi != null) {
            gapi.connect();
        } else {
            buildGoogleApiClient();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) { //possivel que processo tenha sido morto
        Log.i(TAG, "Conexao FALHOU");
        buildGoogleApiClient();
    }


    @Override
    public boolean onMarkerClick(final Marker marker) { //aciona rota
        if (marker.getTitle() != null) { //testa se nao eh um cluster
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(marker.getTitle() + " \nDeseja traçar rota?");
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.parse("http://maps.google.com/maps?&daddr=" + marker.getPosition().latitude) + "," + marker.getPosition().longitude));
                    startActivity(intent);
                }

            });
            dialog.setNegativeButton("Não", null);
            dialog.show();
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) { //chamado a cada X segundos(update de localizacao)
        if (location != null) {
            //   long timeDelta=lastTime;
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mapG = mapa.getMap();
            if (mapG != null) {
                CameraUpdate cupdate = CameraUpdateFactory.newLatLngZoom(loc, 15);
                mapG.animateCamera(cupdate);

            } else {
                mapa.getMapAsync(this);
            }


            long timeDelta = location.getTime() - lastTime;
            Log.i(TAG, "LOC MELHOR MUDANDO");

            if (timeDelta > TWO_MINUTES) { //adicionando/atualizando markers
                Log.i("MapF", "EH PRA ADICIONAR MARKER AQUI");
                lastTime = location.getTime();
                addItems(location, tipo);
            }
        }
    }




    @Override
    public void onClick(DialogInterface dialog, int which) { //opcoes para ligar gps/internet

        if (which == -1) { //positivo
            startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 100);
        }
        else
            finish();
    }



 //   @Override
    public boolean onClusterClick(Cluster<UnidadeSaude> hospitaisCluster) {
        Toast t = Toast.makeText(this,"CLICOU NO CLUSTER?",Toast.LENGTH_SHORT);
        t.show();
        return false;
    }


    @Override
    public void onClusterInfoWindowClick(Cluster<UnidadeSaude> unidadeSaudeCluster) {
        Toast t = Toast.makeText(this,"CLICOU NA INFO?",Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public boolean onClusterItemClick(UnidadeSaude unidadeSaude) {
        Toast t = Toast.makeText(this,"CLICOU NA NO CLUSTERRR?",Toast.LENGTH_SHORT);
        t.show();
        return false;
    }


    @Override
    public void onClusterItemInfoWindowClick(UnidadeSaude unidadeSaude) {
        Toast t = Toast.makeText(this,"CLICOU NA INFO DO CLUSTER??",Toast.LENGTH_SHORT);
        t.show();
    }

    public static void setRange(Long newRange) {
        range = newRange;
        Log.i("MapF","RANGE ALTERADO");
    }
}