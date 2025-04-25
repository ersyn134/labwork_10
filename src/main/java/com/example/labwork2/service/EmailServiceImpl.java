package com.example.labwork2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendSimpleEmail(String[] to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (MailException ex) {
            // Логирование ошибки, выбрасывание или обработка в соответствии с требованиями
            throw ex;
        }
    }

    @Override
    public void sendHtmlEmail(String[] to, String subject, String htmlBody) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = HTML
            mailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            // Логирование ошибки, выбрасывание или обработка в соответствии с требованиями
            throw new RuntimeException("Failed to send HTML email", ex);
        }
    }

    @Override
    public void sendEmailWithAttachment(String[] to, String subject, String body, MultipartFile attachment) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // true - multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            // Прикрепляем файл (используем оригинальное имя файла)
            helper.addAttachment(attachment.getOriginalFilename(), new ByteArrayResource(attachment.getBytes()));
            mailSender.send(mimeMessage);
        } catch (MessagingException | java.io.IOException ex) {
            // Логируем и пробрасываем исключение
            throw new RuntimeException("Failed to send email with attachment", ex);
        }
    }
}
