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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void should_return_Package_update_Status() throws Exception {
        Package p = new Package("0000000000","Dillon1","18711345569",2,"2019-07-15 18:20:00");
        mockMvc.perform(post("/packages").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(p)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(2));

    }

    @Test
    public void should_return_Package_when_save_Package() throws Exception {
        mockMvc.perform(post("/packages").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "        \"id\": \"0000000004\",\n" +
                "        \"name\": \"Dillon4\",\n" +
                "        \"phone\": \"18711345569\"\n" +
                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dillon4"));

    }


}