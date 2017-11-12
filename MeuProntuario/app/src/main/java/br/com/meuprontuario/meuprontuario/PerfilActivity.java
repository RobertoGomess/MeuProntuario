package br.com.meuprontuario.meuprontuario;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PerfilActivity extends AppCompatActivity {

    ImageView imageView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        imageView = (ImageView) findViewById(R.id.imageViewPerfilLoad);
        toolbar = (Toolbar) findViewById(R.id.toolbar_menu_perfil);

        Glide.with(this)
                .load(R.drawable.loading) // aqui é teu gif
                .asGif()
                .into(imageView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        baixarImagem();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 16908332:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void baixarImagem() {


        //criando a nova thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1 -transformando a url em objeto
                    URL url = new URL("https://scontent.frec8-1.fna.fbcdn.net/v/t1.0-9/11745349_846470302106992_5066692979039796868_n.jpg?oh=24217427586e4bdabff570b117de7671&oe=5A207B5D");
                    //2 -abrindo a conexão http
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    //3 - pegando a imagem em byte code
                    InputStream inputStream = http.getInputStream();
                    //4 - transformando em bitmap
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            imageView.setImageBitmap(bitmap);
                        }
                    });

                    Log.i("MainActivity", "Baixou a imagem");

                } catch (Exception e) {

                    Log.i("MainActivity", "Não Baixou a imagem");
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
