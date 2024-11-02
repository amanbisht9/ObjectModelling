package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Playlist;

public interface IPlayListRepository extends CRUDRepository<Playlist,Integer> {

    public Playlist addSongsInPlayList(Integer userId, Integer playListId, List<Integer> playListSongs);
    public Playlist eradicateSongsInPlayList(Integer userId, Integer playListId, List<Integer> playListSongs);
    public Playlist findByUserId(Integer userId);
    
}
