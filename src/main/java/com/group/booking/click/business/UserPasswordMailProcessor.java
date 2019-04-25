package com.group.booking.click.business;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordMailProcessor {
	  
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
 
	public String sendMail(String email, String password) {
        try {
            sendEmail(email, password);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
 
    private void sendEmail(String emailId, String password) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
         
        helper.setTo("ajithraj.in@gmail.com");
        String k = processBody("");
        helper.setText("Your passowrd is reseted to " +password);
        
        helper.setSubject("Boogiee.com : Password Reset");
      
        try {
        	sender.send(message);
        } catch (Exception e) {
        	e.printStackTrace();
			System.out.println("llll");
		}
    }

}
