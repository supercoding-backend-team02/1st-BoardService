package com.supercoding.boardservice.service.mapper;

import com.supercoding.boardservice.dto.posts.PostCreate;
import com.supercoding.boardservice.dto.posts.PostDto;
import com.supercoding.boardservice.repository.posts.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "body")
    @Mapping(target = "author", source = "userId")
    @Mapping(target = "createdAt", source = "writeDt")
    PostDto postEntityToPostDto(PostEntity postEntity);

    @Mapping(target = "postId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "body", source = "content")
    @Mapping(target = "userId", source = "author")
    @Mapping(target = "writeDt", source = "createdAt")
    PostEntity postDtoTopostEntity(PostDto postDto);

    @Mapping(target = "title", source = "postData.title")
    @Mapping(target = "body", source = "postData.content")
    @Mapping(target = "userId", source = "userId")
    PostEntity postCreateToPostEntity(PostCreate postCreate);
}
