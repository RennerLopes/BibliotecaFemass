package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements Dao<Professor>{

    private static List<Professor> professores = new ArrayList<Professor>();


    @Override
    public void gravar(Professor object) throws Exception {
        professores.add(object);
    }

    @Override
    public List<Professor> listar() throws Exception {
        return professores;
    }

    @Override
    public void excluir(Professor object) throws Exception {
        professores.remove(object);
    }

    public Professor buscarPorMatricula(Integer matricula) {
        for(Professor pr : professores) {
            if(pr.getMatricula() == matricula) {
                return pr;
            }
        }
        return null;
    }


    public Boolean professorExiste(Integer matricula) {
        for(Professor pr : professores) {
            if(pr.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }
}
