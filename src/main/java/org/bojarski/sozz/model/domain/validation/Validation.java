package org.bojarski.sozz.model.domain.validation;

import javax.validation.groups.Default;

/**
 * Interfejs pomocniczy wykorzystywany przy
 * określaniu metod walidacji opartych o adnotacje.
 * @author Arkadiusz Bojarski
 *
 */
public interface Validation {
    
    public interface Extended extends Default {
        
    }
    
}
