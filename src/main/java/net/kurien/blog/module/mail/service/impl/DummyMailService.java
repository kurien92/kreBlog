package net.kurien.blog.module.mail.service.impl;

import net.kurien.blog.module.mail.service.MailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyMailService implements MailService {
    public void send(String from, String to, String title, String content) {
        System.out.println("DummyMailService from : " + from + " to : " + to + " title : " + title + " : " + content);
    }

    public void send(String from, List<String> toList, String title, String content) {
        System.out.println("DummyMailService from : " + from + " toList : " + toList + " title : " + title + " : " + content);
    }
}
