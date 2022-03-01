package com.softtechbootcamp.case3.app.prd.service.entityservice;

import com.softtechbootcamp.case3.app.gen.exceptions.DuplicateEntityExceptions;
import com.softtechbootcamp.case3.app.gen.service.BaseEntityService;
import com.softtechbootcamp.case3.app.prd.dao.PrdProductRepository;
import com.softtechbootcamp.case3.app.prd.entity.PrdProduct;
import com.softtechbootcamp.case3.app.prd.enums.PrdErrorMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PrdProductEntityService extends BaseEntityService<PrdProduct, PrdProductRepository> {
    public PrdProductEntityService(PrdProductRepository eRepository) {
        super(eRepository);
    }

    public void checkPropertiesUnique(String name, BigDecimal price){
        PrdProductRepository prdProductRepository = getRepository();
        if (prdProductRepository.existsAllByName(name)){
            throw new DuplicateEntityExceptions(PrdErrorMessage.HAS_DUPLICATE_PRODUCT_NAME);
        }
        if (prdProductRepository.existsAllByNameAndPrice(name, price)){
            throw new DuplicateEntityExceptions(PrdErrorMessage.HAS_DUPLICATE_PRODUCT_NAME_AND_PRICE);
        }
    }
}
