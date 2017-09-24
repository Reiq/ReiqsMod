package com.reiq.reiqsmod.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;

public class Converter {

	public static void write(HashMap<String, String> map, File loc) {
		
		Gson gson = new Gson();

		String json = gson.toJson(map);

		try { BufferedWriter bw = new BufferedWriter(new FileWriter(loc));

		bw.write(json);

		bw.close();}

		catch (IOException e) { e.printStackTrace(); System.out.println("Error writing file."); }

	}

	public static HashMap<String, String> read(File file) {

		Gson gson = new Gson();

		try { BufferedReader br = new BufferedReader(new FileReader(file));

		return gson.fromJson(br, HashMap.class);}

		catch (FileNotFoundException e) { e.printStackTrace(); System.out.println("Error reading file."); }

		return null;

	}

}
