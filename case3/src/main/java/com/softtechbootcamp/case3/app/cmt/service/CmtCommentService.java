package com.softtechbootcamp.case3.app.cmt.service;

import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentDto;
import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentsByProductDto;
import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentsByUserDto;
import com.softtechbootcamp.case3.app.cmt.entity.CmtComment;
import com.softtechbootcamp.case3.app.cmt.enums.CmtErrorMessage;
import com.softtechbootcamp.case3.app.cmt.mapper.CmtCommentMapper;
import com.softtechbootcamp.case3.app.cmt.service.entityservice.CmtCommentEntityService;
import com.softtechbootcamp.case3.app.gen.exceptions.EntityNotFoundExceptions;
import com.softtechbootcamp.case3.app.prd.dao.PrdProductRepository;
import com.softtechbootcamp.case3.app.prd.dto.PrdProductDto;
import com.softtechbootcamp.case3.app.prd.enums.PrdErrorMessage;
import com.softtechbootcamp.case3.app.prd.service.PrdProductService;
import com.softtechbootcamp.case3.app.usr.dao.UsrUserRepository;
import com.softtechbootcamp.case3.app.usr.dto.UsrUserDto;
import com.softtechbootcamp.case3.app.usr.enums.UsrErrorMessage;
import com.softtechbootcamp.case3.app.usr.service.UsrUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CmtCommentService {
    CmtCommentEntityService cmtCommentEntityService;
    UsrUserService usrUserService;
    UsrUserRepository usrUserRepository;
    PrdProductService prdProductService;
    PrdProductRepository prdProductRepository;

    public CmtCommentDto save(CmtCommentDto cmtCommentDto){
        if (!usrUserRepository.existsById(cmtCommentDto.getUserId())){
            throw new EntityNotFoundExceptions(UsrErrorMessage.USER_NOT_FOUND_ID);
        }
        if (!prdProductRepository.existsById(cmtCommentDto.getProductId())){
            throw new EntityNotFoundExceptions(PrdErrorMessage.PRODUCT_NOT_FOUND_ID);
        }
        CmtComment cmtComment = CmtCommentMapper.INSTANCE.convertToCmtComment(cmtCommentDto);
        CmtComment cmtCommentResponse = cmtCommentEntityService.save(cmtComment);
        return CmtCommentMapper.INSTANCE.convertToCmtCommentDto(cmtCommentResponse);
    }

    public List<CmtCommentsByUserDto> findCommentsByUser(Long userId){
        UsrUserDto usrUserDto = usrUserService.findById(userId);
        List<CmtComment> cmtCommentList= cmtCommentEntityService.findCommentsByUserId(userId, usrUserDto.getUsername());
        return  CmtCommentMapper.INSTANCE.convertToCmtCommentsByUserDtoList(cmtCommentList);
    }

    public List<CmtCommentsByProductDto> findCommentsByProduct(Long productId){
        PrdProductDto prdProductDto =  prdProductService.findById(productId);
        List<CmtComment> cmtCommentList = cmtCommentEntityService.findCommentsByProductId(productId, prdProductDto.getName());
        return  CmtCommentMapper.INSTANCE.convertToCmtCommentsByProductDtoList(cmtCommentList);
    }


    public void delete(Long id){
       cmtCommentEntityService.deleteByIdWithControl(id);
    }

}
