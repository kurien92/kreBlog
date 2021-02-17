package net.kurien.blog.module.mail.service.impl;

import net.kurien.blog.module.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class BasicMailService implements MailService {
    private final JavaMailSender mailSender;

    @Autowired
    public BasicMailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text) throws MessagingException {
        mailSend(to, subject, text);
    }

    public void send(String from, List<String> toList, String subject, String text) throws MessagingException {
        String to = String.join(",", toList);

        mailSend(to, subject, text);
    }

    private void mailSend(String to, String subject, String text) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        msg.setSubject(subject);
        msg.setText(text);
        msg.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));

        mailSender.send(msg);
    }
}
