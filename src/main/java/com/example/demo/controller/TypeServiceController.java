package com.example.demo.controller;



import com.example.demo.model.TypeService;
import com.example.demo.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeService")
public class TypeServiceController {
    @Autowired
    public TypeServiceService typeServiceService;
    @PostMapping("/add")
    public TypeService addTypeService(@RequestBody TypeService typeService){
        return typeServiceService.addTypeService(typeService);

    }
    @PutMapping("/update")
    public TypeService updateTypeService(@RequestParam("id") int id, @RequestBody TypeService typeService){
        return typeServiceService.updateTypeService(id,typeService);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteTypeService(@PathVariable("id") int id){
        return typeServiceService.deleteTypeService(id);
    }
    @GetMapping("/list")
    public List<TypeService> getAllTypeService(){
        return typeServiceService.getAllTypeService();
    }
    @GetMapping("/{id}/detail")
    public TypeService getTypeServiceById(@PathVariable int id){
        return typeServiceService.getTypeServiceById(id);
    }
}
