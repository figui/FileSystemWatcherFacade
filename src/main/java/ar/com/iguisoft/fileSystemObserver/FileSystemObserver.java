package ar.com.iguisoft.fileSystemObserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Snakepit on 19/10/2015.
 */
public class FileSystemObserver implements Observer {

    private Logger logger = LoggerFactory.getLogger(FileSystemObserver.class);

    @Override
    public void update(Observable o, Object arg) {
        WatchEvent<Path> event = (WatchEvent<Path>) arg;
        File file = event.context().toFile();
        if(event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
            logger.info("Creando " + file.getAbsolutePath());
        } else if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
            logger.info("modificando " + file.getAbsolutePath());
        } else if(event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
            logger.info("Eliminando " + file.getAbsolutePath());
        }
    }
}
