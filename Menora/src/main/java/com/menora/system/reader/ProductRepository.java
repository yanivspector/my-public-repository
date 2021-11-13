package com.menora.system.reader;

import models.h2.ProductTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductTable,Integer>{
}
