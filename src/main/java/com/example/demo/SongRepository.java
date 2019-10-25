package com.example.demo;

import com.example.demo.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findByName(String name);

    Song findOne(long songId);
}

