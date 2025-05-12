package Registro;

import Modelo.*;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class NotificadorEmail {
    private final String usuario;
    private final String contrasena;
    private final String host;
    private final String puerto;

    public NotificadorEmail(String usuario, String contrasena) {
        this(usuario, contrasena, "smtp.office365.com", "587");
    }

    public NotificadorEmail(String usuario, String contrasena, String host, String puerto) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.host = host;
        this.puerto = puerto;
    }

    /**
     * Envía un correo plano.
     * @param destinatario Email de destino (no nulo, no vacío)
     * @param asunto Asunto del correo
     * @param cuerpo Cuerpo del mensaje
     * @return true si se envió correctamente, false en caso de error
     */
    public boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
        if (destinatario == null || destinatario.isEmpty()) {
            System.err.println("Email de destinatario vacío.");
            return false;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", puerto);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contrasena);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);
            Transport.send(mensaje);
            System.out.println("Correo enviado a: " + destinatario);
            return true;
        } catch (MessagingException e) {
            System.err.println("Error al enviar correo a: " + destinatario);
            e.printStackTrace();
            return false;
        }
    }
}