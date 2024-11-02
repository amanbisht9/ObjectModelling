package com.crio.jukebox.entities;

import java.util.List;

public class Song {

    private int songId;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    private String songName;

    public String getSongName() {
        return songName;
    }

    private String songGenre;

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    private String albumName;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    private String albumArtist;

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    private List<String> featuredArtist;

    public List<String> getFeaturedArtist() {
        return featuredArtist;
    }

    public void setFeaturedArtist(List<String> featuredArtist) {
        this.featuredArtist = featuredArtist;
    }

    public Song(int songId, String songName, String songGenre, String albumName, String albumArtist, List<String> featuredArtist) {
        this.songId = songId;
        this.songName = songName;
        this.songGenre = songGenre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.featuredArtist = featuredArtist;

    }

    @Override
    public String toString() {
        return "Song [albumArtist=" + albumArtist + ", albumName=" + albumName + ", featuredArtist="
                + featuredArtist + ", songGenre=" + songGenre + ", songId=" + songId + ", songName="
                + songName + "]";
    }

    
    
}
