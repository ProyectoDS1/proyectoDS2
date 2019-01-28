/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sendgrid.*;
import java.io.IOException;

/**
 *
 * @author reyes
 */
public final class EmailSender {

    public static final void sendEmail(String from, String subject, String to, String content) {
        //Email from = new Email("test@example.com");
        //String subject = "Sending with SendGrid is Fun";
        //Email to = new Email("test@example.com");
        //Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(new Email(from), subject, new Email(to), new Content("text/plain", content));

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final boolean emailValido(String email) {
        return email.contains("@") && !email.contains("@example.com");
    }
}
