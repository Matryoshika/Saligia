package Matryoshika.mods.saligia.rendering.GUI;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class HUDTransferringTo extends Gui{
	
	public static ResourceLocation crucible = new ResourceLocation(saligia.MODID + ":" + "textures/items/soulCrucible.png");
	public static ResourceLocation arrow = new ResourceLocation(saligia.MODID + ":" + "textures/gui/arrowLeft.png");
	public static ResourceLocation storage = new ResourceLocation(saligia.MODID + ":" + "textures/items/soulCrucible.png");
	
	public HUDTransferringTo(Minecraft mc){
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = scaled.getScaledWidth();
		int height = scaled.getScaledHeight();
		
		GL11.glPushMatrix();
		
		GL11.glScalef(0.125F, 0.125F, 0.125F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		/*mc.renderEngine.bindTexture((ResourceLocation) crucible);
		drawTexturedModalRect((int) (width*4)-512, (height*4)-512, 0, 0, 256, 256);
		//mc.renderEngine.bindTexture((ResourceLocation) arrow);
		
		GL11.glEnable(GL11.GL_POLYGON_STIPPLE);
		mc.renderEngine.bindTexture((ResourceLocation) arrow);
		drawTexturedModalRect((int) (width*4)-128, (height*4)-512, 0, 0, 256, 256);*/
		GL11.glScalef(8F, 8F, 8F); 
		drawCenteredString(mc.fontRenderer, "Transferring to Crucible", width / 2, (height / 2) - 20, Integer.parseInt("FFFFFF", 16));
		
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}

}
