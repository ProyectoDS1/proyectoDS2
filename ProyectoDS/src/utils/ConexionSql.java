/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author User-PC
 */
public class ConexionSql {

    private static ConexionSql conexion = null;
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tr;

    private ConexionSql() {
        emf = Persistence.createEntityManagerFactory("ProyectoDSPU");
        em = emf.createEntityManager();
        tr = em.getTransaction();
    }

    public static ConexionSql getConexion() {
        if (conexion == null) {
            conexion = new ConexionSql();
        }

        return conexion;
    }

    public EntityManager beginTransaction() {
        tr.begin();
        return em;
    }

    public void endTransaction() {
        tr.commit();
    }
}
