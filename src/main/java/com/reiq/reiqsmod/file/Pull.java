package com.reiq.reiqsmod.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Pull {

	private static File data_path = new File("./mods/ReiqCraft/Data");
	
	public static String pull(String filename, String data) {
		
		File f = new File(data_path + File.separator + filename);
		
		if (f.exists()) {
			
			HashMap<String, String> map = Converter.read(f);
			
			if (map.containsKey(data)) {
				
				return map.get(data);
				
			}
		}
		
		return null;
	}
}
