package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.models.Material;
import project.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    @Transactional
    public Material findMaterialById(Integer id) {
        return materialRepository.findOne(id);
    }

    @Override
    @Transactional
    public void uploadFile(Material material) {
        materialRepository.saveAndFlush(material);
    }

    @Override
    @Transactional
    public void deleteMaterial(Integer id){
        Material material = materialRepository.findOne(id);
        System.out.println(material.getFilename());
        materialRepository.delete(material);
    }
}
