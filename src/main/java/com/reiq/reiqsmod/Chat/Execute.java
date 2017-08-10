package com.reiq.reiqsmod.Chat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import org.apache.commons.lang3.math.NumberUtils;

import com.reiq.reiqsmod.ReiqsMod;
import com.reiq.reiqsmod.Util;
import com.reiq.reiqsmod.Chat.ParseType.Type;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Execute {

	public static HashMap<String,Integer> session = new HashMap<String,Integer>();

	public static int getInfo(String type) { if (session.containsKey(type)) { return session.get(type);}return 0; }

	public static void addStat(String type) { if (!session.containsKey(type)) { session.put(type, 1); } else { session.put(type, session.get(type) + 1); }}

	public static boolean execute(Type t, HashMap<String,String> map) {

		if (t.equals(Type.CHAT) || t.equals(Type.OTHER)) { return false; }

		return doQuake(t, map);
	}

	private static boolean doQuake(Type t, HashMap<String,String> map) {

		switch (t) {

		case END:

			String 
			name = ReiqsMod.instance().mc().thePlayer.getName(), 
			winner = map.get("winner");

			if (winner.equals(name)) { addStat("wins"); } else { addStat("losses"); }

			addStat("games");

			return false;

		case COINS:

			String c = map.get("coins");

			if (!NumberUtils.isNumber(c)) { return false; }

			int coins = Integer.valueOf(c);

			addStat("coins");

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

			if (k.equals(n)) { kill = true; addStat("kills"); if (hs) { addStat("headshots"); }}

			if (d.equals(n)) { death = true; addStat("deaths"); }

			//TODO make custom msg configurable

			TextComponentString tcs = Util.pvpMsg(k, defmsg, d, hs);

			ReiqsMod.instance().mc().thePlayer.addChatMessage(tcs);

			return true;

		case STREAK: 

			String pn = ReiqsMod.instance().mc().thePlayer.getName();

			String sn = map.get("name");

			String type = map.get("type");

			if (sn.equals(pn)) { addStat("streaks"); }

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

		default: return false; }
	}
}
