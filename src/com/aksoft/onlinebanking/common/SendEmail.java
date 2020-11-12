package com.aksoft.onlinebanking.common;

import java.io.IOException;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aksoft.onlinebanking.dto.Email;


@SuppressWarnings("serial")
public class SendEmail extends HttpServlet {
	
	
	public String processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
                   throws IOException, ServletException {
	 	Email email =new Email();
	 	HttpSession pasSec = request.getSession();
	 	pasSec.setMaxInactiveInterval(60*60);
		 email=(Email) pasSec.getAttribute("sendEmail");
        String from = email.getFrom();
        String to = email.getTo();
        String subject = email.getSubject();
        String message = email.getMessage();
    	String type= email.getType();
    	System.out.println("sending mail to "+to);
    	String login = email.getLogin();
        String password =email.getPassword();
        String result ="";
        try {
            Properties props = new Properties();
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");
            Authenticator auth = new SMTPAuthenticator(from, password);
            Session sessionS = Session.getInstance(props,auth);
            MimeMessage msg = new MimeMessage(sessionS);
            msg.setContent(message, "text/html");
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);     
            result =  "success";

        } catch (AuthenticationFailedException ex) {
        	  result =  "error";
        	ex.printStackTrace();
        } catch (AddressException ex) {
        	result =  "error";
        	ex.printStackTrace();
        } catch (MessagingException ex) {
        	result =  "error";
        	ex.printStackTrace();
        }
		return result;
    }

    private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }

   
}