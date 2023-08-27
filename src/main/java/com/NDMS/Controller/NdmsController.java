package com.NDMS.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.NDMS.Service.NdmsService;

import jakarta.validation.Valid;

import com.NDMS.Exception.DataValidationException;
import com.NDMS.Model.NdmsModel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Devices")
public class NdmsController {
    
    private final NdmsService ndmsService;

    public NdmsController(NdmsService ndmsService) {
        this.ndmsService = ndmsService;
    }

    @GetMapping
    public ResponseEntity<List<NdmsModel>> getAllDevices() {
        List<NdmsModel> devices = ndmsService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<NdmsModel> getDeviceById(@PathVariable Long deviceId) {
        NdmsModel device = ndmsService.getDeviceById(deviceId);
        if (device != null) {
            return new ResponseEntity<>(device, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> addDevice(@Valid @RequestBody NdmsModel device, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> validationErrors = new ArrayList<>();
            for (ObjectError error : bindingResult.getAllErrors()) {
                validationErrors.add(error.getDefaultMessage());
            }
            throw new DataValidationException("Data validation errors: " + validationErrors);
        }
        
        NdmsModel addedDevice = ndmsService.addDevice(device);
        return new ResponseEntity<>(addedDevice, HttpStatus.CREATED);
    }

    @PostMapping("/add-multiple")
    public ResponseEntity<List<NdmsModel>> addMultipleDevices(@RequestBody List<NdmsModel> devices) {
        List<NdmsModel> addedDevices = new ArrayList<>();
        
        for (NdmsModel device : devices) {
            NdmsModel addedDevice = ndmsService.addDevice(device);
            if (addedDevice != null) {
                addedDevices.add(addedDevice);
            }
        }
        
        return new ResponseEntity<>(addedDevices, HttpStatus.CREATED);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<NdmsModel> updateDevice(@PathVariable Long deviceId, @RequestBody NdmsModel device) {
        NdmsModel updatedDevice = ndmsService.updateDevice(deviceId, device);
        if (updatedDevice != null) {
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long deviceId) {
        ndmsService.deleteDevice(deviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Long> getTotalDeviceCount() {
        long totalDeviceCount = ndmsService.getTotalDeviceCount();
        return new ResponseEntity<>(totalDeviceCount, HttpStatus.OK);
    }

    @GetMapping("/stats/brand-distribution")
    public ResponseEntity<List<String>> getDeviceBrandDistribution() {
        List<String> brandDistribution = ndmsService.getDeviceBrandDistribution();
        return new ResponseEntity<>(brandDistribution, HttpStatus.OK);
    }
}
