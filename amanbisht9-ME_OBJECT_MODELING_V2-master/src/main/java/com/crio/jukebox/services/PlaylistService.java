package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;

public class PlaylistService implements IPlaylistService {

    IPlayListRepository playListRepository;
    IUserRepository userRepository;
    ISongRepository songRepository;

    private Integer indexValue = 0;

    public PlaylistService(IPlayListRepository playListRepository, IUserRepository userRepository,
            ISongRepository songRepository) {
        this.playListRepository = playListRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public String create(Integer userId, String playListName, List<Integer> playListSongs) throws SongNotFoundException {
        // TODO Auto-generated method stub

        // To check user ID...
        if(userRepository.existsById(userId) == false){
            return "User ID not found. Please try again.";
        }

        // To check song ID...
        for(int songId : playListSongs){
            if(songRepository.existsById(songId) == false){
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }

        Playlist playlist = new Playlist(userId, playListName, playListSongs);

        Playlist result = playListRepository.save(playlist);
        
        return "Playlist ID - "+String.valueOf(result.getPlayListId());
    }

    @Override
    public String eradicate(Integer userId, Integer playListId) throws PlaylistNotFoundException {
        // TODO Auto-generated method stub
        if(playListRepository.existsById(playListId) == true){
            playListRepository.deleteById(playListId);
            return "Delete Successful";
        }

        throw new PlaylistNotFoundException("Playlist Not Found");
    }

    @Override
    public String addSongsInPlaylist(Integer userId, Integer playListId, List<Integer> playListSongs) throws SongNotFoundException {
        // TODO Auto-generated method stub
        
        //Check playlist exist
        if(playListRepository.existsById(playListId) == false){
            return "Playlist not exist";
        }

        //check songs exist which are to be added in playlist present in song pool
        for(int sId : playListSongs){
            if(songRepository.existsById(sId) == false){
                throw new SongNotFoundException("Some Requested Songs Not Available. Please try again.");
            }
        }

        Playlist p = playListRepository.addSongsInPlayList(userId, playListId, playListSongs);
        //formatting for out put [1, 2, 3, 4] to 1 2 3 4
        List<Integer> intList = new ArrayList<>(p.getPlayListSongs());
        String resultIntIDList = intList.stream()
                               .map(String::valueOf)
                               .collect(Collectors.joining(" "));

        return "Playlist ID - "+String.valueOf(p.getPlayListId())+"\n"+"Playlist Name - "+p.getPlayListName()+"\n"+"Song IDs - "+resultIntIDList;
        
    }

    @Override
    public String eradicateSongsInPlaylist(Integer userId, Integer playListId, List<Integer> playListSongs) throws SongNotFoundException {
        // TODO Auto-generated method stub
        //Check playlist exist
        if(playListRepository.existsById(playListId) == false){
            return "Playlist not exist";
        }

        //check songs exist in playlist which are to be deleted
        Playlist playlist = playListRepository.findById(playListId);
        for(int sId : playListSongs){
            if(playlist.getPlayListSongs().contains(sId) == false){
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
            }
        }

        Playlist p = playListRepository.eradicateSongsInPlayList(userId, playListId, playListSongs);
        //formatting for out put [1, 2, 3, 4] to 1 2 3 4
        List<Integer> intList = new ArrayList<>(p.getPlayListSongs());
        String resultIntIDList = intList.stream()
                               .map(String::valueOf)
                               .collect(Collectors.joining(" "));

        return "Playlist ID - "+String.valueOf(p.getPlayListId())+"\n"+"Playlist Name - "+p.getPlayListName()+"\n"+"Song IDs - "+resultIntIDList;
    }

    @Override
    public String playSongFromPlaylist(Integer userId, Integer playListId) throws PlaylistNotFoundException,PlaylistEmptyException {
        // TODO Auto-generated method stub
        if(playListRepository.existsById(playListId) == false){
            throw new PlaylistNotFoundException("Playlist not exist");
        }

        Playlist playlist = playListRepository.findById(playListId);

        if(playlist.getPlayListSongs().isEmpty() == true){
            throw new PlaylistEmptyException("Playlist is empty.");
        }
        
        indexValue = 0;

        Integer songFirstID = playlist.getPlayListSongs().get(indexValue);

        Song songDetails = songRepository.findById(songFirstID);

        return "Current Song Playing"+"\n"+"Song - "+songDetails.getSongName()+"\n"+"Album - "+songDetails.getAlbumName()+"\n"+"Artists - "+String.join(",", songDetails.getFeaturedArtist());
    }

    @Override
    public String switchSong(Integer userId, String action) throws PlaylistNotFoundException,SongNotFoundException {
        // TODO Auto-generated method stub

        Playlist playlist = playListRepository.findByUserId(userId);

        if(playlist == null){
            throw new PlaylistNotFoundException("Playlist not found for user. Please try again.");
        }

        List<Integer> songIds = playlist.getPlayListSongs();

        if(action.matches("^-?\\d+$") == true){
            if(songIds.contains(Integer.parseInt(action)) == false){
                throw new SongNotFoundException("Given song id is not a part of the active playlist");
            }else{
                indexValue = songIds.indexOf(Integer.parseInt(action));
                Song songDetails = songRepository.findById(Integer.parseInt(action));
                return "Current Song Playing"+"\n"+"Song - "+songDetails.getSongName()+"\n"+"Album - "+songDetails.getAlbumName()+"\n"+"Artists - "+String.join(",", songDetails.getFeaturedArtist());
            }
        
        }

        if(action.equals("BACK")){
            if(indexValue == 0){
                indexValue = songIds.size()-1;
                Song songDetails = songRepository.findById(songIds.get(indexValue));
                return "Current Song Playing"+"\n"+"Song - "+songDetails.getSongName()+"\n"+"Album - "+songDetails.getAlbumName()+"\n"+"Artists - "+String.join(",", songDetails.getFeaturedArtist()); 
            }else{
                indexValue = indexValue - 1;
                Song songDetails = songRepository.findById(songIds.get(indexValue));
                return "Current Song Playing"+"\n"+"Song - "+songDetails.getSongName()+"\n"+"Album - "+songDetails.getAlbumName()+"\n"+"Artists - "+String.join(",", songDetails.getFeaturedArtist()); 
            }
            
        }

        if(action.equals("NEXT")){
            if(indexValue == songIds.size()-1){
                indexValue = 0;
                Song songDetails = songRepository.findById(songIds.get(indexValue));
                return "Current Song Playing"+"\n"+"Song - "+songDetails.getSongName()+"\n"+"Album - "+songDetails.getAlbumName()+"\n"+"Artists - "+String.join(",", songDetails.getFeaturedArtist()); 
            }else{
                indexValue = indexValue + 1;
                Song songDetails = songRepository.findById(songIds.get(indexValue));
                return "Current Song Playing"+"\n"+"Song - "+songDetails.getSongName()+"\n"+"Album - "+songDetails.getAlbumName()+"\n"+"Artists - "+String.join(",", songDetails.getFeaturedArtist()); 
            }
        }

        return "Action cannot be interpreted";
    }
    
}
