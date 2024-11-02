package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.StringToList;

public class CreatePlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        try {
            
            int userId = Integer.parseInt(tokens.get(1));
            String playListName = tokens.get(2);
            List<Integer> playListSongs = new ArrayList<>();

            for(int i = 3; i<=tokens.size()-1; i++){
                playListSongs.add(Integer.parseInt(tokens.get(i)));
            }

            String result = playlistService.create(userId, playListName, playListSongs);
            System.out.println(result);

        } catch (SongNotFoundException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
