package br.com.faculdade.devmobile.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.faculdade.devmobile.model.UserModel;
import br.com.faculdade.devmobile.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        UserModel user = userService.findByEmail(email);
        return jwtUtil.generateToken(user);
    }

    public UserModel signup(UserModel user) {
        return userService.createUser(user);
    }

}
