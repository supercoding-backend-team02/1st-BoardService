package com.supercoding.boardservice.service.mapper;

import com.supercoding.boardservice.dto.posts.PostCreate;
import com.supercoding.boardservice.dto.posts.PostDto;
import com.supercoding.boardservice.dto.posts.PostUpdate;
import com.supercoding.boardservice.repository.posts.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface PostMapper {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "body")
    @Mapping(target = "author", source = "userId")
//    @Mapping(target = "createdAt", source = "writeDt", qualifiedByName = "convert")
    @Mapping(target = "createdAt", source = "writeDt")
    PostDto postEntityToPostDto(PostEntity postEntity);

    @Mapping(target = "postId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "body", source = "content")
    @Mapping(target = "userId", source = "author")
//    @Mapping(target = "writeDt", source = "createdAt", qualifiedByName = "convert")
    @Mapping(target = "writeDt", source = "createdAt")
    PostEntity postDtoTopostEntity(PostDto postDto);

    @Mapping(target = "body", source = "postCreate.content")
//    @Mapping(target = "writeDt", ignore = true, qualifiedByName = "convert")
//    @Mapping(target = "updateDt", ignore = true, qualifiedByName = "convert")
    @Mapping(target = "writeDt", ignore = true)
    @Mapping(target = "updateDt", ignore = true)
    @Mapping(target = "cntLike", constant = "0")
    PostEntity postCreateToPostEntity(String id, PostCreate postCreate);

    @Named("convert")
    static String localDateTimeToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }
}
