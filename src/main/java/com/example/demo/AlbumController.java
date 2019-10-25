package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    ArtisteRepository artisteRepository;


    @RequestMapping(value = "/album_list", method = RequestMethod.GET)
    public String albums(Model model) {
        Iterable<Album> album_list = albumRepository.findAll();
        model.addAttribute("album_list", album_list);
        return "album_list";
    }

    @RequestMapping(value = "/album_list", method = RequestMethod.POST)
    public String albumSearch(@RequestParam("search") String nameSearch,
                              Model model) {
        List<Album> album_list = albumRepository.findByName(nameSearch);
        model.addAttribute("album_list", album_list);
        return "album_list";
    }

    @RequestMapping("/album_list/{albumId}")
    public String songDetail(@PathVariable("albumId") long albumId,
                             Model model) {
        Album album = albumRepository.findByOne(albumId);
        model.addAttribute("album", album);
        return "show_album";
    }

    @RequestMapping(value = "/album_list/add", method = RequestMethod.GET)
    public String addAlbums(Model model) {
        Iterable<Artiste> artistes = artisteRepository.findAll();
        model.addAttribute("artistes", artistes);
        return "addAlbums";
    }

    @RequestMapping(value = "/albums/add", method = RequestMethod.POST)
    public String addAlbumsPost(@RequestParam("artste") long artisteId,
                                @RequestParam("albumTitle") String albumTitle,
                                @RequestParam("albumPrice") double albumPrice,
                                @RequestParam("duration") double duration,
                                @RequestParam("genre") String genre,
                                @RequestParam("imgUrl") String imgUrl){

        Artiste artiste = artisteRepository.findOne(artisteId);
        Album newAlbum = new Album();
        newAlbum.setArtiste(artiste);
        newAlbum.setAlbumTitle(albumTitle);
        newAlbum.setGenre(genre);
        newAlbum.setAlbumPrice(albumPrice);
        newAlbum.setDuration(duration);
        albumRepository.save(newAlbum);

        return "redirect:/album_list";
    }

    @RequestMapping(value = "/album_list/edit/{albumId}", method = RequestMethod.GET)
    public String editAlbum (@PathVariable("albumId") long albumId,
                             Model model) {
        Iterable<Artiste> artistes = artisteRepository.findAll();
        model.addAttribute("artistes", artistes);

        Album album = albumRepository.findOne(albumId);
        model.addAttribute("album", album);
        return "editAlbum";
    }

    @RequestMapping(value = "/albums/edit/{albumId}", method = RequestMethod.POST)
    public String editAlbumPost (@PathVariable("albumId") long albumId,
                                 @RequestParam("artiste") long artisteId,
                                 @RequestParam("albumTitle") String albumTitle,
                                 @RequestParam("genre") String genre,
                                 @RequestParam("albumPrice") double albumPrice,
                                 @RequestParam("imgUrl") String imgUrl,
                                 Model model) {
//        Artiste artiste = albumRepository.findOne(artisteId);
        Album album = albumRepository.findOne(albumId);

//        album.setArtiste(artiste);
        album.setAlbumTitle(albumTitle);
        album.setGenre(genre);
        album.setAlbumPrice(albumPrice);
        album.setImgUrl(imgUrl);
        albumRepository.save(album);

        return "redirect:/album_list";
    }

}
