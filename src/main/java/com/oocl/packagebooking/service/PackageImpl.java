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

    public List<Package> getSomePackageByStatus(int status) {
        List<Package> list1 = packageRepository.findAllByStatus(status);

        return list1;
    }

    public Package updatePackageByStatus(Package package1) {
        Package package2 = packageRepository.findById(package1.getId()).orElse(null);
        package2.setStatus(package1.getStatus());
        packageRepository.save(package2);

        return packageRepository.findById(package1.getId()).orElse(null);
    }
}
