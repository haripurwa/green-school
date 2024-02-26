package com.green.school.green.dto.response;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomResponse {

    public static ResponseEntity<ParseResponse> controllerInternalServerErrorResponse(Exception e) {
        ParseResponse response = new ParseResponse();
        response.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setResponseCode(500);
        response.setResponseMessage("internal server error");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getHttpCode()));
    }

    public static PageResponse getPageResponse(Page<?> allDataList) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalRecord(allDataList.getTotalElements());
        pageResponse.setPageNumber(allDataList.getPageable().getPageNumber());
        pageResponse.setPageSize(allDataList.getPageable().getPageSize());
        return pageResponse;
    }

}

