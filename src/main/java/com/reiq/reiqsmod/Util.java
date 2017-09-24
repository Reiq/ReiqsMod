package com.reiq.reiqsmod;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.reiq.reiqsmod.chat.Execute;
import com.reiq.reiqsmod.config.ReiqConfig;
import com.reiq.reiqsmod.file.Pull;
import com.reiq.reiqsmod.file.Push;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Util {

	public static KeyBinding[] keys;

	public static TextComponentString pvpMsg(String k, String msg, String d, boolean hs) {

		TextComponentString tcs = new TextComponentString("");

		ReiqConfig cfg = ReiqsMod.instance().config;
		
		String
		
		color_hs = cfg.chat_pvp_headshot_color.substring(0, 2),
		color_k = cfg.chat_pvp_killer_color.substring(0, 2),
		color_m = cfg.chat_pvp_message_color.substring(0, 2),
		color_v = cfg.chat_pvp_victim_color.substring(0, 2),

		n = ReiqsMod.instance().mc().thePlayer.getName(),
		cmsg = Pull.pull("User.txt", "killmessage"),
		k1 = color_k + k + " ",
		m1 = color_m + msg + " ",
		d1 = color_v + d,
		h1 = "";
		
		if (k.equals(n)) {
			k1 = color_k + "" + TextFormatting.BOLD + k + " " + TextFormatting.RESET;
			m1 = color_m + "" + cmsg + " ";
		}

		if (d.equals(n)) { d1 = color_v + TextFormatting.BOLD + d + TextFormatting.RESET; }

		if (hs) { h1 = color_hs + " HS"; }

		tcs.appendText(k1);
		tcs.appendText(m1);
		tcs.appendText(d1);
		tcs.appendText(h1);

		return tcs;
	}

	public static Set<String> getPlayers() {

		Set<String> p = new HashSet<>();

		Minecraft mc = ReiqsMod.instance().mc();

		NetHandlerPlayClient c = mc.getConnection();

		for (NetworkPlayerInfo info : c.getPlayerInfoMap()) {

			p.add(info.getGameProfile().getName());

		}

		return p;
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

	public static String[] colors() {

		final String[] colors = {

				TextFormatting.AQUA + "Aqua",TextFormatting.BLACK + "Black",TextFormatting.BLUE + "Blue",TextFormatting.DARK_AQUA + "Dark_Aqua", TextFormatting.DARK_BLUE + "Dark_Blue", 
				TextFormatting.DARK_GRAY + "Dark_Gray", TextFormatting.DARK_GREEN + "Dark_Green", TextFormatting.DARK_PURPLE + "Dark_Purple", TextFormatting.GOLD + "Gold", 
				TextFormatting.GRAY + "Gray", TextFormatting.LIGHT_PURPLE + "Light_Purple", TextFormatting.RED + "Red", TextFormatting.WHITE + "White", TextFormatting.YELLOW + "Yellow"};

		return colors;
	}

	public static void bindKeys() {

		keys = new KeyBinding[3];

		keys[0] = new KeyBinding("Join Quake-Solo", Keyboard.KEY_RIGHT, "key.categories.multiplayer");
		keys[1] = new KeyBinding("Join Quake-Team", Keyboard.KEY_LEFT, "key.categories.multiplayer");
		keys[2] = new KeyBinding("Open ReiqsMod Config", Keyboard.KEY_BACKSLASH, "key.categories.multiplayer");

		for (int i = 0; i < keys.length; ++i) { ClientRegistry.registerKeyBinding(keys[i]); }
	}
}
