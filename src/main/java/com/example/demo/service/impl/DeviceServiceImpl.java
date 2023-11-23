package com.example.demo.service.impl;

import com.example.demo.model.Device;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService {
    private DeviceRepository deviceRepository;
    @Override
    public Device addDevice(Device device) {
        if(device!=null){
            return deviceRepository.save(device);
        }
        return null;
    }

    @Override
    public Device updateDevice(int id, Device device) {
        if(device!=null){
            Device device1=deviceRepository.getById(id);
            if(device1 !=null){
                device1.setName(device.getName());
                device1.setType(device.getType());
                device1.setCar_info(device.getCar_info());
                device1.setExpirationDate(device.getExpirationDate());
                return deviceRepository.save(device1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteDevice(int id) {
        if(id>=1){
            Device device = deviceRepository.getById(id);
            if(device != null){
                deviceRepository.delete(device);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Device> getAllDevice() {
        return deviceRepository.findAll();
    }

    @Override
    public Device getOneDevice(int id) {
        return deviceRepository.getById(id);
    }

    @Override
    public Device findDevice(int id, Device device) {
        return null;
    }

    @Override
    public Device getDeviceById(int id) {
        return deviceRepository.getById(id);
    }
}
