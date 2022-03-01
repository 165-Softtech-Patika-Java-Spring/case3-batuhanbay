package com.softtechbootcamp.case3.app.cmt.service.entityservice;

import com.softtechbootcamp.case3.app.cmt.dao.CmtCommentRepository;
import com.softtechbootcamp.case3.app.cmt.entity.CmtComment;
import com.softtechbootcamp.case3.app.gen.exceptions.EntityNotFoundExceptions;
import com.softtechbootcamp.case3.app.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmtCommentEntityService extends BaseEntityService<CmtComment, CmtCommentRepository> {
    public CmtCommentEntityService(CmtCommentRepository eRepository) {
        super(eRepository);
    }

    public List<CmtComment> findCommentsByUserId(Long userId, String username){
        CmtCommentRepository cmtCommentRepository = getRepository();
        List<CmtComment> cmtCommentList = cmtCommentRepository.findAllByUserId(userId);
        if (cmtCommentList.isEmpty()){
            throw new EntityNotFoundExceptions(String.format("The user who name is %s, has not any comment for products", username));
        }
        return cmtCommentList;
    }

    public List<CmtComment> findCommentsByProductId(Long productId, String name){
        CmtCommentRepository cmtCommentRepository = getRepository();
        List<CmtComment> cmtCommentList = cmtCommentRepository.findAllByProductId(productId);
        if (cmtCommentList.isEmpty()){
            throw new EntityNotFoundExceptions(String.format("The product which has id as %s, has not any comment by users", name));
        }
        return cmtCommentList;
    }
    public void deleteAllByUserId(Long userId){
        CmtCommentRepository cmtCommentRepository = getRepository();
        cmtCommentRepository.deleteAllByUserId(userId);
    }

    public void deleteAllByProductId(Long productId){
        CmtCommentRepository cmtCommentRepository = getRepository();
        cmtCommentRepository.deleteAllByProductId(productId);
    }
}
