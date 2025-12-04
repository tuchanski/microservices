package dev.tuchanski.ms.email.mapper;

import dev.tuchanski.ms.email.dto.EmailDTO;
import dev.tuchanski.ms.email.model.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public Email toEntity(EmailDTO dto) {
        Email email = new Email();
        email.setEmailTo(dto.emailTo());
        email.setUserId(dto.userId());
        email.setText(dto.text());
        email.setSubject(dto.subject());
        return email;
    }

    public EmailDTO toDTO(Email email) {
        return new EmailDTO(
                email.getUserId(),
                email.getEmailTo(),
                email.getSubject(),
                email.getText()
        );
    }

}
