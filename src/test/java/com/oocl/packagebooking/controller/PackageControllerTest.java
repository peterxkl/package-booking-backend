package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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
    private PackageRepository packageRepository;

    @Before
    public void setUp(){
        List<Package> list = new ArrayList<>();
        list.add(new Package("0000000000","Dillon1","18711345569",1,"2019-07-15 18:20:00"));
        list.add(new Package("0000000001","Dillon2","18711345570",2,"2019-07-16 18:20:00"));
        packageRepository.saveAll(list);
    }

    @Test
    public void should_return_AllPackage() throws Exception {
        mockMvc.perform(get("/packages"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    public void should_return_SomePackageByStatus() throws Exception {
        mockMvc.perform(get("/packages?status={status}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

    }

    @Test
    public void should_return_Package_update_Status() throws Exception {
        mockMvc.perform(post("/packages").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "  \n" +
                "        \"id\": \"0000000000\",\n" +
                "        \"name\": \"Dillon1\",\n" +
                "        \"phone\": \"18711345569\",\n" +
                "        \"status\": 2,\n" +
                "        \"time\": \"2019-07-15 18:20:00\"\n" +
                "\t\n" +
                "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(2));

    }



}