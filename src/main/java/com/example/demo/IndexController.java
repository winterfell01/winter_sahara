package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    ArtisteRepository artisteRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @RequestMapping("/")
    public String index (Model model) {
        Iterable<Artiste> artistes = artisteRepository.findAll();
        model.addAttribute("artistes", artistes);

        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);

        Iterable<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);

        return "index";
    }
}
