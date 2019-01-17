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
public class NotificadorCelular implements Notificador {

    private static NotificadorCelular notificador;

    private NotificadorCelular() {
    }

    public static NotificadorCelular getNotificador() {
        if (notificador == null) {
            notificador = new NotificadorCelular();
        }
        return notificador;
    }

    @Override
    public void notificarPedido(Pedido pedido) {
        System.out.println("***SMS***");
        System.out.println("To: " + pedido.getArticulo().getVendedor().getTelefono());
        System.out.println("Contenido: El cliente "
                + pedido.getComprador().getNombre() + " " + pedido.getComprador().getApellido()
                + " ha pedido " + pedido.getNumeroItems()
                + " unidades del producto " + pedido.getArticulo().getNombreArticulo());
        System.out.println("***END SMS***");
    }

}
