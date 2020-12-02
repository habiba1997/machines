package com.java.main.services;

import com.java.main.models.Material;
import com.java.main.repositories.Material_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Material_Service {

    @Autowired
    private Material_Repository repository;

    public List<Material> getAllMaterials() {
        return this.repository.findAll();
    }


    @Cacheable("fetch_materials")
    public List<Material> getAllMaterialsCached() {
        return this.repository.findAll();
    }
}
