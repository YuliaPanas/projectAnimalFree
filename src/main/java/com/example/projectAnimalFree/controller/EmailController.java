package com.example.projectAnimalFree.controller;

import com.example.projectAnimalFree.dto.EmailDto;
import com.example.projectAnimalFree.exception.BadRequestException;
import com.example.projectAnimalFree.service.EmailSenderService;
import com.example.projectAnimalFree.service.SendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
public class EmailController {

    private final EmailSenderService emailSenderService;
    private final SendService sendService;

    public EmailController(EmailSenderService emailSenderService, SendService sendService) {
        this.emailSenderService = emailSenderService;
        this.sendService = sendService;
    }

    @GetMapping("/email")
    public String getEmailPage(){
        return "emailSend";

    }

    @PostMapping("/email")
    public String registerUser(@ModelAttribute("email") EmailDto emailDto, Model model) throws MessagingException {
        try{
            EmailDto email = sendService.createEmail(emailDto);
            sendService.send(emailDto);
            model.addAttribute("email" ,email);
            System.out.println("bfrvhj");
            return "/home";
        }
        catch (BadRequestException e){
            model.addAttribute("errorMessage",e.getMessage());
        }
        return "errorPage";

    }

}
