package org.omnivor.opinionatedresume.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Education {
    private String year;
    private String institution;
    private String major;
}
