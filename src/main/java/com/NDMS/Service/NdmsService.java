package com.NDMS.Service;

import java.util.List;

import com.NDMS.Model.NdmsModel;

public interface NdmsService {
    
    List<NdmsModel> getAllDevices();

    NdmsModel getDeviceById(Long deviceId);

    NdmsModel addDevice(NdmsModel device);

    NdmsModel updateDevice(Long deviceId, NdmsModel device);

    void deleteDevice(Long deviceId);

    long getTotalDeviceCount();

    List<String> getDeviceBrandDistribution();
}
