package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PackageImpl {

    @Autowired
    private PackageRepository packageRepository;

    public List<Package> getAllPackage(int status){
        List<Package> list = new ArrayList<>();
        if(status==-1){
             list = packageRepository.findAll();
        }else{
             list = packageRepository.findAllByStatus(status);
        }
        return list;
    }

    public Package updatePackage(Package package1) {
        Package package2 = packageRepository.findById(package1.getId()).orElse(null);
        if (package1.getPhone()==null){//预约取件，添加时间，修改状态
            package2.setTime(package1.getTime());
            package2.setStatus(0);
        }else{//取件，修改状态，删除时间
            package2.setStatus(2);
            package2.setTime(null);
        }
        packageRepository.save(package2);
        return packageRepository.findById(package1.getId()).orElse(null);
    }

    public Package addPackage(Package package1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = sdf.format(date);
        package1.setStatus(1);
        package1.setTime(time);
        packageRepository.save(package1);

        return packageRepository.findById(package1.getId()).orElse(null);

    }
}
