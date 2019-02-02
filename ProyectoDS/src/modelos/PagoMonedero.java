/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;

/**
 *
 * @author User-PC
 */
@Entity
public class PagoMonedero extends MetodoPago {

    protected String nombreCliente;
    protected String celular;

    public PagoMonedero() {
    }

    public PagoMonedero(String nombreCliente, String celular, Pedido pedido) {
        super(pedido);
        this.nombreCliente = nombreCliente;
        this.celular = celular;
    }

    @Override
    public boolean confirmar() {
        try {
            URL url = new URL("http://ec2-54-233-186-248.sa-east-1.compute.amazonaws.com:8000/check?phone="
                    + this.celular + "&value=" + String.format("%.2f", this.pedido.getNumeroItems() * this.pedido.getArticulo().getPrecio()));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            Logger.getLogger(PagoMonedero.class.getName()).log(Level.SEVERE, String.valueOf(status));

            return status == 200;
        } catch (IOException ex) {
            Logger.getLogger(PagoMonedero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
