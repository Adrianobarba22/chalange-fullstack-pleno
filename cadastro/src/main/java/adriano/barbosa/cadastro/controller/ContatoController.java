package adriano.barbosa.cadastro.controller;

import adriano.barbosa.cadastro.model.Contato;
import adriano.barbosa.cadastro.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping({"/contato"})
public class ContatoController {

        @Autowired
        private ContatoRepository repository;

        @GetMapping
        public List findAll(){
            return repository.findAll();
        }

        @GetMapping(value = "{id}")
        public ResponseEntity findById(@PathVariable long id){
            return repository.findById(id)
                    .map(record -> ResponseEntity.ok().body(record))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public Contato create(@RequestBody Contato contato){
            return repository.save(contato);
        }

        @PutMapping(value = "{id}")
        public ResponseEntity update(@PathVariable long id, @RequestBody Contato contato){
            return repository.findById(id)
                    .map(record -> {
                        record.setName(contato.getName());
                        record.setEmail(contato.getEmail());
                        record.setPhone(contato.getPhone());
                        Contato update = repository.save(record);
                        return ResponseEntity.ok().body(update);
                    }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping(path = {"/{id}"})
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity <?> delete(@PathVariable long id){
            return repository.findById(id)
                    .map(record -> {
                        repository.deleteById(id);
                        return ResponseEntity.ok().body("Deletado com sucesso");

                    }).orElse(ResponseEntity.notFound().build());
        }

    }

