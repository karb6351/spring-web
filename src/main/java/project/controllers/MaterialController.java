package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import project.models.Lecture;
import project.models.Material;
import project.services.LectureService;
import project.services.MaterialService;
import project.views.DownloadView;

import java.util.Iterator;

@Controller
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/material/create", method = RequestMethod.POST)
    public ResponseEntity upload(MultipartHttpServletRequest request, @RequestParam Integer lectureId){
        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();

                MultipartFile file = request.getFile(uploadedFile);
                String contentType = file.getContentType();
                String filename = file.getOriginalFilename();
                byte[] bytes = file.getBytes();

                Lecture lecture = lectureService.getLectureById(lectureId);

                Material material = new Material(filename, bytes, contentType, lecture);

                materialService.uploadFile(material);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @RequestMapping(value = "material/download/{id}", method = RequestMethod.GET)
    public View download(@PathVariable("id") Integer id) {
        Material material = materialService.findMaterialById(id);
        if (material != null) {
            return new DownloadView(material.getFilename(), material.getContentType(), material.getContent());
        }
        return new RedirectView("/", true);
    }

    @RequestMapping(value = "material/delete/{id}", method = RequestMethod.POST)
    public View delete(@PathVariable("id") Integer id){
        Material material = materialService.findMaterialById(id);
        if (material != null){
            Integer lectureId = material.getLectureId();
            lectureService.deleteMaterial(lectureId, material);
            return new RedirectView("/lecture/" + lectureId, true);
        }
        return new RedirectView("/", true);
    }
}
