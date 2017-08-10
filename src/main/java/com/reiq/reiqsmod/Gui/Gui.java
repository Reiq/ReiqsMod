package com.reiq.reiqsmod.Gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.reiq.reiqsmod.ReiqsMod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class Gui extends GuiScreen {

	public Gui() {}

	private GuiButton butt;
	private final int BUTTON1 = 0;

	@Override
	public void drawScreen(int x, int y, float ticks) {

		GlStateManager.pushMatrix(); {
			
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.color(1, 1, 1, 0.2f);

			int centerX = width / 2;
			int centerY = height / 2;

			drawDefaultBackground();
			
			


		}
		
		GlStateManager.popMatrix();
		
		super.drawScreen(x, y, ticks);
	}

	@Override
	public void initGui() {

		buttonList.clear();

		int x = width / 20;
		int y = height / 4;
		
		buttonList.add(butt = new GuiButton(0, x, y, 100, 20, "button 0"));
		buttonList.add(butt = new GuiButton(1, x, y + 50, 100, 20, "button 1"));
		buttonList.add(butt = new GuiButton(2, x, y + 100, 100, 20, "button 2"));
		buttonList.add(butt = new GuiButton(3, x, y + 150, 100, 20, "button 3"));
		buttonList.add(butt = new GuiButton(4, x, y + 200, 100, 20, "button 4"));
		

		super.initGui();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException { 

		switch(button.id) {

		case BUTTON1:  break;

		default: break; }

		// updateButtons();

		super.actionPerformed(button);
	}

	public void updateButtons() {



	}

	@Override
	public boolean doesGuiPauseGame() { return false; }
}
