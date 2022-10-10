package com.machines.main.services;

import java.util.List;

import com.machines.main.dtos.Material;

public interface MaterialService {

	List<Material> findAll();

	Material findByName(String name);
}
