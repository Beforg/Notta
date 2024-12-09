package beforgts.ec.api_notta.controller;

import beforgts.ec.api_notta.domain.user.LoginResponseDTO;
import beforgts.ec.api_notta.domain.user.UserAuthDTO;
import beforgts.ec.api_notta.domain.user.UserLoginDTO;
import beforgts.ec.api_notta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth") // controller de autenticação
public class AuthController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO dto){
        String token = this.service.login(dto);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public  ResponseEntity<String> register(@RequestBody UserAuthDTO dto){
        this.service.registrer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso.");
    }

    @GetMapping("/activate")
    public ResponseEntity<String> activate(@RequestParam String token){
        this.service.activate(token);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário ativado com sucesso.");
    }

}
