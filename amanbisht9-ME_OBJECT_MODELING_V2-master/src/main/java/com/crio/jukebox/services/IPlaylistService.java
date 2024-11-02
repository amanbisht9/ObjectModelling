package com.crio.jukebox.services;

import java.util.List;

public interface IPlaylistService {

    public String create(Integer userId, String playListName, List<Integer> playListSongs);

    public String eradicate(Integer userId, Integer playListId);

    public String addSongsInPlaylist(Integer userId, Integer playListId, List<Integer> playListSongs);

    public String eradicateSongsInPlaylist(Integer userId, Integer playListId, List<Integer> playListSongs);

    public String playSongFromPlaylist(Integer userId, Integer playListId);

    public String switchSong(Integer userId, String action);
    
}
