package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import project.models.Question;
import project.services.QuestionService;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "question/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, ModelMap modelMap){
        Question question = questionService.getQuestionById(id);
        modelMap.addAttribute("question", question);
        modelMap.addAttribute("isCreate", true);
        return "partial/question/show";
    }

    @RequestMapping(value = "question/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute("model", new Question());
        return "partial/question/create_update";
    }

    @RequestMapping(value = "question/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View save(@RequestBody MultiValueMap<String, String> formData, ModelMap modelMap){
        int id = questionService.createQuestion(formData.getFirst("question"));
        return new RedirectView("/question/" + id, true);
    }

    @RequestMapping(value = "question/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap modelMap){
        Question question = questionService.getQuestionById(id);
        if (question == null){
            return "redirect:/";
        }
        modelMap.addAttribute("model", question);
        modelMap.addAttribute("isCreate", false);
        return "partial/question/create_update";
    }

    @RequestMapping(value = "question/edit/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View update(@RequestBody MultiValueMap<String, String> formData, @PathVariable Integer id, ModelMap modelMap){
        questionService.updateQuestion(id, formData.getFirst("question"));
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "question/delete/{id}", method = RequestMethod.POST)
    public View delete(@PathVariable Integer id, ModelMap modelMap){
        questionService.deleteQuestion(id);
        return new RedirectView("/", true);
    }

}
