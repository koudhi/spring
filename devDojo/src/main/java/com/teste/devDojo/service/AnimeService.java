package com.teste.devDojo.service;

import com.teste.devDojo.domain.Anime;
import com.teste.devDojo.mapper.AnimeMapper;
import com.teste.devDojo.repository.AnimeRepository;
import com.teste.devDojo.requests.AnimePutRequestBody;
import com.teste.devDojo.requests.AnimesPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> listAll(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Anime not found"));
    }
    public Anime save(AnimesPostRequestBody animesPostRequestBody){
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animesPostRequestBody));
    }
    public void delete(Long id){
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody){
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
    }


}
