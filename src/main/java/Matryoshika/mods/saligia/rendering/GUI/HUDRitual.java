package Matryoshika.mods.saligia.rendering.GUI;

import java.awt.Graphics2D;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class HUDRitual {

	public HUDRitual(Minecraft mc, String name) {
		FontRenderer fontRender = mc.fontRenderer;
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		int stringWidth = fontRender.getStringWidth(name);
		GL11.glPushMatrix();
		
		//fontRender.drawString(name, (int) (width/3), height/5 , 0);
		fontRender.drawStringWithShadow(name, (int) (width/2)-(stringWidth/2),(int) (height/1.2), Integer.parseInt("FFFFFF", 16));
		GL11.glPopMatrix();
	}

}
