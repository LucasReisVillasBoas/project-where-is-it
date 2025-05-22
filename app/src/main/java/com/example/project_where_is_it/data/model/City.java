package com.example.project_where_is_it.data.model;

public class City {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return getNome();
    }
}
