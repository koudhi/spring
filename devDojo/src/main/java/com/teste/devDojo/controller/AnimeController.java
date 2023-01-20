package com.teste.devDojo.controller;

import com.teste.devDojo.domain.Anime;
import com.teste.devDojo.requests.AnimePutRequestBody;
import com.teste.devDojo.requests.AnimesPostRequestBody;
import com.teste.devDojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
//    private final Date
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable){
        return new ResponseEntity<>(animeService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anime>> list(){
        return new ResponseEntity<>(animeService.listAllNonPageable(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return new ResponseEntity(animeService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Anime> findByName(@RequestParam(required = false) String name){
        return new ResponseEntity(animeService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@Valid @RequestBody AnimesPostRequestBody anime){
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Anime> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<Anime> delete(@RequestBody AnimePutRequestBody anime){
        System.out.println(anime);
        animeService.replace(anime);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
