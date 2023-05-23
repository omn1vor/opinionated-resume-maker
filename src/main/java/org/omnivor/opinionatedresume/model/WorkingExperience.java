package org.omnivor.opinionatedresume.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkingExperience {
    private String company;
    private String position;
    private String start;
    private String end;
    private List<String> skills;
    private String comment;
}
