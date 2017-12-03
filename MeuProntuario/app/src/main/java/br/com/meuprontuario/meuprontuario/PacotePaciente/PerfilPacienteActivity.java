package br.com.meuprontuario.meuprontuario.PacotePaciente;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.meuprontuario.meuprontuario.R;

public class PerfilPacienteActivity extends AppCompatActivity {

    ImageView imageView;
    Toolbar toolbar;
    Context context = this;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        imageView = (ImageView) findViewById(R.id.imageView);
        Picasso
                .with(context)
                .load("https://scontent.frec7-1.fna.fbcdn.net/v/t1.0-9/11745349_846470302106992_5066692979039796868_n.jpg?oh=a27e19d49f5752b8590c6cbb5c25af52&oe=5A97225D")
                .into(imageView)
        ;

        toolbar = (Toolbar) findViewById(R.id.toolbar_menu_perfil);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolBar);

        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparente));

        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBranco));

        collapsingToolbarLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                Toast.makeText(context, "TEste envet", Toast.LENGTH_LONG).show();
                return null;
            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 16908332:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
