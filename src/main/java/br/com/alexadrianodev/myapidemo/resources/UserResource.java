package br.com.alexadrianodev.myapidemo.resources;

import br.com.alexadrianodev.myapidemo.domain.User;
import br.com.alexadrianodev.myapidemo.domain.dto.UserDTO;
import br.com.alexadrianodev.myapidemo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));

    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map( x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public  ResponseEntity<UserDTO> create(@RequestBody UserDTO user){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(service.create(user)
                .getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO user){
        user.setId(id);
        return  ResponseEntity.ok().body(mapper.map(service.update(user), UserDTO.class));
    }
}
