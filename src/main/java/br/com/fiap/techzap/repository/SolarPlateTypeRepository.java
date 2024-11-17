package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.SolarPlateType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarPlateTypeRepository extends JpaRepository<SolarPlateType, Long> {
    Page<SolarPlateType> findAll(Pageable pageable);
    SolarPlateType findById(long id);
}
