package br.com.faculdade.devmobile.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.faculdade.devmobile.dto.DataResponseBody;
import br.com.faculdade.devmobile.model.DoctorModel;
import br.com.faculdade.devmobile.model.PaginatedApiResponse;
import br.com.faculdade.devmobile.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorModel findById(Integer id) {
        return doctorRepository.findById(id).orElseThrow();
    }

    public PaginatedApiResponse<DoctorModel> findAll(Pageable pageable) {
        Page<DoctorModel> doctors = doctorRepository.findAll(pageable);
        return PaginatedApiResponse.<DoctorModel>builder()
                .content(doctors.getContent())
                .currentPage(doctors.getNumber())
                .totalElements(doctors.getTotalElements())
                .totalPages(doctors.getTotalPages())
                .next(doctors.nextOrLastPageable().getPageNumber())
                .previous(doctors.previousOrFirstPageable().getPageNumber())
                .build();
    }

    public DataResponseBody<List<DoctorModel>> findAll() {
        List<DoctorModel> doctors = doctorRepository.findAll();
        return DataResponseBody.<List<DoctorModel>>builder()
                .data(doctors)
                .build();
    }

}
