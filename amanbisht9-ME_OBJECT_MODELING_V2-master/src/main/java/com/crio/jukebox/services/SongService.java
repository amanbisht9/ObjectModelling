package com.crio.jukebox.services;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;
import com.opencsv.CSVReader;

public class SongService implements ISongService {

    ISongRepository songRepository;

    
    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }


    @Override
    public String create(String songsFile) {
        String result = "";
        // TODO Auto-generated method stub
        try (CSVReader reader = new CSVReader(new FileReader(songsFile))) {
            List<String[]> songs = reader.readAll();
            for (String[] record : songs) {
                int songId = Integer.parseInt(record[0]);
                String songName = record[1];
                String songGenre = record[2];
                String albumName = record[3];
                String albumArtist = record[4];
                List<String> featuredArtist = Arrays.asList(record[5].split("#"));
                Song song = new Song(songId, songName, songGenre, albumName, albumArtist, featuredArtist);
                songRepository.save(song);

            }

            result = "Songs Loaded successfully";
            
        } catch (Exception e) {
            //TODO: handle exception
            result = "Exception: "+e.getMessage();

        }
        return result;
    }
    
}
