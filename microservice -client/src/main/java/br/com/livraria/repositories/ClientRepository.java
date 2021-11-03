package br.com.livraria.repositories;

import br.com.livraria.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ClientRepository extends JpaRepository<Client, Long>{

}
