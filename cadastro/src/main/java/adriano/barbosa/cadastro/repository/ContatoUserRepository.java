package adriano.barbosa.cadastro.repository;

import adriano.barbosa.cadastro.model.ContatoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoUserRepository extends JpaRepository<ContatoUser, Long> {
    ContatoUser findByUsername(String username);
}
