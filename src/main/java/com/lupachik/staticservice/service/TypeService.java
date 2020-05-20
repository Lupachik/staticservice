package com.lupachik.staticservice.service;

import com.lupachik.staticservice.model.Type;
import com.lupachik.staticservice.repository.TypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeService {
    private final TypeRepository repository;

    public TypeService(TypeRepository repository) {
        this.repository = repository;
    }

    public Type loadTypeByTitle(String title){
        return repository.findByTitle(title);
    }
}
