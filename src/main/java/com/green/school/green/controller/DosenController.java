package com.green.school.green.controller;


import com.green.school.green.dto.request.RegistrasiDosenRequest;
import com.green.school.green.dto.request.RegistrasiRequest;
import com.green.school.green.dto.request.UpdateDosenRequest;
import com.green.school.green.dto.request.UpdateRequest;
import com.green.school.green.dto.response.CustomResponse;
import com.green.school.green.dto.response.ParseResponse;
import com.green.school.green.service.DosenService;
import com.green.school.green.service.SiswaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/dosen")
public class DosenController {
    @Autowired
    ParseResponse parseResponse;

    @Autowired
    DosenService dosenService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParseResponse> insert(@Valid @RequestBody RegistrasiDosenRequest request) {
        try {
            ParseResponse responseService = dosenService.registrasi(request);
            return new ResponseEntity<>(responseService, HttpStatus.valueOf(responseService.getHttpCode()));
        } catch (Exception e) {
            return CustomResponse.controllerInternalServerErrorResponse(e);
        }
    }
    @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParseResponse> update(@Valid @RequestBody UpdateDosenRequest request, @PathVariable UUID id) {
        try {
            ParseResponse responseService = dosenService.update(request,id);
            return new ResponseEntity<>(responseService, HttpStatus.valueOf(responseService.getHttpCode()));
        } catch (Exception e) {
            return CustomResponse.controllerInternalServerErrorResponse(e);
        }
    }

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParseResponse> listDosen(Pageable pageable) {
        try {
            ParseResponse responseService = dosenService.listAllData(pageable);
            return new ResponseEntity<>(responseService, HttpStatus.valueOf(responseService.getHttpCode()));
        } catch (Exception e) {
            return CustomResponse.controllerInternalServerErrorResponse(e);
        }
    }

}
