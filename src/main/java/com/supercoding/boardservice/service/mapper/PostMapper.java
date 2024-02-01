package com.supercoding.boardservice.service.mapper;

import com.supercoding.boardservice.dto.posts.PostDto;
import com.supercoding.boardservice.repository.posts.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postData.title", source = "title")
    @Mapping(target = "postData.content", source = "body")
//    @Mapping(target = "author", source = "userEntity.user_id")
    @Mapping(target = "author", source = "userId")
    @Mapping(target = "createdAt", source = "writeDt")
    PostDto postEntityToPostDto(PostEntity postEntity);

    @Mapping(target = "postId", source = "id")
    @Mapping(target = "title", source = "postData.title")
    @Mapping(target = "body", source = "postData.content")
//    @Mapping(target = "author", source = "userEntity.user_id")
    @Mapping(target = "userId", source = "author")
    @Mapping(target = "writeDt", source = "createdAt")
    PostEntity PostDtoTopostEntity(PostDto postDto);
}
