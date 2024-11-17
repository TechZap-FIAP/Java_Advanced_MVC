package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.TurbineWind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurbineWindRepository extends JpaRepository<TurbineWind, Long> {
    Page<TurbineWind> findAll(Pageable pageable);
    TurbineWind findById(long id);
}
