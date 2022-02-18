package com.example.projectAnimalFree.service;

import com.example.projectAnimalFree.dto.EmailDto;
import com.example.projectAnimalFree.dto.Mail;
import com.example.projectAnimalFree.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {



    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
//    @Async
    public void sendEmail(Mail mail, EmailDto emailDto) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
//        helper.addAttachment("template-cover.png", new ClassPathResource("javabydeveloper-email.PNG"));
//        Context context = new Context();
//        context.setVariable("message","sakldnaskjdkjasldn" );
////        context.setVariables(mail.getProps());
//        String html = templateEngine.process("m", context);

        helper.setTo(mail.getMailTo());
//        helper.setText(html, true);
       // helper.setText("sdsadsada");
       helper.setText(emailDto.getText());

        //helper.setSubject(mail.getSubject());
        helper.setSubject("AnimalFree Team");
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
}


