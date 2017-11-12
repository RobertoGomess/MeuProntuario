package br.com.meuprontuario.meuprontuario;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.meuprontuario.meuprontuario.Uteis.NotificationUtil;

/**
 * Created by betoj on 07/04/2017.
 */

public class EsqueceuSenhaActivity extends AppCompatActivity {

    private EditText txtEsqueceuEmail;
    private Button btnEsqueceuSenha;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);


        //instanciando componentes

        txtEsqueceuEmail = (EditText) findViewById(R.id.txtEsqueceuEmail);
        btnEsqueceuSenha = (Button) findViewById(R.id.btnEsqueceuSenha);

        //pegando valores da Loginactivity

        Intent i = getIntent();
        String valor = i.getStringExtra("VALOR");
        Toast.makeText(getApplicationContext(), valor, Toast.LENGTH_LONG).show();

        //instancia toolBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EsqueceuSenhaActivity.this, LoginActivity.class);

                NotificationUtil.create( context , 1 , intent , "Recuração de senha","Foi enviado um e-mail para: "+txtEsqueceuEmail.getText());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();

        return super.onOptionsItemSelected(item);
    }
}
