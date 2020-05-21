package com.lupachik.staticservice.repository;

import com.lupachik.staticservice.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Page<Event> findAll(Pageable pageable);

    Page<Event> findByType_Title(String title, Pageable pageable);
}
