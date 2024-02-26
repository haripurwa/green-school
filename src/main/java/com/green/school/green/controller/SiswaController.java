package com.green.school.green.controller;


import com.green.school.green.dto.request.RegistrasiRequest;
import com.green.school.green.dto.request.UpdateRequest;
import com.green.school.green.dto.response.CustomResponse;
import com.green.school.green.dto.response.ParseResponse;
import com.green.school.green.service.SiswaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("v1/siswa")
public class SiswaController {
    @Autowired
    ParseResponse parseResponse;

    @Autowired
    SiswaService siswaService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParseResponse> insert(@Valid @RequestBody RegistrasiRequest registrasiRequest) {
        try {
            ParseResponse responseService = siswaService.registrasi(registrasiRequest);
            return new ResponseEntity<>(responseService, HttpStatus.valueOf(responseService.getHttpCode()));
        } catch (Exception e) {
            return CustomResponse.controllerInternalServerErrorResponse(e);
        }
    }
    @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParseResponse> update(@Valid @RequestBody UpdateRequest updateRequest, @PathVariable int id) {
        try {
            ParseResponse responseService = siswaService.update(updateRequest,id);
            return new ResponseEntity<>(responseService, HttpStatus.valueOf(responseService.getHttpCode()));
        } catch (Exception e) {
            return CustomResponse.controllerInternalServerErrorResponse(e);
        }
    }

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParseResponse> listSiswa(Pageable pageable) {
        try {
            ParseResponse responseService = siswaService.listAllData(pageable);
            return new ResponseEntity<>(responseService, HttpStatus.valueOf(responseService.getHttpCode()));
        } catch (Exception e) {
            return CustomResponse.controllerInternalServerErrorResponse(e);
        }
    }


}
