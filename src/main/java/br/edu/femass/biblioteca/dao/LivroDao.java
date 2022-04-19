package br.edu.femass.biblioteca.dao;
import br.edu.femass.biblioteca.model.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements Dao<Livro>{
    private static final String ARQUIVO = "filme.xml";
    private static List<Livro> livros = new ArrayList<Livro>();

    @Override
    public void gravar(Livro object) throws Exception {
        livros.add(object);

//        Livro teste = new Livro();
//        teste.setNome("teste1");
//        teste.setAno(299);
//        teste.setCodigo(2);
//
//
//
//        ObjectMapper Obj = new ObjectMapper();
//
//        try {
//
//            // get Organisation object as a json string
//            String jsonStr = Obj.writeValueAsString(teste);
//
//            // Displaying JSON String
//            System.out.println(jsonStr);
//        }
//
//        catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public List<Livro> listar() throws Exception {
        return livros;
    }

    @Override
    public void excluir(Livro object) throws Exception {
        livros.remove(object);
    }

    public Boolean livroExiste(Integer codigoLivro) {
        for(Livro liv : livros) {
            if(liv.getCodigo() == codigoLivro) {
                return true;
            }
        }

        return false;
    }
}
