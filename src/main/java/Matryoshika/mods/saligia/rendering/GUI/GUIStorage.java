package Matryoshika.mods.saligia.rendering.GUI;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GUIStorage extends Gui{
	
	public static ResourceLocation emptyBar = new ResourceLocation(saligia.MODID + ":" + "textures/gui/HUDProgressEmpty.png");
	public static ResourceLocation fullBar = new ResourceLocation(saligia.MODID + ":" + "textures/gui/HUDProgressFull.png");
	
	public GUIStorage(Minecraft mc, String storage, double bar){
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		GL11.glPushMatrix();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		mc.renderEngine.bindTexture((ResourceLocation) emptyBar);
		drawTexturedModalRect((int) (width)-128, (height)-128, 0, 0, 256, 256);
		mc.renderEngine.bindTexture((ResourceLocation) fullBar);
		drawTexturedModalRect((int) (width)-128, (height)-128, 0, 0, (int) (bar * 2.56), 256);
		GL11.glScalef(2F, 2F, 2F);
		drawCenteredString(mc.fontRenderer, storage+"%", width / 2, (height / 2) - 48, Integer.parseInt("FFFFFF", 16));
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}
}
