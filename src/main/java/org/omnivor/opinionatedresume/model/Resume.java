package org.omnivor.opinionatedresume.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.omnivor.opinionatedresume.utilities.StringUtils;

import java.util.List;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resume {
    private String name;
    private String position;
    private String address;
    private String email;
    private String phone;
    private String linkedIn;
    private String github;
    private String photoUrl;
    private List<String> languages;
    private List<String> skills;
    private List<WorkingExperience> experience;
    private List<Project> projects;
    private List<Education> education;
    private List<Course> courses;
    private String summary;

    public String getLinkedInTitle() {
        return StringUtils.urlPrettyPrint(linkedIn);
    }

    public String getGithubTitle() {
        return StringUtils.urlPrettyPrint(github);
    }
}
