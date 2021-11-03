package com.livraria.bookstore.resources;

import com.livraria.bookstore.entities.Bookstore;
import com.livraria.bookstore.services.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/livraria")
public class BookstoreResource {

    @Autowired
    public BookstoreService service;

    @GetMapping
    public ResponseEntity<List<Bookstore>> getAll(){
        List<Bookstore> list = service.getAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(path = {"getId/{id}"})
    public ResponseEntity<Bookstore> getById(@PathVariable Long id){
        Bookstore bookstoreObject = service.getById(id);
        return ResponseEntity.ok().body(bookstoreObject);
    }

    @Transactional
    @PutMapping("update/{id}")
    public ResponseEntity<Bookstore> update(@RequestBody Bookstore bookstore, @RequestHeader("UserId") Long userId){
        service.update(bookstore, userId);
        return ResponseEntity.ok().body(bookstore);
    }
    @PostMapping("insert/")
    public Bookstore insert(@RequestBody Bookstore bookstore, @RequestHeader("UserId") Long userId){
        return service.insert(bookstore, userId);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id, @RequestHeader("UserId") Long userId){
        service.delete(id, userId);
    }
}
