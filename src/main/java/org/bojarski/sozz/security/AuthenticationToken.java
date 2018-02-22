package org.bojarski.sozz.security;

import java.util.UUID;

import org.bojarski.sozz.model.Account;

/**
 * Klasa token uwierzytelniania.
 * @author Arkadiusz Bojarski
 *
 */
public class AuthenticationToken {
    
    private UUID uuid;

    private Account account;
    
    private Long expires;
    
    @SuppressWarnings("unused")
    private AuthenticationToken(){
        
    }
    
    /**
     * Konstruktor zapamiętujący podane parametry.
     * @param uuid {@link UUID} stanowiący unikalny numer token'a.
     * @param account {@link Account} dla którego jest tworzony token uwierzytelniania.
     * @param expires liczba stanowiąca znacznik czasu w jakim token uwierzytelniania wygaśnie.
     */
    public AuthenticationToken(UUID uuid, Account account, Long expires) {
        this.uuid = uuid;
        this.account = account;
        this.expires = expires;
    }

    /**
     * Metoda zwracająca numer uuid.
     * @return numer uuid.
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Metoda zwracająca konto użytkownika.
     * @return konto użytkownika.
     */
    public Account getAccount() {
        return this.account;
    }

    /**
     * Metoda zwracająca numer oznaczający czas w jakim token wygaśnie.
     * @return czas wygaśnięcia token'a.
     */
    public Long getExpires() {
        return this.expires;
    }

}
