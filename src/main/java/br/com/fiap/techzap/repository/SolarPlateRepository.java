package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.SolarPlate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarPlateRepository extends JpaRepository<SolarPlate, Long> {
    Page<SolarPlate> findAll(Pageable pageable);
    SolarPlate findById(long id);
}
