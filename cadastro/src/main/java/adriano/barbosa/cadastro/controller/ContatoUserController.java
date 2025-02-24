package adriano.barbosa.cadastro.controller;

import adriano.barbosa.cadastro.model.ContatoUser;
import adriano.barbosa.cadastro.repository.ContatoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/user"})
public class ContatoUserController {

        @Autowired
        private ContatoUserRepository repository;

        @GetMapping
        public List findAll() {
            return repository.findAll();
        }

        @GetMapping(value = "{id}")
        public ResponseEntity<?> findById(@PathVariable long id){
            return repository.findById(id)
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public ContatoUser create(@RequestBody ContatoUser user) {
            user.setPassword(criptografarPassword(user.getPassword()));
            return repository.save(user);
        }

        @PutMapping(value = "{id}")
        public ResponseEntity<?> update(@PathVariable long id,
                                        @RequestBody ContatoUser user){
            return repository.findById(id)
                    .map(record -> {
                        record.setName(user.getName());
                        record.setUsername(user.getUsername());
                        record.setPassword(criptografarPassword(user.getPassword()));
                        record.setAdmin(false);
                        ContatoUser update = repository.save(record);

                        return ResponseEntity.ok().body("Cliente: \n"
                                + "Nome: " + update.getName() + "\n"
                                + "Username: " + update.getUsername() + "\n"
                                + "Password: Atualizado! \n"
                                + "Atualizado com sucesso!");
                    }).orElse(ResponseEntity.notFound().build());
        }
        @DeleteMapping(path = {"/{id}"})
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> delete(@PathVariable long id){
            return repository.findById(id)
                    .map(objeto -> {
                        repository.deleteById(id);

                        return ResponseEntity.ok().body("Cliente " + objeto.getUsername() + " foi deletado com sucesso!");
                    }).orElse(ResponseEntity.notFound().build());
        }


        private String criptografarPassword(String password) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordCriptografado = passwordEncoder.encode(password);

            return passwordCriptografado;
        }

}
