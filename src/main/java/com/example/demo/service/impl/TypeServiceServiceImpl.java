package com.example.demo.service.impl;

import com.example.demo.model.TypeService;
import com.example.demo.repository.TypeServiceRepository;
import com.example.demo.service.TypeServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TypeServiceServiceImpl implements TypeServiceService {
    @Autowired
    private TypeServiceRepository typeServiceRepository;
    @Override
    public TypeService addTypeService(TypeService typeService) {
        if(typeService != null){
            return typeServiceRepository.save(typeService);
        }
        return null;
    }

    @Override
    public TypeService updateTypeService(int id, TypeService typeService) {
        if(typeService != null){
            TypeService typeService1 = typeServiceRepository.getById(id);
            if(typeService != null){
                typeService1.setName(typeService.getName());
                typeService1.setCode(typeService.getCode());
                typeService1.setPrice(typeService.getPrice());
                typeService1.setServices(typeService.getServices());

                return typeServiceRepository.save(typeService1);
            }
        }
        return null;
    }

    @Override
    public boolean deleteTypeService(int id) {
        if(id>=1){
            TypeService typeService = typeServiceRepository.getById(id);
            if(typeService != null){
                typeServiceRepository.delete(typeService);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<TypeService> getAllTypeService() {
        return typeServiceRepository.findAll();
    }

    @Override
    public TypeService getTypeServiceById(int id) {
        return typeServiceRepository.getById(id);
    }
}
