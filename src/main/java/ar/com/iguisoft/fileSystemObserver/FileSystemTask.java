package ar.com.iguisoft.fileSystemObserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.Observable;

/**
 * Created by Snakepit on 19/10/2015.
 */
public class FileSystemTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(FileSystemTask.class);
    private WatchService watcher;
    private Path path;
    private boolean stopWatch;
    private Observable observables;

    /**
     * @param path
     * @param observables
     * @throws IOException
     */

    public FileSystemTask(Path path, FileSystemObservable observables) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.path = path;
        this.path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        this.observables = observables;
        this.stopWatch = false;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        logger.info("**** Corriendo FileSystemTask *****");
        while(true) {
            try {
                WatchKey key = watcher.take();
                for(WatchEvent<?> event : key.pollEvents()) {
                    if(stopWatch) { break; }
                    observables.notifyObservers((WatchEvent<Path>) event);
                    key.reset();
                }
                if(stopWatch) { logger.info("**** Frenando FileSystem Watcher *****"); break; }
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }
    }

    public void stopWatch() {
        this.stopWatch = true;
    }

    public boolean isRunning() {
        return stopWatch == false;
    }
}
