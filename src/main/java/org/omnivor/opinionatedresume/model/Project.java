package org.omnivor.opinionatedresume.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    private String name;
    private String description;
    private String url;
    private String liveUrl;
    private List<String> skills;
}
