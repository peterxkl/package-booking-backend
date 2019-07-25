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


    @GetMapping("/packages")
    public List<Package> getAllPackage(@RequestParam(value = "status",required = false,defaultValue = "-1")int status){
        return aPackage.getAllPackage(status);
    }

//    @GetMapping("/packages")
//    public List<Package> getSomePackageByStatus(@RequestParam(value = "status")int status){
//        return aPackage.getSomePackageByStatus(status);
//    }

    @PutMapping("/packages")
    public Package updatePackageByStatus(@RequestBody Package package1){
        return aPackage.updatePackage(package1);
    }

    @PostMapping("/packages")
    public Package addPackage(@RequestBody Package package1){
        return aPackage.addPackage(package1);
    }


}
