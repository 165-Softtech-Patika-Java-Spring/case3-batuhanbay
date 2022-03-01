package com.softtechbootcamp.case3.app.cmt.dao;

import com.softtechbootcamp.case3.app.cmt.entity.CmtComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmtCommentRepository extends JpaRepository<CmtComment, Long> {
    List<CmtComment> findAllByUserId(Long userId);
    List<CmtComment> findAllByProductId(Long productId);
    boolean existsByUserId(Long userId);
    boolean existsByProductId(Long productId);
    void deleteAllByUserId(Long userId);
    void deleteAllByProductId(Long productId);
}
