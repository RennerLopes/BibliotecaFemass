package br.edu.femass.biblioteca.model;

import java.util.Date;

public class Emprestimo {
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Integer matriculaUsuario;
    private Integer codigoCopiaLivro;

    public Emprestimo(Date dataEmprestimo, Date dataDevolucao, Integer matriculaUsuario, Integer codigoCopiaLivro) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.matriculaUsuario = matriculaUsuario;
        this.codigoCopiaLivro = codigoCopiaLivro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(Integer matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public Integer getCodigoCopiaLivro() {
        return codigoCopiaLivro;
    }

    public void setCodigoCopiaLivro(Integer codigoCopiaLivro) {
        this.codigoCopiaLivro = codigoCopiaLivro;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", matriculaUsuario=" + matriculaUsuario +
                ", codigoCopiaLivro=" + codigoCopiaLivro +
                '}';
    }
}
