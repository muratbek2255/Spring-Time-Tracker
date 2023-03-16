package com.example.confirmationtoken;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String to, String email) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("${spring.mail.username}");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e){
            LOGGER.error("Fail to send email" + e);
            throw new IllegalStateException("failed to send email");
        }

    }
}
