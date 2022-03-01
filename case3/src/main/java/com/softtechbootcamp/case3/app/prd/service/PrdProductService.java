package com.softtechbootcamp.case3.app.prd.service;

import com.softtechbootcamp.case3.app.cmt.dao.CmtCommentRepository;
import com.softtechbootcamp.case3.app.prd.dto.PrdProductDto;
import com.softtechbootcamp.case3.app.prd.dto.PrdProductUpdateDto;
import com.softtechbootcamp.case3.app.prd.entity.PrdProduct;
import com.softtechbootcamp.case3.app.prd.mapper.PrdProductMapper;
import com.softtechbootcamp.case3.app.prd.service.entityservice.PrdProductEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PrdProductService {
    private PrdProductEntityService prdProductEntityService;
    private CmtCommentRepository cmtCommentRepository;

    public List<PrdProductDto> findAll(){
        List<PrdProduct> prdProductList = prdProductEntityService.findAll();
        return PrdProductMapper.INSTANCE.convertToPrdProductDtoList(prdProductList);
    }

    public PrdProductDto findById(Long id){
        PrdProduct prdProduct = prdProductEntityService.findByIdWithControl(id);
        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProduct);
    }

    public PrdProductDto save(PrdProductDto prdProductDto){
        prdProductEntityService.checkPropertiesUnique(prdProductDto.getName(), prdProductDto.getPrice());
        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductDto);
        return PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProductEntityService.save(prdProduct));
    }

    public PrdProductDto update(PrdProductUpdateDto prdProductUpdateDto, Long id){
        PrdProduct prdProduct = prdProductEntityService.findByIdWithControl(id);
        prdProduct.setPrice(prdProductUpdateDto.getPrice());
        return  PrdProductMapper.INSTANCE.convertToPrdProductDto(prdProductEntityService.save(prdProduct));
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
       if (cmtCommentRepository.existsByProductId(id)){
           cmtCommentRepository.deleteAllByProductId(id);
       }
        prdProductEntityService.deleteByIdWithControl(id);
    }
}
