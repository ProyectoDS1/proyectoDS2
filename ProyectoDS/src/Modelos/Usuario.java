/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author User-PC
 */
public abstract class Usuario {
    protected String email;
    protected String contrasenia;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected boolean whatsapp;
    protected String direccion;
    protected String matricula;
    protected boolean activo;
    private Usuario usuarioActual;
    
    public void iniciarSesion(){
        
    }
    
    public void cerrarSesion(){
    }
    
    public Usuario getUsuarioActual(){
      return this.usuarioActual;
    }
    
    public void setUsuarioActual(Usuario user){
      this.usuarioActual = user;
    }
    
}
