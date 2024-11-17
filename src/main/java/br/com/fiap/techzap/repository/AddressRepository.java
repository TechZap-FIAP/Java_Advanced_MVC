package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
    Page<Address> findAll(Pageable pageable);
    Address findById(long id);
}