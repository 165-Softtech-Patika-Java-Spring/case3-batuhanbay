package com.softtechbootcamp.case3.app.usr.service;

import com.softtechbootcamp.case3.app.cmt.dao.CmtCommentRepository;
import com.softtechbootcamp.case3.app.gen.exceptions.EntityNotFoundExceptions;
import com.softtechbootcamp.case3.app.usr.dto.UsrUserDto;
import com.softtechbootcamp.case3.app.usr.entity.UsrUser;
import com.softtechbootcamp.case3.app.usr.mapper.UsrUserMapper;
import com.softtechbootcamp.case3.app.usr.service.entityservice.UsrUserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsrUserService {
    private UsrUserEntityService usrUserEntityService;
    private CmtCommentRepository cmtCommentRepository;

    public List<UsrUserDto> findAll(){
        List<UsrUser> userList = usrUserEntityService.findAll();
        return UsrUserMapper.INSTANCE.convertToUsrUserDtoList(userList);
    }

    public UsrUserDto findById(Long id){
        UsrUser usrUser = usrUserEntityService.findByIdWithControl(id);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto findByUsername(String username){
        UsrUser usrUser = usrUserEntityService.findUserByUsername(username);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto save(UsrUserDto usrUserDto){
        usrUserEntityService.checkPropertiesUnique(usrUserDto.getUsername(), usrUserDto.getMail(), usrUserDto.getPhoneNumber());
        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserDto);
        return  UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUserEntityService.save(usrUser));
    }

    public UsrUserDto update(UsrUserDto usrUserDto, Long id){
        usrUserEntityService.checkPropertiesUnique(usrUserDto.getUsername(), usrUserDto.getMail(), usrUserDto.getPhoneNumber());
        UsrUser usrUser = usrUserEntityService.findByIdWithControl(id);
        usrUser.setUsername(usrUserDto.getUsername());
        usrUser.setMail(usrUserDto.getMail());
        usrUser.setPhoneNumber(usrUserDto.getPhoneNumber());
        usrUser.setUsrUserType(usrUserDto.getUsrUserType());
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUserEntityService.save(usrUser));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUserByUsernameAndPhoneNumber(Long id, String username, String phoneNumber){
        if (usrUserEntityService.checkValidationForUsernameAndPhoneNumber(username, phoneNumber)){
            throw new EntityNotFoundExceptions(String.format("%s username and %s phone number do not match.",username,phoneNumber));
        }
        if (cmtCommentRepository.existsByUserId(id)){
            cmtCommentRepository.deleteAllByUserId(id);
        }
        usrUserEntityService.deleteByIdWithControl(id);
    }
}
