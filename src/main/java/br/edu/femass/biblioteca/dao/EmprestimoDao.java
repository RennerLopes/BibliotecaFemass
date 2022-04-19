package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao implements Dao<Emprestimo>{
    private static List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

    @Override
    public void gravar(Emprestimo object) throws Exception {
        emprestimos.add(object);
    }

    @Override
    public List<Emprestimo> listar() throws Exception {
        return emprestimos;
    }

    @Override
    public void excluir(Emprestimo object) throws Exception {
        emprestimos.remove(object);
    }
}
