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
public class ArtisteController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @Autowired
    ArtisteRepository artisteRepository;

    @RequestMapping(value = "/artiste_list", method = RequestMethod.GET)
    public String artisteList (Model model) {
        Iterable<Artiste> artiste_list = artisteRepository.findAll();
        model.addAttribute("artiste_list", artiste_list);
        return "artiste_list";
    }

    @RequestMapping(value = "/artiste_list", method = RequestMethod.POST)
    public String artistesSearch (@RequestParam("search") String nameSearch,
                               Model model) {
        List<Artiste> artistes = artisteRepository.findByName(nameSearch);
        model.addAttribute("artistes", artistes);
        return "artiste_list";
    }

    @RequestMapping("/artiste_list/{artisteId}")
    public String showSong (@PathVariable("artisteId") long artisteId,
                              Model model) {
        Artiste artiste = artisteRepository.findOne(artisteId);
        model.addAttribute("artiste", artiste);
        return "show_artiste";
    }

    @RequestMapping(value = "/artiste_list/add", method = RequestMethod.GET)
    public String addArtistes () {

        return "addArtistes";
    }
    @RequestMapping(value = "/artistes/add", method = RequestMethod.POST)
    public String addArtistePost (@RequestParam("artisteName") String artisteName,
                                 @RequestParam("genre") String genre,
                                 @RequestParam("allAlbums") String allAlbums,
                                 @RequestParam("imgUrl") String imgUrl) {

        Artiste newArtiste = new Artiste();
        newArtiste.setArtisteName(artisteName);
        newArtiste.setGenre(genre);
        newArtiste.setAllAlbums(allAlbums);
        newArtiste.setImgUrl(imgUrl);
        artisteRepository.save(newArtiste);

        return "redirect:/artiste_list";
    }

    @RequestMapping(value = "/artiste/edit/{artisteId}", method = RequestMethod.GET)
    public String editArtiste (@PathVariable("artisteId") long artisteId,
                            Model model) {
        Artiste artiste = artisteRepository.findOne(artisteId);

        model.addAttribute("artiste", artiste);
        return "editArtiste";
    }

    @RequestMapping(value = "/artiste/edit/{artisteId}", method = RequestMethod.POST)
    public String editArtistePost (@PathVariable("artisteId") long artisteId,
                                @RequestParam("artisteName") String artisteName,
                                @RequestParam("genre") String genre,
                                @RequestParam("allAlbums") String allAlbums,
                                @RequestParam("imgUrl") String imgUrl,
                                Model model) {
        Artiste artiste = artisteRepository.findOne(artisteId);
        artiste.setArtisteName(artisteName);
        artiste.setGenre(genre);
        artiste.setAllAlbums(allAlbums);
        artiste.setImgUrl(imgUrl);
        artisteRepository.save(artiste);

        return "redirect:/artiste_list";
    }

}



