package org.omnivor.opinionatedresume;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.omnivor.opinionatedresume.model.Resume;
import org.omnivor.opinionatedresume.model.YamlResumeRequest;
import org.omnivor.opinionatedresume.utilities.ResourceReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@SpringBootApplication
public class OpinionatedResumeMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionatedResumeMakerApplication.class, args);
	}

}

@Controller
class WebController {

	private final String defaultResume = ResourceReader.readFileAsString("classpath:default_en.yaml");

	@GetMapping("/")
	public String getMainPage(Model model) {
		model.addAttribute("resumeText", defaultResume);
		return "resumeInput";
	}
}

@RestController
class APIController {

	@PostMapping(value = "/yaml-to-json")
	public Resume getResumeHTML(@Valid @RequestBody YamlResumeRequest yamlResumeRequest) {
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
