package com.example.demo.service;

import com.example.demo.model.TypeService;

import java.util.List;

public interface TypeServiceService {
    public TypeService addTypeService(TypeService typeService);

    public TypeService updateTypeService(int id, TypeService typeService);

    public boolean deleteTypeService(int id);

    public List<TypeService> getAllTypeService();

    public TypeService getTypeServiceById( int id);
}
