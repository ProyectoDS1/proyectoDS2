/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author reyes
 */
public class CompradorTest {

    private Comprador target;

    public CompradorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        target = new Comprador();
        target.setPedidos(new LinkedList<>());
        target.misCalificaciones = new LinkedList<>();

        Usuario.setUsuarioActual(target);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of comprar method, of class Comprador.
     */
    @Test
    public void testComprar() {
        Producto producto = new Producto();
        MetodoPago mp = new PagoEfectivo("nadie", null);

        Pedido pedido = target.comprar(producto, 1, mp);

        assertEquals(pedido.getEstado(), EstadoPedido.PENDIENTE);
        assertEquals(pedido.getComprador(), target);
        assertEquals(pedido.getArticulo(), producto);
        assertEquals(pedido.getNumeroItems(), 1);
    }

    /**
     * Test of mostrarPedidos method, of class Comprador.
     */
    @Test
    public void testMostrarPedidos() {
        assertTrue(target.mostrarPedidos().isEmpty());

        target.mostrarPedidos().add(new Pedido());

        assertEquals(target.mostrarPedidos().size(), 1);

        target.mostrarPedidos().clear();

        assertTrue(target.mostrarPedidos().isEmpty());
    }

    /**
     * Test of haCalificado method, of class Comprador.
     */
    @Test
    public void testHaCalificado_Producto() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();

        Calificacion cp = new CalificacionProducto(p1, 1, target);
        target.getCalificaciones().add(cp);

        assertTrue(target.haCalificado(p1));
        assertFalse(target.haCalificado(p2));
    }

    /**
     * Test of haCalificado method, of class Comprador.
     */
    @Test
    public void testHaCalificado_Vendedor() {
        Vendedor v1 = new Vendedor();
        Vendedor v2 = new Vendedor();

        Calificacion cv = new CalificacionVendedor(v1, 1, target);
        target.getCalificaciones().add(cv);

        assertTrue(target.haCalificado(v1));
        assertFalse(target.haCalificado(v2));
    }

    /**
     * Test of getCalificaciones method, of class Comprador.
     */
    @Test
    public void testGetCalificaciones() {
        Vendedor v1 = new Vendedor();
        Calificacion cv = new CalificacionVendedor(v1, 1, target);
        Producto p1 = new Producto();
        Calificacion cp = new CalificacionProducto(p1, 1, target);

        assertTrue(target.getCalificaciones().isEmpty());

        target.getCalificaciones().add(cp);
        target.getCalificaciones().add(cv);

        assertEquals(target.getCalificaciones().size(), 2);
        assertEquals(target.getCalificaciones().get(0), cp);
    }

}
