package com.NDMS.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.NDMS.Model.NdmsModel;
import com.NDMS.Repository.NdmsRepository;

@Service
public class NdmsServiceImpl implements NdmsService {
    private final NdmsRepository ndmsRepository;

    public NdmsServiceImpl(NdmsRepository ndmsRepository) {
        this.ndmsRepository = ndmsRepository;
    }

    @Override
    public List<NdmsModel> getAllDevices() {
        return ndmsRepository.findAll();
    }

    @Override
    public NdmsModel getDeviceById(Long deviceId) {
        return ndmsRepository.findById(deviceId).orElse(null);
    }

    @Override
    public NdmsModel addDevice(NdmsModel device) {
        return ndmsRepository.save(device);
    }

    @Override
    public NdmsModel updateDevice(Long deviceId, NdmsModel device) {
        if (ndmsRepository.existsById(deviceId)) {
            device.setId(deviceId);
            return ndmsRepository.save(device);
        }
        return null;
    }

    @Override
    public void deleteDevice(Long deviceId) {
        ndmsRepository.deleteById(deviceId);
    }

    @Override
    public long getTotalDeviceCount() {
        return ndmsRepository.count();
    }

    @Override
    public List<String> getDeviceBrandDistribution() {
        return ndmsRepository.getDeviceBrandDistribution();
    }

}
