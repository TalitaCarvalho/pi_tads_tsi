package br.com.faculdade.devmobile.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.faculdade.devmobile.dto.DataResponseBody;
import br.com.faculdade.devmobile.dto.ScheduleRequestBody;
import br.com.faculdade.devmobile.model.ClinicModel;
import br.com.faculdade.devmobile.model.DoctorModel;
import br.com.faculdade.devmobile.model.PaginatedApiResponse;
import br.com.faculdade.devmobile.model.ScheduleModel;
import br.com.faculdade.devmobile.model.UserModel;
import br.com.faculdade.devmobile.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final UserService userService;

    private final DoctorService doctorService;

    private final ClinicService clinicService;

    public Page<ScheduleModel> findAll(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    public DataResponseBody<List<ScheduleModel>> findAll() {
        List<ScheduleModel> appointments = scheduleRepository.findAll();
        return DataResponseBody.<List<ScheduleModel>>builder()
                .data(appointments)
                .build();
    }

    public ScheduleModel findById(Integer id) {
        Optional<ScheduleModel> schedule = scheduleRepository.findById(id);
        return schedule.orElseThrow();
    }

    public PaginatedApiResponse<ScheduleModel> findByUserEmail(String email, PageRequest pageable) {
        Page<ScheduleModel> appointments = scheduleRepository.findByUser_Email(email, pageable);
        return PaginatedApiResponse.<ScheduleModel>builder()
                .content(appointments.getContent())
                .currentPage(appointments.getNumber())
                .totalElements(appointments.getTotalElements())
                .totalPages(appointments.getTotalPages())
                .next(appointments.nextOrLastPageable().getPageNumber())
                .previous(appointments.previousOrFirstPageable().getPageNumber())
                .build();
    }

    public DataResponseBody<List<ScheduleModel>> findByUserEmail(String email) {
        List<ScheduleModel> appointments = scheduleRepository.findByUser_Email(email);
        return DataResponseBody.<List<ScheduleModel>>builder()
                .data(appointments)
                .build();
    }

    public ScheduleModel createSchedule(String ownerEmail, ScheduleRequestBody schedule) {
        DoctorModel doctor = doctorService.findById(schedule.getDoctor());
        ClinicModel clinic = clinicService.findById(schedule.getClinic());
        UserModel user = userService.findByEmail(ownerEmail);
        ScheduleModel newSchedule = ScheduleModel.builder()
                .id(schedule.getId())
                .user(user)
                .treatment(schedule.getTreatment())
                .date(schedule.getDate())
                .doctor(doctor)
                .online(schedule.getOnline())
                .inPerson(schedule.getInPerson())
                .clinic(clinic)
                .build();
        return scheduleRepository.save(newSchedule);
    }

    public boolean deleteSchedule(Integer id) {
        try {
            scheduleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
