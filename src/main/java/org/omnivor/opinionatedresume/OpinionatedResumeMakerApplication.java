package org.omnivor.opinionatedresume;

import lombok.extern.slf4j.Slf4j;
import org.omnivor.opinionatedresume.model.Resume;
import org.omnivor.opinionatedresume.dto.YamlResumeRequest;
import org.omnivor.opinionatedresume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.validation.Valid;
import java.util.Locale;

@SpringBootApplication
public class OpinionatedResumeMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionatedResumeMakerApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		Locale.setDefault(new Locale("en"));
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}
}

@Slf4j
@Controller
class WebController {

	@Autowired
	private ResumeService resumeService;

	@GetMapping("/")
	public String getMainPage(Model model) {
		String defaultResume = resumeService.getDefaultResume(LocaleContextHolder.getLocale().getLanguage());
		model.addAttribute("resumeText", defaultResume);
		log.info(LocaleContextHolder.getLocale().getLanguage());
		return "resumeInput";
	}

	@PostMapping("/yaml-to-html")
	public String getResumeHtml(Model model, @Valid @RequestBody YamlResumeRequest yamlResumeRequest) {
		Resume resume = resumeService.parseYaml(yamlResumeRequest);
		model.addAttribute("resume", resume);
		log.info(LocaleContextHolder.getLocale().getLanguage());

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
