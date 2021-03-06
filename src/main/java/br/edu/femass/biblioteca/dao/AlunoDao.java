package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements Dao<Aluno>{

    private static List<Aluno> alunos = new ArrayList<Aluno>();


    @Override
    public void gravar(Aluno object) throws Exception {
        alunos.add(object);
    }

    @Override
    public List<Aluno> listar() throws Exception {
        return alunos;
    }

    @Override
    public void excluir(Aluno object) throws Exception {
        alunos.remove(object);
    }

    public Aluno BuscarPorMatricula(Integer matriculaAluno) {
        for(Aluno al : alunos) {
            if(al.getMatricula() == matriculaAluno) {
                return al;
            }
        }
        return null;
    }

    public Boolean alunoExiste(Integer matriculaAluno) {

        for(Aluno al : alunos) {
            if(al.getMatricula() == matriculaAluno) {
                return true;
            }
        }
        return false;
    }
}
