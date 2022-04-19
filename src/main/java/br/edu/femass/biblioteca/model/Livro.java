package br.edu.femass.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private Integer codigo;
    private String nome;
    private Integer ano;
    private String edicao;
    private Genero genero;
    private Autor autor;

    private static Integer proximoCodigo = 1;

    public Livro() {
        codigo = proximoCodigo;
        proximoCodigo++;
    }

    public String getNome() {
        return nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public static Integer getProximoCodigo() {
        return proximoCodigo;
    }

    public static void setProximoCodigo(Integer proximoCodigo) {
        Livro.proximoCodigo = proximoCodigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return nome;
    }
}


