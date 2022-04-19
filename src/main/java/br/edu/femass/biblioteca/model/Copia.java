package br.edu.femass.biblioteca.model;

import java.util.function.Predicate;

public class Copia {
    private Integer codigo;
    private Integer codigoLivro;
    private Boolean Fixo;

    private static Integer proximoCodigo = 1;

    public Copia() {
        codigo = proximoCodigo;
        proximoCodigo++;
    }

    public Integer getCodigoLivro() {
        return codigoLivro;
    }

    public void setCodigoLivro(Integer codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public static Integer getProximoCodigo() {
        return proximoCodigo;
    }

    public static void setProximoCodigo(Integer proximoCodigo) {
        Copia.proximoCodigo = proximoCodigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Boolean getFixo() {
        return Fixo;
    }

    public void setFixo(Boolean fixo) {
        Fixo = fixo;
    }

    @Override
    public String toString() {
        return "Copia{" +
                "codigo=" + codigo +
                ", codigoLivro=" + codigoLivro +
                ", Fixo=" + Fixo +
                '}';
    }
}
