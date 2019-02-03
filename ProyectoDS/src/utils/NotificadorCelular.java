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
        Logger.getLogger(NotificadorCelular.class.getName()).log(Level.INFO, "***SMS***");
        Logger.getLogger(NotificadorCelular.class.getName()).log(Level.INFO, "To: {0}", pedido.getArticulo().getVendedor().getTelefono());
        Logger.getLogger(NotificadorCelular.class.getName()).log(Level.INFO, "Contenido: El cliente {0} {1} ha pedido {2} unidades del producto {3}", new Object[]{pedido.getComprador().getNombre(), pedido.getComprador().getApellido(), pedido.getNumeroItems(), pedido.getArticulo().getNombreArticulo()});
        Logger.getLogger(NotificadorCelular.class.getName()).log(Level.INFO, "*** END SMS***");
    }

}
