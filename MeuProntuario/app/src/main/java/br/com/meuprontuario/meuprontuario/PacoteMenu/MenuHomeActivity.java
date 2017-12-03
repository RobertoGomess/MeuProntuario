package br.com.meuprontuario.meuprontuario.PacoteMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import br.com.meuprontuario.meuprontuario.EmergenciaFragment;
import br.com.meuprontuario.meuprontuario.HomeFragment;
import br.com.meuprontuario.meuprontuario.PacoteReceita.ReceitasFragment;
import br.com.meuprontuario.meuprontuario.PacotePaciente.PerfilPacienteActivity;
import br.com.meuprontuario.meuprontuario.R;

public class MenuHomeActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private BottomBar  bottomBar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home);
        iniciarTollBar();

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch ( tabId){

                    case R.id.tab_home:
                        inflarFragment(R.id.contentContainer,new HomeFragment());
                            myToolbar.setTitle(R.string.app_name);
                        break;
                    case R.id.tab_emergencia:
                            inflarFragment(R.id.contentContainer_Map,new EmergenciaFragment());
                        myToolbar.setTitle(R.string.title_emergencia);
                        break;
                    case R.id.tab_receitas:
                        inflarFragment(R.id.contentContainer, new ReceitasFragment());
                        myToolbar.setTitle(R.string.title_receitas);
                        break;

                }

            }
        });

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_Perfil:
                Intent intent = new Intent(this,PerfilPacienteActivity.class);
                startActivity(intent);
                break;
            case R.id.action_configuracao:
                Toast.makeText(MenuHomeActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_FeedBack:
                Toast.makeText(MenuHomeActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
                break;
            case R.id.action_Termo:
                Toast.makeText(MenuHomeActivity.this,item.getItemId()+"",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Sair")
                .setMessage("Deseja realmente sair do aplicativo?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
        //super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void inflarFragment(int frameLayout, Fragment fragment){
        if (getSupportFragmentManager().findFragmentById(R.id.contentContainer_Map) != null){
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager()
                    .findFragmentById(R.id.contentContainer_Map)).commit();
        }
        getSupportFragmentManager().beginTransaction().replace(frameLayout,fragment).commitNow();

    }
    private void iniciarTollBar(){
        //instanciando tollbar personalizada
        myToolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(myToolbar);

    }

}
