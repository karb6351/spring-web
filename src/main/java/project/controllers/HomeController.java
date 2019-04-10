package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.models.Lecture;
import project.services.LectureService;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    LectureService lectureService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap, Principal principal){
        List<Lecture> lectures = lectureService.getLectures();

        modelMap.addAttribute("lectures", lectures);
        return "partial/home/index";
    }

}
