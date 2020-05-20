package com.lupachik.staticservice.repository;

import com.lupachik.staticservice.model.Event;
import com.lupachik.staticservice.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByTypeOrderByDateDescNameAsc(String type);

    List<Event> findByOrderByDateDescNameAsc();

    @Query("SELECT e FROM Event e join fetch e.type where e.type.title=?1 order by e.date DESC, e.name ASC")
    List<Event> findAllByType(String title);
}
