/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indefensos.controladores.mail;

/**
 *
 * @author David
 */
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.NewsAddress;

public class Mailer {

    public static void send(String para, String sujeto) throws UnsupportedEncodingException {

        final String user = "correofixedup@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "fixedup2018";

        String mensaje = "<<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \"></h1>" + "";

        String nuevoMensaje = "<h1 style=\"font-size: 20px; color:#0C0; font-weight: bold; text-transform: uppercase ; \">Actividad Creada" + "</h1>" + "<img src='https://sites.google.com/site/fundacionfei/_/rsrc/1470269597406/home/fei3.jpg?height=200&width=176'/ style=\"float: left;\"><p> <br>\n"
                + "<p style=\"text-align: center; color: #307EDF\">\n"
                + "</p> \n"
                + "<br>\n"
                + "<p style=\"color:#0C0;font-weight: bold;\" > Este Seguimiento Ayudara a tu Familiar. </p> ";
//1st paso) Obtener el objeto de sesión

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            //Archivos adjuntos
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");
//BodyPart adjunto = new MimeBodyPart();
//adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/cartagena.txt")));
//adjunto.setFileName("cartagena.txt");

            MimeMultipart multiparte = new MimeMultipart();
            multiparte.addBodyPart(texto);
//multiparte.addBodyPart(adjunto);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new NewsAddress(para));
            message.setSubject(sujeto);
            message.setContent(multiparte, "text/html; charset=utf-8");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
}
