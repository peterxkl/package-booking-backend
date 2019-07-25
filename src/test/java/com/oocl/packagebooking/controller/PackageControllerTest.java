package com.oocl.packagebooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import com.oocl.packagebooking.service.PackageImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PackageImpl aPackage;

    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void should_return_AllPackage() throws Exception {
        List<Package> list = new ArrayList<>();
        List<Package> list1 = new ArrayList<>();
        list.add(new Package("0000000000","Dillon1","18711345569",1,"2019-07-15 18:20:00"));
        list.add(new Package("0000000001","Dillon2","18711345570",2,"2019-07-16 18:20:00"));
        list.add(new Package("0000000004","Dillon4","18711345569",2,"2019-07-16 18:20:00"));
        list1.add(new Package("0000000000","Dillon1","18711345569",1,"2019-07-15 18:20:00"));

        given(aPackage.getAllPackage(-1)).willReturn(list);

        given(aPackage.getAllPackage(1)).willReturn(list1);

        mockMvc.perform(get("/packages"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));

        mockMvc.perform(get("/packages?status={status}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }


    @Test
    public void should_return_Package_update_Status()  throws Exception{
        Package p1 = new Package("0000000000","2019-07-15 18:20:00");
        Package p2 = new Package("0000000000","Dillon1","18711345569",0,"2019-07-15 18:20:00");

       Package p3 = new Package("0000000000");
       Package p4 = new Package("0000000000","Dillon1","18711345569",2,"");

        given(aPackage.updatePackage(p2)).willReturn(p1);

        given(aPackage.updatePackage(p3)).willReturn(p4);

        mockMvc.perform(patch("/packages").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(p1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.time").value("2019-07-15 18:20:00"));

        mockMvc.perform(patch("/packages").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(p3)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(2));

    }

    @Test
    public void should_return_Package_when_save_Package() throws Exception {
        Package p1 = new Package("0000000000","Dillon1","18711345569");
        Package p2 = new Package("0000000000","Dillon1","18711345569",1);

        given(aPackage.addPackage(p1)).willReturn(p2);

        mockMvc.perform(post("/packages").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(p1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(1));

    }


}