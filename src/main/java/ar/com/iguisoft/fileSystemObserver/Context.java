package ar.com.iguisoft.fileSystemObserver;

import java.io.IOException;

/**
 * Created by Snakepit on 19/10/2015.
 */
public class Context {

    public static void main(String[] args) throws IOException{
        FileSystemObserver observer = new FileSystemObserver();

        FileSystemManager.watch("C:\\xampp\\htdocs", observer);
        FileSystemManager.watch("C:\\xampp\\htdocs", observer);

        while(true) { }
    }
}
