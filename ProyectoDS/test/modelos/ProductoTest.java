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
     * Test of getNombreArticulo method, of class Producto.
     */
    @Test
    public void testGetNombreArticulo() {
        System.out.println("getNombreArticulo");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getNombreArticulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombreArticulo method, of class Producto.
     */
    @Test
    public void testSetNombreArticulo() {
        System.out.println("setNombreArticulo");
        String nombreArticulo = "";
        Producto instance = new Producto();
        instance.setNombreArticulo(nombreArticulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescripcion method, of class Producto.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescripcion method, of class Producto.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String descripcion = "";
        Producto instance = new Producto();
        instance.setDescripcion(descripcion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoria method, of class Producto.
     */
    @Test
    public void testGetCategoria() {
        System.out.println("getCategoria");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getCategoria();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoria method, of class Producto.
     */
    @Test
    public void testSetCategoria() {
        System.out.println("setCategoria");
        String categoria = "";
        Producto instance = new Producto();
        instance.setCategoria(categoria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecio method, of class Producto.
     */
    @Test
    public void testGetPrecio() {
        System.out.println("getPrecio");
        Producto instance = new Producto();
        float expResult = 0.0F;
        float result = instance.getPrecio();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrecio method, of class Producto.
     */
    @Test
    public void testSetPrecio() {
        System.out.println("setPrecio");
        float precio = 0.0F;
        Producto instance = new Producto();
        instance.setPrecio(precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiempoEntrega method, of class Producto.
     */
    @Test
    public void testGetTiempoEntrega() {
        System.out.println("getTiempoEntrega");
        Producto instance = new Producto();
        Date expResult = null;
        Date result = instance.getTiempoEntrega();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTiempoEntrega method, of class Producto.
     */
    @Test
    public void testSetTiempoEntrega() {
        System.out.println("setTiempoEntrega");
        Date tiempoEntrega = null;
        Producto instance = new Producto();
        instance.setTiempoEntrega(tiempoEntrega);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCalificaciones method, of class Producto.
     */
    @Test
    public void testGetCalificaciones() {
        System.out.println("getCalificaciones");
        Producto instance = new Producto();
        List<CalificacionProducto> expResult = null;
        List<CalificacionProducto> result = instance.getCalificaciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCalificaciones method, of class Producto.
     */
    @Test
    public void testSetCalificaciones() {
        System.out.println("setCalificaciones");
        List<CalificacionProducto> calificaciones = null;
        Producto instance = new Producto();
        instance.setCalificaciones(calificaciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDisponible method, of class Producto.
     */
    @Test
    public void testIsDisponible() {
        System.out.println("isDisponible");
        Producto instance = new Producto();
        boolean expResult = false;
        boolean result = instance.isDisponible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDisponible method, of class Producto.
     */
    @Test
    public void testSetDisponible() {
        System.out.println("setDisponible");
        boolean disponible = false;
        Producto instance = new Producto();
        instance.setDisponible(disponible);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEliminado method, of class Producto.
     */
    @Test
    public void testIsEliminado() {
        System.out.println("isEliminado");
        Producto instance = new Producto();
        boolean expResult = false;
        boolean result = instance.isEliminado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEliminado method, of class Producto.
     */
    @Test
    public void testSetEliminado() {
        System.out.println("setEliminado");
        boolean eliminado = false;
        Producto instance = new Producto();
        instance.setEliminado(eliminado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVendedor method, of class Producto.
     */
    @Test
    public void testGetVendedor() {
        System.out.println("getVendedor");
        Producto instance = new Producto();
        Vendedor expResult = null;
        Vendedor result = instance.getVendedor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVendedor method, of class Producto.
     */
    @Test
    public void testSetVendedor() {
        System.out.println("setVendedor");
        Vendedor vendedor = null;
        Producto instance = new Producto();
        instance.setVendedor(vendedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumVistas method, of class Producto.
     */
    @Test
    public void testGetNumVistas() {
        System.out.println("getNumVistas");
        Producto instance = new Producto();
        int expResult = 0;
        int result = instance.getNumVistas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumVistas method, of class Producto.
     */
    @Test
    public void testSetNumVistas() {
        System.out.println("setNumVistas");
        int numVistas = 0;
        Producto instance = new Producto();
        instance.setNumVistas(numVistas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaCreacion method, of class Producto.
     */
    @Test
    public void testGetFechaCreacion() {
        System.out.println("getFechaCreacion");
        Producto instance = new Producto();
        Date expResult = null;
        Date result = instance.getFechaCreacion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFechaCreacion method, of class Producto.
     */
    @Test
    public void testSetFechaCreacion() {
        System.out.println("setFechaCreacion");
        Date fechaCreacion = null;
        Producto instance = new Producto();
        instance.setFechaCreacion(fechaCreacion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStock method, of class Producto.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        Producto instance = new Producto();
        int expResult = 0;
        int result = instance.getStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStock method, of class Producto.
     */
    @Test
    public void testSetStock() {
        System.out.println("setStock");
        int stock = 0;
        Producto instance = new Producto();
        instance.setStock(stock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Producto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Producto instance = new Producto();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Producto.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Producto instance = new Producto();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Producto.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.toString();
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
    
}
