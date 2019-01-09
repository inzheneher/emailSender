package email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void main(String[] args) {

        Properties properties = System.getProperties();
        properties.put(PROPERTY_STARTTLS_KEY, PROPERTY_TRUE_VALUE);
        properties.put(PROPERTY_PORT_KEY, PORT);
        properties.put(PROPERTY_AUTH_KEY, PROPERTY_TRUE_VALUE);

        Session session = Session.getDefaultInstance(properties, null);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            message.setSubject(MESSAGE_SUBJECT);
            message.setText(MESSAGE_TEXT);

            Transport transport = session.getTransport(TRANSPORT_PROTOCOL);
            transport.connect(HOST, FROM, PASS);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            log.debug(LOG_MESSAGE_SUCCESS);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static final Logger log = LogManager.getLogger(SendEmail.class);

    private static final String TO = "";
    private static final String FROM = "";

    private static final String PASS = "";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    private static final String PROPERTY_STARTTLS_KEY = "mail.smtp.starttls.enable";
    private static final String PROPERTY_PORT_KEY = "mail.smtp.port";
    private static final String PROPERTY_AUTH_KEY = "mail.smtp.auth";

    private static final String PROPERTY_TRUE_VALUE = "true";

    private static final String MESSAGE_SUBJECT = "This is my first test e-mail from program";
    private static final String MESSAGE_TEXT = "Ok, this is exactly what I want to say!";

    private static final String TRANSPORT_PROTOCOL = "smtp";

    private static final String LOG_MESSAGE_SUCCESS = "Sent message successfully...";

}
