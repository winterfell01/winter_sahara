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
public class SongController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    ArtisteRepository artisteRepository;

    @RequestMapping(value = "/song_list", method = RequestMethod.GET)
    public String songs (Model model) {
        Iterable<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "song_list";
    }

    @RequestMapping(value = "/song_list", method = RequestMethod.POST)
    public String songsSearch (@RequestParam("search") String nameSearch,
                               Model model) {
        List<Song> songs = songRepository.findByName(nameSearch);
        model.addAttribute("songs", songs);
        return "song_list";
    }

    @RequestMapping("/song_list/{songId}")
    public String songDetail (@PathVariable("songId") long songId,
                              Model model) {
        Song song = songRepository.findOne(songId);
        model.addAttribute("song", song);
        return "song_list";
    }

    @RequestMapping(value = "/songs/add", method = RequestMethod.GET)
    public String addSongs (Model model) {
        Iterable<Artiste> artistes = artisteRepository.findAll();
        model.addAttribute("artistes", artistes);

        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);

        return "addSongs";
    }

    @RequestMapping(value = "/songs/add", method = RequestMethod.POST)
    public String addSongsPost (@RequestParam("artiste") long artisteId,
                                @RequestParam("album") long albumId,
                                @RequestParam("songTitle") String songTitle,
                                @RequestParam("duration") double duration) {
        Artiste artiste = artisteRepository.findOne(artisteId);
        Album album = albumRepository.findOne(albumId);

        Song newSong = new Song();
        newSong.setSongTitle(songTitle);
        newSong.setDuration(duration);
        newSong.setArtiste(artiste);
        newSong.setAlbum(album);

        songRepository.save(newSong);

        return "redirect:/song_list";
    }

    @RequestMapping(value = "/song_list/edit/{songId}", method = RequestMethod.GET)
    public String editSong (@PathVariable("songId") long songId,
                            Model model) {
        Iterable<Artiste> artistes = artisteRepository.findAll();
        model.addAttribute("artistes", artistes);

        Iterable<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);

        Song song = songRepository.findOne(songId);
        model.addAttribute("song", song);
        return "editSong";
    }

    @RequestMapping(value = "/song_list/edit/{songId}", method = RequestMethod.POST)
    public String editAlbumPost (@PathVariable("songId") long songId,
                                 @RequestParam("artiste") long artisteId,
                                 @RequestParam("album") long albumId,
                                 @RequestParam("songTitle") String songTitle,
                                 @RequestParam("duartion") double duration,
                                 Model model) {
        Artiste artiste = artisteRepository.findOne(artisteId);
        Album album = albumRepository.findOne(albumId);
        Song song = songRepository.findOne(songId);
        song.setArtiste(artiste);
        song.setAlbum(album);
        song.setSongTitle(songTitle);
        song.setDuration(duration);
        songRepository.save(song);

        return "redirect:/song_list";
    }
}


