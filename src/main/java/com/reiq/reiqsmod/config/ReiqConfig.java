package com.reiq.reiqsmod.config;

import java.io.File;

import com.reiq.reiqsmod.Util;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Configuration;

public class ReiqConfig {
	
	private Configuration config;
	
	public ReiqConfig(File conf) { this.config = new Configuration(conf, "1");this.config.load(); }
	
	public static final String CHAT_BOOL_CAT = "chatbool";
	
	public static final String CHAT_COLOR_CAT = "chatcolor";
	
	public static final String HUD_BOOL_CAT = "hudbool";
	
	public static final String HUD_COLOR_CAT = "hudcolor";
	
	private String[] colors = Util.colors();

	public boolean	
	stats_enabled, kd_enabled, wl_enabled, kw_enabled,
	kills_enabled, killstreak_enabled, deaths_enabled, deathstreak_enabled,
	wins_enabled, winstreak_enabled, losses_enabled, lossstreak_enabled,
	headshots_enabled, headshotstreak_enabled,
	streaks_enabled, coins_enabled, games_enabled,
	chat_coins_enabled, chat_pvp_enabled, chat_streak_enabled, chat_shutdown_enabled, chat_powerup_enabled,
	chat_coins_cust_enabled, chat_pvp_cust_enabled, chat_streak_cust_enabled, chat_shutdown_cust_enabled, chat_powerup_cust_enabled;

	public String
	kd_color, wl_color, kw_color,
	kills_color, killstreak_color,
	deaths_color, deathstreak_color,
	wins_color, winstreak_color,
	losses_color, lossstreak_color,
	headshots_color, headshotstreak_color,
	streaks_color,
	coins_color,
	games_color,
	chat_main_color,
	chat_coins_color,
	chat_pvp_headshot_color, chat_pvp_killer_color, chat_pvp_message_color, chat_pvp_victim_color,
	chat_streak_name_color, chat_shutdown_name_color,
	chat_powerup_name_color, chat_powerup_speed_color, chat_powerup_rapid_color;
	
	public void syncConfig() {
		
		stats_enabled = this.config.get(HUD_BOOL_CAT, "- Stat Hud Enabled -", true, "Enable stat HUD").getBoolean(true);
		kd_enabled = this.config.get(HUD_BOOL_CAT, "KDR Enabled", true, "kill/death ratio").getBoolean(true);
		wl_enabled = this.config.get(HUD_BOOL_CAT, "WLR Enabled", false, "win/loss ratio").getBoolean(true);
		kw_enabled = this.config.get(HUD_BOOL_CAT, "KWR Enabled", true, "kill/win ratio").getBoolean(true);
		kills_enabled = this.config.get(HUD_BOOL_CAT, "Kills Enabled", true, "kills").getBoolean(true);
		killstreak_enabled = this.config.get(HUD_BOOL_CAT, "Kill Streak Enabled", true, "current kill streak").getBoolean(true);
		deaths_enabled = this.config.get(HUD_BOOL_CAT, "Deaths Enabled", true, "deaths").getBoolean(true);
		deathstreak_enabled = this.config.get(HUD_BOOL_CAT, "Death Streak Enabled", false, "current death streak").getBoolean(true);
		wins_enabled = this.config.get(HUD_BOOL_CAT, "Wins Enabled", true, "wins").getBoolean(true);
		winstreak_enabled = this.config.get(HUD_BOOL_CAT, "Win Streak Enabled", true, "win streak").getBoolean(true);
		losses_enabled = this.config.get(HUD_BOOL_CAT, "Losses Enabled", true, "losses").getBoolean(true);
		lossstreak_enabled = this.config.get(HUD_BOOL_CAT, "Loss Streak Enabled", false, "loss streak").getBoolean(true);
		headshots_enabled = this.config.get(HUD_BOOL_CAT, "Headshots Enabled", false, "headshots").getBoolean(true);
		headshotstreak_enabled = this.config.get(HUD_BOOL_CAT, "Headshot Streak Enabled", false, "headshot streak").getBoolean(true);
		streaks_enabled = this.config.get(HUD_BOOL_CAT, "Streaks Enabled", false, "number of kill streaks").getBoolean(true);
		coins_enabled = this.config.get(HUD_BOOL_CAT, "Coins Enabled", true, "coins").getBoolean(true);
		games_enabled = this.config.get(HUD_BOOL_CAT, "Games Enabled", false, "games").getBoolean(true);
		
		kd_color = this.config.getString("KDR Color", HUD_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Kill / Death Ratio", colors);
		wl_color = this.config.getString("WLR Color", HUD_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Win / Loss Ratio", colors);
		kw_color = this.config.getString("KWR Color", HUD_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Kill / Win Ratio", colors);
		kills_color = this.config.getString("Kills Color", HUD_COLOR_CAT, TextFormatting.DARK_GREEN + "Dark_Green", "HUD", colors);
		killstreak_color = this.config.getString("Kill Streak Color", HUD_COLOR_CAT, TextFormatting.DARK_GREEN + "Dark_Green", "HUD", colors);
		deaths_color = this.config.getString("Deaths Color", HUD_COLOR_CAT, TextFormatting.DARK_RED + "Dark_Red", "HUD", colors);
		deathstreak_color = this.config.getString("Death Streak Color", HUD_COLOR_CAT, TextFormatting.DARK_RED + "Dark_Red", "HUD", colors);
		wins_color = this.config.getString("Wins Color", HUD_COLOR_CAT, TextFormatting.DARK_GREEN + "Dark_Green", "HUD", colors);
		winstreak_color = this.config.getString("Win Streak Color", HUD_COLOR_CAT, TextFormatting.DARK_GREEN + "Dark_Green", "HUD", colors);
		losses_color = this.config.getString("Losses Color", HUD_COLOR_CAT, TextFormatting.DARK_RED + "Dark_Red", "HUD", colors);
		lossstreak_color = this.config.getString("Loss Streak Color", HUD_COLOR_CAT, TextFormatting.DARK_RED + "Dark_Red", "HUD", colors);
		headshots_color = this.config.getString("Headshots Color", HUD_COLOR_CAT, TextFormatting.GOLD + "Gold", "HUD", colors);
		headshotstreak_color = this.config.getString("Headshot Streak Color", HUD_COLOR_CAT, TextFormatting.GOLD + "Gold", "HUD", colors);
		streaks_color = this.config.getString("Streaks Color", HUD_COLOR_CAT, TextFormatting.BLUE + "Blue", "HUD", colors);
		coins_color = this.config.getString("Coins Color", HUD_COLOR_CAT, TextFormatting.GOLD + "Gold", "HUD", colors);
		games_color = this.config.getString("Games Color", HUD_COLOR_CAT, TextFormatting.BLUE + "Blue", "HUD", colors);
		
		chat_pvp_enabled = this.config.get(CHAT_BOOL_CAT, "PVP - Show message in chat", true, "Show pvp").getBoolean(true);
		chat_pvp_cust_enabled = this.config.get(CHAT_BOOL_CAT, "PVP - Custom message", true, "example: Reiq pewpew'd RandomNon").getBoolean(true);
		chat_coins_enabled = this.config.get(CHAT_BOOL_CAT, "Coin - Show message in chat", true, "Show coins").getBoolean(true);
		chat_coins_cust_enabled = this.config.get(CHAT_BOOL_CAT, "Coin - Custom message", true, "example: +48 coins!").getBoolean(true);
		chat_streak_enabled = this.config.get(CHAT_BOOL_CAT, "Streak - Show message in chat", true, "Show streaks").getBoolean(true);
		chat_streak_cust_enabled = this.config.get(CHAT_BOOL_CAT, "Streak - Custom colors", true, "Enable streak message colors").getBoolean(true);
		chat_shutdown_enabled = this.config.get(CHAT_BOOL_CAT, "Shutdown - Show message in chat", true, "Show shutdown").getBoolean(true);
		chat_shutdown_cust_enabled = this.config.get(CHAT_BOOL_CAT, "Shutdown - Custom colors", true, "Enable shutdown message colors").getBoolean(true);
		chat_powerup_enabled = this.config.get(CHAT_BOOL_CAT, "Powerup - Show message in chat", true, "Show powerups").getBoolean(true);
		chat_powerup_cust_enabled = this.config.get(CHAT_BOOL_CAT, "Powerup - Custom colors", true, "Enable powerup message colors").getBoolean(true);
		
		chat_main_color = this.config.getString("- Main Text Color -", CHAT_COLOR_CAT, TextFormatting.BLUE + "Blue", "Chat", colors);
		chat_pvp_headshot_color = this.config.getString("PVP: Headshot Color", CHAT_COLOR_CAT, TextFormatting.DARK_RED + "Dark_Red", "Chat", colors);
		chat_pvp_killer_color = this.config.getString("PVP: Killer Name Color", CHAT_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Chat", colors);
		chat_pvp_message_color = this.config.getString("PVP: Message Color", CHAT_COLOR_CAT, TextFormatting.AQUA + "Aqua", "Chat", colors);
		chat_pvp_victim_color = this.config.getString("PVP: Victim Name Color", CHAT_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Chat", colors);
		chat_coins_color = this.config.getString("Coins Color", CHAT_COLOR_CAT, TextFormatting.GOLD + "Gold", "Chat", colors);
		chat_streak_name_color = this.config.getString("Spree Name Color", CHAT_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Chat", colors);
		chat_shutdown_name_color = this.config.getString("Shutdown Name Color", CHAT_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Chat", colors);
		chat_powerup_name_color = this.config.getString("Powerup: Name Color", CHAT_COLOR_CAT, TextFormatting.DARK_AQUA + "Dark_Aqua", "Chat", colors);
		chat_powerup_speed_color = this.config.getString("Powerup: Speed Boost Color", CHAT_COLOR_CAT, TextFormatting.GOLD + "Gold", "Chat", colors);
		chat_powerup_rapid_color = this.config.getString("Powerup: Rapid Fire Color", CHAT_COLOR_CAT, TextFormatting.GOLD + "Gold", "Chat", colors);

		if (this.config.hasChanged()){this.config.save();}
	}

	public Configuration getConfig(){return config;}
}
