package br.com.faculdade.devmobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdade.devmobile.dto.DataResponseBody;
import br.com.faculdade.devmobile.dto.ScheduleRequestBody;
import br.com.faculdade.devmobile.model.ScheduleModel;
import br.com.faculdade.devmobile.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("agendamentos")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<DataResponseBody<List<ScheduleModel>>> fetchAll() {
        DataResponseBody<List<ScheduleModel>> schedules = scheduleService.findAll();
        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("{id}")
    public ResponseEntity<ScheduleModel> fetchById(@PathVariable Integer id, Pageable pageable) {
        try {
            ScheduleModel schedules = scheduleService.findById(id);
            return ResponseEntity.ok().body(schedules);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ScheduleModel> schedule(
            @RequestBody ScheduleRequestBody body,
            Authentication authentication) {
        var userInfo = (User) authentication.getPrincipal();
        ScheduleModel newSchedule = scheduleService.createSchedule(userInfo.getUsername(), body);
        return ResponseEntity.status(201).body(newSchedule);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Id não pode ser nulo"));
        }
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok(Map.of("message", "Agendamento deletado com sucesso"));
    }

    @PutMapping("{id}")
    public ResponseEntity<ScheduleModel> update(
            @PathVariable("id") Integer id,
            @RequestBody ScheduleRequestBody body,
            Authentication authentication) {
        try {
            var userInfo = (User) authentication.getPrincipal();
            ScheduleModel newSchedule = scheduleService.createSchedule(userInfo.getUsername(), body);
            return ResponseEntity.status(201).body(newSchedule);
        } catch (Exception e) {
            log.error("Falha ao atualizar item " + id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
