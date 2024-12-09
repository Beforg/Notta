package beforgts.ec.api_notta.infra;

import beforgts.ec.api_notta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> user = userAuthRepository.findByEmail(username);
        Optional<UserDetails> email = userAuthRepository.findByUsername(username);
        if (user.isEmpty() && email.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user.orElse(email.get());
    }
}