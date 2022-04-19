package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.AlunoDao;
import br.edu.femass.biblioteca.model.Aluno;
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

public class AlunoController implements Initializable {
    private AlunoDao alunoDao = new AlunoDao();

    @FXML
    private Button BtnGravarAluno;

    @FXML
    private Button BtnExcluirAluno;

    @FXML
    private Button BtnIncluirAluno;

    @FXML
    private Button BtnCancelarAluno;

    @FXML
    private ListView<Aluno> LstAlunos;

    @FXML
    private TextField TxtNomeAluno;

    @FXML
    private TextField TxtSobrenomeAluno;

    @FXML
    private TextField TxtMatriculaAluno;

    private void limparTela() {
        TxtNomeAluno.setText("");
        TxtSobrenomeAluno.setText("");
        TxtMatriculaAluno.setText("");
    }

    private void habilitarInterface(boolean incluir) {
        BtnGravarAluno.setDisable(!incluir);
        BtnCancelarAluno.setDisable(!incluir);
        TxtNomeAluno.setDisable(!incluir);
        TxtSobrenomeAluno.setDisable(!incluir);
        TxtMatriculaAluno.setDisable(!incluir);
        BtnExcluirAluno.setDisable(incluir);
        BtnIncluirAluno.setDisable(incluir);
        LstAlunos.setDisable(incluir);
    }

    private void exibirAluno() {
        Aluno aluno = LstAlunos.getSelectionModel().getSelectedItem();
        TxtNomeAluno.setText(aluno.getNome());
        TxtSobrenomeAluno.setText(aluno.getSobrenome());
        TxtMatriculaAluno.setText(aluno.getMatricular().toString());
    }

    private void atualizarLista() {
        List<Aluno> alunos = null;

        try {
            alunos = alunoDao.listar();
        } catch(Exception e) {
            alunos = new ArrayList<Aluno>();
        }

        ObservableList<Aluno> alunosOb = FXCollections.observableArrayList(alunos);
        LstAlunos.setItems(alunosOb);

    }

    @FXML
    private void LstAlunos_MouseClicked(MouseEvent evento) {
        exibirAluno();
    }

    @FXML
    private void LstAlunos_KeyPressed(KeyEvent evento) {
        exibirAluno();
    }

    @FXML
    private void BtnIncluirAluno_Action(ActionEvent evento) {
        habilitarInterface(true);
        TxtNomeAluno.requestFocus();
        limparTela();
    }

    @FXML
    private void BtnExcluirAluno_Action(ActionEvent evento) {
        Aluno aluno = LstAlunos.getSelectionModel().getSelectedItem();

        if(aluno == null) return;

        try {
            alunoDao.excluir(aluno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnGravarAluno_Action(ActionEvent evento) {
        Aluno aluno = new Aluno(TxtNomeAluno.getText(), TxtSobrenomeAluno.getText(), Integer.parseInt(TxtMatriculaAluno.getText()));
        List<Aluno> alunos = null;

        try {
            alunos = alunoDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Aluno al : alunos) {
            if(al.getMatricular() == aluno.getMatricular()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Aluno já possue cadastro!");
                alert.showAndWait();
                return;
            }
        }

        try {
            alunoDao.gravar(aluno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

        habilitarInterface(false);
    }

    @FXML
    private void BtnCancelarAluno_Action(ActionEvent evento) {
        habilitarInterface(false);
        limparTela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        atualizarLista();
    }
}
