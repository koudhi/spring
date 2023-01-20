package com.teste.devDojo.mapper;

import com.teste.devDojo.domain.Anime;
import com.teste.devDojo.requests.AnimePutRequestBody;
import com.teste.devDojo.requests.AnimesPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);
    public abstract Anime toAnime(AnimesPostRequestBody animesPostRequestBody);
}
