package br.com.alexadrianodev.myapidemo.resources;

import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.domain.dto.UserDTO;
import br.com.alexadrianodev.myapidemo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    public static final int ID          = 1;
    public static final String NAME     = "Alex Adriano";
    public static final String EMAIL    = "alex.adriano@gmail.com";
    public static final String PASSWORD = "100492";
    private User userEntity;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSucess() {
        when(service.findById(anyInt())).thenReturn(userEntity);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NAME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void wheFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn(List.of(userEntity));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().get(0).getClass());

        Assertions.assertEquals(ID, response.getBody().get(0).getId());
        Assertions.assertEquals(NAME, response.getBody().get(0).getName());
        Assertions.assertEquals(EMAIL, response.getBody().get(0).getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().get(0).getPassword());


    }

    @Test
    void whenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(userEntity);
        ResponseEntity<UserDTO> response = resource.create(userDTO);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        userEntity = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

    }
}