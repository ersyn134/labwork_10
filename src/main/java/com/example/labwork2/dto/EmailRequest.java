package com.example.labwork2.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmailRequest {

    private String[] to;
    private String subject;
    private String body;
    // Если в запросе должно передаваться вложение,
    // можно использовать MultipartFile (или отдельно задавать URL для файла)
    private MultipartFile attachment;

    // Геттеры и сеттеры

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }
}
