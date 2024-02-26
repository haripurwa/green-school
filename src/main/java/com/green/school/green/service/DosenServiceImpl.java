package com.green.school.green.service;


import com.green.school.green.dto.request.RegistrasiDosenRequest;
import com.green.school.green.dto.request.UpdateDosenRequest;
import com.green.school.green.dto.response.CustomResponse;
import com.green.school.green.dto.response.PageResponse;
import com.green.school.green.dto.response.ParseResponse;
import com.green.school.green.model.Dosen;
import com.green.school.green.repository.DosenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class DosenServiceImpl implements DosenService {
    @Autowired
    DosenRepository dosenRepository;

    @Override
    public ParseResponse registrasi(RegistrasiDosenRequest request) {
        ParseResponse response = new ParseResponse();
        Optional<Dosen> byNik = dosenRepository.findByNik(request.getNik());
        if(byNik.isPresent()){
            response.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            response.setResponseCode(422);
            response.setResponseMessage("data already exist");
            response.setData(null);
            return response;
        }
        Dosen dosen = new Dosen();
        dosen.setName(request.getName());
        dosen.setNik(request.getNik());
        dosen.setEmail(request.getEmail());
        dosen.setKelasDosen(request.getKelasDosen());
        dosenRepository.save(dosen);
        return CustomResponse.getParseResponseSuccess();

    }

    @Override
    public ParseResponse update(UpdateDosenRequest request, UUID id) {
        ParseResponse response = new ParseResponse();
        Optional<Dosen> byId = dosenRepository.findById(id);
        if (byId.isPresent()) {
            Dosen dosen = byId.get();
            dosen.setName(request.getName());
            dosen.setEmail(request.getEmail());
            dosen.setKelasDosen(request.getKelasDosen());
            dosen.setUpdatedAt(new Date());
            dosenRepository.save(dosen);
            return CustomResponse.getParseResponseSuccess();
        } else {
            response.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            response.setResponseCode(422);
            response.setResponseMessage("data not found");
            response.setData(null);
            return response;
        }

    }

    @Override
    public ParseResponse listAllData(Pageable pageable) {
        Page<Dosen> all = dosenRepository.findAll(pageable);
        PageResponse pageResponse = CustomResponse.getPageResponse(all);
        return CustomResponse.getResponse(all, pageResponse);
    }
}
