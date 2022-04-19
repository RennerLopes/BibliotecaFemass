package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Genero;

import java.util.ArrayList;
import java.util.List;

public class GeneroDao implements Dao<Genero>{
    private static List<Genero> generos = new ArrayList<Genero>();

    @Override
    public void gravar(Genero object) throws Exception {
        generos.add(object);
    }

    @Override
    public List<Genero> listar() throws Exception {
        return generos;
    }

    @Override
    public void excluir(Genero object) throws Exception {
        generos.remove(object);
    }
}
