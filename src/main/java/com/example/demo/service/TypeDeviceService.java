package com.example.demo.service;

import com.example.demo.model.TypeDevice;

import java.util.List;

public interface TypeDeviceService {
    public TypeDevice addTypeDevice(TypeDevice typeDevice);
    public TypeDevice updateTypeDevice(int id, TypeDevice typeDevice);
    public boolean deleteTypeDevice(int id);
    public List<TypeDevice> getAllTypeDevice();
    public TypeDevice getTypeDeviceById(int id);
    public List<TypeDevice> searchTypeDeviceByName(String name);
}
