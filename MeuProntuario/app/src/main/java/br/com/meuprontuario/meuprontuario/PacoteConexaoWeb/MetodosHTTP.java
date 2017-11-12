package br.com.meuprontuario.meuprontuario.PacoteConexaoWeb;

/**
 * Created by Beto on 19/06/2017.
 */

public enum MetodosHTTP {
    GET("Get"),
    POST("Post");

    private final String name;

    MetodosHTTP(final String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

}
