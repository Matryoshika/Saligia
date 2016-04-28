package Matryoshika.mods.saligia.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.altars.TilePaganAltar;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class tileSoulNexusRenderer extends TileEntitySpecialRenderer {
	
	ResourceLocation objModelLocation;
	IModelCustom model;
	ResourceLocation texture;
	
	public tileSoulNexusRenderer(){
        texture = new ResourceLocation(saligia.MODID, "models/Storage_nexus.png");
        objModelLocation = new ResourceLocation(saligia.MODID, "models/Storage_nexus.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
    }

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float ticks) {
		TileSoulNexus nexus = (TileSoulNexus) tile;
    	
    	GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		GL11.glTranslated(x, y + 1, z);
		GL11.glRotatef(90F, -90F, 0F, 0.5F);
		GL11.glTranslated(0.5F, -0.5F, -1F);
		
		
		model.renderAll();

		GL11.glTranslated(-0.5F, 0.5F, 1.0F);
		GL11.glRotatef(-270F, 270F, 0F, 2F);
		GL11.glScalef(0.25F, 0.25F, 0.25F);
		GL11.glScalef(1F, -1F, -1F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		
	}

}
