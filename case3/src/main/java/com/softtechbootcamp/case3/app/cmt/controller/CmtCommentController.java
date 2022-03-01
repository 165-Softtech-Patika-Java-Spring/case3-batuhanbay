package com.softtechbootcamp.case3.app.cmt.controller;

import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentDto;
import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentsByProductDto;
import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentsByUserDto;
import com.softtechbootcamp.case3.app.cmt.service.CmtCommentService;
import com.softtechbootcamp.case3.app.gen.dto.GeneralResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
@AllArgsConstructor
public class CmtCommentController {

    CmtCommentService cmtCommentService;

    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CmtCommentDto cmtCommentDto){
        CmtCommentDto responseCmtCommentDto = cmtCommentService.save(cmtCommentDto);
        return ResponseEntity.ok(GeneralResponse.of(responseCmtCommentDto));
    }

    @GetMapping("/by/users/{userId}")
    public ResponseEntity findAllByUser(@PathVariable("userId") Long userId){
        List<CmtCommentsByUserDto> cmtCommentsByUserDtoList = cmtCommentService.findCommentsByUser(userId);
        return ResponseEntity.ok(GeneralResponse.of(cmtCommentsByUserDtoList));
    }

    @GetMapping("/by/products/{productId}")
    public ResponseEntity findAllByProduct(@PathVariable("productId") Long productId){
        List<CmtCommentsByProductDto> cmtCommentsByProductDtoList = cmtCommentService.findCommentsByProduct(productId);
        return ResponseEntity.ok(GeneralResponse.of(cmtCommentsByProductDtoList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        cmtCommentService.delete(id);
        return ResponseEntity.ok(GeneralResponse.empty());
    }
}
