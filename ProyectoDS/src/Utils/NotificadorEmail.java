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
        System.out.println("***EMAIL***");
        System.out.println("To: " + pedido.getArticulo().getVendedor().getEmail());
        System.out.println("Asunto: Pedido nuevo!");
        System.out.println("Contenido: El cliente "
                + pedido.getComprador().getNombre() + " " + pedido.getComprador().getApellido()
                + " ha pedido " + pedido.getNumeroItems()
                + " unidades del producto " + pedido.getArticulo().getNombreArticulo());
        System.out.println("***END EMAIL***");
    }

}
