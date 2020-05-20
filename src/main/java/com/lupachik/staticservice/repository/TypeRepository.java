package com.lupachik.staticservice.repository;

import com.lupachik.staticservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findByTitle(String title);
}
