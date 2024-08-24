package com.projeto.APIBooks.DataTransferObjects;

import org.springframework.beans.factory.annotation.Value;

public record DTOSendMail(

        @Value("spring.mail.username")
        String email
) {
}
