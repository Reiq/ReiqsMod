package com.reiq.reiqsmod.Config;

import java.io.File;

import com.reiq.reiqsmod.Util;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.Configuration;

public class Config {
	
	private Configuration config;
	
	public Config(File conf) {this.config = new Configuration(conf, "1");this.config.load();}
	
	public static final String STATS_CATEGORY = "stats";
	
	private String[] colors = Util.colors();

	public boolean
	
	stats_enabled, 
	
	kd_enabled, wl_enabled, kw_enabled,
	
	kills_enabled, deaths_enabled, 
	
	wins_enabled, losses_enabled,
	
	headshots_enabled, streaks_enabled, 
	
	coins_enabled, games_enabled;

	public String
	
	kd_color, wl_color, kw_color,
	
	kills_color, deaths_color,
	
	wins_color, losses_color,
	
	headshots_color, streaks_color, 
	
	coins_color, games_color;
	
	public void syncConfig() {
		
		stats_enabled = this.config.get(STATS_CATEGORY, "(Stat Hud) Enabled", true, "Enable stat HUD").getBoolean(true);
		
		kd_enabled = this.config.get(STATS_CATEGORY, "KDR Enabled", true, "kill/death ratio on HUD").getBoolean(true);
		wl_enabled = this.config.get(STATS_CATEGORY, "WLR Enabled", true, "win/loss ratio on HUD").getBoolean(true);
		kw_enabled = this.config.get(STATS_CATEGORY, "KWR Enabled", true, "kill/win ratio on HUD").getBoolean(true);
		kills_enabled = this.config.get(STATS_CATEGORY, "Kills Enabled", true, "kills on HUD").getBoolean(true);
		deaths_enabled = this.config.get(STATS_CATEGORY, "Deaths Enabled", true, "deaths on HUD").getBoolean(true);
		wins_enabled = this.config.get(STATS_CATEGORY, "Wins Enabled", true, "wins on HUD").getBoolean(true);
		losses_enabled = this.config.get(STATS_CATEGORY, "Losses Enabled", true, "losses on HUD").getBoolean(true);
		headshots_enabled = this.config.get(STATS_CATEGORY, "Headshots Enabled", true, "headshots on HUD").getBoolean(true);
		streaks_enabled = this.config.get(STATS_CATEGORY, "Streaks Enabled", true, "streaks on HUD").getBoolean(true);
		coins_enabled = this.config.get(STATS_CATEGORY, "Coins Enabled", true, "coins on HUD").getBoolean(true);
		games_enabled = this.config.get(STATS_CATEGORY, "Games Enabled", true, "games on HUD").getBoolean(true);
		
		kd_color = this.config.getString("KDR Color", STATS_CATEGORY, TextFormatting.DARK_AQUA + "Dark_Aqua", "(HUD) 'KDR' color", colors);
		wl_color = this.config.getString("WLR Color", STATS_CATEGORY, TextFormatting.DARK_AQUA + "Dark_Aqua", "(HUD) 'WLR' color", colors);
		kw_color = this.config.getString("KWR Color", STATS_CATEGORY, TextFormatting.DARK_AQUA + "Dark_Aqua", "(HUD) 'KWR' color", colors);
		kills_color = this.config.getString("Kills Color", STATS_CATEGORY, TextFormatting.DARK_GREEN + "Dark_Green", "(HUD) 'Kills' color", colors);
		deaths_color = this.config.getString("Deaths Color", STATS_CATEGORY, TextFormatting.DARK_RED + "Dark_Red", "(HUD) 'Deaths' color", colors);
		wins_color = this.config.getString("Wins Color", STATS_CATEGORY, TextFormatting.DARK_GREEN + "Dark_Green", "(HUD) 'Wins' color", colors);
		losses_color = this.config.getString("Losses Color", STATS_CATEGORY, TextFormatting.DARK_RED + "Dark_Red", "(HUD) 'Losses' color", colors);
		headshots_color = this.config.getString("Headshots Color", STATS_CATEGORY, TextFormatting.GOLD + "Gold", "(HUD) 'Headshots' color", colors);
		streaks_color = this.config.getString("Streaks Color", STATS_CATEGORY, TextFormatting.BLUE + "Blue", "(HUD) 'Streaks' color", colors);
		coins_color = this.config.getString("Coins Color", STATS_CATEGORY, TextFormatting.GOLD + "Gold", "(HUD) 'Coins' color", colors);
		games_color = this.config.getString("Games Color", STATS_CATEGORY, TextFormatting.BLUE + "Blue", "(HUD) 'Games' color", colors);

		if (this.config.hasChanged()){this.config.save();}
	}

	public Configuration getConfig(){return config;}
}
