package br.com.livraria.repositories;

import br.com.livraria.entities.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface SaleRepository extends JpaRepository<Sell, Long> {
}
