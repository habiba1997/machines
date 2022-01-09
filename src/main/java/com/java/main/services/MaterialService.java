package com.java.main.services;

import java.util.List;

import com.java.main.dtos.Material;

public interface MaterialService {

	List<Material> findAll();

	Material findByName(String name);
}
