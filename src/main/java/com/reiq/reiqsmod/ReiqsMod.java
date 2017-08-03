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
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
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

	@EventHandler public void preInit(FMLPreInitializationEvent event) { this.instance = this; }

	@EventHandler
	public void init(FMLInitializationEvent event) {
		this.mc = Minecraft.getMinecraft();
		MinecraftForge.EVENT_BUS.register(this);
		keys = new KeyBinding[2];
		keys[0] = new KeyBinding("Join Quake-Solo", Keyboard.KEY_RIGHT, "key.categories.multiplayer");
		keys[1] = new KeyBinding("Join Quake-Team", Keyboard.KEY_LEFT, "key.categories.multiplayer");
		for (int i = 0; i < keys.length; ++i) { ClientRegistry.registerKeyBinding(keys[i]); }
	}
	
	@SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
		
		// TODO Check if already in game. If so, ask to confirm
		
        if (keys[0].isPressed()) { mc().thePlayer.sendChatMessage("/play quake_solo"); }
        
        if (keys[1].isPressed()) { mc().thePlayer.sendChatMessage("/play quake_teams"); }
        
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
	public void renderTick(RenderTickEvent event) {
		
		GuiScreen screen = mc.currentScreen;

		if(mc.theWorld != null) {
			WorldClient world = mc.theWorld;

			if(event.phase == Phase.END) {
				
				
				
				int kills = Execute.getInfo("kills");
				int deaths = Execute.getInfo("deaths");
				int wins = Execute.getInfo("wins");
				int losses = Execute.getInfo("losses");
				int headshots = Execute.getInfo("headshots");
				int streaks = Execute.getInfo("streaks");
				int coins = Execute.getInfo("coins");
				int games = Execute.getInfo("games");
				
				int kill = kills;
				
				Double death = (double) deaths;
				
				Double kd = (double) kill;
				
				if (death != 0) { kd = kill/death; }
				
				Double win = (double) wins;
				
				Double kw = (double) 0;
				
				if (win != 0) { kw = kill/win; }
				
				NumberFormat format = new DecimalFormat("#0.00");
				
				mc.fontRendererObj.drawStringWithShadow("KDR", 5, 5, 0x1fa9ff);
				mc.fontRendererObj.drawStringWithShadow(" > " + format.format(kd), 60, 5, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("KWR", 5, 15, 0xffbf1f);
				mc.fontRendererObj.drawStringWithShadow(" > " + format.format(kw), 60, 15, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Kills", 5, 25, 0x46d848);
				mc.fontRendererObj.drawStringWithShadow(" > " + kills, 60, 25, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Deaths", 5, 35, 0xdd1313);
				mc.fontRendererObj.drawStringWithShadow(" > " + deaths, 60, 35, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Wins", 5, 45, 0x46d848);
				mc.fontRendererObj.drawStringWithShadow(" > " + wins, 60, 45, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Losses", 5, 55, 0xdd1313);
				mc.fontRendererObj.drawStringWithShadow(" > " + losses, 60, 55, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Headshots", 5, 65, 0xffbf1f);
				mc.fontRendererObj.drawStringWithShadow(" > " + headshots, 60, 65, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Streaks", 5, 75, 0x1fa9ff);
				mc.fontRendererObj.drawStringWithShadow(" > " + streaks, 60, 75, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Coins", 5, 85, 0xffbf1f);
				mc.fontRendererObj.drawStringWithShadow(" > " + coins, 60, 85, 0x00d6af);
				
				mc.fontRendererObj.drawStringWithShadow("Games" , 5, 95, 0x1fa9ff);
				mc.fontRendererObj.drawStringWithShadow(" > " + games, 60, 95, 0x00d6af);
				
			}
		}
	}
	
	/*

	@SubscribeEvent
	public void serverSwitch(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerSP) {
			this.mc.player.sendChatMessage("/whereami");
		}
	}
	 */
}
