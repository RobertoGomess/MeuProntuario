package br.com.meuprontuario.meuprontuario.PacoteReceita;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.widget.Toast;

import br.com.meuprontuario.meuprontuario.BaseRequester;
import br.com.meuprontuario.meuprontuario.Const.Constantes;
import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.MetodosHTTP;
import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.Requester;
import br.com.meuprontuario.meuprontuario.PacotePaciente.ConvertJsonPaciente;
import br.com.meuprontuario.meuprontuario.R;

public class ReceitasActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context = this;


    ArrayList<Receita> listReceita = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressDialog = ProgressDialog.show(ReceitasActivity.this, "Aguarde", "Baixando informações...");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    listReceita = lerJson();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            ReceitasFragment fragment = (ReceitasFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
                            if (fragment == null) {
                                fragment = new ReceitasFragment();
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.rl_frag_receita, fragment, "mainFrag");
                                ft.commit();
                            }
                        }
                    });


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public ArrayList<Receita> getListReceita() {
        return listReceita;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case 16908332:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Receita> lerJson() throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Receita> rtnListaReceita = new ArrayList<>();

        //se comunicando com o servidor
        Requester requester = new Requester();
        requester.setUrl(Constantes.URL_LISTA_RECEITAS);

        //
        requester.setMetodosHTTP(MetodosHTTP.POST);

        //

        String jsonString = requester.execute().get();


        if (requester.isSucessoConexao()) {

            //intanciando,convertendo e recebendo o objeto esperado do json
            ConvertJsonReceita convertJsonReceita = new ConvertJsonReceita();
            rtnListaReceita = convertJsonReceita.converteArrayReceita(jsonString);
            return rtnListaReceita;

        } else {

            rtnListaReceita.get(0).setStatus("3");
            rtnListaReceita.get(0).setMessagem("Não foi possível realizar a conexão com a internet, verifique sua conexão.");

            return rtnListaReceita;
        }


    }


}
