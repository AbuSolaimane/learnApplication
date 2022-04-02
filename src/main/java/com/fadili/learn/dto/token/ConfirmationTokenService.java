package com.fadili.learn.dto.token;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken) {

        return this.confirmationTokenRepository.save(confirmationToken);
    }

}
