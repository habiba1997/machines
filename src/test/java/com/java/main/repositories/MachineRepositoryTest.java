package com.java.main.repositories;

import com.java.main.models.Machine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MachineRepositoryTest{

    @Mock
    private MachineRepository machineRepository;

    @Test
    public void testFindAllReturnNotNull()
    {
        Set<Machine> set = machineRepository.findAll();
        assertNotNull(set);
    }

}