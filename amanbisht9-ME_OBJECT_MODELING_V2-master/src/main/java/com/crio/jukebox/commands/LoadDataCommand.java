package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand {

    private final ISongService songService;

    public LoadDataCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String filePath = tokens.get(1);
        String result = songService.create(filePath);
        System.out.println(result);
        
    }
    
}
