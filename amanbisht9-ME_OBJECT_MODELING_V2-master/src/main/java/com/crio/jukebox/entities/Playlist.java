package com.crio.jukebox.entities;

import java.util.List;

public class Playlist {
    
    private int playListId;
    public int getPlayListId() {
        return playListId;
    }


    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }


    private int userId;
    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }


    private String playListName;
    public String getPlayListName() {
        return playListName;
    }


    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }


    private List<Integer> playListSongs;

    public List<Integer> getPlayListSongs() {
        return playListSongs;
    }


    public void setPlayListSongs(List<Integer> playListSongs) {
        this.playListSongs = playListSongs;
    }


    public Playlist(Integer userId, String playListName, List<Integer> playListSongs) {
        this.userId = userId;
        this.playListName = playListName;
        this.playListSongs = playListSongs;
    }

    public Playlist(int playListId, int userId, String playListName, List<Integer> playListSongs) {
        this.playListId = playListId;
        this.userId = userId;
        this.playListName = playListName;
        this.playListSongs = playListSongs;
    }


    @Override
    public String toString() {
        return "Playlist [playListId=" + playListId + ", playListName=" + playListName
                + ", playListSongs=" + playListSongs + ", userId=" + userId + "]";
    }

    

    

    
    

    
}
