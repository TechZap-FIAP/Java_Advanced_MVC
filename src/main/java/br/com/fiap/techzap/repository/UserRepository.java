package br.com.fiap.techzap.repository;

import br.com.fiap.techzap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
    User findById(long idUser);
}
