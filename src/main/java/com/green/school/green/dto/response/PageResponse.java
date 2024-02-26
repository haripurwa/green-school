package com.green.school.green.dto.response;

import lombok.Data;

@Data
public class PageResponse {
    private int pageNumber;
    private int pageSize;
    private long totalRecord;

}
