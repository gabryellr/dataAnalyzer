package com.gabryelrock.core.temaFinal;

import com.gabryelrock.core.temaFinal.ErrorHandler.ErrorListenDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.*;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Service
public class ListenDirectory {

    @Autowired
    DatFile datFile;

    @Value("${path.file}" )
    private String fileName;

    @Value("${file.extension}")
    private String fileExtension;

    public void run(){
        Path path = Paths.get(fileName);

        try{
            WatchService watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

                WatchKey key;
                while ((key = watchService.take()) != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if(event.context().toString().endsWith(fileExtension)){
                            datFile.execute();
                        }
                    }
                    key.reset();
                }

        }  catch (IOException | InterruptedException error){
            throw new ErrorListenDirectory("error while listening the folder");
        }
    }
}
