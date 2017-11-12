package br.com.meuprontuario.meuprontuario.PacoteReceita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;

import br.com.meuprontuario.meuprontuario.PacoteMenu.MenuHomeActivity;
import br.com.meuprontuario.meuprontuario.R;

public class DetalheReceitaActivity extends AppCompatActivity {

    Intent intent;
    Receita receita;
    TextView txtData;
    TextView txtValidade;
    TextView txtDoenca;
    TextView txtDescricao;
    LikeButton likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_receita);

        txtData = (TextView) findViewById(R.id.txt_data_detalhe);
        txtValidade = (TextView) findViewById(R.id.txt_validade_detalhe);
        txtDoenca = (TextView) findViewById(R.id.txt_doenca_detalhe);
        txtDescricao = (TextView) findViewById(R.id.txt_descricao_detalhe);
        likeButton = (LikeButton) findViewById(R.id.star_button_detalhe);

        intent = getIntent();
        receita = new Receita();
        receita.setData(intent.getStringExtra("data"));
        receita.setValidade(intent.getStringExtra("validade"));
        receita.setDoenca(intent.getStringExtra("doenca"));
        receita.setDescricao(intent.getStringExtra("descricao"));
        receita.setLiked(intent.getBooleanExtra("liked",false));

        txtData.setText("Data: "+receita.getData());
        txtValidade.setText("Validade: "+receita.getValidade());
        txtDoenca.setText(receita.getDoenca());
        txtDescricao.setText(receita.getDescricao());
        likeButton.setLiked(receita.isLiked());

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_menu_receitaDetalhe);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 16908332:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
