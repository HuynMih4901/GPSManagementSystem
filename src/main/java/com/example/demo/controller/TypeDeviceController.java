package com.example.demo.controller;

import com.example.demo.model.TypeDevice;
import com.example.demo.service.TypeDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typedevice")
public class TypeDeviceController {
    @Autowired
    private TypeDeviceService typeDeviceService;
    @PostMapping("/add")
    public TypeDevice addTypeDevice(@RequestBody TypeDevice typeDevice){
        return typeDeviceService.addTypeDevice(typeDevice);
    }

    @PutMapping("/update")
    public TypeDevice updateTypeDevice(@RequestParam("id") int id, @RequestBody TypeDevice typeDevice ){
        return typeDeviceService.updateTypeDevice(id,typeDevice);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteTypeDevice(@PathVariable("id") int id){
        return typeDeviceService.deleteTypeDevice(id);
    }
    @GetMapping("/list")
    public List<TypeDevice> getAllTypeDevice(){
        return typeDeviceService.getAllTypeDevice();
    }
    @GetMapping("/{id}/detail")
    public  TypeDevice getTypeDeviceById(@PathVariable int id){
        return typeDeviceService.getTypeDeviceById(id);
    }
    //tim kiem theo ten
    @GetMapping("/search")
    public List<TypeDevice> searchTypeDevice(@RequestParam String name){
        return typeDeviceService.searchTypeDeviceByName(name);
    }
}
