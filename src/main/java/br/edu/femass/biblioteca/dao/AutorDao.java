package br.edu.femass.biblioteca.dao;
import br.edu.femass.biblioteca.model.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorDao implements Dao<Autor>{
    private static List<Autor> autores = new ArrayList<Autor>();

    @Override
    public void gravar(Autor object) throws Exception {
        autores.add(object);
    }

    @Override
    public List<Autor> listar() throws Exception {
        return autores;
    }

    @Override
    public void excluir(Autor object) throws Exception {
    autores.remove(object);
    }

    public Boolean autorExiste(Autor object) {
        if(autores.equals(object)) {
            return true;
        }

        return false;
    }
}
