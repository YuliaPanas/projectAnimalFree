package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.EmailDto;

import javax.mail.MessagingException;

public interface SendService {

     void send(EmailDto emailDto ) throws MessagingException;

     EmailDto createEmail(EmailDto emailDto);


}
