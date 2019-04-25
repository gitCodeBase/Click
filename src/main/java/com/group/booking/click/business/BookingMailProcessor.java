package com.group.booking.click.business;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class BookingMailProcessor {
	  
	private String processBody(String toMail) { 
		  StringBuilder email = new StringBuilder();
		  email.append("<html><body>");
		  email.append("mmmmmmmmmmmmmmmmmmmmmmmm"); 
		  email.append("<br><br>");
		  email.append("</body></html>");
	  
		  return email.toString();
	}
	  
	
	@Autowired
    private JavaMailSender sender;
 
	public String sendMail(String toMail, String fileName) {
        try {
            sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
 
    private void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
         
        helper.setTo("ajithraj.in@gmail.com");
        String k = processBody("");
        helper.setText("How are you? " +k, true);
        
        helper.setSubject("Hi");
        
        FileSystemResource file = new FileSystemResource(new File("iTextHello.pdf"));
        helper.addAttachment("Invoice.pdf", file);
   
        try {
        	sender.send(message);
        } catch (Exception e) {
        	e.printStackTrace();
			System.out.println("llll");
		}
    }

}
