package project.services;

import project.models.Material;

public interface MaterialService {

    // Retrieve file
    Material findMaterialById(Integer id);

    // Upload the file
    void uploadFile(Material material);

    // delete
    void deleteMaterial(Integer id);

}
