package com.example.demo.controller;

import com.example.demo.model.Device;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRepository deviceRepository;
//    @PostMapping("/add")
//    public Device addDevice (@RequestBody Device device){
//        return deviceService.addDevice(device);
//    }

//    @PutMapping("/update")
//    public Device updateDevice(@RequestParam("id") int id, @RequestBody Device device){
//        return deviceService.updateDevice(id, device);
//    }

//    @DeleteMapping("delete/{id}")
//    public boolean deleteDevice(@PathVariable("id") int id){
//        return deviceService.deleteDevice(id);
//    }
    @GetMapping("/list")
    public List<Device> getAllDevice(){
        return deviceRepository.findAll();
    }
    // hien thi thong tin chi tin cua ng dung
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById (@PathVariable int id){
        Optional<Device> optionalDevice=deviceRepository.findById(id);

        return optionalDevice.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    // sua thong tin  thiet bi ng dung
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable int id, @RequestBody Device updateDevice){
        Optional<Device> optionalDevice= deviceRepository.findById(id);

        if (optionalDevice.isPresent()){
            Device existingDevice = optionalDevice.get();

            //chi cho phep sua thong tin xe di kem
            existingDevice.setCar_info(updateDevice.getCar_info());
            //luu thong tin da sua
            deviceRepository.save(existingDevice);

            return ResponseEntity.ok(existingDevice);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    //xoa thiet bi khi k dc gia han
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable int id){
        Optional<Device> optionalDevice= deviceRepository.findById((id));

        if(optionalDevice.isPresent()){
            Device existingDevice = optionalDevice.get();
            //ktra xem thiet bi da het han va khong dc gia han them k
            Date currentDate = new Date();
            if(existingDevice.getExpirationDate() !=null && currentDate.after(existingDevice.getExpirationDate())){
                // thiet bi da het ahn va khong gia han them, thuc hien xoa
                deviceRepository.delete(existingDevice);
                return ResponseEntity.noContent().build();
            }else {
                //thiet bi chua het han hoac da dc gia han them
                return ResponseEntity.badRequest().build();
            }
        }else {
            // khong tim thay thiet bi
            return ResponseEntity.notFound().build();
        }
    }
}
