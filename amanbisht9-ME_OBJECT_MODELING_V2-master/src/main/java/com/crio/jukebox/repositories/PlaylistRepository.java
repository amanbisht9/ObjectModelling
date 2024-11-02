package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepository implements IPlayListRepository {

    ISongRepository songRepository;
    IUserRepository userRepository;

    private final Map<Integer,Playlist> playListMap;
    private Integer autoIncrement = 0;

    public PlaylistRepository(){
        playListMap = new HashMap<Integer,Playlist>();
    }

    public PlaylistRepository(Map<Integer,Playlist> playListMap) {
        this.playListMap = playListMap;
        this.autoIncrement = playListMap.size();
    }


    @Override
    public Playlist save(Playlist entity) {
        // TODO Auto-generated method stub

        autoIncrement++;

        Playlist p = new Playlist(autoIncrement, entity.getUserId(), entity.getPlayListName(), entity.getPlayListSongs());

        playListMap.put(autoIncrement,p);
        return p;
    }

    @Override
    public Playlist addSongsInPlayList(Integer userId, Integer playListId, List<Integer> playListSongs) {
        // TODO Auto-generated method stub
        Playlist p = playListMap.get(playListId);

        List<Integer> existingSongs = new ArrayList<>(p.getPlayListSongs());
        
        for(int sId : playListSongs){
            if(existingSongs.contains(sId) == false){
                existingSongs.add(sId);
            }else{
                continue;
            }
        }

        p.setPlayListSongs(existingSongs);

        playListMap.put(playListId,p);

        return playListMap.get(playListId);
    }

    @Override
    public Playlist eradicateSongsInPlayList(Integer userId, Integer playListId, List<Integer> playListSongs) {
        // TODO Auto-generated method stub
        Playlist p = playListMap.get(playListId);
        List<Integer> existingSongs = new ArrayList<>(p.getPlayListSongs());

        for(int sId : playListSongs){
            existingSongs.remove(Integer.valueOf(sId));
        }
        
        p.setPlayListSongs(existingSongs);

        playListMap.put(playListId,p);

        return playListMap.get(playListId);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        playListMap.remove(id);
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        if(playListMap.containsKey(id)){
            return true;
        }
        return false;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Playlist findById(Integer id) {
        // TODO Auto-generated method stub
        if(playListMap.containsKey(id)){
            return playListMap.get(id);
        }
        return null;
    }

    @Override
    public Playlist findByUserId(Integer userId) {
        // TODO Auto-generated method stub
        for(Playlist playlist : playListMap.values()){
            if(playlist.getUserId() == userId){
                return playlist;
            }
        }
        
        return null;
    }
    
}
