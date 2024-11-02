package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.appConfig.ApplicationConfig;
import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.NoSuchCommandException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;
import com.opencsv.CSVReader;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"

	public static void main(String[] args) throws FileNotFoundException, IOException {

        

        // String csvFile = "songs.csv";
        // SongRepository songRepository  = new SongRepository();
        // SongService songService  = new SongService(songRepository);
        // songService.create(csvFile);

        // PlaylistRepository playlistRepository = new PlaylistRepository();

        // UserRepository userRepository  = new UserRepository();
        // UserService userService  = new UserService(userRepository);
        // userService.create("Aman");
        // userService.create("Raman");

        // PlaylistService playlistService = new PlaylistService(playlistRepository,userRepository,songRepository);
        // List<Integer> list = Arrays.asList(1, 2, 3 ,4 ,5 ,6);

        // System.out.println(playlistService.create(1, "Yellow", list));
        // System.out.println(playlistService.create(2, "Yellow", list));
        // System.out.println(playlistService.eradicate(2, 2));

        // System.out.println(playlistService.addSongsInPlaylist(1, 1, Arrays.asList(8, 9, 10)));
        // System.out.println(playlistService.eradicateSongsInPlaylist(1, 1, Arrays.asList(1,2,3)));

        // System.out.println("--------------");

        // //System.out.println(playlistService.playSongFromPlaylist(1, 1));
        // System.out.println(playlistService.switchSong(1,"4"));
        // System.out.println(playlistService.switchSong(1,"BACK"));

        

        // UserRepository userRepository  = new UserRepository();
        // UserService userService  = new UserService(userRepository);

        // User result = userService.create("Aman");
        // System.out.println(result.toString());

        // User result1 = userService.create("Aman");
        // System.out.println(result1.toString());



		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));

        System.out.println("actual: "+commandLineArgs.toString());
        
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        BufferedReader reader;
        String inputFile = commandLineArgs.get(0).split("=")[1];
        commandLineArgs.remove(0);
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0),tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }
    }
}
