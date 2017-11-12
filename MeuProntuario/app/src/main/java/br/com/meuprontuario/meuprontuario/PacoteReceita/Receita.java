package br.com.meuprontuario.meuprontuario.PacoteReceita;

import java.util.Date;

import br.com.meuprontuario.meuprontuario.StatusComunicacao;

/**
 * Created by aluno on 19/04/2017.
 */

public class Receita extends StatusComunicacao {

    public Receita() {

    }

    public Receita(int id, String data, String validade, String doenca, String descricao, boolean liked) {
        this.id = id;
        this.data = data;
        this.validade = validade;
        this.doenca = doenca;
        this.descricao = descricao;
        this.liked = liked;
    }

    private int id;
    private String data;
    private String validade;
    private String doenca;
    private String descricao;
    private boolean liked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
