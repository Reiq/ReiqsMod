package com.reiq.reiqsmod.chat;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;

import com.reiq.reiqsmod.ReiqsMod;
import com.reiq.reiqsmod.Util;

public class ParseType {

	public static enum Type { PVP, STREAK, SHUTDOWN, POWERUP, COINS, CHAT, START, END, OTHER }

	public static String last_killer;

	public static Set<String> plrs_atstart = new HashSet<>();

	public static Type parse(String mu) {

		if (isChat(mu)) { return Type.CHAT; }

		else if (isPvp(mu)) { return Type.PVP; }

		else if (isCoin(mu)) { return Type.COINS; }

		else if (isStreak(mu)) { return Type.STREAK; }

		else if (isShutdown(mu)) { return Type.SHUTDOWN; }

		else if (isPowerup(mu)) { return Type.POWERUP; }

		else if (isStart(mu)) { return Type.START; }

		else if (isEnd(mu)) { return Type.END; }

		return Type.OTHER;
	}

	public static boolean isStart(String mu) {

		mu = mu.trim();

		if (mu.equals("Right-click with your Railgun to shoot")) {

			plrs_atstart.clear();

			plrs_atstart.addAll(Util.getPlayers());

			return true;
		}

		return false;
	}

	public static boolean isEnd(String mu) {

		mu = mu.trim();

		String b = "", a = "", n = "";

		for (String s : plrs_atstart) {

			if (mu.equals("Winner - " + s)) { return true; }

		}

		return false;
	}

	public static boolean isShutdown(String mu) {

		String name = "", name2 = "", sd = " got shutdown by ";

		for (String s : Util.getPlayers()) {

			if (mu.startsWith(s + sd)) { name = s; }

			if (mu.endsWith(sd + s + "!")) { name2 = s; }

		}

		if (mu.equals(name + sd + name2 + "!")) { return true; }

		return false;

	}

	public static boolean isStreak(String mu) {

		String name = "";

		for (String s : Util.getPlayers()) { if (mu.startsWith(s)) { name = s; }}

		for (String s : Util.sMsg()) {

			if (mu.toUpperCase().equals(name.toUpperCase() + s.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	public static boolean isChat(String mu) {

		if (!mu.contains(":")) { return false; }

		for (String s : Util.getChats()) { if (mu.startsWith(s)) { return true; }}

		String rank = "", cc = "";

		for (String s : Util.getRanks()) { if (mu.startsWith(s)) { rank = s; }}

		for (String s : Util.getPlayers()) {

			if (rank.startsWith("§")) { cc = "§f"; if (rank.equals("§7")) { cc = "§7"; } }

			if (mu.startsWith(rank + s + cc + ": ")) { return true; }
		}

		return false;
	}

	public static boolean isPvp(String mu) {

		String p1 = null, p2 = null;

		if (mu.endsWith(" HEADSHOT")) { mu = mu.replace(" HEADSHOT", ""); }

		for (String s : Util.getPlayers()) {

			if (mu.startsWith(s + " ")) { p1 = s.toUpperCase(); }

			if (mu.endsWith(" " + s)) { p2 = s.toUpperCase(); }

		}

		if (p1 != null && p2 != null) {

			for (String s : Util.kMsg()) {

				if (mu.toUpperCase().equals(p1 + " " + s + " " + p2)) {

					last_killer = p1;

					return true;

				}
			}
		}

		return false;
	}

	public static boolean isCoin(String mu) {

		if (mu.startsWith("+") && mu.contains(" coins! ") && mu.contains(" (") && mu.contains(")")) {

			return true;

		}

		return false;
	}
	
	public static boolean isPowerup(String mu) {

		String name = "";

		for (String s : Util.getPlayers()) { if (mu.startsWith(s)) { name = s; } }

		if (mu.startsWith(name + " activated the SPEED BOOST powerup!")) { return true; }

		if (mu.startsWith(name + " activated the RAPID FIRE powerup!")) { return true; }

		return false;
	}
}

