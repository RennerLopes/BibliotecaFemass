package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.AutorDao;
import br.edu.femass.biblioteca.dao.GeneroDao;
import br.edu.femass.biblioteca.dao.LivroDao;
import br.edu.femass.biblioteca.model.Autor;
import br.edu.femass.biblioteca.model.Genero;
import br.edu.femass.biblioteca.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivroController implements Initializable {
    private LivroDao livroDao = new LivroDao();
    private AutorDao autorDao = new AutorDao();
    private GeneroDao generoDao = new GeneroDao();

    @FXML
    private ListView<Livro> LstLivros;

    @FXML
    private Button BtnIncluirLivro;

    @FXML
    private Button BtnExcluirLivro;

    @FXML
    private Button BtnGravarLivro;

    @FXML
    private Button BtnCancelarLivro;

    @FXML
    private TextField TxtCodigoLivro;

    @FXML
    private TextField TxtNomeLivro;

    @FXML
    private TextField TxtAnoLivro;

    @FXML
    private TextField TxtEdicaoLivro;

    @FXML
    private TextField TxtGeneroLivro;

    @FXML
    private TextField TxtNomeAutorLivro;

    @FXML
    private TextField TxtSobrenomeAutorLivro;

    private void limparTela() {
        TxtCodigoLivro.setText("");
        TxtAnoLivro.setText("");
        TxtEdicaoLivro.setText("");
        TxtNomeLivro.setText("");
        TxtGeneroLivro.setText("");
        TxtNomeAutorLivro.setText("");
        TxtSobrenomeAutorLivro.setText("");
    }

    private void habilitarInterface(boolean incluir) {
        BtnGravarLivro.setDisable(!incluir);
        BtnCancelarLivro.setDisable(!incluir);
        TxtAnoLivro.setDisable(!incluir);
        TxtEdicaoLivro.setDisable(!incluir);
        TxtNomeLivro.setDisable(!incluir);
        TxtGeneroLivro.setDisable(!incluir);
        TxtNomeAutorLivro.setDisable(!incluir);
        TxtSobrenomeAutorLivro.setDisable(!incluir);
        BtnExcluirLivro.setDisable(incluir);
        BtnIncluirLivro.setDisable(incluir);
        LstLivros.setDisable(incluir);
    }

    private void exibirLivro() {
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if (livro==null) return;
        TxtNomeLivro.setText(livro.getNome());
        TxtAnoLivro.setText(livro.getAno().toString());
        TxtEdicaoLivro.setText(livro.getEdicao());
        TxtCodigoLivro.setText(livro.getCodigo().toString());
        TxtGeneroLivro.setText(livro.getGenero().getNome());
        TxtNomeAutorLivro.setText(livro.getAutor().getNome());
        TxtSobrenomeAutorLivro.setText(livro.getAutor().getSobrenome());
    }

    @FXML
    private void LstLivros_MouseClicked(MouseEvent evento) {
        exibirLivro();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento) {
        exibirLivro();
    }

    @FXML
    private void BtnIncluirLivro_Action(ActionEvent evento) {
        habilitarInterface(true);
        TxtNomeLivro.requestFocus();
        limparTela();
    }

    @FXML
    private void BtnExcluirLivro_Action(ActionEvent evento) {
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();

        if(livro == null) return;

        try {
            livroDao.excluir(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
    }


    @FXML
    private void BtnGravarLivro_Action(ActionEvent evento) {
        Livro livro = new Livro();
        Genero genero = new Genero(TxtGeneroLivro.getText());
        Autor autor = new Autor(TxtNomeAutorLivro.getText(), TxtSobrenomeAutorLivro.getText());

        livro.setAno(Integer.parseInt(TxtAnoLivro.getText()));
        livro.setNome(TxtNomeLivro.getText());
        livro.setEdicao(TxtEdicaoLivro.getText());
        livro.setGenero(genero);
        livro.setAutor(autor);

        try {
            if(!autorDao.autorExiste(autor)) {
                autorDao.gravar(autor);
            }

            if(!generoDao.generoExiste(genero)) {
                 generoDao.gravar(genero);
            }

            livroDao.gravar(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
        habilitarInterface(false);
    }

    @FXML
    private void BtnCancelarLivro_Action(ActionEvent evento) {
        habilitarInterface(false);
        limparTela();
    }

    private void atualizarLista() {
        List<Livro> livros = null;

        try {
            livros = livroDao.listar();
        } catch(Exception e) {
            livros = new ArrayList<Livro>();
        }

        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livrosOb);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }

}