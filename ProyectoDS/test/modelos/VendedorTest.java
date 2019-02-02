/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author leone
 */
public class VendedorTest {
    private Vendedor vendedor;
    
    public VendedorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        vendedor = new Vendedor();
        vendedor.setPedidos(new LinkedList<>());
        vendedor.calificaciones = new LinkedList<>();
        vendedor.misPedidos = new LinkedList<>();
        vendedor.misArticulos=new LinkedList<>();
        vendedor.misCalificaciones=new LinkedList<>();

        Usuario.setUsuarioActual(vendedor);
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testVentasPendientes(){
        Pedido pedido1=new Pedido();
        pedido1.setEstado(EstadoPedido.ENTREGADO);
        
        Pedido pedido2=new Pedido();
        pedido2.setEstado(EstadoPedido.PENDIENTE);
        
        Pedido pedido3=new Pedido();
        pedido3.setEstado(EstadoPedido.PENDIENTE);
        
        Pedido pedido4=new Pedido();
        pedido4.setEstado(EstadoPedido.ANULADO);
        
        Pedido pedido5=new Pedido();
        pedido5.setEstado(EstadoPedido.PENDIENTE);
        List<Pedido> pedido_all=new LinkedList<>();
        pedido_all.add(pedido1);
        pedido_all.add(pedido2);
        pedido_all.add(pedido3);
        pedido_all.add(pedido4);
        pedido_all.add(pedido5);
        vendedor.setMisPedidos(pedido_all);
        assertEquals(vendedor.totalProductosPendientes(),3,0);
    }
    
    /*
    Test of comprar but now how Vendedor
     */       
    @Test
    public void testComprar() {
        Producto producto = new Producto();
        MetodoPago mp = new PagoEfectivo("ninguno", null);

        Pedido pedido = vendedor.comprar(producto, 1, mp);

        assertEquals(pedido.getEstado(), EstadoPedido.PENDIENTE);
        assertEquals(pedido.getComprador(), vendedor);
        assertEquals(pedido.getArticulo(), producto);
        assertEquals(pedido.getNumeroItems(), 1);
    }
    
    
    /**
     * Test of mostrarPedidos method, of class Vendedor.
     */
    @Test
    public void testMostrarPedidos() {
        assertTrue(vendedor.mostrarPedidos().isEmpty());

        vendedor.mostrarPedidos().add(new Pedido());

        assertEquals(vendedor.mostrarPedidos().size(), 1);

        vendedor.mostrarPedidos().clear();

        assertTrue(vendedor.mostrarPedidos().isEmpty());
    }

    /**
     * Test of haCalificado method, of class Vendedor.
     */
    @Test
    public void testHaCalificado_Producto() {
        Producto p1 = new Producto();
        Producto p2 = new Producto();

        Calificacion cp = new CalificacionProducto(p1, 1, vendedor);
        vendedor.getCalificaciones().add(cp);

        assertTrue(vendedor.haCalificado(p1));
        assertFalse(vendedor.haCalificado(p2));
    }

    /**
     * Test of haCalificado method, of class Vendedor.
     * and calculate your mean
     */
    @Test
    public void testHaCalificado_Vendedor_y_Promedio() {
        Vendedor v1 = new Vendedor();
        Vendedor v2 = new Vendedor();
        Calificacion cv = new CalificacionVendedor(v1, 4, vendedor);
        CalificacionVendedor cV=new CalificacionVendedor(vendedor,4,new Comprador()); 
        CalificacionVendedor cV1=new CalificacionVendedor(vendedor,4,new Comprador()); 
        vendedor.getCalificaciones().add(cv);
        vendedor.getCalificacionesV().add(cV);
        vendedor.getCalificacionesV().add(cV1);
        
        assertTrue(vendedor.haCalificado(v1));
        assertFalse(vendedor.haCalificado(v2));
        assertEquals(vendedor.getPromedioCalificaciones(),4f,0.0);
        
    }
    

    /**
     * Test of getCalificaciones method, of class Vendedor.
     */
    @Test
    public void testGetCalificaciones() {
        Vendedor v1 = new Vendedor();
        Calificacion cv = new CalificacionVendedor(v1, 4, vendedor);
        Producto p1 = new Producto();
        Calificacion cp = new CalificacionProducto(p1, 4, vendedor);

        assertTrue(vendedor.getCalificaciones().isEmpty());

        vendedor.getCalificaciones().add(cp);
        vendedor.getCalificaciones().add(cv);

        assertEquals(vendedor.getCalificaciones().size(), 2);
        assertEquals(vendedor.getCalificaciones().get(0), cp);
    }
}
