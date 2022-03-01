package com.softtechbootcamp.case3.app.gen.service;

import com.softtechbootcamp.case3.app.gen.entity.BaseAdditionalFields;
import com.softtechbootcamp.case3.app.gen.entity.BaseEntity;
import com.softtechbootcamp.case3.app.gen.enums.GeneralErrorMessage;
import com.softtechbootcamp.case3.app.gen.exceptions.EntityNotFoundExceptions;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>> {
    private R eRepository;

    public E save(E e){
        setAdditionalFields(e);
        return eRepository.save(e);
    }

    public E findByIdWithControl(Long id){
        E entity =  eRepository.findById(id).orElseThrow(() -> new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND));
        return entity;
    }

    public List<E> findAll(){
        List<E> entityList = eRepository.findAll();
        if (entityList.isEmpty()){
            throw  new EntityNotFoundExceptions(GeneralErrorMessage.ENTITIES_NOT_FOUND);
        }
        return entityList;
    }

    public boolean existsById(Long id){
        return eRepository.existsById(id);
    }

    public void deleteByIdWithControl(Long id){
        E entity = findByIdWithControl(id);
        eRepository.delete(entity);
    }
    public R getRepository() {
        return eRepository;
    }
    private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(1L); //TODO: It will be fixed after JWT token lecture
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(1L); //TODO: It will be fixed after JWT token lecture

    }
}
