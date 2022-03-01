package com.softtechbootcamp.case3.app.prd.dao;

import com.softtechbootcamp.case3.app.prd.entity.PrdProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PrdProductRepository  extends JpaRepository<PrdProduct, Long> {
    boolean existsAllByNameAndPrice(String name, BigDecimal price);
    boolean existsAllByName(String name);
}
