package org.omnivor.opinionatedresume;

import org.omnivor.opinionatedresume.model.Resume;
import org.omnivor.opinionatedresume.dto.YamlResumeRequest;
import org.omnivor.opinionatedresume.service.ResumeService;
import org.omnivor.opinionatedresume.utilities.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SpringBootApplication
public class OpinionatedResumeMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionatedResumeMakerApplication.class, args);
	}

	@Bean("defaultResume")
	public String getDefaultResume() {
		return ResourceReader.readFileAsString("classpath:default_en.yaml");
	}
}

@Controller
class WebController {

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private String defaultResume;

	@GetMapping("/")
	public String getMainPage(Model model) {
		model.addAttribute("resumeText", defaultResume);
		return "resumeInput";
	}

	@PostMapping("/yaml-to-html")
	public String getResumeHtml(Model model, @Valid @RequestBody YamlResumeRequest yamlResumeRequest) {
		Resume resume = resumeService.parseYaml(yamlResumeRequest);
		model.addAttribute("resume", resume);
		return "resumeOutput";
	}
}

@RestController
class APIController {

	@Autowired
	ResumeService resumeService;

	@PostMapping("/yaml-to-json")
	public Resume getResumeJSON(@Valid @RequestBody YamlResumeRequest yamlResumeRequest) {
		return resumeService.parseYaml(yamlResumeRequest);
	}
}
