package com.example.projectAnimalFree.mapper;

import com.example.projectAnimalFree.dto.EmailDto;
import com.example.projectAnimalFree.entity.Email;


public class EmailMapper {

    public static Email toEntity(EmailDto emailDto) {
        Email email = new Email();
        email.setId(emailDto.getId());
        email.setEmail(emailDto.getEmail());
        email.setText(emailDto.getText());
        email.setLocalDate(emailDto.getLocalDate());
        return email;

    }

    public static EmailDto toDto(Email email) {
        EmailDto emailDto = new EmailDto();
        emailDto.setId(email.getId());
        emailDto.setEmail(email.getEmail());
        emailDto.setText(email.getText());
        emailDto.setLocalDate(email.getLocalDate());
        return emailDto;
    }
}
