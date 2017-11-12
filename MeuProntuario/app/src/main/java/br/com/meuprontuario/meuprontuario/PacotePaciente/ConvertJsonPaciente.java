package br.com.meuprontuario.meuprontuario.PacotePaciente;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Beto on 22/06/2017.
 */

public class ConvertJsonPaciente {
    public Paciente convertJson(String json) {
        Paciente paciente = new Paciente();

        try {

            JSONObject jsonReturnApi = new JSONObject(json);

            paciente.setId(Integer.parseInt(jsonReturnApi.getString("id")));
            paciente.setNome(jsonReturnApi.getString("nome"));
            paciente.setSexo(Integer.parseInt(jsonReturnApi.getString("sexo").toString()));
            paciente.setTelefone(jsonReturnApi.getString("telefone"));
            paciente.setCpf(jsonReturnApi.getString("cpf"));
            paciente.setSus(Long.parseLong(jsonReturnApi.getString("sus")));
            paciente.setEmail(jsonReturnApi.getString("email"));
            paciente.setSenha(jsonReturnApi.getString("senha"));
            paciente.setEndereco_id(Integer.parseInt(jsonReturnApi.getString("endereco_id").toString()));
            paciente.setStatus(jsonReturnApi.getString("status"));
            paciente.setMessagem(jsonReturnApi.getString("messagem"));

            return paciente;

        } catch (Exception e) {
            Log.e("Erro json", e.getMessage());
            return paciente;
        }
    }

    public String convertPacienteParaJson(Paciente paciente) {
        JSONObject json = new JSONObject();
        try {


            json.put("id", paciente.getId());
            json.put("nome", paciente.getNome());
            json.put("sexo", paciente.getSexo());
            json.put("telefone", paciente.getTelefone());
            json.put("cpf", paciente.getCpf());
            json.put("sus", paciente.getSus());
            json.put("email", paciente.getEmail());
            json.put("senha", paciente.getSenha());
            json.put("endereco_id", paciente.getEndereco_id());
            json.put("status", paciente.getStatus());
            json.put("messagem", paciente.getMessagem());


            return json.toString();

        } catch (Exception e) {
            Log.e("Erro json", e.getMessage());
            return json.toString();
        }
    }

}
