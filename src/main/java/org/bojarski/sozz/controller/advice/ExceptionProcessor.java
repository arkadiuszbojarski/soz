package org.bojarski.sozz.controller.advice;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.bojarski.sozz.messages.Messages;
import org.bojarski.sozz.model.exception.AlreadyExistsException;
import org.bojarski.sozz.model.exception.NotFoundException;
import org.bojarski.sozz.model.exception.UsedException;
import org.bojarski.sozz.model.exception.WrongPasswordException;
import org.bojarski.sozz.model.response.error.ErrorInfo;
import org.bojarski.sozz.model.response.error.FieldErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Klasa procesora wyjątków. Obiekt klasy zwraca do klienta sformatowane komunikaty o wyjątkach jakie mogły wystąpić na skutek przetwarzania zapytań.
 * @author Arkadiusz Bojarski
 *
 */
@ControllerAdvice
public class ExceptionProcessor {

    /**
     * Metoda obsługująca wyjątki związane z podaniem wartości nie spełniających ograniczeń walidacji.
     * @param exception {@link MethodArgumentNotValidException} rzucany w przypadku złamania ograniczeń walidacji.
     * @return {@link ResponseEntity} zawierająca kolekcję {@link FieldErrorInfo} oraz {@link HttpStatus} oznaczający nieprawidłowe zapytanie.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> processValidationError(MethodArgumentNotValidException exception) {
        
        BindingResult result = exception.getBindingResult();
        ArrayList<FieldErrorInfo> errorInfos = new ArrayList<>();
        result.getFieldErrors().forEach((error) -> {
            errorInfos.add(new FieldErrorInfo(error.getField(), error.getCode(), error.getDefaultMessage()));
        });
        
        return new ResponseEntity<Collection<?>>(errorInfos, HttpStatus.BAD_REQUEST);
    }

    /**
     * Metoda obsługująca wyjątek rzucany w przypadku próby odwołania do nieistniejącego zasobu.
     * @param request {@link HttpServletRequest} które zawierało odwołanie do nieistniejącego zasobu.
     * @param exception {@link NotFoundException} rzucany w przypadku próby odwołania do nieistniejącego zasobu.
     * @return odpowiedź zawierająca {@link ErrorInfo} przechowującą podstawowe informację dotyczące wyjątku oraz status oznaczający próbę odwołania do nieistniejącego zasobu.
     *  
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleNotFoundException(HttpServletRequest request,
            NotFoundException exception) {
        return new ResponseEntity<ErrorInfo>(new ErrorInfo(exception.getCode(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
    
    /**
     * Metoda obsługująca wyjątek rzucany w przypadku próby utworzenia zasobu nie spełniającego ograniczeń unikalności.
     * @param request zapytanie które zawierało polecenie utworzenia zasobu nie spełniającego ograniczeń unikalności. 
     * @param exception {@link AlreadyExistsException} rzucany w przypadku próby utworzenia zasobu nie spełniającego ograniczeń unikalności.
     * @return odpowiedź zawierająca informacje o polu które nie spełniało ograniczeń unikalności oraz status oznaczający nieprawidłowe zapytanie.
     */
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<?> handleAlreadyExistsException(HttpServletRequest request,
            AlreadyExistsException exception) {

     // Informacja o wyjątku jest przekazywana w jedno-elementowej kolekcji aby odzwierciedlać odpowiedź serwera w przypadku złamania ograniczeń walidacji.
        ArrayList<FieldErrorInfo> errorInfos = new ArrayList<>();

        errorInfos.add(new FieldErrorInfo(exception.getField(), exception.getCode(), exception.getMessage()));

        return new ResponseEntity<Collection<?>>(errorInfos, HttpStatus.BAD_REQUEST);
    }

    /**
     * Metoda obsługująca wyjątek rzucany w przypadku próby usunięcia zasobu który jest wykorzystywany przez inny zasób.
     * @param request zapytanie zawierające polecenie usunięcia zasobu który jest wykorzystywany przez inny zasób.
     * @param exception {@link UsedException} rzucany w przypadku próby usunięcia zasobu używanego przez inny zasób.
     * @return odpowiedź zawierająca podstawowe informacje dotyczące wyjątku oraz status oznaczający konflikt.
     */
    @ExceptionHandler(UsedException.class)
    @ResponseBody
    public ResponseEntity<?> handleUsedException(HttpServletRequest request,
            UsedException exception) {
        return new ResponseEntity<ErrorInfo>(new ErrorInfo(exception.getCode(), exception.getMessage()), HttpStatus.CONFLICT);
    }
    
    /**
     * Metoda obsługująca wyjątek rzucany w przypadku podanie nieprawidłowego hasła.
     * @param request zapytanie zawierające nieprawidłowe hasło.
     * @param exception {@link WrongPasswordException} rzucany w przypadku próby podanie nieprawidłowego hasła.
     * @return odpowiedź zawierająca kolekcję podstawowych informacji o wyjątku oraz status oznaczający nieprawidłowe zapytanie.
     */
    @ExceptionHandler(WrongPasswordException.class)
    @ResponseBody
    public ResponseEntity<?> handleWrongPasswordException(HttpServletRequest request,
            WrongPasswordException exception) {
        
        // Informacja o wyjątku jest przekazywana w jedno-elementowej kolekcji aby odzwierciedlać odpowiedź serwera w przypadku złamania ograniczeń walidacji. 
        ArrayList<FieldErrorInfo> errorInfos = new ArrayList<>();

        errorInfos.add(new FieldErrorInfo(Messages.PASSWORD, exception.getCode(), exception.getMessage()));
        
        return new ResponseEntity<Collection<?>>(errorInfos, HttpStatus.BAD_REQUEST);
    }

}
