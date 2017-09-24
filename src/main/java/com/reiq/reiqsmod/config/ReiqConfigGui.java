package com.reiq.reiqsmod.config;

import java.util.ArrayList;
import java.util.List;

import com.reiq.reiqsmod.ReiqsMod;
import com.sun.security.auth.login.ConfigFile;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ReiqConfigGui extends GuiConfig {

	public ReiqConfigGui(GuiScreen parent) {
		
		super(parent, getConfigElements(), ReiqsMod.MODID, false, false, "ReiqsMod Config"); 
	}
	
	private static List<IConfigElement> getConfigElements() {
		
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        
        list.add(categoryElement(ReiqConfig.CHAT_BOOL_CAT, "Chat - Enable or disable game messages", "reiq.conf.chat.bool"));
        
        list.add(categoryElement(ReiqConfig.CHAT_COLOR_CAT, "Chat - Change color of game messages", "reiq.conf.chat.color"));
        
        list.add(categoryElement(ReiqConfig.HUD_BOOL_CAT, "Hud - Enable or disable stats", "reiq.conf.hud.bool"));
        
        list.add(categoryElement(ReiqConfig.HUD_COLOR_CAT, "Hud - Change color of stats", "reiq.conf.hud.color"));
        
        return list;
    }
	
	private static IConfigElement categoryElement(String category, String name, String tooltip_key) {
		
		Configuration cf = ReiqsMod.instance().config.getConfig();
		
        return new DummyConfigElement.DummyCategoryElement(name, tooltip_key, new ConfigElement(cf.getCategory(category)).getChildElements());
    }
}
