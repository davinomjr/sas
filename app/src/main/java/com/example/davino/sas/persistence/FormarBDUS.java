package com.example.davino.sas.persistence;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.davino.sas.R;
import com.example.davino.sas.model.Emergencia;
import com.example.davino.sas.model.Hospital;
import com.example.davino.sas.model.UPA;
import com.example.davino.sas.model.UnidadeSaude;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Davino on 24/05/2015.
 */
public class FormarBDUS extends Activity {
        UnidadeSaude hosp = new Hospital();
        UnidadeSaude emerg = new Emergencia();
        UnidadeSaude upa = new UPA();
        int i = 0;

        ArrayList<String> nomes = new ArrayList<String>();
        ArrayList<LatLng> locs = new ArrayList<LatLng>();
        BDUS bd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layoutedit);
            bd = new BDUS(this);

            preencherHosp();
            salvarHosps();
            free(nomes,locs);


            preencherEmerg();
            salvarEmerg();
            free(nomes,locs);

            preencherUPA();
            salvarUPA();
            free(nomes,locs);
/*
            preencherLAFEPE();
            salvarLAFEPE();
            free(nomes,locs);  */
            }


    private void salvarUPA() {
        int b=0;
        while(b<nomes.size()){
            Log.i("TAG", "Teoricamente salvando UPAS");
            upa.setId(i);
            upa.setNome(nomes.get(b));
            LatLng loc = new LatLng(locs.get(b).latitude, locs.get(b).longitude);
            upa.setLoc(loc);
            upa.setTipo(3);
            bd.inserir(upa);
            i++;b++;
        }
    }

    private void preencherUPA() {
        nomes = new ArrayList<String>();
        locs = new ArrayList<LatLng>();

        final LatLng UPAIM = new LatLng(-8.1206391,-34.9122137);
        final LatLng UPAIB = new LatLng(-8.128782,-34.949591);
        final LatLng UPACAX = new LatLng(-8.03046,-34.957577);
        final LatLng UPAOL = new LatLng(-7.979014,-34.860333);
        final LatLng UPAIGA = new LatLng(-7.878354,-34.905888);
        final LatLng UPAPAUL = new LatLng(-7.948021,-34.890106);
        final LatLng UPATO = new LatLng(-8.063133,-34.934127);
        final LatLng UPASL = new LatLng(-7.990893,-35.049976);
        final LatLng UPACU = new LatLng(-8.080411,-34.996586);
        final LatLng UPABJ = new LatLng(-8.218788,-34.934194);
        final LatLng UPAEV = new LatLng(-8.110812,-35.006332);
        final LatLng UPACSA = new LatLng(-8.29664,-35.027446);
        final LatLng UPAND = new LatLng(-8.005826,-34.913607);



        nomes.add(0,"UPA IMBIRIBEIRA");
        nomes.add(1,"UPA IBURA");
        nomes.add(2,"UPA CAXANGÁ");
        nomes.add(3,"UPA OLINDA");
        nomes.add(4,"UPA IGARASSU");
        nomes.add(5,"UPA PAULISTA");
        nomes.add(6,"UPA TORRÕES");
        nomes.add(7,"UPA SSÃO LOURENÇO DA MATA");
        nomes.add(8,"UPA CURADO");
        nomes.add(9," UPA BARRA DE JANGADA");
        nomes.add(10,"UPA ENGENHO VELHO");
        nomes.add(11,"UPA CABO DE SANTO AGOSTINHO");
        nomes.add(12,"UPA NOVA DESCOBERTA");


        locs.add(0,UPAIM);
        locs.add(1,UPAIB);
        locs.add(2,UPACAX);
        locs.add(3,UPAOL);
        locs.add(4,UPAIGA);
        locs.add(5,UPAPAUL);
        locs.add(6,UPATO);
        locs.add(7,UPASL);
        locs.add(8,UPACU);
        locs.add(9,UPABJ);
        locs.add(10,UPAEV);
        locs.add(11,UPACSA);
        locs.add(12,UPAND);

    }




    private void free(ArrayList<String> nomes, ArrayList<LatLng> locs) {
            nomes.clear();
            locs.clear();
    }


    private void exibirArrays() {
        for(int i= 0;i<nomes.size();i++){
            Toast t= Toast.makeText(this,nomes.get(i),Toast.LENGTH_LONG);
            t.show();
        }
    }


        private void preencherEmerg() {
            nomes = new ArrayList<String>();
            locs = new ArrayList<LatLng>();


            final LatLng HSP = new LatLng(-8.0526235,-34.8974798);
            final LatLng HAG = new LatLng(-8.030728,-34.907092);
            final LatLng UNIMEDR = new LatLng(-8.0606539,-34.8946589);
            final LatLng MSJ = new LatLng(-8.0597379,-34.897151);
            final LatLng ESP = new LatLng(-8.0666321,-34.8952547);
            final LatLng HOPE = new LatLng(-8.0667018,-34.8959225);
            final LatLng HAS = new LatLng(-8.0656336,-34.8951031);
            final LatLng IMIP = new LatLng(-8.0671155,-34.8905917);
            final LatLng RHP = new LatLng(-8.0637419,-34.8979924);
            final LatLng HOC = new LatLng(-8.04764,-34.887671);
            final LatLng HJF = new LatLng(-8.0514088,-34.9004678);
            final LatLng GOT = new LatLng(-8.0599921,-34.8947523);
            final LatLng EPHVC = new LatLng(-8.0605524,-34.8902355);
            final LatLng HSM = new LatLng(-8.0623793,-34.8995894);
            final LatLng PROC = new LatLng(-8.049,-34.887444);
            final LatLng HAV = new LatLng(-8.0521796,-34.90692865);
            final LatLng HA = new LatLng(-8.122408,-34.903891);
            final LatLng RHPBV = new LatLng(-8.111484,-34.891916);
            final LatLng HBL = new LatLng(-8.0388463,-34.9389787);
            final LatLng FAV = new LatLng(-8.059068,-34.88978);
            final LatLng HCA = new LatLng(-8.041657,-34.872601);
            final LatLng SSP = new LatLng(-8.046914,-34.895915);
            final LatLng HC = new LatLng(-8.047163,-34.945376);
            final LatLng HR = new LatLng(-8.0537942,-34.8976776);
            final LatLng HGV = new LatLng(-8.05108,-34.921526);
            final LatLng HF = new LatLng(-8.0555338,-34.8888517);
            final LatLng HPM = new LatLng(-8.0558416,-34.9010418);
            final LatLng HIL = new LatLng(-8.0639452,-34.8952084);
            final LatLng HIMC = new LatLng(-8.0357049,-34.9073667);
            final LatLng HO = new LatLng(-8.0629877,-34.8928962);
            final LatLng HBV = new LatLng(-8.1180586,-34.8977203);
            final LatLng UNIMEDPR = new LatLng(-8.1269379,-34.9007244);
            final LatLng UNIMEDIII = new LatLng(-8.064565,-34.89176);

            nomes.add(0,"HOSPITAL STA JOANA");
            nomes.add(1,"HOSPITAL AGAMENON MAGALHAES");
            nomes.add(2,"HOSPITAL UNIMED RECIFE");
            nomes.add(3,"HOSPITAL MEMORIAL SAO JOSE");
            nomes.add(4,"HOSPITAL ESPERANÇA");
            nomes.add(5,"HOPE - HOSPITAL DE OLHOS DE PERNAMBUCO");
            nomes.add(6,"HOSPITAL ALBERT SABIN");
            nomes.add(7,"IMIP");
            nomes.add(8,"REAL HOSPITAL PORTUGUES");
            nomes.add(9,"HOSPITAL OSWALDO CRUZ");
            nomes.add(10,"HOSPITAL JAIME DA FONTE");
            nomes.add(11,"GOT - GRUPO DE ORTOPEDIA E TRAUMATOLOGIA");
            nomes.add(12,"EMERGÊNCIA PEDIÁTRICA HOSPITAL VASCO LUCENA");
            nomes.add(13,"HOSPITAL SAO MARCOS");
            nomes.add(14,"PROCAPE");
            nomes.add(15,"HOSPITAL DE AVILA");
            nomes.add(16,"HOSPITAL ALFA");
            nomes.add(17,"EMERGENCIA PORTUGUES BV");
            nomes.add(18,"HOSPITAL BARAO DE LUCENA");
            nomes.add(19,"FUNDAÇÃO ALTINO VENTURA");
            nomes.add(20,"HOSPITAL DO CANCER");
            nomes.add(21,"SASSEPE HOSPITAL DOS SERVIDORES");
            nomes.add(22,"HOSPITAL DAS CLINICAS");
            nomes.add(23,"HOSPITAL DA RESTAURAÇÃO");
            nomes.add(24,"HOSPITAL GETULIO VARGAS");
            nomes.add(25,"HOSPITAL DE FRATURAS");
            nomes.add(26,"HOSPITAL DA PM");
            nomes.add(27,"HOSPITAL ILHA DO LEITE");
            nomes.add(28,"HOSPITAL INFANTIL MARIA LUCINDA");
            nomes.add(29,"HOSPITAL DE ONCONCLINICA");
            nomes.add(30,"HOSPITAL BOA VIAGEM");
            nomes.add(31,"UNIMED RECIFE PRONTO ATENDIMENTO");
            nomes.add(32,"UNIMED RECIFE III");

            locs.add(0,HSP);
            locs.add(1,HAG);
            locs.add(2,UNIMEDR);
            locs.add(3,MSJ);
            locs.add(4,ESP);
            locs.add(5,HOPE);
            locs.add(6,HAS);
            locs.add(7,IMIP);
            locs.add(8,RHP);
            locs.add(9,HOC);
            locs.add(10,HJF);
            locs.add(11,GOT);
            locs.add(12,EPHVC);
            locs.add(13,HSM);
            locs.add(14,PROC);
            locs.add(15,HAV);
            locs.add(16,HA);
            locs.add(17,RHPBV);
            locs.add(18,HBL);
            locs.add(19,FAV);
            locs.add(20,HCA);
            locs.add(21,SSP);
            locs.add(22,HC);
            locs.add(23,HR);
            locs.add(24,HGV);
            locs.add(25,HF);
            locs.add(26,HPM);
            locs.add(27,HIL);
            locs.add(28,HIMC);
            locs.add(29,HO);
            locs.add(30,HBV);
            locs.add(31,UNIMEDPR);
            locs.add(32,UNIMEDIII);
        }


    private void salvarEmerg() {
       int b=0;
       while(b<nomes.size()){
            emerg.setId(i);
            emerg.setNome(nomes.get(b));
            LatLng loc = new LatLng(locs.get(b).latitude, locs.get(b).longitude);
            emerg.setLoc(loc);
            emerg.setTipo(1);
            bd.inserir(emerg);
            i++;b++;
        }
    }

    private void preencherHosp() {
        nomes = new ArrayList<String>();
        locs = new ArrayList<LatLng>();


        final LatLng HSP = new LatLng(-8.0526235,-34.8974798);
        final LatLng HAG = new LatLng(-8.030728,-34.907092);
        final LatLng UNIMEDR = new LatLng(-8.0606539,-34.8946589);
        final LatLng MSJ = new LatLng(-8.0597379,-34.897151);
        final LatLng ESP = new LatLng(-8.0666321,-34.8952547);
        final LatLng HOPE = new LatLng(-8.0667018,-34.8959225);
        final LatLng HAS = new LatLng(-8.0656336,-34.8951031);
        final LatLng IMIP = new LatLng(-8.0671155,-34.8905917);
        final LatLng RHP = new LatLng(-8.0637419,-34.8979924);
        final LatLng HOC = new LatLng(-8.04764,-34.887671);
        final LatLng HJF = new LatLng(-8.0514088,-34.9004678);
        final LatLng GOT = new LatLng(-8.0599921,-34.8947523);
        final LatLng EPHVC = new LatLng(-8.0605524,-34.8902355);
        final LatLng HSM = new LatLng(-8.0623793,-34.8995894);
        final LatLng PROC = new LatLng(-8.049,-34.887444);
        final LatLng HAV = new LatLng(-8.0521796,-34.90692865);
        final LatLng HA = new LatLng(-8.122408,-34.903891);
        final LatLng RHPBV = new LatLng(-8.111484,-34.891916);
        final LatLng HBL = new LatLng(-8.0388463,-34.9389787);
        final LatLng FAV = new LatLng(-8.059068,-34.88978);
        final LatLng HCA = new LatLng(-8.041657,-34.872601);
        final LatLng SSP = new LatLng(-8.046914,-34.895915);
        final LatLng HC = new LatLng(-8.047163,-34.945376);
        final LatLng HR = new LatLng(-8.0537942,-34.8976776);
        final LatLng HGV = new LatLng(-8.05108,-34.921526);
        final LatLng HF = new LatLng(-8.0555338,-34.8888517);
        final LatLng HPM = new LatLng(-8.0558416,-34.9010418);
        final LatLng HIL = new LatLng(-8.0639452,-34.8952084);
        final LatLng HIMC = new LatLng(-8.0357049,-34.9073667);
        final LatLng HO = new LatLng(-8.0629877,-34.8928962);
        final LatLng HBV = new LatLng(-8.1180586,-34.8977203);
        final LatLng UNIMEDPR = new LatLng(-8.1269379,-34.9007244);
        final LatLng UNIMEDIII = new LatLng(-8.064565,-34.89176);
        final LatLng AACD = new LatLng(-8.0720034,-34.8941191);

        nomes.add(0,"HOSPITAL STA JOANA");
        nomes.add(1,"HOSPITAL AGAMENON MAGALHAES");
        nomes.add(2,"HOSPITAL UNIMED RECIFE");
        nomes.add(3,"HOSPITAL MEMORIAL SAO JOSE");
        nomes.add(4,"HOSPITAL ESPERANÇA");
        nomes.add(5,"HOPE - HOSPITAL DE OLHOS DE PERNAMBUCO");
        nomes.add(6,"HOSPITAL ALBERT SABIN");
        nomes.add(7,"IMIP");
        nomes.add(8,"REAL HOSPITAL PORTUGUES");
        nomes.add(9,"HOSPITAL OSWALDO CRUZ");
        nomes.add(10,"HOSPITAL JAIME DA FONTE");
        nomes.add(11,"GOT - GRUPO DE ORTOPEDIA E TRAUMATOLOGIA");
        nomes.add(12,"EMERGÊNCIA PEDIÁTRICA HOSPITAL VASCO LUCENA");
        nomes.add(13,"HOSPITAL SAO MARCOS");
        nomes.add(14,"PROCAPE");
        nomes.add(15,"HOSPITAL DE AVILA");
        nomes.add(16,"HOSPITAL ALFA");
        nomes.add(17,"EMERGENCIA PORTUGUES BV");
        nomes.add(18,"HOSPITAL BARAO DE LUCENA");
        nomes.add(19,"FUNDAÇÃO ALTINO VENTURA");
        nomes.add(20,"HOSPITAL DO CANCER");
        nomes.add(21,"SASSEPE HOSPITAL DOS SERVIDORES");
        nomes.add(22,"HOSPITAL DAS CLINICAS");
        nomes.add(23,"HOSPITAL DA RESTAURAÇÃO");
        nomes.add(24,"HOSPITAL GETULIO VARGAS");
        nomes.add(25,"HOSPITAL DE FRATURAS");
        nomes.add(26,"HOSPITAL DA PM");
        nomes.add(27,"HOSPITAL ILHA DO LEITE");
        nomes.add(28,"HOSPITAL INFANTIL MARIA LUCINDA");
        nomes.add(29,"HOSPITAL DE ONCONCLINICA");
        nomes.add(30,"HOSPITAL BOA VIAGEM");
        nomes.add(31,"UNIMED RECIFE PRONTO ATENDIMENTO");
        nomes.add(32,"UNIMED RECIFE III");
        nomes.add(33,"AACD");

        locs.add(0,HSP);
        locs.add(1,HAG);
        locs.add(2,UNIMEDR);
        locs.add(3,MSJ);
        locs.add(4,ESP);
        locs.add(5,HOPE);
        locs.add(6,HAS);
        locs.add(7,IMIP);
        locs.add(8,RHP);
        locs.add(9,HOC);
        locs.add(10,HJF);
        locs.add(11,GOT);
        locs.add(12,EPHVC);
        locs.add(13,HSM);
        locs.add(14,PROC);
        locs.add(15,HAV);
        locs.add(16,HA);
        locs.add(17,RHPBV);
        locs.add(18,HBL);
        locs.add(19,FAV);
        locs.add(20,HCA);
        locs.add(21,SSP);
        locs.add(22,HC);
        locs.add(23,HR);
        locs.add(24,HGV);
        locs.add(25,HF);
        locs.add(26,HPM);
        locs.add(27,HIL);
        locs.add(28,HIMC);
        locs.add(29,HO);
        locs.add(30,HBV);
        locs.add(31,UNIMEDPR);
        locs.add(32,UNIMEDIII);
        locs.add(33,AACD);

    }
    public void salvarHosps(){
        int b=0;
        while(b<nomes.size()){
            hosp.setId(i);
            hosp.setNome(nomes.get(b));
            LatLng loc = new LatLng(locs.get(b).latitude, locs.get(b).longitude);
            hosp.setLoc(loc);
            hosp.setTipo(2);
            bd.inserir(hosp);
            i++;b++;
        }   }

    public void editarHospital(View v){
        LatLng newLoc=new LatLng(0,0);
        hosp.setNome("");
        hosp.setLoc(newLoc);
        bd.update(hosp);

    }
}
