package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PackageImplTest {

    @MockBean
    private PackageRepository packageRepository;

    @Autowired
    private PackageImpl aPackage;

    @Test
    public void should_return_All_package(){
        List<Package> list = new ArrayList<>();
        List<Package> list1 = new ArrayList<>();
        list.add(new Package("0000000000","Dillon1","18711345569",1,"2019-07-15 18:20:00"));
        list.add(new Package("0000000001","Dillon2","18711345570",2,"2019-07-16 18:20:00"));
        list.add(new Package("0000000004","Dillon4","18711345569",2,"2019-07-16 18:20:00"));
        list1.add(new Package("0000000000","Dillon1","18711345569",1,"2019-07-15 18:20:00"));

        given(packageRepository.findAll()).willReturn(list);
        given(packageRepository.findAllByStatus(1)).willReturn(list1);


        assertSame(list.size(),aPackage.getAllPackage(-1).size());
        assertSame(list1.size(),aPackage.getAllPackage(1).size());

    }

}