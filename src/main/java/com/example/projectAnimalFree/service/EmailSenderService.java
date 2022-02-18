package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.EmailDto;
import com.example.projectAnimalFree.dto.Mail;

import javax.mail.MessagingException;

public interface EmailSenderService {

    void sendEmail(Mail mail, EmailDto emailDto) throws MessagingException;



}
