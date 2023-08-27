package com.NDMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.NDMS.Model.NdmsModel;
import java.util.List;

public interface NdmsRepository extends JpaRepository<NdmsModel, Long> {

    @Query("SELECT brand FROM NdmsModel")
    List<String> getDeviceBrandDistribution();
}
