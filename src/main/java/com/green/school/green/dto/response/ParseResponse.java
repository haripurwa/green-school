package com.green.school.green.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        setterVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.NONE
)
@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ParseResponse {
    @JsonIgnore
    private Integer httpCode= HttpStatus.OK.value();
    private Integer responseCode=null;
    private String responseMessage=null;
    private List<?> data=null;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageResponse page =null;

}

