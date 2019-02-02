/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremy GS
 */
public class ProductoTest {

    public ProductoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of nombreArticulo methods, of class Producto.
     */
    @Test
    public void testNombreArticulo() {
        System.out.println("setNombreArticulo");
        String nombreArticulo = "";
        Producto instance = new Producto();
        instance.setNombreArticulo(nombreArticulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of descripcion methods, of class Producto.
     */
    @Test
    public void testDescripcion() {
        System.out.println("setDescripcion");
        String descripcion = "";
        Producto instance = new Producto();
        instance.setDescripcion(descripcion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of categoria methods, of class Producto.
     */
    @Test
    public void testCategoria() {
        System.out.println("setCategoria");
        String categoria = "";
        Producto instance = new Producto();
        instance.setCategoria(categoria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of precio methods, of class Producto.
     */
    @Test
    public void testPrecio() {
        System.out.println("setPrecio");
        float precio = 0.0F;
        Producto instance = new Producto();
        instance.setPrecio(precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tiempoEntrega methods, of class Producto.
     */
    @Test
    public void testTiempoEntrega() {
        System.out.println("setTiempoEntrega");
        Date tiempoEntrega = null;
        Producto instance = new Producto();
        instance.setTiempoEntrega(tiempoEntrega);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calificaciones methods, of class Producto.
     */
    @Test
    public void testCalificaciones() {
        System.out.println("setCalificaciones");
        List<CalificacionProducto> calificaciones = null;
        Producto instance = new Producto();
        instance.setCalificaciones(calificaciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disponible methods, of class Producto.
     */
    @Test
    public void testDisponible() {
        System.out.println("setDisponible");
        boolean disponible = false;
        Producto instance = new Producto();
        instance.setDisponible(disponible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminado methods, of class Producto.
     */
    @Test
    public void testEliminado() {
        System.out.println("setEliminado");
        boolean eliminado = false;
        Producto instance = new Producto();
        instance.setEliminado(eliminado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vendedor methods, of class Producto.
     */
    @Test
    public void testVendedor() {
        System.out.println("setVendedor");
        Vendedor vendedor = null;
        Producto instance = new Producto();
        instance.setVendedor(vendedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numVistas methods, of class Producto.
     */
    @Test
    public void testNumVistas() {
        System.out.println("getNumVistas");
        Producto instance = new Producto();
        int expResult = 0;
        int result = instance.getNumVistas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fechaCreacion methods, of class Producto.
     */
    @Test
    public void testFechaCreacion() {
        System.out.println("getFechaCreacion");
        Producto instance = new Producto();
        Date expResult = null;
        Date result = instance.getFechaCreacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stock methods, of class Producto.
     */
    @Test
    public void testStock() {
        System.out.println("getStock");
        Producto instance = new Producto();
        int expResult = 0;
        int result = instance.getStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPromedioCalificaciones method, of class Producto.
     */
    @Test
    public void testGetPromedioCalificaciones() {
        System.out.println("getPromedioCalificaciones");
        Producto instance = new Producto();
        float expResult = 0.0F;
        float result = instance.getPromedioCalificaciones();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscado method, of class Producto.
     */
    @Test
    public void testBuscado() {
        System.out.println("buscado");
        Producto instance = new Producto();
        instance.buscado();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiempoEntregaTexto method, of class Producto.
     */
    @Test
    public void testGetTiempoEntregaTexto() {
        System.out.println("getTiempoEntregaTexto");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getTiempoEntregaTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPromedioCalificacionesTexto method, of class Producto.
     */
    @Test
    public void testGetPromedioCalificacionesTexto() {
        System.out.println("getPromedioCalificacionesTexto");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getPromedioCalificacionesTexto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
