package br.com.faculdade.devmobile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.faculdade.devmobile.controller.body.LoginRequestBody;
import br.com.faculdade.devmobile.dto.AuthResponseBody;
import br.com.faculdade.devmobile.model.UserModel;
import br.com.faculdade.devmobile.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseBody> login(@RequestBody LoginRequestBody loginRequestBody,
            HttpServletResponse response) {
        String jwtToken = authService.login(loginRequestBody.email(), loginRequestBody.password());
        var body = new AuthResponseBody(jwtToken);
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserModel userModel, HttpServletResponse response) {
        try {
            authService.signup(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
