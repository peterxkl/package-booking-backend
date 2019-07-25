package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageImpl {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> getAllPackage(){
        List<Package> list = packageRepository.findAll();
        return list;
    }
}
