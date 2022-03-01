package com.softtechbootcamp.case3.app.prd.mapper;

import com.softtechbootcamp.case3.app.prd.dto.PrdProductDto;
import com.softtechbootcamp.case3.app.prd.entity.PrdProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PrdProductMapper {
    PrdProductMapper INSTANCE = Mappers.getMapper(PrdProductMapper.class);
    PrdProductDto convertToPrdProductDto(PrdProduct prdProduct);
    PrdProduct convertToPrdProduct(PrdProductDto prdProductDto);
    List<PrdProductDto> convertToPrdProductDtoList(List<PrdProduct> prdProductList);
}
