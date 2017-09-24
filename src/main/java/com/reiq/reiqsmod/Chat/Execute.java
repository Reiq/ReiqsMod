package com.reiq.reiqsmod.chat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import org.apache.commons.lang3.math.NumberUtils;

import com.reiq.reiqsmod.ReiqsMod;
import com.reiq.reiqsmod.Util;
import com.reiq.reiqsmod.chat.ParseType.Type;
import com.reiq.reiqsmod.config.ReiqConfig;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Execute {

	public static HashMap<String,Integer> session = new HashMap<String,Integer>();

	public static int getInfo(String type) { if (session.containsKey(type)) { return session.get(type);} return 0; }

	public static void addStat(String type) { if (!session.containsKey(type)) { session.put(type, 1); } else { session.put(type, session.get(type) + 1); }}

	public static void resetStat(String type) { session.put(type, 0); }

	public static boolean execute(Type t, HashMap<String,String> map) {

		if (t.equals(Type.CHAT) || t.equals(Type.OTHER)) { return false; }

		return doQuake(t, map);
	}

	public static ReiqConfig cfg = ReiqsMod.instance().config;

	private static boolean doQuake(Type t, HashMap<String,String> map) {

		switch (t) {

		case END:

			String name = ReiqsMod.instance().mc().thePlayer.getName(), winner = map.get("winner");

			if (winner.equals(name)) { addStat("wins"); addStat("winstreak"); resetStat("lossstreak"); }

			else { addStat("losses"); addStat("lossstreak"); resetStat("winstreak"); }

			addStat("games");

			return false;

		case COINS:

			String c = map.get("coins");

			if (!NumberUtils.isNumber(c)) { return false; }

			int coins = Integer.valueOf(c);

			addStat("coins");

			if (!cfg.chat_coins_enabled) { return true; }

			if (cfg.chat_coins_cust_enabled) { return false; }

			String ms_color = cfg.chat_main_color.substring(0, 2);

			String coins_color = cfg.chat_coins_color.substring(0, 2);

			TextComponentString msg = new TextComponentString(ms_color + "+" + coins_color + coins + ms_color + " coins!");

			ReiqsMod.instance().mc().thePlayer.addChatMessage(msg);

			return true;

		case PVP: 

			String n = ReiqsMod.instance().mc().thePlayer.getName(), k = map.get("killer"), defmsg = map.get("message"), d = map.get("victim");

			boolean kill = false, death = false, hs = Boolean.valueOf(map.get("headshot"));

			if (k.equals(n)) { kill = true; addStat("kills"); addStat("killstreak"); 

			if (hs) { addStat("headshots"); addStat("headshotstreak"); }

			else { resetStat("headshotstreak"); }}

			if (d.equals(n)) { death = true; addStat("deaths"); resetStat("deathstreak"); }

			if (!cfg.chat_pvp_enabled) { return true; }

			if (cfg.chat_pvp_cust_enabled) { return false; }

			TextComponentString tcs = Util.pvpMsg(k, defmsg, d, hs);

			ReiqsMod.instance().mc().thePlayer.addChatMessage(tcs);

			return true;

		case STREAK: 

			String pn = ReiqsMod.instance().mc().thePlayer.getName(), sn = map.get("name"), type = map.get("type");

			if (sn.equals(pn)) { addStat("streaks"); }

			if (!cfg.chat_streak_enabled) { return true; }

			if (cfg.chat_streak_cust_enabled) { return false; }

			String mq_color = cfg.chat_main_color.substring(0, 2);

			String nme_color = cfg.chat_streak_name_color.substring(0, 2);

			TextComponentString mg = new TextComponentString(nme_color + sn + " " + mq_color + type);

			ReiqsMod.instance().mc().thePlayer.addChatMessage(mg);

			return true;

		case SHUTDOWN:

			String shut = map.get("shut"), shot = map.get("shot");

			if (!cfg.chat_shutdown_enabled) { return true; }

			if (cfg.chat_shutdown_cust_enabled) { return false; }

			String md_color = cfg.chat_main_color.substring(0, 2);

			String namaa_color = cfg.chat_shutdown_name_color.substring(0, 2);

			TextComponentString mew = new TextComponentString(namaa_color + shut + md_color + " got shutdown by " + namaa_color + shot + md_color + "!");

			ReiqsMod.instance().mc().thePlayer.addChatMessage(mew);

			return true;

		case POWERUP:

			String plr = map.get("name"), typep = map.get("type");

			if (!cfg.chat_powerup_enabled) { return true; }

			if (!cfg.chat_powerup_cust_enabled) { return false; }

			String m_colorr = cfg.chat_main_color.substring(0, 2);

			String name_colorr = cfg.chat_powerup_name_color.substring(0, 2);

			if (typep.equals("SPEED BOOST")) {

				String speed = cfg.chat_powerup_speed_color.substring(0, 2);

				TextComponentString meas = new TextComponentString(name_colorr + plr + m_colorr + " activated " + speed + typep + m_colorr + "!");
				ReiqsMod.instance().mc().thePlayer.addChatMessage(meas);

			} else if (typep.equals("RAPID FIRE")) {

				String rapid = cfg.chat_powerup_rapid_color.substring(0, 2);

				TextComponentString meas = new TextComponentString(name_colorr + plr + m_colorr + " activated " + rapid + typep + m_colorr + "!");
				ReiqsMod.instance().mc().thePlayer.addChatMessage(meas);
			}

			return true;

		default: return false; }
	}
}
