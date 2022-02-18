package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.EmailDto;
import com.example.projectAnimalFree.dto.Mail;
import com.example.projectAnimalFree.entity.Email;

import com.example.projectAnimalFree.mapper.EmailMapper;

import com.example.projectAnimalFree.repository.EmailRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SendServiceImpl  implements SendService{



    private final EmailSenderService emailSenderService;

    private final EmailRepository emailRepository;


    public SendServiceImpl(EmailSenderService emailSenderService, EmailRepository emailRepository) {
        this.emailSenderService = emailSenderService;
        this.emailRepository = emailRepository;
    }

    @Override
    public void send(EmailDto emailDto) throws MessagingException {
        Mail mail = new Mail();
        mail.setSubject(emailDto.getText());
        mail.setMailTo(emailDto.getEmail());
        mail.setFrom("y.panas.3@gmail.com");
        emailSenderService.sendEmail(mail,emailDto);
    }

    @Override
    public EmailDto createEmail(EmailDto emailDto) {
        Email email = EmailMapper.toEntity(emailDto);
        email = emailRepository.save(email);
        emailDto = EmailMapper.toDto(email);
        return emailDto;
    }
}


