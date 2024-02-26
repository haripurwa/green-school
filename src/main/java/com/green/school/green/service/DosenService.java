package com.green.school.green.service;

import com.green.school.green.dto.request.RegistrasiDosenRequest;
import com.green.school.green.dto.request.UpdateDosenRequest;
import com.green.school.green.dto.response.ParseResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface DosenService {

    ParseResponse registrasi(RegistrasiDosenRequest request);

    ParseResponse update(UpdateDosenRequest updateDosenRequest, UUID id);

    ParseResponse listAllData(Pageable pageable);
}
