module br.edu.femass.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;

    opens br.edu.femass.biblioteca.gui to javafx.fxml;
    exports br.edu.femass.biblioteca;
    opens br.edu.femass.biblioteca to javafx.fxml;
}