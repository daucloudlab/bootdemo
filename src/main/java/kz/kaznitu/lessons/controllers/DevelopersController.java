package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Developer;
import kz.kaznitu.lessons.models.Skill;
import kz.kaznitu.lessons.repositories.DeveloperRepository;
import kz.kaznitu.lessons.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DevelopersController {
    @Autowired
    private DeveloperRepository developerRepository ;

    @Autowired
    private SkillRepository skillRepository ;

    @RequestMapping("/developer/{id}")
    public String developer(@PathVariable("id")int id, Model model){
        model.addAttribute("developer", developerRepository.findById(id).get()) ;
        model.addAttribute("skills", skillRepository.findAll()) ;
        return "developer" ;
    }

    @RequestMapping(value="/developers",method=RequestMethod.GET)
    public String developersList(Model model) {
        model.addAttribute("developers", developerRepository.findAll());
        return "developers";
    }

    @RequestMapping(value="/developers",method=RequestMethod.POST)
    public String developersAdd(@RequestParam String email,
                                @RequestParam String firstName, @RequestParam String lastName, Model model) {
        Developer newDeveloper = new Developer();
        newDeveloper.setEmail(email);
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        developerRepository.save(newDeveloper);

        model.addAttribute("developer", newDeveloper);
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:/developer/" + newDeveloper.getId();
    }

    @RequestMapping(value="/developer/{id}/skills", method= RequestMethod.POST)
    public String developersAddSkill(@PathVariable Integer id, @RequestParam Integer skillId, Model model) {
        Skill skill = skillRepository.findById(skillId).get();
        Developer developer = developerRepository.findById(id).get();

        if (developer != null) {
            if (!developer.hasSkill(skill)) {
                developer.getSkills().add(skill);
            }
            developerRepository.save(developer);
            model.addAttribute("developer", developerRepository.findById(id).get());
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/developer/" + developer.getId();
        }

        model.addAttribute("developers", developerRepository.findAll());
        return "redirect:/developers";
    }

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public String skillsAdd(Model model){
        model.addAttribute("skill", new Skill()) ;
        return "skills" ;

    }
    @RequestMapping(value = "/skills", method = RequestMethod.POST)
    public String skillsAdd(@ModelAttribute Skill skill){
        skillRepository.save(skill) ;
        return "redirect:/developers" ;

    }
}
