package br.com.meuprontuario.meuprontuario;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import br.com.meuprontuario.meuprontuario.Const.Constantes;
import br.com.meuprontuario.meuprontuario.PacoteCadastro.CadastroActivity;
import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.MetodosHTTP;
import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.Requester;
import br.com.meuprontuario.meuprontuario.PacoteMenu.MenuHomeActivity;
import br.com.meuprontuario.meuprontuario.PacotePaciente.ConvertJsonPaciente;
import br.com.meuprontuario.meuprontuario.PacotePaciente.Paciente;

/**
 * Created by betoj on 07/04/2017.
 */

public class LoginActivity extends AppCompatActivity {

    //declaração dos elementos

    private Button btnCadastrar;
    private Button btnEntrar;
    public EditText txtEmail;
    public EditText txtSenha;
    private Switch switchLembraSenha;
    private TextView txtvEsqueceSenha;

    //declaração variaveis
    ProgressDialog pdia;
    Context context = this;
    String rtnPacienteJson;
    Paciente rtnPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //instanciando elementos

        txtEmail = (EditText) findViewById(R.id.txtNomeUsuario);
        txtSenha = (EditText) findViewById(R.id.txtConfirmaSenha);
        switchLembraSenha = (Switch) findViewById(R.id.switchLembraSenha);
        txtvEsqueceSenha = (TextView) findViewById(R.id.textViewEsqueceuSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnEntrar = (Button) findViewById(R.id.btn_Entrar);
        //instanciando o Tollbar
        final Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Eventos click

        txtvEsqueceSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent esqueceuSenha = new Intent(LoginActivity.this, EsqueceuSenhaActivity.class);
                //passando valor para a outra activity
                esqueceuSenha.putExtra("VALOR", txtEmail.getText().toString());
                startActivity(esqueceuSenha);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastro = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(cadastro);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        //pdia = ProgressDialog.show(context, "Aguarde", "Validando acesso.");

                                    }

                                });

                                rtnPaciente = verificarLogin();
                                ConvertJsonPaciente cv = new ConvertJsonPaciente();
                                rtnPacienteJson = cv.convertPacienteParaJson(rtnPaciente);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        pdia.dismiss();
                                    }
                                });

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (rtnPaciente.getId() > 0) {
                                            Toast.makeText(context, rtnPaciente.getMessagem(), Toast.LENGTH_LONG).show();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {

                                                            Intent telaHome = new Intent(LoginActivity.this, MenuHomeActivity.class);
                                                            telaHome.putExtra("jsonPaciente", rtnPacienteJson);
                                                            startActivity(telaHome);
                                                        }
                                                    }).start();

                                                }
                                            });

                                        } else {
                                            Intent telaHome = new Intent(LoginActivity.this, MenuHomeActivity.class);
                                            telaHome.putExtra("jsonPaciente", rtnPacienteJson);
                                            startActivity(telaHome);
                                            Toast.makeText(context, rtnPaciente.getMessagem(), Toast.LENGTH_LONG).show();
                                        }


                                    }
                                });


                            } catch (Exception e) {
                                Toast.makeText(context, "Erro ao tentar realizar login", Toast.LENGTH_LONG).show();
                            }


                        }
                    }).start();


                } catch (Exception ex) {
                    pdia.dismiss();
                    Toast.makeText(context, "Erro ao tentar realizar login", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public Paciente verificarLogin() throws JSONException, ExecutionException, InterruptedException {
        Paciente paciente = new Paciente();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdia = ProgressDialog.show(context, "Aguarde", "Validando acesso.");
                        }
                    });

                }
            }).start();

            Requester requester = new Requester();
            //setando a url que vamos consultar
            requester.setUrl(Constantes.URL_LOGIN);

            //caso precise enviar um Post ou Get para o servidor
            //setando tipo de metodo

            //caso for POST
            requester.setMetodosHTTP(MetodosHTTP.POST);

            //caso for GET
            //requester.setMetodosHTTP(MetodosHTTP.GET);

            //criando o json que será enviado para o servidor
            JSONObject objJson = new JSONObject();

            //preechendo o atributos do json
            objJson.put("email", txtEmail.getText().toString());
            objJson.put("senha", txtSenha.getText().toString());
            requester.setJsonObjectPut(objJson);

            //executando o request e recebendo o retorno do servidor
            String retorno = requester.execute().get();

            if (requester.isSucessoConexao()) {
                //intanciando,convertendo e recebendo o objeto esperado do json
                ConvertJsonPaciente cvPaciente = new ConvertJsonPaciente();
                paciente = cvPaciente.convertJson(retorno);
            } else {
                paciente.setStatus("3");
                paciente.setMessagem("Não foi possível realizar a conexão com a internet, verifique sua conexão.");
                return paciente;
            }

        } catch (Exception e) {
            paciente.setStatus("3");
            paciente.setMessagem("Erro inesperado: " + e.getMessage());
            return paciente;
        }


        return paciente;
    }


}
