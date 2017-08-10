package com.reiq.reiqsmod.Config;

import java.util.ArrayList;
import java.util.List;

import com.reiq.reiqsmod.ReiqsMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigGui extends GuiConfig {

	private static List<IConfigElement> getConfigElements() {
		
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        
        Configuration configFile = ReiqsMod.instance().config.getConfig();
        
        list.addAll(new ConfigElement(configFile.getCategory(Config.STATS_CATEGORY)).getChildElements());
        
        return list;
    }

	public ConfigGui(GuiScreen parent) {
		
        super(parent, getConfigElements(), ReiqsMod.MODID, false, false, "ReiqsMod Config");
        
    }
}
