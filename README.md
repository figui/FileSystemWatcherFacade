# FileSystemWatcherFacade

Facade for Java FileSystems WatchService (1.7 or higher), the Facade runs with threading, its means, for some Path request to watch, a thread is started and keep watch until the program is finish or stop it.

For use it, you must to invoke the "static watch(java.lang.String, java.util.Observer" from "FileSystemManager" class, this keep in track all watchs requested and if the path was requested then the observer will be added into the observable's observer list else, create new Observable object and start new thread watch the requestPath
