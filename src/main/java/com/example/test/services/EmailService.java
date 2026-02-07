package com.example.test.services;
import com.example.test.util.constants.email.EmailDetails;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(EmailDetails details) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(details.getRecipient());
        message.setSubject(details.getSubject());
        message.setText(details.getMessageBody());

        mailSender.send(message);
    }
    public void sendOrderMail(String customerEmail, Long orderId) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customerEmail);
        message.setSubject("Siparişiniz Alındı ✔");
        message.setText(
                "Siparişiniz başarıyla alınmıştır.\n" +
                        "Sipariş Numaranız: " + orderId + "\n" +
                        "Sipariş durumunu 'Hesabım -> Siparişlerim' sayfasından takip edebilirsiniz."
        );

        mailSender.send(message);
    }

}
