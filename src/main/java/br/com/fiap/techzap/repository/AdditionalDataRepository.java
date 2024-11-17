package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.AdditionalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalDataRepository extends JpaRepository<AdditionalData, Long> {
    Page<AdditionalData> findAll(Pageable pageable);
    AdditionalData findById(long id);
}
