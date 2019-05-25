/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.modelo;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Samuel
 */
public class Registro {

    private static final String ALFANUMERICO = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int LONGITUD_CODIGO = 6;

    private static final String ASUNTO = "Confirma tu cuenta";
    private static final String HOST = "smtp.gmail.com";
    private static final int PUERTO = 587;

    //private String clave = "!Q2w3e4r5t6y7u8i9o0p";
    public String enviarcorreo(String destinatario, String remitente, String clave) {
        StringBuilder codigo = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            codigo.append(ALFANUMERICO.charAt(r.nextInt(ALFANUMERICO.length())));
        }

        String mensaje = "Hola " + destinatario.split("@")[0] + "\n\n"
                + "Bienvenido a Knight Fight. Para activar tu cuenta y poder jugar\n"
                + "introduce el siguiente código en la casilla de verificación dentro de la aplicación:\n"
                + codigo.toString() + "\n\n"
                + "Esperamos que tu experiencia en el juego sea agradable,\n\n"
                + "Knight Fight Team";

        try {
            Properties prop = System.getProperties();
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.host", HOST);
            prop.put("mail.smtp.port", PUERTO);
            prop.put("mail.smtp.user", remitente);
            prop.put("mail.smtp.password", clave);

            Session session = Session.getDefaultInstance(prop, null);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(ASUNTO);
            message.setText(mensaje);

            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return codigo.toString();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
