package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        int userId = Integer.parseInt(tokens.get(1));
        int playListId = Integer.parseInt(tokens.get(2));

        try {
            
            String result = playlistService.playSongFromPlaylist(userId, playListId);
            System.out.println(result);


        } catch (PlaylistNotFoundException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        } catch (PlaylistEmptyException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
