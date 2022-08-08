package org.omnivor.opinionatedresume.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class YamlResumeRequest {
    @NotNull(message = "YAML text can't be empty!")
    private String yaml;
}
