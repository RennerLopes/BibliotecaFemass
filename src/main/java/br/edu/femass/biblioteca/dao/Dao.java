package br.edu.femass.biblioteca.dao;

import java.util.List;

public interface Dao<T> {
    public void gravar(T object) throws Exception;
    public List<T> listar() throws Exception;
    public void excluir(T object) throws Exception;
}
