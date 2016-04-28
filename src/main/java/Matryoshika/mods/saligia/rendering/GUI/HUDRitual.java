package Matryoshika.mods.saligia.rendering.GUI;

import java.awt.Graphics2D;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUDRitual extends Gui{
	

	public HUDRitual(Minecraft mc, String name) {
		FontRenderer fontRender = mc.fontRenderer;
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		int stringWidth = fontRender.getStringWidth(name);
		
		
		int tooltipXOffset = -2;
		int tooltipYOffset = -2;
		int tooltipHeight = 4;
		int tooltipWidth = stringWidth;
		int tooltipX = (int) (width/2)-(stringWidth/2) + tooltipXOffset;
	    int tooltipY = (int) (height/1.2) + tooltipYOffset;
		
		GL11.glPushMatrix();
		
		int innerAlpha = -0xFEFFFF0;  //very very dark purple
	    drawGradientRect(tooltipX, tooltipY - 1, tooltipX + tooltipWidth + 6, tooltipY, innerAlpha, innerAlpha);
	    drawGradientRect(tooltipX, tooltipY + tooltipHeight + 6, tooltipX + tooltipWidth + 6, tooltipY + tooltipHeight + 7, innerAlpha, innerAlpha);
	    drawGradientRect(tooltipX, tooltipY, tooltipX + tooltipWidth + 6, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);
	    drawGradientRect(tooltipX - 1, tooltipY, tooltipX, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);
	    drawGradientRect(tooltipX + tooltipWidth + 6, tooltipY, tooltipX + tooltipWidth + 7, tooltipY + tooltipHeight + 6, innerAlpha, innerAlpha);
	    
	    int outerAlpha1 = 0x505000FF;
	    int outerAlpha2 = (outerAlpha1 & 0xFEFEFE) >> 1 | outerAlpha1 & -0x1000000;
	    drawGradientRect(tooltipX, tooltipY + 1, tooltipX + 1, tooltipY + tooltipHeight + 6 - 1, outerAlpha1, outerAlpha2);
	    drawGradientRect(tooltipX + tooltipWidth + 5, tooltipY + 1, tooltipX + tooltipWidth + 7, tooltipY + tooltipHeight + 6 - 1, outerAlpha1, outerAlpha2);
	    drawGradientRect(tooltipX, tooltipY, tooltipX + tooltipWidth + 5, tooltipY + 1, outerAlpha1, outerAlpha1);
	    drawGradientRect(tooltipX, tooltipY + tooltipHeight + 5, tooltipX + tooltipWidth + 7, tooltipY + tooltipHeight + 6, outerAlpha2, outerAlpha2);

		
		//fontRender.drawString(name, (int) (width/3), height/5 , 0);
		fontRender.drawString(name,  (int) (width/2)-(stringWidth/2), (int) (height/1.2), Integer.parseInt("FFFFFF", 16), true);
		GL11.glPopMatrix();
	}

}
