package com.reiq.reiqsmod;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.reiq.reiqsmod.Chat.Execute;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class Util {

	public static Set<String> getPlayers() {

		Set<String> p = new HashSet<>();

		Minecraft mc = ReiqsMod.instance().mc();

		NetHandlerPlayClient c = mc.getConnection();

		for (NetworkPlayerInfo info : c.getPlayerInfoMap()) {

			p.add(info.getGameProfile().getName());

		}

		return p;
	}

	public static String[] colors() {

		final String[] colors = {
				
				TextFormatting.AQUA + "Aqua",TextFormatting.BLACK + "Black",TextFormatting.BLUE + "Blue",TextFormatting.DARK_AQUA + "Dark_Aqua", TextFormatting.DARK_BLUE + "Dark_Blue", 
				TextFormatting.DARK_GRAY + "Dark_Gray", TextFormatting.DARK_GREEN + "Dark_Green", TextFormatting.DARK_PURPLE + "Dark_Purple", TextFormatting.GOLD + "Gold", 
				TextFormatting.GRAY + "Gray", TextFormatting.LIGHT_PURPLE + "Light_Purple", TextFormatting.RED + "Red", TextFormatting.WHITE + "White", TextFormatting.YELLOW + "Yellow"};
		
		return colors;
	}
	
	public static String getRatio(String type) {
		
		int kill = Execute.getInfo("kills"), wins = Execute.getInfo("wins");;
		
		Double death = (double) Execute.getInfo("deaths"), win = (double) wins, loss = (double) Execute.getInfo("losses"), kd = (double) kill, wl = (double) 0, kw = (double) 0;
		
		if (death != 0) { kd = kill/death; } if (loss != 0) { wl = wins/loss; } if (win != 0) { kw = kill/win; }
		
		NumberFormat format = new DecimalFormat("#0.00");
		
		switch(type) {
		
		case "kd": return format.format(kd);
		
		case "wl": return format.format(wl);
		
		case "kw": return format.format(kw);
		
		default: return "Error"; }
		
	}

	public static Set<String> getRanks() {

		Set<String> mm = new HashSet<>();

		mm.add("§b[MVP§c+§b] ");mm.add("§b[MVP§6+§b] ");mm.add("§b[MVP§a+§b] ");mm.add("§b[MVP§e+§b] ");mm.add("§b[MVP§d+§b] ");mm.add("§b[MVP§f+§b] ");mm.add("§b[MVP§9+§b] ");mm.add("§b[MVP§2+§b] ");mm.add("§b[MVP§4+§b] ");mm.add("§b[MVP§3+§b] ");mm.add("§b[MVP§5+§b] ");mm.add("§b[MVP§8+§b] ");mm.add("§b[MVP§0+§b] ");

		mm.add("§7");mm.add("§b[MVP] ");mm.add("§a[VIP] ");mm.add("§a[VIP§6+§a] ");mm.add("§9[HELPER] ");mm.add("§3[BUILD TEAM] ");mm.add("§1[MOD] ");mm.add("§6[YT] ");mm.add("§4[ADMIN] ");mm.add("§4[OWNER] ");mm.add("§6[MOJANG] ");mm.add("§9[BEAM] ");mm.add("§4[SLOTH] ");mm.add("§6[APPLE] ");

		mm.add("[MVP] ");mm.add("[MVP+] ");mm.add("[VIP] ");mm.add("[VIP+] ");mm.add("[HELPER] ");mm.add("[BUILD TEAM] ");mm.add("[MOD] ");mm.add("[YT] ");mm.add("[ADMIN] ");mm.add("[OWNER] ");mm.add("[MOJANG] ");mm.add("[BEAM] ");mm.add("[SLOTH] ");mm.add("[APPLE] ");

		return mm;
	}

	public static Set<String> getChats() {

		Set<String> mm = new HashSet<>();

		mm.add("§2Guild >"); mm.add("§9Party >"); mm.add("To "); mm.add("From ");

		return mm;
	}

	public static Set<String> sMsg() {

		Set<String> sm = new HashSet<>();

		sm.add(" is on a killing spree!");sm.add(" is on a rampage!");sm.add(" is dominating!");sm.add(" is unstoppable!");sm.add(" is godlike");sm.add(" broke the game!");sm.add(" fixed the game!");sm.add(" entered a new dimension!");sm.add(" went into oblivion!");sm.add(" reached infinite and beyond!");sm.add(" found the meaning of life!");

		return sm;
	}

	public static Set<String> kMsg() {

		Set<String> km = new HashSet<>();

		km.add("SHELLED");km.add("SLIMED");km.add("REDSTONED");km.add("FELL IN LOVE WITH");km.add("GIBBED");km.add("WORMED");km.add("DROWNED");km.add("BAZINGA'D");km.add("PUZZLED");km.add("MAGNIFIED");km.add("POMMED");km.add("VAPORIZED");km.add("SMELTED");km.add("CONSUMED");km.add("FARMED");km.add("BAKED");km.add("SLAPPED");

		return km;
	}

	//TODO make custom msg configurable

	public static TextComponentString pvpMsg(String k, String msg, String d, boolean hs) {

		TextComponentString tcs = new TextComponentString("");

		String n = ReiqsMod.instance().mc().thePlayer.getName();

		String cmsg = "Boop'd";

		String k1 = TextFormatting.DARK_AQUA + k + " ";

		String m1 = TextFormatting.AQUA + msg + " ";

		String d1 = TextFormatting.DARK_AQUA + d;

		String h1 = "";

		if (k.equals(n)) { 

			k1 = TextFormatting.DARK_RED + "" + TextFormatting.BOLD + k + " "; 
			m1 = TextFormatting.DARK_AQUA + "" + TextFormatting.BOLD + cmsg + " ";
			d1 = TextFormatting.DARK_RED + "" + TextFormatting.BOLD + d; 

		}

		if (d.equals(n)) { d1 = TextFormatting.DARK_AQUA + d; }

		if (hs) { h1 = TextFormatting.RED + " HS"; }

		tcs.appendText(k1);
		tcs.appendText(m1);
		tcs.appendText(d1);
		tcs.appendText(h1);

		return tcs;
	}

}
