package br.edu.femass.biblioteca.model;

public class Autor {
    private String nome;
    private String Sobrenome;

    public Autor(String nome, String sobrenome) {
        this.nome = nome;
        Sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        Sobrenome = sobrenome;
    }
}


