package com.reiq.reiqsmod.hud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.reiq.reiqsmod.ReiqsMod;
import com.reiq.reiqsmod.Util;
import com.reiq.reiqsmod.chat.Execute;
import com.reiq.reiqsmod.config.ReiqConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Property;

public class Stats {

	private static List<String> display = new ArrayList<String>();

	private static List getDisplay() {

		List<String> a = new ArrayList<String>();

		ReiqConfig cfg = ReiqsMod.instance().config;

		if (cfg.kd_enabled) { a.add(cfg.kd_color.substring(0,2) + "KDR" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.kd_color.substring(0,2) + Util.getRatio("kd")); }
		if (cfg.wl_enabled) { a.add(cfg.wl_color.substring(0,2) + "WLR" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.wl_color.substring(0,2) + Util.getRatio("wl")); }
		if (cfg.kw_enabled) { a.add(cfg.kw_color.substring(0,2) + "KWR" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.kw_color.substring(0,2) + Util.getRatio("kw")); }
		if (cfg.kills_enabled) { a.add(cfg.kills_color.substring(0,2) + "Kills" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.kills_color.substring(0,2) + Execute.getInfo("kills")); }
		if (cfg.killstreak_enabled) { a.add(cfg.killstreak_color.substring(0,2) + "Kill Streak" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.killstreak_color.substring(0,2) + Execute.getInfo("killstreak")); }
		if (cfg.deaths_enabled) { a.add(cfg.deaths_color.substring(0,2) + "Deaths" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.deaths_color.substring(0,2) + Execute.getInfo("deaths")); }
		if (cfg.deathstreak_enabled) { a.add(cfg.deathstreak_color.substring(0,2) + "Death Streak" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.deathstreak_color.substring(0,2) + Execute.getInfo("deathstreak")); }
		if (cfg.wins_enabled) { a.add(cfg.wins_color.substring(0,2) + "Wins" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.wins_color.substring(0,2) + Execute.getInfo("wins")); }
		if (cfg.winstreak_enabled) { a.add(cfg.winstreak_color.substring(0,2) + "Win Streak" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.winstreak_color.substring(0,2) + Execute.getInfo("winstreak")); }
		if (cfg.losses_enabled) { a.add(cfg.losses_color.substring(0,2) + "Losses" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.losses_color.substring(0,2) + Execute.getInfo("losses")); }
		if (cfg.lossstreak_enabled) { a.add(cfg.lossstreak_color.substring(0,2) + "Loss Streak" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.lossstreak_color.substring(0,2) + Execute.getInfo("lossstreak")); }
		if (cfg.games_enabled) { a.add(cfg.games_color.substring(0,2) + "Games" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.games_color.substring(0,2) + Execute.getInfo("games")); }
		if (cfg.headshots_enabled) { a.add(cfg.headshots_color.substring(0,2) + "Headshots" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.headshots_color.substring(0,2) + Execute.getInfo("headshots")); }
		if (cfg.headshotstreak_enabled) { a.add(cfg.headshotstreak_color.substring(0,2) + "Headshot Streak" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.headshotstreak_color.substring(0,2) + Execute.getInfo("headshotstreak")); }
		if (cfg.streaks_enabled) { a.add(cfg.streaks_color.substring(0,2) + "Streaks" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.streaks_color.substring(0,2) + Execute.getInfo("streaks")); }
		if (cfg.coins_enabled) { a.add(cfg.coins_color.substring(0,2) + "Coins" + TextFormatting.BLACK + TextFormatting.BOLD + " > " + cfg.coins_color.substring(0,2) + Execute.getInfo("coins")); }

		return a;
	}

	public static void onRender() {

		if (ReiqsMod.instance().config.stats_enabled) {

			display = getDisplay();

			int x = 5, y = 5;

			for (int i = 0 ; i < display.size(); i++) {

				if ((display.get(i) != null) && (!(display.get(i)).isEmpty())) {

					Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(display.get(i), x, y, 0xFFFFFF);

					y += 10;
				}
			}
		}
	}
}
