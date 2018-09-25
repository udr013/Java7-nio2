package com.udr013.watchservices;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServices{

	public static void main(String[] args){

		WatchService watchService = null;

		try{
			watchService = FileSystems.getDefault().newWatchService(); //throws IOException

			Path path1 = Paths.get("newfolder/another");
			Path path2 = Paths.get("newfolder/other");

			path1.register(watchService, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
			path2.register(watchService, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);

			WatchKey watchKey = null;

			while(true){
				watchKey = watchService.take(); //throws InterruptedException
				for(WatchEvent event : watchKey.pollEvents()){
					WatchEvent.Kind kind = event.kind();
					Path path = (Path) event.context();

					if(kind == OVERFLOW){
						continue;
					} else if(kind == ENTRY_CREATE){
						System.out.format("\nCreate -  %s", path);
					} else if(kind == ENTRY_MODIFY){
						System.out.format("\nModify -  %s", path);
					} else if(kind == ENTRY_DELETE){
						System.out.format("\nDelete -  %s", path);
					}
				}

				if(!watchKey.reset()){
					break;
				}
			}

		} catch(IOException e){
			System.out.println(e.getMessage());
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally{
			try{
				watchService.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
