package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.services.IPlaylistService;

public class SwitchSongCommand implements ICommand {
    private final IPlaylistService playlistService;

    public SwitchSongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub

        int userId = Integer.parseInt(tokens.get(1));
        String action = tokens.get(2);

        try {
            String result = playlistService.switchSong(userId, action);
            System.out.println(result);
        } catch (PlaylistNotFoundException e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        } catch (SongNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
