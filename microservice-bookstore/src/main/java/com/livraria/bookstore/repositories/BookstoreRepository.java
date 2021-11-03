package com.livraria.bookstore.repositories;

import com.livraria.bookstore.entities.Bookstore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreRepository extends JpaRepository<Bookstore, Long> {

}
