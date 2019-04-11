package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.models.Lecture;
import project.models.Question;
import project.services.LectureService;
import project.services.QuestionService;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    LectureService lectureService;

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap, Principal principal){
        List<Lecture> lectures = lectureService.getLectures();
        List<Question> questions = questionService.getQuestions();

        modelMap.addAttribute("lectures", lectures);
        modelMap.addAttribute("questions", questions);

        return "partial/home/index";
    }

}
