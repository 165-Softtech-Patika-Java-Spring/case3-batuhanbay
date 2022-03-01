package com.softtechbootcamp.case3.app.cmt.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CmtCommentDto {
    @NotNull(message = "User id parameter can not be null")
    Long userId;
    @NotNull(message = "Product id parameter can not be null")
    Long productId;
    @NotNull(message = "Comment parameter can not be null")
    @NotBlank(message = "Comment parameter can not be blank")
    String comment;
}
