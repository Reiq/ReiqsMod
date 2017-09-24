package com.reiq.reiqsmod;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import com.reiq.reiqsmod.chat.Execute;
import com.reiq.reiqsmod.chat.ParseInfo;
import com.reiq.reiqsmod.chat.ParseType;
import com.reiq.reiqsmod.chat.ParseType.Type;
import com.reiq.reiqsmod.cmd.CmdQS;
import com.reiq.reiqsmod.config.ReiqConfig;
import com.reiq.reiqsmod.config.ReiqConfigGui;
import com.reiq.reiqsmod.hud.Stats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
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
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

@Mod(modid = ReiqsMod.MODID, name = ReiqsMod.NAME, version = ReiqsMod.VERSION, clientSideOnly = true, acceptedMinecraftVersions = "*", guiFactory = "com.reiq.reiqsmod.config.ReiqConfigFactory")

public class ReiqsMod {

	private Minecraft mc;
	private static ReiqsMod instance;
	private boolean isHypixel, isDebug;

	public static final String MODID = "reiqsmod", NAME = "ReiqsMod", VERSION = "1.0";
	public Minecraft mc() { return mc; }
	public static ReiqsMod instance() { return instance; }
	public ReiqConfig config;

	@EventHandler public void preInit(FMLPreInitializationEvent event) {
		
		config = new ReiqConfig(event.getSuggestedConfigurationFile());
		
		config.syncConfig();
		
		this.instance = this; 
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		isDebug = true;
		
		isHypixel = false;
		
		this.mc = Minecraft.getMinecraft();
		
		MinecraftForge.EVENT_BUS.register(this);
		
		ClientCommandHandler.instance.registerCommand(new CmdQS());
		
		Util.bindKeys();
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

		//if (!isDebug || !isHypixel) { return; }

		if (Util.keys[0].isPressed()) { mc().thePlayer.sendChatMessage("/play quake_solo"); }
		if (Util.keys[1].isPressed()) { mc().thePlayer.sendChatMessage("/play quake_teams"); }
		if (Util.keys[2].isPressed()) { FMLClientHandler.instance().getClient().displayGuiScreen(new ReiqConfigGui(null)); }
	}

	@SubscribeEvent
	public void readChat(ClientChatReceivedEvent event) {

		//if (!isDebug || !isHypixel) { return; }

		String mu = event.getMessage().getUnformattedText();
		Type t = ParseType.parse(mu);
		HashMap<String, String> i = ParseInfo.parse(t, mu);
		boolean b = Execute.execute(t, i);

		event.setCanceled(b);

		return;
	}

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {

		//if (!isDebug || !isHypixel) { return; }

		try {

			GuiScreen screen = mc.currentScreen;

			if (mc.theWorld != null) {

				WorldClient world = mc.theWorld;

				if (event.phase == Phase.END) { Stats.onRender(); }}
			
		} catch (Exception e) { e.printStackTrace(); }

	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {

		try { if(event.getModID().equals(MODID)){ config.syncConfig(); }}

		catch (Exception ex) { ex.printStackTrace(); } 
	}

	@SubscribeEvent
	public void serverJoin(ClientConnectedToServerEvent event) { if (event.getManager().getRemoteAddress().toString().contains("hypixel")) { isHypixel = true; }}

	@SubscribeEvent
	public void serverLeave(ClientDisconnectionFromServerEvent event) { if (event.getManager().getRemoteAddress().toString().contains("hypixel")) { isHypixel = false; }}

	/*

	@SubscribeEvent
	public void serverSwitch(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayerSP) {
			this.mc.player.sendChatMessage("/whereami");
		}
	}
	 */
}
