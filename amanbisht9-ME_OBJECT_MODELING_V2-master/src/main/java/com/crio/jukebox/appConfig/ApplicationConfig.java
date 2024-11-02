package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.SwitchSongCommand;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {

    private final IUserRepository userRepository = new UserRepository();
    private final ISongRepository songRepository = new SongRepository();
    private final IPlayListRepository playlistRepository = new PlaylistRepository();

    private final UserService userService = new UserService(userRepository);
    private final SongService songService = new SongService(songRepository);
    private final PlaylistService playlistService = new PlaylistService(playlistRepository, userRepository, songRepository);

    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final LoadDataCommand loadDataCommand  = new LoadDataCommand(songService);
    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final PlayPlaylistCommand playlistCommand = new PlayPlaylistCommand(playlistService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    private final SwitchSongCommand switchSongCommand = new SwitchSongCommand(playlistService);


    CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("CREATE-USER",createUserCommand);
        commandInvoker.register("LOAD-DATA",loadDataCommand);
        commandInvoker.register("CREATE-PLAYLIST",createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST",deletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST",playlistCommand);
        commandInvoker.register("MODIFY-PLAYLIST",modifyPlaylistCommand);
        commandInvoker.register("PLAY-SONG",switchSongCommand);
        
        return commandInvoker;
    }

    


    
}
