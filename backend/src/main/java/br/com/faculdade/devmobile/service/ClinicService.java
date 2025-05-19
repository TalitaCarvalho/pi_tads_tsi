package br.com.faculdade.devmobile.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.faculdade.devmobile.model.ClinicModel;
import br.com.faculdade.devmobile.model.PaginatedApiResponse;
import br.com.faculdade.devmobile.repository.ClinicRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicModel findById(Integer id) {
        return clinicRepository.findById(id).orElseThrow();
    }

    public PaginatedApiResponse<ClinicModel> findAllFromUser(Pageable pageable) {
        Page<ClinicModel> clinics = clinicRepository.findAll(pageable);
        return PaginatedApiResponse.<ClinicModel>builder()
                .content(clinics.getContent())
                .currentPage(clinics.getNumber())
                .totalElements(clinics.getTotalElements())
                .totalPages(clinics.getTotalPages())
                .next(clinics.nextOrLastPageable().getPageNumber())
                .previous(clinics.previousOrFirstPageable().getPageNumber())
                .build();
    }

    public PaginatedApiResponse<ClinicModel> findAll(Pageable pageable) {
        Page<ClinicModel> clinics = clinicRepository.findAll(pageable);
        return PaginatedApiResponse.<ClinicModel>builder()
                .content(clinics.getContent())
                .currentPage(clinics.getNumber())
                .totalElements(clinics.getTotalElements())
                .totalPages(clinics.getTotalPages())
                .next(clinics.nextOrLastPageable().getPageNumber())
                .previous(clinics.previousOrFirstPageable().getPageNumber())
                .build();
    }

    public List<ClinicModel> findAll() {
        return clinicRepository.findAll();
    }
}
