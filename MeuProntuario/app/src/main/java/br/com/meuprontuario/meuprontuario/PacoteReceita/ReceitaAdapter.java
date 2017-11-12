package br.com.meuprontuario.meuprontuario.PacoteReceita;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;

import java.util.List;

import br.com.meuprontuario.meuprontuario.HomeFragment;
import br.com.meuprontuario.meuprontuario.PacoteMenu.MenuHomeActivity;
import br.com.meuprontuario.meuprontuario.R;

/**
 * Created by aluno on 19/04/2017.
 */

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaAdapter.MyViewHolder> {

    private List<Receita> mListaReceica;
    private LayoutInflater mLayoutInflater;
    private RecycleViewOnClikeListenerHack recycleViewOnClikeListenerHack;
    private Context context;

    public ReceitaAdapter(List<Receita> mListaReceica, Context context) {
        this.mListaReceica = mListaReceica;
        this.context = context;
        Animation animation = new TranslateAnimation(50,0,0,0);
        animation.setFillAfter(true);
        animation.setDuration(2000);
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtData;
        public TextView txtValidade;
        public TextView txtDoenca;
        public TextView txtDescrico;
        public ImageButton imageButton;
        public LikeButton likeButton;

        public View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtData = (TextView) itemView.findViewById(R.id.txt_data);
            txtDoenca = (TextView) itemView.findViewById(R.id.txt_doenca);
            txtDescrico = (TextView) itemView.findViewById(R.id.txt_descricao);
            txtValidade = (TextView) itemView.findViewById(R.id.txt_validade);
            imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
            likeButton = (LikeButton) itemView.findViewById(R.id.star_button);

            itemView.setOnClickListener(this);
            view = itemView;
        }


        @Override
        public void onClick(View v) {
            if (recycleViewOnClikeListenerHack != null) {
                recycleViewOnClikeListenerHack.onClickListener(v, getAdapterPosition());

            }
        }
    }

    public void setRecyclerViewOnClick(RecycleViewOnClikeListenerHack recycleViewOnClikeListenerHack) {
        this.recycleViewOnClikeListenerHack = recycleViewOnClikeListenerHack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.linha_receita, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        recycleViewOnClikeListenerHack.onBindViewHolder(holder,position);
    }


    @Override
    public void onBindViewHolder( MyViewHolder holder, final int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

    }

    @Override
    public int getItemCount() {
        return mListaReceica.size();
    }

    private void insertItem(Receita receita) {
        mListaReceica.add(receita);
        notifyItemInserted(getItemCount());
    }

    private void updateItem(int position) {
        mListaReceica.get(position);
        notifyItemChanged(position);
    }

    private void removerItem(int position) {
        mListaReceica.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mListaReceica.size());
    }

    public void updateList(Receita receita) {
        insertItem(receita);
    }



}
