package com.example.demo.service;

import com.example.demo.model.Device;

import java.util.List;

public interface DeviceService {
    public Device addDevice (Device device);
    public Device updateDevice (int id, Device device);
    public boolean deleteDevice (int id);
    public List<Device> getAllDevice();
    public Device getOneDevice(int id);
    public Device findDevice(int id, Device device);
    public Device getDeviceById(int id);

}
