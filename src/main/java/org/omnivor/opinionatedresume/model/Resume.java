package org.omnivor.opinionatedresume.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resume {
    private String name;
    private String role;
    private String email;
    private String phone;
    private String linkedIn;
    private String github;
    private List<String> languages;
    private List<String> skills;

}
