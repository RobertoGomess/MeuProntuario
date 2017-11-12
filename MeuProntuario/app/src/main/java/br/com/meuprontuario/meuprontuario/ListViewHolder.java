package br.com.meuprontuario.meuprontuario;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aluno on 19/04/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewDoenca;
    public TextView textViewValidade;
    public TextView textViewData;
    public TextView textViewDescricao;

    public ListViewHolder(View itemView) {
        super(itemView);

        textViewData = (TextView) itemView.findViewById(R.id.txt_data);
        textViewDescricao = (TextView) itemView.findViewById(R.id.txt_descricao);
        textViewDoenca = (TextView) itemView.findViewById(R.id.txt_doenca);
        textViewValidade = (TextView) itemView.findViewById(R.id.txt_validade);

    }
}
