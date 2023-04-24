package vttp.csf.finalproject.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import vttp.csf.finalproject.server.Models.EmailDets;

    @Service
    public class EmailService {
        @Autowired 
        private JavaMailSender javaMailSender;
        
        @Value("${spring.mail.username}") private String sender;
     
        // To send a simple email
        public String sendSimpleMail(EmailDets details)
        {
     
            // Try block to check for exceptions
            try {
     
                // Creating a simple mail message
                SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
     
                System.out.println(details.toString());
                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(details.getRecipient());
                mailMessage.setText(details.getMsgBody());
                mailMessage.setSubject(details.getSubject());
                System.out.println("mailMessage: ");
    
                System.out.println(mailMessage);
     
                // Sending the mail
                javaMailSender.send(mailMessage);
                return "Mail Sent Successfully...";
            }
     
            // Catch block to handle the exceptions
            catch (Exception e) {
                return e.getMessage();
    //            return "Error while Sending Mail";
            }
        }
    
}
