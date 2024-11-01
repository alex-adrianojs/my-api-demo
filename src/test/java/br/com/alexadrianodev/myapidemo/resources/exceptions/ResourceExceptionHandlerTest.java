package br.com.alexadrianodev.myapidemo.resources.exceptions;

import br.com.alexadrianodev.myapidemo.services.exceptions.DataIntegratyViolatedException;
import br.com.alexadrianodev.myapidemo.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String THIS_EMAIL_IS_ALREADY_REGISTERED = "This email is already registered";
    @InjectMocks
    ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException(USER_NOT_FOUND),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(USER_NOT_FOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void dataIntegrityViolationException() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolated(
                        new DataIntegratyViolatedException(THIS_EMAIL_IS_ALREADY_REGISTERED),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(THIS_EMAIL_IS_ALREADY_REGISTERED, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}