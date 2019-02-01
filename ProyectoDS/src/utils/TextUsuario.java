/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author leone
 */
public class TextUsuario {
    protected final String comprador1="Comprador";
    protected final String vendedor1="Vendedor";
    protected final String administrador1="Administrador";
   
    @FXML
    protected TextField nombre;
    @FXML
    protected TextField apellido;
    @FXML
    protected TextField email;
    @FXML
    protected TextField telefono;
    @FXML
    protected TextField direccion;
    @FXML
    protected PasswordField contrasenia;
    @FXML
    protected TextField cedula;
    @FXML
    protected TextField matricula;

    public TextField getNombre() {
        return nombre;
    }

    public void setNombre(TextField nombre) {
        this.nombre = nombre;
    }

    public TextField getApellido() {
        return apellido;
    }

    public void setApellido(TextField apellido) {
        this.apellido = apellido;
    }

    public TextField getEmail() {
        return email;
    }

    public void setEmail(TextField email) {
        this.email = email;
    }

    public TextField getTelefono() {
        return telefono;
    }

    public void setTelefono(TextField telefono) {
        this.telefono = telefono;
    }

    public TextField getDireccion() {
        return direccion;
    }

    public void setDireccion(TextField direccion) {
        this.direccion = direccion;
    }

    public PasswordField getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(PasswordField contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TextField getCedula() {
        return cedula;
    }

    public void setCedula(TextField cedula) {
        this.cedula = cedula;
    }

    public TextField getMatricula() {
        return matricula;
    }

    public void setMatricula(TextField matricula) {
        this.matricula = matricula;
    }
    
    
}
