package net.kurien.blog.module.mail.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

public interface MailService {
    void send(String from, String to, String title, String content) throws MessagingException;
    void send(String from, List<String> toList, String title, String content) throws MessagingException;
}
