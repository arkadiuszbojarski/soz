package org.bojarski.sozz.messages;

/**
 * Klasa zawierająca zbiór stałych wykorzystywanych w aplikacji.
 * 
 * @author Arkadiusz Bojarski
 *
 */
public class Messages {
    
    public static final String UNIT_USED_BY_PART = "error.used.unit.part";
    public static final String UNIT_USED_BY_REQUISITION = "error.used.unit.requisition";
    public static final String CATEGORY_USED_BY_PART = "error.used.category.part";
    public static final String SUPPLIER_USED_BY_PART = "error.used.supplier.part";
    public static final String DRAWING_USED_BY_REQUISITION = "error.used.drawing.requisition";
    public static final String PART_USED_BY_REQUISITION = "error.used.part.requisition";
    
    public static final String UNIT_NOT_FOUND = "error.no.unit.id";
    public static final String CATEGORY_NOT_FOUND = "error.no.category.id";
    public static final String SUPPLIER_NOT_FOUND = "error.no.supplier.id";
    public static final String PART_NOT_FOUND = "error.no.part.id";
    public static final String REQUISITION_NOT_FOUND = "error.no.requisition.id";
    public static final String DRAWING_NOT_FOUND = "error.no.drawing.id";
    public static final String ACCOUNT_NOT_FOUND = "error.no.account.id";
    
    public static final String UNIT_NOT_FOUND_DEFAULT = "unit not found";
    public static final String CATEGORY_NOT_FOUND_DEFAULT = "category not found";
    public static final String SUPPLIER_NOT_FOUND_DEFAULT = "supplier not found";
    public static final String PART_NOT_FOUND_DEFAULT = "part not found";
    public static final String REQUISITION_NOT_FOUND_DEFAULT = "requisition not found";
    public static final String DRAWING_NOT_FOUND_DEFAULT = "drawing not found";
    public static final String ACCOUNT_NOT_FOUND_DEFAULT = "account not found";
    
    public static final String UNIT_EXISTS = "fail.exists.unit.name";
    public static final String CATEGORY_EXISTS = "fail.exists.category.name";
    public static final String SUPPLIER_EXISTS = "fail.exists.supplier.name";
    public static final String DRAWING_EXISTS = "fail.exists.drawing.number";
    public static final String ACCOUNT_USERNAME_EXISTS = "fail.exists.account.username";
    public static final String ACCOUNT_EMAIL_EXISTS = "fail.exists.account.email";
    public static final String PART_EXISTS = "fail.exists.part.number";
    
    public static final String NAME = "name";
    public static final String NUMBER = "number";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "passwordOld";
    public static final String PASSWORD_REPEATED = "passwordRepeated";
    
    public static final String UNIT_EXISTS_DEFAULT = "unit name already exists";
    public static final String CATEGORY_EXISTS_DEFAULT = "category name already exists";
    public static final String SUPPLIER_EXISTS_DEFAULT = "supplier name already exists";
    public static final String DRAWING_EXISTS_DEFAULT = "drawing number already exists";
    public static final String ACCOUNT_EMAIL_EXISTS_DEFAULT = "account email already exists";
    public static final String ACCOUNT_USERNAME_EXISTS_DEFAULT = "account name already exists";
    public static final String PART_EXISTS_DEFAULT = "part number already exists";
    
    public static final String PASSWORDS_DO_NOT_MATCH = "NoMatch";
    public static final String WRONG_PASSWORD = "WrongPassword";
    
    public static final String PASSWORDS_DO_NOT_MATCH_DEFAULT = "passwords do not match";
    public static final String WRONG_PASSWORD_DEFAULT = "wrong password";
    
    public static final String HMAC_INIT_FAIL = "could not initialize HMAC";
    public static final String SUSPICIOUS_TOKEN_AUTHORIZATION_ATTEMPT = "suspicious token authorization attemp";
     
}
