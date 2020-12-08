package com.java.main.repositories;

import com.java.main.models.Machine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.Set;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class MachineRepositoryTest {

    @Autowired
    private MachineRepository machineRepository;

    @Test
    public void testFindAllReturnNotNull()
    {
        Set<Machine> set = machineRepository.findAll();
        assertNotNull(set);
    }

    @Test
    public void testFindAllReturnNotEmpty()
    {
        Set<Machine> set = machineRepository.findAll();
        assertFalse(set.isEmpty());
    }

}