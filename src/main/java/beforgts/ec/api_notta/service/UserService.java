package beforgts.ec.api_notta.service;

import beforgts.ec.api_notta.domain.user.User;
import beforgts.ec.api_notta.domain.user.UserAuthDTO;
import beforgts.ec.api_notta.domain.user.UserLoginDTO;
import beforgts.ec.api_notta.infra.TokenService;
import beforgts.ec.api_notta.infra.exception.InvalidPasswordException;
import beforgts.ec.api_notta.infra.exception.UserNotActivatedException;
import beforgts.ec.api_notta.infra.exception.UserNotFoundException;
import beforgts.ec.api_notta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;

    public void registrer(UserAuthDTO dto) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto);
        user.setPassword(senhaCriptografada);
        user.setActivationCode(UUID.randomUUID().toString());
        this.repository.save(user);
        emailService.sendEmail(user.getEmail(), user.getActivationCode());
    }

    public String  login(UserLoginDTO dto) {
        Optional<UserDetails> userOptional = repository.findByUsername(dto.user());
        if (userOptional.isEmpty()) {
            userOptional = repository.findByEmail(dto.user());
        }

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Usuário não encontrado.");
        }

        User user = (User) userOptional.get();
        if (!new BCryptPasswordEncoder().matches(dto.password(), user.getPassword())) {
            throw new InvalidPasswordException( "Senha inválida");
        }
        if (!user.isEnabled()) {
            throw new UserNotActivatedException("Conta não está ativa.");
        }

        return tokenService.genereteToken(user);
    }

    public void activate(String code) {
        Optional<User> userOptional = repository.findByActivationCode(code);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid code");
        }
        User user = userOptional.get();
        user.setAccountActive(true);
        repository.save(user);
    }
}

