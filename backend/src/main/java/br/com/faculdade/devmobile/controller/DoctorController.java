package br.com.faculdade.devmobile.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdade.devmobile.dto.DataResponseBody;
import br.com.faculdade.devmobile.model.DoctorModel;
import br.com.faculdade.devmobile.model.PaginatedApiResponse;
import br.com.faculdade.devmobile.service.DoctorService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("medicos")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<DataResponseBody<List<DoctorModel>>> fetchAll() {
        DataResponseBody<List<DoctorModel>> doctors = doctorService.findAll();
        return ResponseEntity.ok().body(doctors);
    }

    public ResponseEntity<PaginatedApiResponse<DoctorModel>> fetchAll(Pageable pageable) {
        PageRequest page = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "name")));
        PaginatedApiResponse<DoctorModel> doctors = doctorService.findAll(page);
        return ResponseEntity.ok().body(doctors);
    }

}
