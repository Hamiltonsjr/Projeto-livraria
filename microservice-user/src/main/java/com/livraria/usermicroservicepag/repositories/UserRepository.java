package com.livraria.usermicroservicepag.repositories;

import com.livraria.usermicroservicepag.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
