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
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PackageImplTest {

    @MockBean
    private PackageRepository packageRepository;

    @MockBean
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

    @Test
    public void should_return_new_package() throws  Exception{
        Package p1 = new Package("0000000000","2019-07-15 18:20:00");
        Package p2 = aPackage.updatePackage(p1);
        Package p = new Package("0000000000","Dillon1","18711345569",1);

//        given(packageRepository.findById("0000000000")).willReturn(p);

        assertSame(0,p2.getStatus());

        Package p3 = new Package("0000000000");

        Package p4 = aPackage.updatePackage(p3);

        assertSame(2,p4.getStatus());

    }

}