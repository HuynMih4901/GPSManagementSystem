package com.example.demo.service.impl;

import com.example.demo.model.TypeDevice;
import com.example.demo.repository.TypeDeviceRepository;
import com.example.demo.service.TypeDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeDeviceServiceImpl implements TypeDeviceService {
    @Autowired
    private TypeDeviceRepository typeDeviceRepository;
    public TypeDevice addTypeDevice(TypeDevice typeDevice) {
        if(typeDevice != null){
            return typeDeviceRepository.save(typeDevice);
        }
        return null;
    }

    @Override
    public TypeDevice updateTypeDevice(int id, TypeDevice typeDevice) {
        if(typeDevice != null){
            TypeDevice typeDevice1=typeDeviceRepository.getById(id);
            if(typeDevice1 != null){
                typeDevice1.setName(typeDevice.getName());
                typeDevice1.setCode(typeDevice.getCode());
                typeDevice1.setGpsList(typeDevice.getGpsList());

                return typeDeviceRepository.save(typeDevice1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteTypeDevice(int id) {
        if(id>=1){
            TypeDevice typeDevice = typeDeviceRepository.getById(id);
            if(typeDevice !=null){
                typeDeviceRepository.delete(typeDevice);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TypeDevice> getAllTypeDevice() {
        return typeDeviceRepository.findAll();
    }

    @Override
    public TypeDevice getTypeDeviceById(int id) {
        return typeDeviceRepository.getById(id);
    }

    @Override
    public List<TypeDevice> searchTypeDeviceByName(String name) {
        return typeDeviceRepository.findByNameContaining(name);
    }


}
