package com.reiq.reiqsmod.Chat;

import java.util.HashMap;

import com.reiq.reiqsmod.Util;
import com.reiq.reiqsmod.Chat.ParseType.Type;

public class ParseInfo {

	public static HashMap<String, String> parse(Type t, String mu) {

		HashMap<String, String> map = new HashMap<String, String>();

		switch(t) {

		case CHAT: return getChat(mu);

		case PVP: return getPvp(mu);

		case COINS: return getCoins(mu);

		case STREAK: return getStreak(mu);

		case SHUTDOWN: return getShutdown(mu);
		
		case POWERUP: return getPowerup(mu);

		case END: return getEnd(mu);

		default: return map; }

	}
	
	private static HashMap<String, String> getPowerup(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();
		
		String[] s = mu.split("[ ]+");
		
		map.put("name", s[0]);
		
		String type = s[3] + " " + s[4];
		
		map.put("type", type);
		
		return map;
	}
	
	private static HashMap<String, String> getChat(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();
		
		String type = "chat";
		
		for (String s : Util.getChats()) {
			
			if(mu.startsWith(s)) {
				
				mu.replace(s, "").trim();
				
				type = s.replace("§","").replace("2","").replace("9","").replace(">","").toLowerCase().trim();
			}
		}
		
		map.put("type", type);
		
		String[] t1 = mu.split("[:]+");
		
		String d = t1[0], msg = mu.replace(d + ": ", ""), rank = "non", name = d;
		
		for (String s : Util.getRanks()) { if (d.startsWith(s)) { rank = s; name = d.replace(s, "");}}
		
		map.put("rank", rank);
		map.put("name", name);
		map.put("message", msg);
		
		return map;
	}
	
	private static HashMap<String, String> getEnd(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();
		
		mu = mu.trim();
		
		String[] tokens = mu.split("[-]+");
		
		map.put("winner", tokens[1].trim());
		
		return map;
	}

	private static HashMap<String, String> getShutdown(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();
		
		String[] tokens = mu.split("[ ]+");
		
		map.put("shut", tokens[0]);
		map.put("shot", tokens[4]);
		
		return map;
	}

	private static HashMap<String, String> getStreak(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();

		String[] tokens = mu.split("[ ]+");

		String 
		name = tokens[0], 
		type = mu.replace(name + " ", "");

		map.put("name", name);
		map.put("type", type);

		return map;
	}

	private static HashMap<String, String> getPvp(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();

		String[] tokens = mu.split("[ ]+");

		boolean hs = false;

		if (tokens.length == 4) { hs = true; }
		
		map.put("killer", tokens[0]);
		map.put("message", tokens[1]);
		map.put("victim", tokens[2]);
		map.put("headshot", String.valueOf(hs));  

		return map;
	}

	private static HashMap<String, String> getCoins(String mu) {

		HashMap<String, String> map = new HashMap<String, String>();

		String[] tokens = mu.split("[ ]+");

		String a = tokens[0].replace("+", "");

		map.put("coins", a);

		return map;
	}



}
