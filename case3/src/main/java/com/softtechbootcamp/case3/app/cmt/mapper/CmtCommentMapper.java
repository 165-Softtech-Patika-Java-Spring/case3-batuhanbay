package com.softtechbootcamp.case3.app.cmt.mapper;

import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentDto;
import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentsByProductDto;
import com.softtechbootcamp.case3.app.cmt.dto.CmtCommentsByUserDto;
import com.softtechbootcamp.case3.app.cmt.entity.CmtComment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CmtCommentMapper {
    CmtCommentMapper INSTANCE = Mappers.getMapper(CmtCommentMapper.class);
    CmtComment convertToCmtComment(CmtCommentDto cmtCommentDto);
    CmtCommentDto convertToCmtCommentDto(CmtComment cmtComment);
    CmtCommentsByUserDto convertToCmtCommentsWithByUserDto(CmtComment cmtComment);
    CmtCommentsByProductDto convertToCmtCommentsByProductDto(CmtComment cmtComment);
    List<CmtCommentsByUserDto> convertToCmtCommentsByUserDtoList(List<CmtComment> commentList);
    List<CmtCommentsByProductDto> convertToCmtCommentsByProductDtoList(List<CmtComment> commentList);
}