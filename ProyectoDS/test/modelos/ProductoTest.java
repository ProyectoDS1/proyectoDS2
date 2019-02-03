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
 * @author Jeremy GS
 */
public class ProductoTest {
    
    private Producto producto; 
    
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
        producto=new Producto();
        producto.calificaciones=new LinkedList<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of nombreArticulo methods, of class Producto.
     */
    @Test
    public void testNombreArticulo() {
       producto.setNombreArticulo("Mica");
       assertSame(producto.getNombreArticulo(),"Mica");
    }

    /**
     * Test of categoria methods, of class Producto.
     */
    @Test
    public void testCategoria() {
        producto.setCategoria("Accesorios celulares");
        assertSame(producto.getCategoria(),"Accesorios celulares");
    }

    /**
     * Test of precio methods, of class Producto.
     */
    @Test
    public void testPrecio() {
        producto.setPrecio(15);
        assertEquals(producto.getPrecio(),(float) 15.0f,0.00);
    }

    /**
     * Test of disponible methods, of class Producto.
     */
    @Test
    public void testDisponible() {
        assertFalse(producto.isDisponible());
        producto.setDisponible(true);
        assertTrue(producto.isDisponible());
    }

    /**
     * Test of eliminado methods, of class Producto.
     */
    @Test
    public void testEliminado() {
        assertFalse(producto.isEliminado());
        producto.setEliminado(true);
        assertTrue(producto.isEliminado());
    }

    /**
     * Test of vendedor methods, of class Producto.
     */
    @Test
    public void testVendedor() {
        assertNull(producto.getVendedor());
        Vendedor vendedor1=new Vendedor();
        producto.setVendedor(vendedor1);
        assertNotNull(producto.getVendedor());
    }

    /**
     * Test of getPromedioCalificaciones method, of class Producto.
     */
    @Test
    public void testGetPromedioCalificaciones() {
        assertTrue(producto.getCalificaciones().isEmpty());
        Comprador comprador=new Comprador();
        Comprador comprador2=new Comprador();
        CalificacionProducto calificacion1=new CalificacionProducto(producto,4,comprador);
        CalificacionProducto calificacion2=new CalificacionProducto(producto,5,comprador2);
        producto.getCalificaciones().add(calificacion1);
        producto.getCalificaciones().add(calificacion2);
        assertFalse(producto.getCalificaciones().isEmpty());
        assertEquals(producto.getPromedioCalificaciones(),4.5f,0.00);
    }

    /**
     * Test of buscado method, of class Producto.
     */
    @Test
    public void testBuscado() {
        assertEquals(producto.getNumVistas(),0);
        producto.buscado();
        assertNotEquals(producto.getNumVistas(),0);
        assertEquals(producto.getNumVistas(),1);
    }

    /**
     * Test of getPromedioCalificacionesTexto method, of class Producto.
     */
    @Test
    public void testGetPromedioCalificacionesTexto() {      
        assertSame(producto.getPromedioCalificacionesTexto(),"Este producto no tiene calificaciones");
        Comprador comprador=new Comprador();
        Comprador comprador2=new Comprador();
        CalificacionProducto calificacion1=new CalificacionProducto(producto,3,comprador);
        CalificacionProducto calificacion2=new CalificacionProducto(producto,4,comprador2);
        producto.getCalificaciones().add(calificacion1);
        producto.getCalificaciones().add(calificacion2);
        assertEquals(producto.getPromedioCalificacionesTexto(),"3.5/5");
    }

}
