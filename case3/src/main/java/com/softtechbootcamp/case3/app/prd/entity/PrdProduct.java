package com.softtechbootcamp.case3.app.prd.entity;

import com.softtechbootcamp.case3.app.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "prd_product")
public class PrdProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", precision = 19, scale = 2, nullable = false)
    private BigDecimal price;
}
