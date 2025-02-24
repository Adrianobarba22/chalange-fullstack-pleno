package adriano.barbosa.cadastro.service;

import adriano.barbosa.cadastro.model.ContatoUser;
import adriano.barbosa.cadastro.repository.ContatoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class CustomUserDetailService  implements UserDetailsService{


    private final ContatoUserRepository contatoUserRepository;

    @Autowired
    public CustomUserDetailService(ContatoUserRepository contatoUserRepository){
        this.contatoUserRepository = contatoUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ContatoUser user = Optional.ofNullable(contatoUserRepository.findByUsername(username))
                .orElseThrow(()-> new UsernameNotFoundException("Usuario não Encontrado"));


        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");


        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), user.isAdmin() ? authorityListAdmin : authorityListUser);
    }

}