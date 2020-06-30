package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class Hello {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("time", new java.util.Date().toString());
        return "index";
    }

    @GetMapping("/person/new")
    public String createPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "person_form";
    }

    @PostMapping("/person")
    public String processPersonForm(@Valid Person person, BindingResult result,
                                    Model model) {
        if (result.hasErrors()) {
            return "person_form";
        }
        personRepository.save(person);
        Iterable<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);
        return "person_show";
    }

    @GetMapping("/person")
    public String getAllPerson(Model model) {
        Iterable<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);
        return "person_list";
    }
}
