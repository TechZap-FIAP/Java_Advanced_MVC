package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.TypeTurbine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTurbineRepository extends JpaRepository<TypeTurbine, Long> {
    Page<TypeTurbine> findAll(Pageable pageable);
    TypeTurbine findById(long id);
}
