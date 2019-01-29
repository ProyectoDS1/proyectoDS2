/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Pedido;

/**
 *
 * @author User-PC
 */
public class NotificadorEmail implements Notificador {

    private static NotificadorEmail notificador = null;

    private NotificadorEmail() {
    }

    public static NotificadorEmail getNotificador() {
        if (notificador == null) {
            notificador = new NotificadorEmail();
        }
        return notificador;
    }

    @Override
    public void notificarPedido(Pedido pedido) {
        final String email = pedido.getArticulo().getVendedor().getEmail();
        if (!EmailSender.emailValido(email)) {
            //System.out.println("***EMAIL***");
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE,"***EMAIL***");
            //System.out.println("From: notificaciones@poliventas.com");
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE,"From: notificaciones@poliventas.com");
            //System.out.println("To: " + email);
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, "To: {0}", email);
            //System.out.println("Subject: Pedido nuevo!");
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE,"Subject: Pedido nuevo!");
            /*
            System.out.println("Contenido: El cliente "
                    + pedido.getComprador().getNombre() + " " + pedido.getComprador().getApellido()
                    + " ha pedido " + pedido.getNumeroItems()
                    + " unidades del producto " + pedido.getArticulo().getNombreArticulo());
            */
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, "Contenido: El cliente {0} {1} ha pedido {2} unidades del producto {3}", new Object[]{pedido.getComprador().getNombre(), pedido.getComprador().getApellido(), pedido.getNumeroItems(), pedido.getArticulo().getNombreArticulo()});
            //System.out.println("***END EMAIL***");
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE,"***END EMAIL***");
            return;
        }

        EmailSender.sendEmail("notificaciones@poliventas.com",
                "Pedido nuevo!",
                email,
                "El cliente " + pedido.getComprador().getNombre() + " "
                + pedido.getComprador().getApellido() + " ha pedido "
                + pedido.getNumeroItems() + " unidades del producto "
                + pedido.getArticulo().getNombreArticulo());
    }

}
