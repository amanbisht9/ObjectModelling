package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository {

    private final Map<Integer,Song> songMap;

    public SongRepository(){
        songMap = new HashMap<Integer,Song>();
    }

    public SongRepository(Map<Integer,Song> songMap) {
        this.songMap = songMap;
    }

    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        songMap.put(entity.getSongId(),entity);
        return entity;

    }


    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        if (songMap.containsKey(id)) {
            return true;
        }
        
        return false;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Song findById(Integer id) {
        // TODO Auto-generated method stub
        if (songMap.containsKey(id)) {
            return songMap.get(id);
        }
        
        return null;
    }
    
}
