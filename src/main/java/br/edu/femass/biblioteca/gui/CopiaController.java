package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.CopiaDao;
import br.edu.femass.biblioteca.dao.LivroDao;
import br.edu.femass.biblioteca.model.Copia;
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

public class CopiaController implements Initializable {
    private CopiaDao copiaDao = new CopiaDao();
    private LivroDao livroDao = new LivroDao();

    @FXML
    private ListView<Copia> LstCopias;

    @FXML
    private Button BtnIncluirCopia;

    @FXML
    private Button BtnExcluirCopia;

    @FXML
    private Button BtnGravarCopia;

    @FXML
    private Button BtnCancelarCopia;

    @FXML
    private TextField TxtCodigoCopia;

    @FXML
    private TextField TxtCodigoLivroCopia;

    @FXML
    private CheckBox CbLivroFixo;

    private void limparTela() {
        TxtCodigoCopia.setText("");
        TxtCodigoLivroCopia.setText("");
        CbLivroFixo.setSelected(false);
    }

    private void habilitarInterface(boolean incluir) {
        BtnGravarCopia.setDisable(!incluir);
        BtnCancelarCopia.setDisable(!incluir);
        TxtCodigoLivroCopia.setDisable(!incluir);
        CbLivroFixo.setDisable(!incluir);
        BtnExcluirCopia.setDisable(incluir);
        BtnIncluirCopia.setDisable(incluir);
        LstCopias.setDisable(incluir);
    }

    private void exibirLivro() {
        Copia copia = LstCopias.getSelectionModel().getSelectedItem();
        TxtCodigoCopia.setText(copia.getCodigo().toString());
        TxtCodigoLivroCopia.setText(copia.getCodigoLivro().toString());
        CbLivroFixo.setSelected(copia.getFixo());
    }

    private void atualizarLista() {
        List<Copia> copias = null;

        try {
            copias = copiaDao.listar();
        } catch(Exception e) {
            copias = new ArrayList<Copia>();
        }

        ObservableList<Copia> copiasOb = FXCollections.observableArrayList(copias);
        LstCopias.setItems(copiasOb);

    }

    @FXML
    private void LstCopias_MouseClicked(MouseEvent evento) {
        exibirLivro();
    }

    @FXML
    private void LstCopias_KeyPressed(KeyEvent evento) {
        exibirLivro();
    }

    @FXML
    private void BtnIncluirCopia_Action(ActionEvent evento) {
        habilitarInterface(true);
        TxtCodigoLivroCopia.requestFocus();
        limparTela();
    }

    @FXML
    private void BtnExcluirCopia_Action(ActionEvent evento) {
        Copia copia = LstCopias.getSelectionModel().getSelectedItem();

        if(copia == null) return;

        try {
            copiaDao.excluir(copia);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnGravarCopia_Action(ActionEvent evento) {
        Copia copia = new Copia();
        List<Copia> copias = null;
        List<Livro> livros = null;
        Boolean possuiCopiaFixa = false;
        Boolean possuiLivro = false;


        copia.setCodigoLivro(Integer.parseInt(TxtCodigoLivroCopia.getText()));
        copia.setFixo(CbLivroFixo.isSelected());

        try {
            copias = copiaDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            livros = livroDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(CbLivroFixo.isSelected() != true) {
            for(Copia cp : copias) {
                if(cp.getCodigoLivro() == copia.getCodigoLivro() && cp.getFixo() == true) {
                    possuiCopiaFixa = true;
                }
            }
        }

        for(Livro liv : livros) {
            if(liv.getCodigo() == copia.getCodigoLivro()) {
                possuiLivro = true;
            }
        }

        if(!possuiLivro) {
            ErrorDialog("Livro não encontrado!");
            return;
        }

        if(!possuiCopiaFixa && copia.getFixo() != true) {
            ErrorDialog("A primeira cópia deve ser fixa!");
            return;
        }

        try {
            copiaDao.gravar(copia);

        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

        habilitarInterface(false);
    }

    private void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void BtnCancelarCopia_Action(ActionEvent evento) {
        habilitarInterface(false);
        limparTela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarLista();
    }
}
