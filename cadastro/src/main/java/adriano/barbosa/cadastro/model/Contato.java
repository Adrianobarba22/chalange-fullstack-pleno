package adriano.barbosa.cadastro.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Contato {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String lastName;

        @NotBlank(message = "O e-mail não pode ser vazio")
        @Email(message = "O e-mail deve ser válido")
        private String email;

        @NotBlank(message = "O telefone não pode ser vazio")
        @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "O telefone deve estar no formato (XX) XXXXX-XXXX")
        private String phone;

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
}



