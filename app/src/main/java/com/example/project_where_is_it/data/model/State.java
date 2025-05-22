package com.example.project_where_is_it.data.model;

public class State {
    private String id;
    private String sigla;
    private String nome;

    public String getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
