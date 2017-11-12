package br.com.meuprontuario.meuprontuario.PacoteReceita;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.com.meuprontuario.meuprontuario.Const.Constantes;
import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.MetodosHTTP;
import br.com.meuprontuario.meuprontuario.PacoteConexaoWeb.Requester;
import br.com.meuprontuario.meuprontuario.R;

/**
 * Created by betoj on 13/04/2017.
 */

public class ReceitasFragment extends Fragment implements RecycleViewOnClikeListenerHack {

    private RecyclerView recyclerView;
    private ArrayList<Receita> listReceita = new ArrayList<Receita>();
    private ReceitaAdapter receitaAdapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_receitas, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_receitas);
        recyclerView.setHasFixedSize(true);
        sgeraListReceita();

        LinearLayoutManager lln = new LinearLayoutManager(getActivity());
        lln.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lln);
        //listReceita = ((MenuHomeActivity) getActivity());
        context = getContext();
        receitaAdapter = new ReceitaAdapter(listReceita, context);
        receitaAdapter.setRecyclerViewOnClick(this);
        recyclerView.setAdapter(receitaAdapter);

        /*recyclerView.addItemDecoration(
                new DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        );*/

        return view;
    }

    public void sgeraListReceita() {

        for(int x = 0; x<10; x++){
            Receita receita = new Receita();
            receita.setId(x);
            receita.setData(x+"/12/2017");
            receita.setValidade(x+"/01/2018");
            receita.setDescricao("descricao: "+x);
            receita.setDoenca("Doenca:" +x);
            listReceita.add(receita);
        }
    }

    public ArrayList<Receita> getListReceita() {
        return listReceita;
    }

    public ArrayList<Receita> lerJson() throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Receita> rtnListaReceita = new ArrayList<>();

        //se comunicando com o servidor
        Requester requester = new Requester();
        requester.setUrl(Constantes.URL_LISTA_RECEITAS);

        //
        requester.setMetodosHTTP(MetodosHTTP.POST);

        //

        String jsonString = requester.execute().get();


        if (requester.isSucessoConexao()) {

            //intanciando,convertendo e recebendo o objeto esperado do json
            ConvertJsonReceita convertJsonReceita = new ConvertJsonReceita();
            rtnListaReceita = convertJsonReceita.converteArrayReceita(jsonString);
            return rtnListaReceita;

        } else {

            rtnListaReceita.get(0).setStatus("3");
            rtnListaReceita.get(0).setMessagem("Não foi possível realizar a conexão com a internet, verifique sua conexão.");

            return rtnListaReceita;
        }

    }

    @Override
    public void onClickListener(View view, final int position) {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ReceitaAdapter.MyViewHolder holder, final int position) {
        //interfce - Click nos elementos da view

        holder.txtData.setText("Data: " + listReceita.get(position).getData());
        holder.txtDescrico.setText("Descrição: " + listReceita.get(position).getDescricao());
        holder.txtDoenca.setText("Doença: " + listReceita.get(position).getDoenca());
        holder.txtValidade.setText("Validade: " + listReceita.get(position).getValidade());
        holder.likeButton.setLiked(listReceita.get(position).isLiked());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(context)
                        .setTitle(listReceita.get(position).getDoenca())
                        .setMessage("Deseja ver mais detalhes da receitas?")
                        .setCancelable(false);
                alert.setNegativeButton("Não", null);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context,DetalheReceitaActivity.class);
                        intent.putExtra("data",listReceita.get(position).getData());
                        intent.putExtra("validade",listReceita.get(position).getValidade());
                        intent.putExtra("doenca",listReceita.get(position).getDoenca());
                        intent.putExtra("descricao",listReceita.get(position).getDescricao());
                        intent.putExtra("liked",listReceita.get(position).isLiked());
                        context.startActivity(intent);

                    }
                });
                alert.create();
                alert.show();
            }
        });
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listReceita.get(position).isLiked() == false){
                    listReceita.get(position).setLiked(true);
                    holder.likeButton.setLiked(true);
                }
                else{
                    listReceita.get(position).setLiked(false);
                    holder.likeButton.setLiked(false);
                }

            }
        });

    }

}
