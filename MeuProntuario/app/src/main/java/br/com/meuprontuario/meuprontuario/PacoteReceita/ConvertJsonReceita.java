package br.com.meuprontuario.meuprontuario.PacoteReceita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.Requester;

/**
 * Created by Beto on 23/06/2017.
 */

public class ConvertJsonReceita {

    public ArrayList<Receita> converteArrayReceita(String jsonString) throws JSONException {

        ArrayList<Receita> rtnListaReceita = new ArrayList<>();

        //transformando o json texto em JsonOArray
        JSONArray jsonArrayReceita = new JSONArray(jsonString);

        for (int i = 0; i < jsonArrayReceita.length(); i++) {

            JSONObject jsonObjectReceita = jsonArrayReceita.getJSONObject(i);

            Receita receita = new Receita();

            receita.setId(Integer.parseInt(jsonObjectReceita.get("id").toString()));
            receita.setData(jsonObjectReceita.getString("data").toString());
            receita.setValidade(jsonObjectReceita.getString("validade").toString());
            receita.setDoenca(jsonObjectReceita.getString("doenca").toString());
            receita.setDescricao(jsonObjectReceita.getString("descricao").toString());

            rtnListaReceita.add(receita);
        }

        return rtnListaReceita;
    }
}
