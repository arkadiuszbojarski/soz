package org.bojarski.sozz.service.authorization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.authentication.AuthenticationToken;
import org.bojarski.sozz.model.domain.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TokenHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHandler.class);
    private static final String HMAC_ALGORITHM = "HmacSHA512";
    private static final String SEPARATOR = ".";
    private static final String SEPARATOR_SPLITTER = "\\.";
    private static final long DAY = 1000 * 60 * 60 * 24;  
    
    private final Mac hmac;
    
    @Autowired
    public TokenHandler(@Value("${token.secret}") byte[] secretKey) {
        try {
            hmac = Mac.getInstance(HMAC_ALGORITHM);
            hmac.init(new SecretKeySpec(secretKey, HMAC_ALGORITHM));
        } catch (NoSuchAlgorithmException | InvalidKeyException exception) {
            throw new IllegalStateException(Messages.HMAC_INIT_FAIL + ": " + exception);
        }
    }
    
    public Account parseAccountFromAuthenticationToken(String token) {
        final String[] parts = token.split(SEPARATOR_SPLITTER);
        if(parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
            try {
                final byte[] tokenBytes = fromBase64(parts[0]);
                final byte[] hash = fromBase64(parts[1]);
                
                boolean validHash = Arrays.equals(createHmac(tokenBytes), hash);
                if(validHash) {
                    final AuthenticationToken authenticationToken = fromJSON(tokenBytes);
                    if(new Date().getTime() < authenticationToken.getExpires()) {
                        return authenticationToken.getAccount();
                    }
                }
            } catch (IllegalArgumentException exception) {
                LOGGER.warn(Messages.SUSPICIOUS_TOKEN_AUTHORIZATION_ATTEMPT);
            }
        }
        return null;
    }
    
    public String createAuthenticationTokenForAccount(Account account) {
        AuthenticationToken token = new AuthenticationToken(UUID.randomUUID(), account, System.currentTimeMillis() + DAY);
        byte[] tokenBytes = toJSON(token);
        byte[] tokenHash = createHmac(tokenBytes);
                
        final StringBuilder builder = new StringBuilder(170);
        builder.append(toBase64(tokenBytes));
        builder.append(SEPARATOR);
        builder.append(toBase64(tokenHash));
        return builder.toString();
    }
        
    private byte[] toJSON(AuthenticationToken token) {
        try {
            return new ObjectMapper().writeValueAsBytes(token);                    
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(exception);
        }
    }
        
    private AuthenticationToken fromJSON(final byte[] bytes) {
        try {
            return new ObjectMapper().readValue(new ByteArrayInputStream(bytes), AuthenticationToken.class);
        } catch (IOException exception) {
            throw new IllegalStateException(exception);
        }
    }
    
    
    private String toBase64(byte[] content) {
        return DatatypeConverter.printBase64Binary(content);
    }
    
    private byte[] fromBase64(String content) {
        return DatatypeConverter.parseBase64Binary(content);
    }
    
    private synchronized byte[] createHmac(byte[] content) {
        return hmac.doFinal(content);
    }
    
}
