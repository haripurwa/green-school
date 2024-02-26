package com.green.school.green.service;

import com.green.school.green.dto.request.RegistrasiRequest;
import com.green.school.green.dto.request.UpdateRequest;
import com.green.school.green.dto.response.ParseResponse;
import org.springframework.data.domain.Pageable;


public interface SiswaService {

    ParseResponse registrasi(RegistrasiRequest registrasiRequest);

    ParseResponse update(UpdateRequest updateRequest,int id);

    ParseResponse listAllData(Pageable pageable);
}
