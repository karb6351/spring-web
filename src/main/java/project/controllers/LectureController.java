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
import project.exceptions.LectureCommentNotFoundException;
import project.models.Lecture;
import project.models.LectureComment;
import project.models.User;
import project.services.LectureCommentService;
import project.services.LectureService;
import project.services.UserService;

import java.security.Principal;

@Controller
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureCommentService lectureCommentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/lecture/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Integer id, ModelMap modelMap){
        Lecture lecture = lectureService.getLectureById(id);
        if (lecture == null){
            return "redirect:/";
        }
        modelMap.addAttribute("lecture", lecture);
        modelMap.addAttribute("lectureComments", lectureCommentService.getLectureCommentsByLectureIdSortByDate(lecture.getId()));
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

    @RequestMapping(value = "/lecture/comment/{lectureId}", method = RequestMethod.GET)
    public String addComment(@PathVariable Integer lectureId, ModelMap modelMap){
        modelMap.addAttribute("parentId", lectureId);
        modelMap.addAttribute("model", new LectureComment());
        modelMap.addAttribute("isCreate", true);
        return "partial/comment/create_update";
    }

    @RequestMapping(value = "/lecture/comment/{lectureId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public View saveComment(@PathVariable Integer lectureId, @RequestBody MultiValueMap<String, String> formData, ModelMap modelMap, Principal principal){
        String content = formData.getFirst("content");
        User user = userService.getUserByUsername(principal.getName());
        Lecture lecture = lectureService.getLectureById(lectureId);
        lectureCommentService.createLectureComment(content, lecture, user);
        return new RedirectView("/lecture/" + lecture.getId(), true);
    }

    @RequestMapping(value = "/lecture/comment/edit/{id}", method = RequestMethod.GET)
    public String editComment(@PathVariable Integer id, ModelMap modelMap){
        LectureComment lectureComment = lectureCommentService.getLectureCommentById(id);
        modelMap.addAttribute("parentId", lectureComment.getLecture().getId());
        modelMap.addAttribute("model", lectureComment);
        modelMap.addAttribute("isCreate", false);
        return "partial/comment/create_update";
    }

    @RequestMapping(value = "/lecture/comment/edit/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, method = RequestMethod.POST)
    public View updateComment(@PathVariable Integer id, @RequestBody MultiValueMap<String, String> formData){
        String content = formData.getFirst("content");
        LectureComment lectureComment = lectureCommentService.getLectureCommentById(id);
        try{
            lectureCommentService.updateLectureComment(id, content);
            return new RedirectView("/lecture/" + lectureComment.getLecture().getId(), true);
        }catch(LectureCommentNotFoundException lcnfe){
            System.out.println(lcnfe.getMessage());
            return new RedirectView("/lecture/" + lectureComment.getLecture().getId(), true);
        }
    }

    @RequestMapping(value = "/lecture/comment/delete/{id}", method = RequestMethod.POST)
    public View deleteComment(@PathVariable Integer id){
        LectureComment lectureComment = lectureCommentService.getLectureCommentById(id);
        try{
            if (lectureComment != null){
                lectureService.deleteLectureComment(lectureComment.getLectureId(), lectureComment);
                lectureCommentService.deleteLectureComment(id);
                return new RedirectView("/lecture/" + lectureComment.getLecture().getId(), true);
            }
            return new RedirectView("/", true);
        }catch(LectureCommentNotFoundException lcnfe){
            System.out.println(lcnfe.getMessage());
            return new RedirectView("/", true);
        }


    }

}
