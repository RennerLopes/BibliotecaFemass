package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Copia;

import java.util.ArrayList;
import java.util.List;

public class CopiaDao implements Dao<Copia>  {

    private static List<Copia> copias = new ArrayList<Copia>();

    @Override
    public void gravar(Copia object) throws Exception {
        copias.add(object);
    }

    @Override
    public List<Copia> listar() throws Exception {
        return copias;
    }

    @Override
    public void excluir(Copia object) throws Exception {
        copias.remove(object);
    }

    public Copia buscarPorCodigo(Integer codigoCopiaLivro) {
        for(Copia cp : copias) {
            if(cp.getCodigo() == codigoCopiaLivro && cp.getFixo() == true) {
                return cp;
            }
        }

        return null;
    }

    public Boolean copiaFixaExiste(Integer codigoCopiaLivro) {
        for(Copia cp : copias) {
            if(cp.getCodigoLivro() == codigoCopiaLivro && cp.getFixo() == true) {
                return true;
            }
        }

        return false;
    }
}
