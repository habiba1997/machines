package com.java.main.services;

import com.java.main.models.Machine;
import com.java.main.repositories.Machine_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Machine_Service {

    @Autowired
    private Machine_Repository repository;

    public Iterable<Machine> getAllMachines(){
        return this.repository.findAll();
    }

    @Cacheable("fetch_machines")
    public Iterable<Machine> getAllMachinesCached(){
        return this.repository.findAll();
    }
}
