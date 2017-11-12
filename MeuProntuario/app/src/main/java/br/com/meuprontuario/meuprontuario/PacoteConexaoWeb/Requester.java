package br.com.meuprontuario.meuprontuario.PacoteConexaoWeb;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.meuprontuario.meuprontuario.Uteis.UteisJava;

/**
 * Created by aluno on 21/06/2017.
 */

public class Requester extends AsyncTask<Void, Void, String> {

    private String url;
    private JSONObject jsonObjectPut;
    private MetodosHTTP metodosHTTP;
    private boolean sucessoConexao;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            String returnStr = "";
            URL url = new URL(getUrl());

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod(String.valueOf(getMetodosHTTP()));
            /**Informa que iremos realizar operacao de leitura nessa determinada conexão*/
            urlConnection.setDoInput(true);
            /**Informa que iremos realizar operacao de escrita nessa determinada conexão, ou seja, iremos enviar alguma parametro no seu corpo*/
            urlConnection.setDoOutput(true);

            /**
             * O nosso servidor espera um json, entao vamos converter a nossa string em um objeto JsonObject
             */
            JSONObject objJson = getJsonObjectPut();

            /**
             * Vamos transformar agora o nosso jsonObjet em bytes para enviar para o servidor
             */
            if (objJson != null) {
                OutputStream out = urlConnection.getOutputStream();
                out.write(objJson.toString().getBytes());
                out.close();
            }


            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_BAD_REQUEST) {
                returnStr = UteisJava.bytesParaString(urlConnection.getInputStream());
                urlConnection.disconnect();
                setSucessoConexao(true);
            } else {
                setSucessoConexao(false);
            }

            return returnStr;


        } catch (Exception e) {

            Log.e("Erro json", e.getMessage());
            JSONObject jsonObjectErro = new JSONObject();

            try {
                jsonObjectErro.put("status", "3");
                jsonObjectErro.put("mensagem", "Falha ao conectar, verifique sua conexão.");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return jsonObjectErro.toString();
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    //metodos set e get

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getJsonObjectPut() {
        return jsonObjectPut;
    }

    public void setJsonObjectPut(JSONObject jsonObjectPut) {
        this.jsonObjectPut = jsonObjectPut;
    }

    public MetodosHTTP getMetodosHTTP() {
        return metodosHTTP;
    }

    public void setMetodosHTTP(MetodosHTTP metodosHTTP) {
        this.metodosHTTP = metodosHTTP;
    }


    private void setSucessoConexao(boolean sucessoConexao) {
        this.sucessoConexao = sucessoConexao;
    }

    public boolean isSucessoConexao() {
        return sucessoConexao;
    }
}
