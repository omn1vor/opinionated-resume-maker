package org.omnivor.opinionatedresume.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.omnivor.opinionatedresume.model.Resume;
import org.omnivor.opinionatedresume.dto.YamlResumeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ResumeService {
    public Resume parseYaml(YamlResumeRequest yamlResumeRequest) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        Resume resume;
        try {
            resume = mapper.readValue(yamlResumeRequest.getYaml(), Resume.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error in your resume YAML text");
        }
        return resume;
    }
}
