package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailHandler {
    void sendMail(){
       Properties sysProperties= System.getProperties();

       System.out.print(sysProperties);

       sysProperties.put("mail.smtp.host",MailMetaData.HostServer);
       sysProperties.put("mail.smtp.port",MailMetaData.port);
       sysProperties.put(MailMetaData.sslproperty,"true");
       sysProperties.put(MailMetaData.authPerm,"true");

       //creating session

        Authenticator mailAuthenticator=new CustomizedMailAuthentication();
        Session mailSession= Session.getInstance(sysProperties,mailAuthenticator);

        MimeMessage mailMessage=new MimeMessage(mailSession);

        try {
            mailMessage.setFrom(MailMetaData.myUserMail);
            mailMessage.setSubject("this.just a java testing mail");
            mailMessage.setText("hii i am vikash saini just sending mail via java code");

            Address receiverEmail = new InternetAddress(MailMetaData.receiverMail);
            mailMessage.setRecipient(Message.RecipientType.TO, receiverEmail);

            System.out.println("code success");
            Transport.send(mailMessage);

        }
        catch(Exception mailException){
            System.out.println(mailException.toString());
        }

    }
}
