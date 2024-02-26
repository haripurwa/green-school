package com.green.school.green.service;

import com.green.school.green.dto.request.RegistrasiRequest;
import com.green.school.green.dto.request.UpdateRequest;
import com.green.school.green.dto.response.CustomResponse;
import com.green.school.green.dto.response.PageResponse;
import com.green.school.green.dto.response.ParseResponse;
import com.green.school.green.model.Siswa;
import com.green.school.green.repository.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiswaServiceImpl implements SiswaService {

    @Autowired
    SiswaRepository siswaRepository;

    @Override
    public ParseResponse registrasi(RegistrasiRequest req) {
        saveSiswa(req);
        return getParseResponseSuccess();
    }

    @Override
    public ParseResponse update(UpdateRequest req, int id) {
        ParseResponse response = new ParseResponse();
        Optional<Siswa> byId = siswaRepository.findById(id);
        if (byId.isEmpty()) {
            response.setHttpCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
            response.setResponseCode(422);
            response.setResponseMessage("data not found");
            response.setData(null);
            return response;
        }
        Siswa siswa=byId.get();
        siswa.setName(req.getName());
        siswa.setAddress(req.getAddress());
        siswa.setGender(req.getGender());
        siswa.setDob(req.getDob());
        siswa.setPob(req.getPob());
        siswa.setEmail(req.getEmail());
        siswaRepository.save(siswa);
        return getParseResponseSuccess();
    }

    @Override
    public ParseResponse listAllData(Pageable pageable) {
        Page<Siswa> all = siswaRepository.findAll(pageable);
        PageResponse pageResponse = CustomResponse.getPageResponse(all);
        return getResponse(all, pageResponse);
    }

    private void saveSiswa(RegistrasiRequest req) {
        Siswa siswa = new Siswa();
        siswa.setName(req.getName());
        siswa.setAddress(req.getAddress());
        siswa.setGender(req.getGender());
        siswa.setDob(req.getDob());
        siswa.setPob(req.getPob());
        siswa.setEmail(req.getEmail());
        siswaRepository.save(siswa);
    }
    private ParseResponse getParseResponseSuccess() {
        ParseResponse response = new ParseResponse();
        response.setHttpCode(HttpStatus.OK.value());
        response.setResponseCode(200);
        response.setResponseMessage("success");
        response.setData(null);
        return response;
    }

    private ParseResponse getResponse(Page<?> allDataList, PageResponse pageResponse) {
        ParseResponse response = new ParseResponse();
        response.setHttpCode(HttpStatus.OK.value());
        response.setResponseCode(200);
        response.setResponseMessage("success");
        response.setData(allDataList.getContent());
        response.setPage(pageResponse);
        return response;
    }
}
