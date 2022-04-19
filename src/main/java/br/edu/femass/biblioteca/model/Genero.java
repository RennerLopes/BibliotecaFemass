package br.edu.femass.biblioteca.model;

public class Genero {
    private Integer codigo;
    private String nome;

    private static Integer proximoCodigo = 1;

    public Genero(String nome) {
        this.nome = nome;
        codigo = proximoCodigo;
        proximoCodigo++;
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
        Genero.proximoCodigo = proximoCodigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


