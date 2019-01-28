/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sendgrid.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author reyes
 */
public final class EmailSender {
       
    private EmailSender() {
    }

    public static final void sendEmail(String from, String subject, String to, String content){
        Mail mail = new Mail(new Email(from), subject, new Email(to), new Content("text/plain", content));
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.err.println(response.getStatusCode());
            System.err.println(response.getBody());
            System.err.println(response.getHeaders());
        } catch (IOException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final boolean emailValido(String email) {
        return email.contains("@") && !email.contains("@example.com");
    }
}
