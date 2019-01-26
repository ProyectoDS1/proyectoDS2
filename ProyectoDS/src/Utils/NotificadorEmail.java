/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Modelos.Pedido;

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
            System.out.println("***EMAIL***");
            System.out.println("From: notificaciones@poliventas.com");
            System.out.println("To: " + email);
            System.out.println("Subject: Pedido nuevo!");
            System.out.println("Contenido: El cliente "
                    + pedido.getComprador().getNombre() + " " + pedido.getComprador().getApellido()
                    + " ha pedido " + pedido.getNumeroItems()
                    + " unidades del producto " + pedido.getArticulo().getNombreArticulo());
            System.out.println("***END EMAIL***");
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
