package pl.mirek.ksb2.moviecollector.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.mirek.ksb2.moviecollector.model.Email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Mukuljaiswal
 */
@Service
public class MailService {

    /*
     * The Spring Framework provides an easy abstraction for sending email by using
     * the JavaMailSender interface, and Spring Boot provides auto-configuration for
     * it as well as a starter module.
     */
    private JavaMailSender javaMailSender;

    /**
     * @param javaMailSender
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * This function is used to send mail without attachment.
     *
     * @param email
     * @throws MailException
     */

    public void sendEmail(Email email) throws MailException {

        // Wydzielam watek, bo zbyt to dlugo trwa, niech se chodzi w tle

        Thread thread = new Thread(() -> {
            /*
             * This JavaMailSender Interface is used to send Mail in Spring Boot. This
             * JavaMailSender extends the MailSender Interface which contains send()
             * function. SimpleMailMessage Object is required because send() function uses
             * object of SimpleMailMessage as a Parameter
             */

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email.getEmailAddress());
            mail.setSubject(email.getSubject());
            mail.setText(email.getContent());

            /*
             * This send() contains an Object of SimpleMailMessage as an Parameter
             */
            javaMailSender.send(mail);

        });
        thread.start();
    }

    /**
     * This fucntion is used to send mail that contains a attachment.
     *
     * @param email
     * @throws MailException
     * @throws MessagingException
     */
    public void sendEmailWithAttachment(Email email) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(email.getEmailAddress());
        helper.setSubject("Testing Mail API with Attachment");
        helper.setText("Please find the attached document below.");

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(mimeMessage);
    }

}