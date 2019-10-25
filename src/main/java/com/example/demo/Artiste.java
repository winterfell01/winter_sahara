package com.example.demo;

import com.example.demo.Album;
import com.example.demo.Song;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artiste")
public class Artiste {
    private  long id;
    private String artisteName;
    private String genre;
    private String allAlbums;
    @Column(name = "img_url")
    private String imgUrl;

    @OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL)
    private List<Album> albums;

    @OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL)
    private List<Song> songs;

    public Artiste () {

    }

    public Artiste(String artisteName, String genre, String allAlbums, String imgUrl, List<Album> albums, List<Song> songs) {
        this.artisteName = artisteName;
        this.allAlbums = allAlbums;
        this.genre = genre;
        this.imgUrl = imgUrl;
        this.albums = albums;
        this.songs = songs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtisteName() {
        return artisteName;
    }

    public void setArtisteName(String artisteName) {
        this.artisteName = artisteName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAllAlbums() {
        return allAlbums;
    }

    public void setAllAlbums(String allAlbums) {
        this.allAlbums = allAlbums;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
