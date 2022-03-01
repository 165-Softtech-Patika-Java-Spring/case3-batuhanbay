package com.softtechbootcamp.case3.app.cmt.entity;

import com.softtechbootcamp.case3.app.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cmt_comment")
@Data
public class CmtComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "id_product", nullable = false)
    private Long productId;

    @Column(name = "comment", nullable = false)
    private String comment;

}
