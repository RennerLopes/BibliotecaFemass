package br.edu.femass.biblioteca.model;

public class Usuario {
    private Integer prazoDevolucaoEmDias;
    private String nome;
    private String sobrenome;
    private Integer matricula;

    public Usuario(Integer prazoDevolucaoEmDias, String nome, String sobrenome, Integer matricular) {
        this.prazoDevolucaoEmDias = prazoDevolucaoEmDias;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricular;
    }

    public Integer getPrazoDevolucaoEmDias() {
        return prazoDevolucaoEmDias;
    }

    public void setPrazoDevolucaoEmDias(Integer prazoDevolucaoEmDias) {
        this.prazoDevolucaoEmDias = prazoDevolucaoEmDias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricular) {
        this.matricula = matricular;
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome;
    }
}
