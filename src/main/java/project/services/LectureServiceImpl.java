package project.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.exceptions.LectureNotFoundException;
import project.models.Lecture;
import project.models.LectureComment;
import project.models.Material;
import project.repositories.LectureRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    @Resource
    private LectureRepository lectureRepository;

    @Override
    @Transactional
    public List<Lecture> getLectures() {
        return lectureRepository.findAll();
    }

    @Override
    @Transactional
    public Lecture getLectureById(int id){
        return lectureRepository.findOne(id);
    }

    @Override
    @Transactional
    public int createLecture(String name) {
        Lecture lecture = lectureRepository.save(new Lecture(name));
        return lecture.getId();
    }

    @Override
    @Transactional
    public void updateLecture(Integer id, String name){
        Lecture lecture = lectureRepository.findOne(id);
        lecture.setName(name);
        lectureRepository.save(lecture);
    }

    @Override
    @Transactional
    public void updateLecture(Lecture lecture){
        lectureRepository.save(lecture);
    }

    @Override
    @Transactional
    public void deleteLecture(Integer id) {
        Lecture lecture = lectureRepository.findOne(id);
        lectureRepository.delete(lecture);
    }

    @Override
    @Transactional
    public void deleteMaterial(Integer lectureId, Material material){
        Lecture lecture = lectureRepository.findOne(lectureId);
        List<Material> removeList = new ArrayList<>();
        for(Material buffer: lecture.getMaterials()){
            if (buffer.getId().equals(material.getId())){
                removeList.add(buffer);
            }
        }
        for(Material buffer: removeList){
            lecture.removeMaterial(buffer);
        }
        lectureRepository.save(lecture);
    }

    @Override
    @Transactional
    public void deleteLectureComment(Integer lectureId, LectureComment lectureComment){
        Lecture lecture = lectureRepository.findOne(lectureId);
        List<LectureComment> removeList = new ArrayList<>();
        for(LectureComment buffer: lecture.getLectureComments()){
            if (buffer.getId().equals(lectureComment.getId())){
                removeList.add(buffer);
            }
        }
        for(LectureComment buffer: removeList){
            lecture.removeLectureComment(buffer);
        }
        lectureRepository.save(lecture);
    }


    @Override
    @Transactional
    public boolean isLectureExist(Integer id){
        return lectureRepository.findOne(id) != null;
    }

}
