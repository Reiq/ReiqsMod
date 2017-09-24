package com.reiq.reiqsmod.cmd;

import java.util.HashMap;

import com.reiq.reiqsmod.ReiqsMod;
import com.reiq.reiqsmod.Util;
import com.reiq.reiqsmod.file.Push;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CmdQS extends CommandBase {

	@Override
	public String getCommandName() {return "qs";}

	@Override
	public String getCommandUsage(ICommandSender sender) {return "qs";}

	@Override
	public int getRequiredPermissionLevel() { return 0; }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		if (args.length < 3) { return; }
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if (args[0].equals("killmessage") || args[0].equals("km")) {
			
			String s = "";
			
			for (int i = 1; i < args.length; i++) { s = s + args[i] + " "; }
			
			map.put("killmessage", s.trim());
			
			Push.push("User.txt", map);
			
			TextComponentString send = new TextComponentString(TextFormatting.DARK_AQUA + "Changed kill message to: " + s);
			ReiqsMod.instance().mc().thePlayer.addChatMessage(send);
			send = new TextComponentString(TextFormatting.DARK_AQUA + "Customize colors in the config!");
			ReiqsMod.instance().mc().thePlayer.addChatMessage(send);
		}
		
		
		return;
	}
}



