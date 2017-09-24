package com.reiq.reiqsmod.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Push {

	private static File data_path = new File("./mods/ReiqCraft/Data");
	
	public static void push(String filename, HashMap<String, String> data) {
		
		if (!data_path.exists()) { data_path.mkdirs(); }
		
		File f = new File(data_path + File.separator + filename);
		
		if (!f.exists()) { try { f.createNewFile(); } catch (IOException e) { e.printStackTrace(); }}
		
		Converter.write(data, f);
	}
}
