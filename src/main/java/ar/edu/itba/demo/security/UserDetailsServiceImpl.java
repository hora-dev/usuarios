package ar.edu.itba.demo.security;

import ar.edu.itba.demo.model.Usuario;
import ar.edu.itba.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent()) {
            return new User(username, new BCryptPasswordEncoder().encode(usuario.get().getPassword()), new ArrayList<>());
        }
        throw new UsernameNotFoundException("Usuario con username: " + username + " no encontrado");
    }
}
