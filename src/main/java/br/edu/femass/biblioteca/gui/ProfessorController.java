package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.AlunoDao;
import br.edu.femass.biblioteca.dao.ProfessorDao;
import br.edu.femass.biblioteca.model.Aluno;
import br.edu.femass.biblioteca.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfessorController implements Initializable {
    private ProfessorDao professorDao = new ProfessorDao();

    @FXML
    private Button BtnGravarProfessor;

    @FXML
    private Button BtnExcluirProfessor;

    @FXML
    private Button BtnIncluirProfessor;

    @FXML
    private Button BtnCancelarProfessor;

    @FXML
    private ListView<Professor> LstProfessores;

    @FXML
    private TextField TxtNomeProfessor;

    @FXML
    private TextField TxtSobrenomeProfessor;

    @FXML
    private TextField TxtMatriculaProfessor;

    private void limparTela() {
        TxtNomeProfessor.setText("");
        TxtSobrenomeProfessor.setText("");
        TxtMatriculaProfessor.setText("");
    }

    private void habilitarInterface(boolean incluir) {
        BtnGravarProfessor.setDisable(!incluir);
        BtnCancelarProfessor.setDisable(!incluir);
        TxtNomeProfessor.setDisable(!incluir);
        TxtSobrenomeProfessor.setDisable(!incluir);
        TxtMatriculaProfessor.setDisable(!incluir);
        BtnExcluirProfessor.setDisable(incluir);
        BtnIncluirProfessor.setDisable(incluir);
        LstProfessores.setDisable(incluir);
    }

    private void exibirAluno() {
        Professor professor = LstProfessores.getSelectionModel().getSelectedItem();
        TxtNomeProfessor.setText(professor.getNome());
        TxtSobrenomeProfessor.setText(professor.getSobrenome());
        TxtMatriculaProfessor.setText(professor.getMatricular().toString());
    }

    private void atualizarLista() {
        List<Professor> professores = null;

        try {
            professores = professorDao.listar();
        } catch(Exception e) {
            professores = new ArrayList<Professor>();
        }

        ObservableList<Professor> professoresOb = FXCollections.observableArrayList(professores);
        LstProfessores.setItems(professoresOb);

    }

    @FXML
    private void LstProfessores_MouseClicked(MouseEvent evento) {
        exibirAluno();
    }

    @FXML
    private void LstProfessores_KeyPressed(KeyEvent evento) {
        exibirAluno();
    }

    @FXML
    private void BtnIncluirProfessor_Action(ActionEvent evento) {
        habilitarInterface(true);
        TxtNomeProfessor.requestFocus();
        limparTela();
    }

    @FXML
    private void BtnExcluirProfessor_Action(ActionEvent evento) {
        Professor professor = LstProfessores.getSelectionModel().getSelectedItem();

        if(professor == null) return;

        try {
            professorDao.excluir(professor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnGravarProfessor_Action(ActionEvent evento) {
        Professor professor = new Professor(TxtNomeProfessor.getText(), TxtSobrenomeProfessor.getText(), Integer.parseInt(TxtMatriculaProfessor.getText()));
        List<Professor> professores = null;

        try {
            professores = professorDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Professor pr : professores) {
            if(pr.getMatricular() == professor.getMatricular()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Professor já possue cadastro!");
                alert.showAndWait();
                return;
            }
        }

        try {
            professorDao.gravar(professor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

        habilitarInterface(false);
    }

    @FXML
    private void BtnCancelarProfessor_Action(ActionEvent evento) {
        habilitarInterface(false);
        limparTela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarLista();
    }
}
