package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.Package;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class PackageRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void should_return_Some_Package(){
            Package p1 = new Package("0000000000","Dillon1","18711345569",0,"2019-07-15 18:20:00");
            Package p2 = new Package("0000000001","Dillon2","18711345570",0,"2019-07-15 18:20:00");
            testEntityManager.persist(p1);
            testEntityManager.persist(p2);

            List<Package> list = packageRepository.findAllByStatus(0);

            assertSame(2,list.size());
    }

}