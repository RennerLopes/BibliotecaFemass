package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.AlunoDao;
import br.edu.femass.biblioteca.dao.CopiaDao;
import br.edu.femass.biblioteca.dao.EmprestimoDao;
import br.edu.femass.biblioteca.dao.ProfessorDao;
import br.edu.femass.biblioteca.model.Aluno;
import br.edu.femass.biblioteca.model.Copia;
import br.edu.femass.biblioteca.model.Emprestimo;
import br.edu.femass.biblioteca.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.*;

public class EmprestimoController implements Initializable {
    private EmprestimoDao emprestimoDao = new EmprestimoDao();
    private AlunoDao alunoDao = new AlunoDao();
    private ProfessorDao professorDao = new ProfessorDao();
    private CopiaDao copiaDao = new CopiaDao();

    @FXML
    private Button BtnGravarEmprestimo;

    @FXML
    private Button BtnExcluirEmprestimo;

    @FXML
    private Button BtnIncluirEmprestimo;

    @FXML
    private Button BtnCancelarEmprestimo;

    @FXML
    private ListView<Emprestimo> LstEmprestimos;


    @FXML
    private ComboBox<String> CboTipoUsuario;

    @FXML
    private TextField TxtMatriculaUsuario;

    @FXML
    private TextField TxtCodigoCopiaLivro;

    private void limparTela() {
        TxtMatriculaUsuario.setText("");
        TxtCodigoCopiaLivro.setText("");
        CboTipoUsuario.getSelectionModel().select(null);
    }

    private void habilitarInterface(boolean incluir) {
        BtnGravarEmprestimo.setDisable(!incluir);
        BtnCancelarEmprestimo.setDisable(!incluir);
        TxtMatriculaUsuario.setDisable(!incluir);
        TxtCodigoCopiaLivro.setDisable(!incluir);
        CboTipoUsuario.setDisable(!incluir);
        LstEmprestimos.setDisable(incluir);
    }

    private void exibirEmprestimo() {
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();
        TxtMatriculaUsuario.setText(emprestimo.getMatriculaUsuario().toString());
        TxtCodigoCopiaLivro.setText(emprestimo.getCodigoCopiaLivro().toString());
    }

    private void atualizarLista() {
        List<Emprestimo> emprestimos = null;

        try {
            emprestimos = emprestimoDao.listar();
        } catch(Exception e) {
            emprestimos = new ArrayList<Emprestimo>();
        }

        ObservableList<Emprestimo> emprestimosOb = FXCollections.observableArrayList(emprestimos);
        LstEmprestimos.setItems(emprestimosOb);

    }

    @FXML
    private void LstEmprestimos_MouseClicked(MouseEvent evento) {
        exibirEmprestimo();
    }

    @FXML
    private void LstEmprestimos_KeyPressed(KeyEvent evento) {
        exibirEmprestimo();
    }

    @FXML
    private void BtnIncluirEmprestimo_Action(ActionEvent evento) {
        habilitarInterface(true);
        TxtMatriculaUsuario.requestFocus();
        limparTela();
    }

    @FXML
    private void BtnExcluirEmprestimo_Action(ActionEvent evento) {
        Emprestimo emprestimo = LstEmprestimos.getSelectionModel().getSelectedItem();

        if(emprestimo == null) return;

        try {
            emprestimoDao.excluir(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();
    }

    @FXML
    private void BtnGravarEmprestimo_Action(ActionEvent evento) {
        List<Emprestimo> emprestimos = null;
        List<Copia> copias = null;
        Boolean possuiUsuario = false;
        Boolean possuiCopia = false;
        Integer qntEmprestimos = 0;

        Date dt = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        if(CboTipoUsuario.getSelectionModel().isSelected(0)) {
            List<Aluno> alunos = null;

            try {
                alunos = alunoDao.listar();
            } catch(Exception e) {
                alunos = new ArrayList<Aluno>();
            }

            for(Aluno al : alunos) {
                if(al.getMatricular() == Integer.parseInt(TxtMatriculaUsuario.getText())) {
                    c.add(Calendar.DATE, al.getPrazoDevolucaoEmDias());
                    possuiUsuario = true;
                }
            }



        } else if(CboTipoUsuario.getSelectionModel().isSelected(1)) {
            List<Professor> professores = null;


            try {
                professores = professorDao.listar();
            } catch(Exception e) {
                professores = new ArrayList<Professor>();
            }

            for(Professor pr : professores) {
                if(pr.getMatricular() == Integer.parseInt(TxtMatriculaUsuario.getText())) {
                    c.add(Calendar.DATE, pr.getPrazoDevolucaoEmDias());
                    possuiUsuario = true;
                }
            }
        }

        try {
            copias = copiaDao.listar();
        } catch(Exception e) {
            copias = new ArrayList<Copia>();
        }

        for(Copia cp : copias) {
            if(cp.getCodigo() == Integer.parseInt(TxtCodigoCopiaLivro.getText()) && cp.getFixo() != true) {
                possuiCopia = true;
            }
        }

        if(!possuiUsuario) {
            ErrorDialog("Usuário Não encontrado!");
            return;
        }

        if(!possuiCopia) {
            ErrorDialog("Cópia de livro Não encontrado!");
            return;
        }


        try {
            emprestimos = emprestimoDao.listar();
        } catch(Exception e) {
            emprestimos = new ArrayList<Emprestimo>();
        }

        for(Emprestimo emp : emprestimos) {
            if(emp.getCodigoCopiaLivro() == Integer.parseInt(TxtCodigoCopiaLivro.getText()) && dt.before(emp.getDataDevolucao())) {
                ErrorDialog("Cópia já está emprestada!");
                return;
            }

            if(emp.getMatriculaUsuario() == Integer.parseInt(TxtMatriculaUsuario.getText()) && dt.before(emp.getDataDevolucao())) {
                qntEmprestimos ++;
            }
        }

        if(qntEmprestimos == 5) {
            ErrorDialog("Usuário já possui 5 emprestimos de livros!");
            return;
        }



        Emprestimo emprestimo = new Emprestimo(
                dt,
                c.getTime(),
                Integer.parseInt(TxtMatriculaUsuario.getText()),
                Integer.parseInt(TxtCodigoCopiaLivro.getText()));

        try {
            emprestimoDao.gravar(emprestimo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

        habilitarInterface(false);

        return;

    }

    private void ErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void BtnCancelarEmprestimo_Action(ActionEvent evento) {
        habilitarInterface(false);
        limparTela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CboTipoUsuario.getItems().removeAll(CboTipoUsuario.getItems());
        CboTipoUsuario.getItems().addAll("Aluno", "Professor");
        CboTipoUsuario.getSelectionModel().select(null);
    }
}
