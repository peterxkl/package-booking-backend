package com.oocl.packagebooking.repository;

import com.oocl.packagebooking.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PackageRepository extends JpaRepository<Package,String> {

    List<Package> findAllByStatus(int status);

}
