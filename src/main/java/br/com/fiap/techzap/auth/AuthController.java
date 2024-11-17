package br.com.fiap.techzap.auth;

import br.com.fiap.techzap.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/logout")
    public String logout() { return "logout"; }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) {
        var user = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new RuntimeException("Access Denied"));

        if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
            throw new RuntimeException("Access Denied");
        }

        return tokenService.create(user);
    }
}