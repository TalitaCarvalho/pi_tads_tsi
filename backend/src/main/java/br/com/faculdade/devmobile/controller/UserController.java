package br.com.faculdade.devmobile.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdade.devmobile.dto.DataResponseBody;
import br.com.faculdade.devmobile.model.ScheduleModel;
import br.com.faculdade.devmobile.model.UserModel;
import br.com.faculdade.devmobile.service.ScheduleService;
import br.com.faculdade.devmobile.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UserController {

    private final UserService userService;

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserModel user) {
        UserModel createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/agendamentos")
    public ResponseEntity<DataResponseBody<List<ScheduleModel>>> fetchUserSchedule(Authentication authentication) {
        var userInfo = (User) authentication.getPrincipal();
        try {
            DataResponseBody<List<ScheduleModel>> schedules = scheduleService.findByUserEmail(userInfo.getUsername());
            return ResponseEntity.ok().body(schedules);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
