package br.com.faculdade.devmobile.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faculdade.devmobile.model.ClinicModel;
import br.com.faculdade.devmobile.model.PaginatedApiResponse;
import br.com.faculdade.devmobile.service.ClinicService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("clinicas")
public class ClinicController {

    private final ClinicService clinicService;

    @GetMapping
    public ResponseEntity<PaginatedApiResponse<ClinicModel>> fetchAll(Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest page = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(sort));
        PaginatedApiResponse<ClinicModel> clinics = clinicService.findAll(page);
        return ResponseEntity.ok().body(clinics);
    }

}
