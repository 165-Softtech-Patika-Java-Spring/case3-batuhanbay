package com.softtechbootcamp.case3.app.prd.controller;

import com.softtechbootcamp.case3.app.gen.dto.GeneralResponse;
import com.softtechbootcamp.case3.app.prd.dto.PrdProductDto;
import com.softtechbootcamp.case3.app.prd.dto.PrdProductUpdateDto;
import com.softtechbootcamp.case3.app.prd.service.PrdProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class PrdProductController {

    PrdProductService prdProductService;

    @GetMapping
    public ResponseEntity findAll(){
        List<PrdProductDto> prdProductDtoList = prdProductService.findAll();
        return ResponseEntity.ok(GeneralResponse.of(prdProductDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id){
        PrdProductDto prdProductDto = prdProductService.findById(id);
        return ResponseEntity.ok(GeneralResponse.of(prdProductDto));
    }
    @Validated
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid PrdProductDto prdProductDto){
        PrdProductDto responsePrdProductDto = prdProductService.save(prdProductDto);
        return ResponseEntity.ok(GeneralResponse.of(responsePrdProductDto));
    }

    @Validated
    @PatchMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid PrdProductUpdateDto prdProductUpdateDto , @PathVariable("id") Long id){
        PrdProductDto responsePrdProductDto = prdProductService.update(prdProductUpdateDto, id);
        return ResponseEntity.ok(GeneralResponse.of(responsePrdProductDto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity delete(@PathVariable("id") Long id){
        prdProductService.delete(id);
        return ResponseEntity.ok(GeneralResponse.empty());
    }
}
