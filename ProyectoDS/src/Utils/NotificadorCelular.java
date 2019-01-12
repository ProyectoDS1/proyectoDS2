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
public class NotificadorCelular implements Notificador{
    
    private NotificadorCelular notificador;
    
    private void notificarCelular(){
    
    }
    
    public NotificadorCelular getNotificador(){
      return this;
    }

    @Override
    public void notificarPedido(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
