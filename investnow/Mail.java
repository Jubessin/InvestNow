package com.example.android.investnow;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

public class Mail extends javax.mail.Authenticator {
    private String mailhost = "smtp.gmail.com";
    private String user;
    private String password;
    private Session session;

    static {
        Security.addProvider(new com.example.android.investnow.JSSEProvider());
    }

    public Mail(String user, String password) {
        this.user = user;
        this.password = password;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
        try{
            MimeMessage message = new MimeMessage(session);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(handler);
            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            Transport.send(message);
        }catch(Exception e){

        }
    }

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}
//public class Mail extends javax.mail.Authenticator {
////    private String mailhost = "smtp.gmail.com";
////    private String user = "A";
////    private String password = "A";
////    private Session session;
////
//////    static {
//////        Security.addProvider(new org.apache.harmony.xnet.provider.jsse.JSSEProvider());
//////    }
////
////    public Mail(String user, String password) {
////        this.user = user;
////        this.password = password;
////
////        Properties props = new Properties();
////        props.setProperty("mail.transport.protocol", "smtp");
////        props.setProperty("mail.host", mailhost);
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.port", "888");
////        props.put("mail.smtp.socketFactory.port", "888");
////        //465
////        props.put("mail.smtp.socketFactory.class",
////                "javax.net.ssl.SSLSocketFactory");
////        props.put("mail.smtp.socketFactory.fallback", "false");
////        props.setProperty("mail.smtp.quitwait", "false");
////        props.put("mail.smtp.connectiontimeout", "t1");
////        props.put("mail.smtp.timeout", "t2");
////        session = Session.getInstance(props, this);
////    }
////
////    protected PasswordAuthentication getPasswordAuthentication() {
////        return new PasswordAuthentication(user, password);
////    }
////
////    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
////        MimeMessage message = new MimeMessage(session);
////        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
////        message.setSender(new InternetAddress(sender));
////        message.setSubject(subject);
////        message.setDataHandler(handler);
////        if (recipients.indexOf(',') > 0)
////            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
////        else
////            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
////        Transport.send(message);
////    }
////
////    public class ByteArrayDataSource implements DataSource {
////        private byte[] data;
////        private String type;
////
////        public ByteArrayDataSource(byte[] data, String type) {
////            super();
////            this.data = data;
////            this.type = type;
////        }
////
////        public ByteArrayDataSource(byte[] data) {
////            super();
////            this.data = data;
////        }
////
////        public void setType(String type) {
////            this.type = type;
////        }
////
////        public String getContentType() {
////            if (type == null)
////                return "application/octet-stream";
////            else
////                return type;
////        }
////
////        public InputStream getInputStream() throws IOException {
////            return new ByteArrayInputStream(data);
////        }
////
////        public String getName() {
////            return "ByteArrayDataSource";
////        }
////
////        public OutputStream getOutputStream() throws IOException {
////            throw new IOException("Not Supported");
////        }
////    }
////}