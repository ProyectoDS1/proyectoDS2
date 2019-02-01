/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.VentanaAdministracionController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import utils.ConexionSql;

/**
 *
 * @author User-PC
 */
@Entity
public class Vendedor extends Comprador {

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    protected List<Producto> misArticulos;
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    protected List<CalificacionVendedor> calificaciones;

    public List<Producto> mostrarArticulos() {
        return this.misArticulos;
    }

    public void agregarRating(int rate) {
        // TODO Not implemented yet
    }

    public float getPromedioCalificaciones() {
        if (calificaciones.isEmpty()) {
            return -1;
        }

        float promedio = 0;
        for (CalificacionVendedor c : calificaciones) {
            promedio += c.getEstrellas();
        }

        return promedio / this.calificaciones.size();
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (Vendedor)";
    }

    public static Vendedor transferir(Usuario u) {
        if (u instanceof Vendedor) {
            // Usuario ya es vendedor
            return (Vendedor) u;
        }

        // Si no, no era un Vendedor
        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(u); // Eliminar de la base de datos
        ConexionSql.getConexion().endTransaction();

        // Crear nuevo vendedor y pasarle todos los datos del usuario eliminado
        Vendedor nuevo = (Vendedor)u;
        /*
                new Vendedor();
        nuevo.setNombre(u.getNombre());
        nuevo.setApellido(u.getApellido());
        nuevo.setEmail(u.getEmail());
        nuevo.setCedula(u.getCedula());
        nuevo.setMatricula(u.getMatricula());
        nuevo.setTelefono(u.getTelefono());
        nuevo.setContrasenia(u.getContrasenia());
        nuevo.setDireccion(u.getDireccion());
        nuevo.setActivo(u.isActivo());
        */
        em = ConexionSql.getConexion().beginTransaction();
        if ((u instanceof Comprador) && !(u instanceof Vendedor)) { // Si el antiguo era un comprador no vendedor, preservar sus pedidos y sus calificaciones
            nuevo.setMisCalificaciones(((Comprador) u).getCalificaciones());
            for (Calificacion c : nuevo.getCalificaciones()) {
                c.setAutor(nuevo);
                em.persist(c);
            }
            nuevo.setPedidos(((Comprador) u).mostrarPedidos());
            //System.out.println(nuevo.mostrarPedidos());
            Logger.getLogger(VentanaAdministracionController.class.getName()).log(Level.SEVERE,nuevo.mostrarPedidos().toString());

            for (Pedido p : nuevo.mostrarPedidos()) {
                p.setComprador(nuevo);
                em.persist(p);
            }
        }

        em.persist(nuevo);
        ConexionSql.getConexion().endTransaction();

        return nuevo;
    }

}
