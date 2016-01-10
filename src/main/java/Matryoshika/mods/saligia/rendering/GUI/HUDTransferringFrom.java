package Matryoshika.mods.saligia.rendering.GUI;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class HUDTransferringFrom  extends Gui{
	
	public static ResourceLocation dagger = new ResourceLocation(saligia.MODID + ":" + "textures/items/dagger1.png");
	
	public HUDTransferringFrom(Minecraft mc){
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		GL11.glPushMatrix();
		
		mc.renderEngine.bindTexture((ResourceLocation) dagger);
		GL11.glScalef(0.125F, 0.125F, 0.125F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		//drawTexturedModalRect((int) (width*4)-128, (height*4)-512, 0, 0, 256, 256);
		GL11.glScalef(8F, 8F, 8F);
		drawCenteredString(mc.fontRenderer, "Transferring to Storage", width / 2, (height / 2) - 20, Integer.parseInt("FFFFFF", 16));
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}
}
