package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.StringToList;

public class ModifyPlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
            
        int userId = Integer.parseInt(tokens.get(2));
        int playListId = Integer.parseInt(tokens.get(3));
        List<Integer> playListSongs = new ArrayList<>();

        for(int i = 4; i<=tokens.size()-1; i++){
            playListSongs.add(Integer.parseInt(tokens.get(i)));
        }

        if(tokens.get(1).equals("ADD-SONG")){
            try {
                String result = playlistService.addSongsInPlaylist(userId, playListId, playListSongs);
                System.out.println(result);
                
            } catch (SongNotFoundException e) {
                //TODO: handle exception
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        if(tokens.get(1).equals("DELETE-SONG")){
            try {
                String result = playlistService.eradicateSongsInPlaylist(userId, playListId, playListSongs);
                System.out.println(result);
                
            } catch (SongNotFoundException e) {
                //TODO: handle exception
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }  
    }
    
}
