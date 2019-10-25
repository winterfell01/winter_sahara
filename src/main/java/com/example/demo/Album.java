package com.example.demo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String albumTitle;
    private String genre;
    private double duration;
    private double albumPrice;
    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "artiste_id")
    private Artiste artiste;

    @OneToMany (mappedBy = "album", cascade = CascadeType.ALL)
    public List<Song> songs;

    public Album() {
    }
    public Album( String genre, String albumTitle,double albumPrice, double duration, String imgUrl, Artiste artiste, List<Song> songs) {

        this.albumTitle = albumTitle;
        this.duration = duration;
        this.genre = genre;
        this.artiste = artiste;
        this.songs = songs;
        this.albumPrice = albumPrice;
        this.imgUrl = imgUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(double albumPrice) {
        this.albumPrice = albumPrice;
    }
}


