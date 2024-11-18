package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.PlanContracted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanContractedRepository extends JpaRepository<PlanContracted, Long> {
    Page<PlanContracted> findAll(Pageable pageable);
    PlanContracted findById(long id);
}
