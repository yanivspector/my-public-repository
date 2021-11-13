package com.menora.system.reader;

import models.h2.EventTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventTable,Integer> {
}
