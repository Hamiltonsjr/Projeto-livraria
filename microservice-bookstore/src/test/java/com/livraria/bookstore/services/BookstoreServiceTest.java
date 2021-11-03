/*package com.livraria.bookstore.services;

import com.livraria.bookstore.entities.Bookstore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookstoreServiceTest {

    @Autowired
    public BookstoreService service;

    public Bookstore bookstore;

    @Test
    public void shouldLoadABookstoreWhenSearchingById() {
        Long idBookstore = 3L;
        Bookstore bookstore = service.getById(idBookstore);
        Assert.assertNotNull(bookstore);
        Assert.assertEquals(idBookstore,bookstore.getId());
    }

    @Test
    public void shouldNotLoadABookstoreWhenSearchingByUnregisteredId() throws Exception {
        Optional<Bookstore> response = Optional.ofNullable(service.getById(14L));
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void shouldUpdateAnExistingBookstore() {
        Bookstore bookstore = new Bookstore(22L,"test","test","12","02934000");
        service.update(bookstore, 22L);
    }

    @Test
    public void NotShouldUpdateAnExistingBookstore() {
        Bookstore bookstore = new Bookstore(100L,"test","test","12","02934000");
        service.update(bookstore, 100L);
        Assert.assertNull(bookstore);
    }

    @Test
    public void shouldDeleteBookstoreWhenSearchingById() throws Exception {
        Long idBookstore = 21L;
        Long userId = 1L;
        service.delete(idBookstore, userId);
        Assert.assertNull(bookstore);
        Assert.assertEquals(idBookstore, bookstore.getId());
    }

    @Test
    public void shouldNotDeleteBookstoreWhenSearchingById() throws Exception {
        Long idBookstore = 100L;
        Long userId = 1L;
        service.delete(idBookstore,userId);
        Assert.assertNull(bookstore);
        Assert.assertEquals(idBookstore, bookstore.getId());
    }
}*/
