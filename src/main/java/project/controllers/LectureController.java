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
import project.models.Lecture;
import project.services.LectureService;

@Controller
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/lecture/{id}", method = RequestMethod.GET)
    public String create(@PathVariable Integer id, ModelMap modelMap){
        Lecture lecture = lectureService.getLectureById(id);
        if (lecture == null){
            return "redirect:/";
        }
        modelMap.addAttribute("lecture", lecture);
        return "partial/lecture/show";
    }

    @RequestMapping(value = "/lecture/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap){
        modelMap.addAttribute("model", new Lecture());
        modelMap.addAttribute("isCreate", true);
        return "partial/lecture/create_update";
    }

    @RequestMapping(value = "/lecture/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View save(@RequestBody MultiValueMap<String, String> formData, ModelMap modelMap){
        int id = lectureService.createLecture(formData.getFirst("name"));
        return new RedirectView("/lecture/" + id, true);
    }

    @RequestMapping(value = "/lecture/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, ModelMap modelMap){
        Lecture lecture = lectureService.getLectureById(id);
        if (lecture == null){
            return "redirect:/";
        }
        modelMap.addAttribute("model", lecture);
        modelMap.addAttribute("isCreate", false);

        return "partial/lecture/create_update";
    }

    @RequestMapping(value = "/lecture/edit/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public View update(@PathVariable int id, @RequestBody MultiValueMap<String, String> formData){
        Lecture lecture = lectureService.getLectureById(id);
        if (lecture == null){
            return new RedirectView("/lecture/edit/" + id, true);
        }
        lectureService.updateLecture(id, formData.getFirst("name"));
        return new RedirectView("/lecture/" + id, true);
    }

    @RequestMapping(value = "/lecture/delete/{id}", method = RequestMethod.POST)
    public View delete(@PathVariable int id){
        Lecture lecture = lectureService.getLectureById(id);
        if (lecture == null){
            return new RedirectView("/", true);
        }
        lectureService.deleteLecture(id);
        return new RedirectView("/", true);
    }
}
