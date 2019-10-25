package com.example.demo;

import com.example.demo.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findByName(String name);

    Album findByOne(long albumId);


    Album findOne(long albumId);
}
