package br.com.meuprontuario.meuprontuario.PacoteReceita;

import android.view.View;

import java.util.List;

/**
 * Created by Beto on 24/04/2017.
 */

public interface RecycleViewOnClikeListenerHack {
    void onClickListener(View view, int position);

    void onBindViewHolder(final ReceitaAdapter.MyViewHolder holder, final int position);

}
