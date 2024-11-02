package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand {
    private final IPlaylistService playlistService;

    public DeletePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        try {
            int userId = Integer.parseInt(tokens.get(1));
            int playListId = Integer.parseInt(tokens.get(2));
            String result = playlistService.eradicate(userId, playListId);
            System.out.println(result);
            
        } catch (PlaylistNotFoundException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
        
    }


    
}
