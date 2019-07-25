package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.service.PackageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageController {

    @Autowired
    private PackageImpl aPackage;


//    @GetMapping("/packages")
//    public List<Package> getAllPackage(){
//        return aPackage.getAllPackage();
//    }

    @GetMapping("/packages")
    public List<Package> getSomePackageByStatus(@RequestParam(value = "status")int status){
        return aPackage.getSomePackageByStatus(status);
    }

    @PutMapping("/packages")
    public Package updatePackageByStatus(@RequestBody Package package1){
        return aPackage.updatePackageByStatus(package1);
    }
}
