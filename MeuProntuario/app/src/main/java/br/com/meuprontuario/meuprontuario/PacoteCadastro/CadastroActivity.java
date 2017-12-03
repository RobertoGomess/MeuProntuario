package br.com.meuprontuario.meuprontuario.PacoteCadastro;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import br.com.meuprontuario.meuprontuario.R;

public class CadastroActivity extends AppCompatActivity {

    //decaração do componentes
    private TextView txtvInforAcesso;
    private EditText txtNomeUsuario;
    private EditText txtEmail;
    private EditText txtvSenha;
    private EditText txtConfirmaSenha;
    private Switch switchTermos;
    private FloatingActionButton btnCadastrarPasso2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Instaciando componentes
        txtvInforAcesso = (TextView) findViewById(R.id.txtViewInforAcesso);
        txtNomeUsuario = (EditText) findViewById(R.id.txtNomeUsuario);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtvSenha = (EditText) findViewById(R.id.txtSenha);
        txtConfirmaSenha = (EditText) findViewById(R.id.txtConfirmaSenha);
        switchTermos = (Switch) findViewById(R.id.switchTermos);
        btnCadastrarPasso2 = (FloatingActionButton) findViewById(R.id.btnCadastrarPasso2);
        //instanciando ToolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //eventos>>>>
        btnCadastrarPasso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroPasso2 = new Intent(CadastroActivity.this, CadastroPasso2Activity.class);
                startActivity(cadastroPasso2);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();

        return super.onOptionsItemSelected(item);
    }
}
