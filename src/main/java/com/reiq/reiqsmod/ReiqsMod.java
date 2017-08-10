package com.reiq.reiqsmod;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import com.reiq.reiqsmod.Chat.Execute;
import com.reiq.reiqsmod.Chat.ParseInfo;
import com.reiq.reiqsmod.Chat.ParseType;
import com.reiq.reiqsmod.Chat.ParseType.Type;
import com.reiq.reiqsmod.Config.Config;
import com.reiq.reiqsmod.Config.ConfigGui;
import com.reiq.reiqsmod.Gui.Gui;
import com.reiq.reiqsmod.Hud.Stats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

@Mod(modid=ReiqsMod.MODID,name=ReiqsMod.NAME,version=ReiqsMod.VERSION,clientSideOnly=true,acceptedMinecraftVersions="*")
public class ReiqsMod {

	public static final String MODID = "reiqsmod", NAME = "ReiqsMod", VERSION = "1.0";

	private Minecraft mc;
	public Minecraft mc() { return mc; }

	private static ReiqsMod instance;
	public static ReiqsMod instance() { return instance; }

	public static KeyBinding[] keys;

	public Config config;

	@EventHandler public void preInit(FMLPreInitializationEvent event) {
		
		config = new Config(event.getSuggestedConfigurationFile());
		config.syncConfig();
		
		this.instance = this; 
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		this.mc = Minecraft.getMinecraft();
		
		MinecraftForge.EVENT_BUS.register(this);
		
		keys = new KeyBinding[3];
		
		keys[0] = new KeyBinding("Join Quake-Solo", Keyboard.KEY_RIGHT, "key.categories.multiplayer");
		keys[1] = new KeyBinding("Join Quake-Team", Keyboard.KEY_LEFT, "key.categories.multiplayer");
		keys[2] = new KeyBinding("Open ReiqsMod Config", Keyboard.KEY_BACKSLASH, "key.categories.multiplayer");
		
		for (int i = 0; i < keys.length; ++i) { ClientRegistry.registerKeyBinding(keys[i]); }
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (keys[0].isPressed()) { mc().thePlayer.sendChatMessage("/play quake_solo"); }

		if (keys[1].isPressed()) { mc().thePlayer.sendChatMessage("/play quake_teams"); }

		if (keys[2].isPressed()) { FMLClientHandler.instance().getClient().displayGuiScreen(new Gui());}
	}

	@SubscribeEvent
	public void readChat(ClientChatReceivedEvent event) {

		String mu = event.getMessage().getUnformattedText();

		Type t = ParseType.parse(mu);

		HashMap<String, String> i = ParseInfo.parse(t, mu);

		boolean b = Execute.execute(t, i);

		event.setCanceled(b);

		return;
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {

		try {

			if(event.getModID().equals(MODID)){ config.syncConfig(); }} 

		catch(Exception ex) {

			ex.printStackTrace(); 

		}
	}

	/*
	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		try {
			
			GuiScreen screen = mc.currentScreen;

			if (mc.theWorld != null) {
				
				WorldClient world = mc.theWorld;
				
				int w = screen.width / 2;
				int h = screen.height / 4;
				
				if (event.phase == Phase.END) { Stats.onRender(); }
			}
			
		} catch (Exception e) { e.printStackTrace(); }
	
	}

	*/

	/*

	@SubscribeEvent
	public void serverSwitch(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerSP) {
			this.mc.player.sendChatMessage("/whereami");
		}
	}
	 */
}
