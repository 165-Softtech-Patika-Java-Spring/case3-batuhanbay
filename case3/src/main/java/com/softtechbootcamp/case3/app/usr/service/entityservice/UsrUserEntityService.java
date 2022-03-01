package com.softtechbootcamp.case3.app.usr.service.entityservice;

import com.softtechbootcamp.case3.app.gen.exceptions.DoesNotExistExceptions;
import com.softtechbootcamp.case3.app.gen.exceptions.DuplicateEntityExceptions;
import com.softtechbootcamp.case3.app.gen.exceptions.EntityNotFoundExceptions;
import com.softtechbootcamp.case3.app.gen.service.BaseEntityService;
import com.softtechbootcamp.case3.app.usr.dao.UsrUserRepository;
import com.softtechbootcamp.case3.app.usr.entity.UsrUser;
import com.softtechbootcamp.case3.app.usr.enums.UsrErrorMessage;
import org.springframework.stereotype.Service;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserRepository> {

    public UsrUserEntityService(UsrUserRepository eRepository) {
        super(eRepository);
    }

    public UsrUser findUserByUsername(String username){
        if (username.isBlank()){
            throw new DoesNotExistExceptions(UsrErrorMessage.HAS_BLANK_USERNAME_PARAMETER);
        }
        UsrUser usrUser = getRepository().findUsrUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundExceptions(UsrErrorMessage.USER_NOT_FOUND_USERNAME));
        return usrUser;
    }

    public boolean checkValidationForUsernameAndPhoneNumber(String username, String phoneNumber){
        boolean isValid = true;
        if (getRepository().existsByUsernameAndPhoneNumber(username, phoneNumber)){
            isValid=false;
        }
        return  isValid;
    }

    public void checkPropertiesUnique(String name, String email, String phoneNumber){
        UsrUserRepository usrUserRepository = getRepository();
        boolean existName = usrUserRepository.existsAllByUsername(name);
        if (existName){
            throw new DuplicateEntityExceptions(UsrErrorMessage.HAS_DUPLICATE_USER_USERNAME);
        }
        boolean existEmail = usrUserRepository.existsAllByMail(email);
        if (existEmail){
            throw new DuplicateEntityExceptions(UsrErrorMessage.HAS_DUPLICATE_USER_EMAIL);
        }
        boolean existPhoneNumber = usrUserRepository.existsAllByPhoneNumber(phoneNumber);
        if (existPhoneNumber){
            throw new DuplicateEntityExceptions(UsrErrorMessage.HAS_DUPLICATE_USER_PHONE_NUMBER);
        }
    }
}
