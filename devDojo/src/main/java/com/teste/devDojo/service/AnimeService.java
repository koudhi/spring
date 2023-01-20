package com.teste.devDojo.service;

import com.teste.devDojo.domain.Anime;
import com.teste.devDojo.exception.BadRequestException;
import com.teste.devDojo.mapper.AnimeMapper;
import com.teste.devDojo.repository.AnimeRepository;
import com.teste.devDojo.requests.AnimePutRequestBody;
import com.teste.devDojo.requests.AnimesPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> listAllNonPageable(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(long id){
        return animeRepository.findById(id).
                orElseThrow(()->new BadRequestException("Anime not found"));
    }

    public List<Anime> findByName(String name){
        return animeRepository.findByName(name);
    }

    public Anime save(AnimesPostRequestBody animesPostRequestBody){
        Anime anime = Anime.builder().name(animesPostRequestBody.getName()).build();
        System.out.println(AnimeMapper.INSTANCE.toAnime(animesPostRequestBody)+",,,"+animesPostRequestBody);
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animesPostRequestBody));
    }
    public void delete(Long id){
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody){
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setAnimeId(savedAnime.getAnimeId());
        animeRepository.save(anime);
    }


}
