package com.softtechbootcamp.case3.app.usr.controller;

import com.softtechbootcamp.case3.app.gen.dto.GeneralResponse;
import com.softtechbootcamp.case3.app.usr.dto.UsrUserDto;
import com.softtechbootcamp.case3.app.usr.service.UsrUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsrUserController {

    private UsrUserService usrUserService;

    @GetMapping
    public ResponseEntity findAll(){
        List<UsrUserDto> usrUserDtoList = usrUserService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(usrUserDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id){
        UsrUserDto usrUserDto = usrUserService.findById(id);
        return ResponseEntity.ok(GeneralResponse.of(usrUserDto));
    }

    @GetMapping("/by/username/{username}")
    public ResponseEntity findByUsername(@PathVariable("username") String username){
        UsrUserDto usrUserDto = usrUserService.findByUsername(username);
        return  ResponseEntity.ok(GeneralResponse.of(usrUserDto));
    }

    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid UsrUserDto usrUserDto){
        UsrUserDto responseUsrUserDto = usrUserService.save(usrUserDto);
        return ResponseEntity.ok(GeneralResponse.of(responseUsrUserDto));
    }

    @Validated
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid UsrUserDto usrUserDto, @PathVariable("id") Long id){
        UsrUserDto responseUsrUserDto = usrUserService.update(usrUserDto, id);
        return ResponseEntity.ok(GeneralResponse.of(responseUsrUserDto));
    }

    @DeleteMapping("{id}/by/username/{username}/and/phoneNumber/{phoneNumber}")
    public  ResponseEntity delete(@PathVariable("id") Long id,@PathVariable("username") String username,@PathVariable("phoneNumber") String phoneNumber){
        usrUserService.deleteUserByUsernameAndPhoneNumber(id, username, phoneNumber);
        return ResponseEntity.ok(GeneralResponse.empty());
    }

}
