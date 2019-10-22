package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String albumTitle;
    private String artisteName;
    private String genre;
    private double duration;

    @OneToMany(mappedBy = "album",cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REMOVE },fetch = FetchType.EAGER)
    public Set<Song> songs;

    public Album() {
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getAlbumTitle() { return albumTitle; }

    public void setAlbumTitle(String albumTitle) { this.albumTitle = albumTitle; }

    public String getArtisteName() { return artisteName; }

    public void setArtisteName(String artisteName) { this.artisteName = artisteName; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public Set<Song> getSongs() { return songs; }

    public void setSongs(Set<Song> songs) { this.songs = songs; }

    public double getDuration() { return duration; }

    public void setDuration(double duration) { this.duration = duration; }
}


