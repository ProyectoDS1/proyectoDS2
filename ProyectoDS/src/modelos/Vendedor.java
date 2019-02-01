/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


import java.util.List;
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

    public static Vendedor transferir(Usuario usuario) {
        if (usuario instanceof Vendedor) {
            return (Vendedor) usuario;
        }

        EntityManager em = ConexionSql.getConexion().beginTransaction();
        em.remove(usuario);
        ConexionSql.getConexion().endTransaction();

        Vendedor nuevoVendedor = new Vendedor();
        nuevoVendedor.setNombre(usuario.getNombre());
        nuevoVendedor.setApellido(usuario.getApellido());
        nuevoVendedor.setEmail(usuario.getEmail());
        nuevoVendedor.setCedula(usuario.getCedula());
        nuevoVendedor.setMatricula(usuario.getMatricula());
        nuevoVendedor.setTelefono(usuario.getTelefono());
        nuevoVendedor.setContrasenia(usuario.getContrasenia());
        nuevoVendedor.setDireccion(usuario.getDireccion());
        nuevoVendedor.setActivo(usuario.isActivo());
        em = ConexionSql.getConexion().beginTransaction();
        if ((usuario instanceof Comprador) && !(usuario instanceof Vendedor)) { 
            nuevoVendedor.setMisCalificaciones(((Comprador) usuario).getCalificaciones());
            for (Calificacion c : nuevoVendedor.getCalificaciones()) {
                c.setAutor(nuevoVendedor);
                em.persist(c);
            }

            nuevoVendedor.setPedidos(((Comprador) usuario).mostrarPedidos());
            System.out.println(nuevoVendedor.mostrarPedidos());
            for (Pedido p : nuevoVendedor.mostrarPedidos()) {
                p.setComprador(nuevoVendedor);
                em.persist(p);
            }
        }

        em.persist(nuevoVendedor);
        ConexionSql.getConexion().endTransaction();

        return nuevoVendedor;
    }

}
