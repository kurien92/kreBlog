package net.kurien.blog.module.mail.service;

import java.util.List;

public interface MailService {
    void send(String from, String to, String title, String content);
    void send(String from, List<String> toList, String title, String content);
}
