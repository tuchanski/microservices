package dev.tuchanski.ms.email.service.impl;

import dev.tuchanski.ms.email.dto.EmailDTO;
import dev.tuchanski.ms.email.mapper.EmailMapper;
import dev.tuchanski.ms.email.model.Email;
import dev.tuchanski.ms.email.model.enums.EmailStatus;
import dev.tuchanski.ms.email.repository.EmailRepository;
import dev.tuchanski.ms.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    private final EmailMapper emailMapper;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    @Transactional
    public EmailDTO sendEmail(EmailDTO emailDTO) {

        Email email = emailMapper.toEntity(emailDTO);
        try {
            email.setCreatedAt(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);

            email.setEmailStatus(EmailStatus.SENT);
        } catch (MailException e) {
            email.setEmailStatus(EmailStatus.ERROR);
        } finally {
            email = emailRepository.save(email);
        }

        return emailMapper.toDTO(email);
    }
}
