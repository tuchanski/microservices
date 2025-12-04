package dev.tuchanski.ms.email.service;

import dev.tuchanski.ms.email.dto.EmailDTO;
import dev.tuchanski.ms.email.model.Email;

public interface EmailService {
    EmailDTO sendEmail(EmailDTO emailDTO);
}
