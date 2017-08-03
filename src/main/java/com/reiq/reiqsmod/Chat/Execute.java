package com.reiq.reiqsmod.Chat;

import java.util.HashMap;

import org.apache.commons.lang3.math.NumberUtils;

import com.reiq.reiqsmod.ReiqsMod;
import com.reiq.reiqsmod.Util;
import com.reiq.reiqsmod.Chat.ParseType.Type;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Execute {

	public static HashMap<String,Integer> current = new HashMap<String,Integer>();

	public static HashMap<String,Integer> session = new HashMap<String,Integer>();

	public static int getInfo(String type) {

		if (session.containsKey(type)) {

			return session.get(type);

		}

		return 0;
	}

	public static boolean execute(Type t, HashMap<String,String> map) {

		if (t.equals(Type.OTHER)) { return false; }

		if (t.equals(Type.CHAT)) { return doChat(map);}

		return doQuake(t, map);
	}

	private static boolean doQuake(Type t, HashMap<String,String> map) {

		boolean r = false;

		switch (t) {

		case START: current.clear(); return false;

		case END:

			String 
			name = ReiqsMod.instance().mc().thePlayer.getName(), 
			winner = map.get("winner");

			if (winner.equals(name)) {

				if (!session.containsKey("wins")) { session.put("wins", 1); }

				else { session.put("wins", session.get("wins") + 1); }

			} else {

				if (!session.containsKey("losses")) { session.put("losses", 1); }

				else { session.put("losses", session.get("losses") + 1); }
			}

			if (!session.containsKey("games")) { session.put("games", 1); }

			else { session.put("games", session.get("games") + 1); }

			current.clear();

			return false;

		case COINS:

			String c = map.get("coins");

			if (!NumberUtils.isNumber(c)) { return false; }

			int coins = Integer.valueOf(c);

			if (!current.containsKey("coins")) { current.put("coins", coins); }

			else { current.put("coins", current.get("coins") + coins); }

			if (!session.containsKey("coins")) { session.put("coins", coins); }

			else { session.put("coins", session.get("coins") + coins); }

			TextComponentString msg = new TextComponentString(TextFormatting.BLUE + "+" + TextFormatting.GOLD + coins + TextFormatting.BLUE + " coins!");

			ReiqsMod.instance().mc().thePlayer.addChatMessage(msg);

			return true;

		case PVP: 

			String n = ReiqsMod.instance().mc().thePlayer.getName();

			String k = map.get("killer");

			String defmsg = map.get("message");

			String d = map.get("victim");

			boolean kill = false;

			boolean death = false;

			boolean hs = Boolean.valueOf(map.get("headshot"));

			if (k.equals(n)) {

				kill = true;

				if (!current.containsKey("kills")) { current.put("kills", 1); }

				else { current.put("kills", current.get("kills") + 1); }

				if (!session.containsKey("kills")) { session.put("kills", 1); }

				else { session.put("kills", session.get("kills") + 1); }

				if (hs) {

					if (!current.containsKey("headshots")) { current.put("headshots", 1); }

					else { current.put("headshots", current.get("headshots") + 1); }

					if (!session.containsKey("headshots")) { session.put("headshots", 1); }

					else { session.put("headshots", session.get("headshots") + 1); }
				}

			}

			if (d.equals(n)) {

				death = true;

				if (!current.containsKey("deaths")) { current.put("deaths", 1); }

				else { current.put("deaths", current.get("deaths") + 1); }

				if (!session.containsKey("deaths")) { session.put("deaths", 1); }

				else { session.put("deaths", session.get("deaths") + 1); }

			}

			//TODO make custom msg configurable

			TextComponentString tcs = Util.pvpMsg(k, defmsg, d, hs);

			ReiqsMod.instance().mc().thePlayer.addChatMessage(tcs);

			return true;

		case STREAK: 

			String pn = ReiqsMod.instance().mc().thePlayer.getName();

			String sn = map.get("name");

			String type = map.get("type");

			if (sn.equals(pn)) {

				if (!current.containsKey("streaks")) { current.put("streaks", 1); }

				else { current.put("streaks", current.get("streaks") + 1); }

				if (!session.containsKey("streaks")) { session.put("streaks", 1); }

				else { session.put("streaks", session.get("streaks") + 1); }

			}

			TextComponentString mg = new TextComponentString(TextFormatting.DARK_AQUA + sn + " " + TextFormatting.BLUE + type);

			ReiqsMod.instance().mc().thePlayer.addChatMessage(mg);

			return true;

		case SHUTDOWN: 

			String shut = map.get("shut");

			String shot = map.get("shot");

			TextComponentString mew = new TextComponentString(TextFormatting.DARK_AQUA + shut + TextFormatting.BLUE + " got shutdown by " + TextFormatting.DARK_AQUA + shot + TextFormatting.BLUE + "!");

			ReiqsMod.instance().mc().thePlayer.addChatMessage(mew);

			return true;
		case POWERUP: 

			String plr = map.get("name");

			String typep = map.get("type");

			TextComponentString meas = new TextComponentString(TextFormatting.DARK_AQUA + plr + TextFormatting.BLUE + " activated " + TextFormatting.GOLD + typep + TextFormatting.BLUE + "!");

			ReiqsMod.instance().mc().thePlayer.addChatMessage(meas);

			return true;

		default: break; }

		return r;
	}

	private static boolean doChat(HashMap<String,String> map) {

		boolean r = false;

		String type = map.get("type");


		return r;
	}
}
