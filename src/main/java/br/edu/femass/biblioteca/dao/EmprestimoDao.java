package br.edu.femass.biblioteca.dao;

import br.edu.femass.biblioteca.model.Emprestimo;

import java.util.ArrayList;
import java.util.Date;
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

    public Emprestimo buscarPorCodigoCopiaLivro(Integer codigo) {
        for(Emprestimo emp : emprestimos) {
            if(emp.getCodigoCopiaLivro() == codigo) {
                return emp;
            }
        }

        return null;
    }

    public Integer quantidadeEmprestimosUsuario(Integer codigoUsuario){
        Date dt = new Date(System.currentTimeMillis());
        Integer qntEmprestimos = 0;
        for(Emprestimo emp : emprestimos) {
        if(emp.getMatriculaUsuario() == codigoUsuario && dt.before(emp.getDataDevolucao())) {
            qntEmprestimos ++;
            }
        }
        return qntEmprestimos;
    }
}
