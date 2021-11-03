package com.livraria.usermicroservicepag.resource;

import com.livraria.usermicroservicepag.entities.User;
import com.livraria.usermicroservicepag.resource.dto.UserDto;
import com.livraria.usermicroservicepag.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UserResource {

    @Autowired
    public UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> users = service.getAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping(path ={"insert/"})
    public User insert(@RequestBody User user, @RequestHeader("userId") Long userId){
        return service.insert(user,userId);
    }

    @GetMapping(path = {"getId/{id}"})
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        UserDto optionalUser = service.getById(id);
        return ResponseEntity.ok().body(optionalUser);
    }

    @DeleteMapping(path ={"delete/{id}"})
    @Transactional
    public void delete(@PathVariable Long id, @RequestHeader("userId") Long userId){
        service.delete(id, userId);
    }

    @PutMapping(path ={"update/{id}"})
    @Transactional
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id,@RequestHeader("userId") Long userId)  {
        service.update(user,userId);
        return ResponseEntity.ok().body(user);
    }
}

