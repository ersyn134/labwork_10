package com.example.labwork2.controller;

import com.example.labwork2.dto.EmailRequest;
import com.example.labwork2.dto.MassEmailRequest;
import com.example.labwork2.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
public class EmailController {

    // private final EmailService emailService;

    @Autowired
    private EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Отправка простого текстового письма
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/send-simple-email")
    public ResponseEntity<String> sendSimpleEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendSimpleEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.ok("Simple email sent successfully");
    }

    // Отправка HTML письма
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/send-html-email")
    public ResponseEntity<String> sendHtmlEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendHtmlEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.ok("HTML email sent successfully");
    }

    // Отправка письма с вложением
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<String> sendEmailWithAttachment(
            @ModelAttribute EmailRequest emailRequest,
            @RequestParam("attachment") MultipartFile attachment) {
        // В данном случае можно принимать attachment через @RequestParam,
        // а остальные поля через @ModelAttribute
        emailRequest.setAttachment(attachment);
        emailService.sendEmailWithAttachment(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody(), attachment);
        return ResponseEntity.ok("Email with attachment sent successfully");
    }
      @PostMapping("/send-mass-email")
    public ResponseEntity<String> sendMassEmail(@RequestBody MassEmailRequest req) {
        // Конвертируем List<String> в String[]
        String[] toArray = req.getEmails().toArray(new String[0]);
        emailService.sendSimpleEmail(toArray, req.getSubject(), req.getBody());
        return ResponseEntity.ok("Mass email sent successfully to " + req.getEmails().size() + " recipients");
    }
}
