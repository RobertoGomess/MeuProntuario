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
import android.widget.TextView;

import br.com.meuprontuario.meuprontuario.R;

public class CadastroPasso2Activity extends AppCompatActivity {

    //declaracao de componentes
    private TextView txtvInforPessoa;
    private EditText txtNome;
    private EditText txtTelefone;
    private EditText txtCpf;
    private EditText txtDataNascimento;
    private FloatingActionButton btnConcluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_passo2);

        //instanciando componentes
        txtvInforPessoa = (TextView) findViewById(R.id.txtvInforPessoa);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtCpf = (EditText) findViewById(R.id.txtCpf);
        txtDataNascimento = (EditText) findViewById(R.id.txtDataNascimento);
        btnConcluir = (FloatingActionButton) findViewById(R.id.btnConcluir);
        //instanciando ToolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //eventos
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "click do botao concluir" ,Toast.LENGTH_LONG).show();
                Intent cadastroEndereco = new Intent(CadastroPasso2Activity.this, CadastroPasso3Activity.class);
                startActivity(cadastroEndereco);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();

        return super.onOptionsItemSelected(item);
    }
}
