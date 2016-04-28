package Matryoshika.mods.saligia.rendering;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.runes.ItemRune;
import Matryoshika.mods.saligia.tile.altars.TilePaganAltar;

public class tilePaganAltarRenderer extends TileEntitySpecialRenderer{

	public Minecraft mc = Minecraft.getMinecraft();
	//private ModelPedestal stand = new ModelPedestal();
	
	//private static final ResourceLocation texture = new ResourceLocation(saligia.MODID+":paganAltar");
	ResourceLocation objModelLocation;
	IModelCustom model;
	ResourceLocation texture;
	
	public tilePaganAltarRenderer(){
        texture = new ResourceLocation(saligia.MODID, "models/Altar_pagan.png");
        objModelLocation = new ResourceLocation(saligia.MODID, "models/Altar_pagan.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
    }
	
	//ModelPaganAltar model = new ModelPaganAltar()
	EntityItem entItem = null;
	
	private final RenderBlocks renderBlock = new RenderBlocks();
    RenderItem renderItem = new RenderItem();

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float ticks){
    	
    	TilePaganAltar altar = (TilePaganAltar) tile;
    	
    	GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		GL11.glTranslated(x, y + 1, z);
		
		GL11.glRotatef(90F, -90F, 0F, 0.5F);
		GL11.glTranslated(0.5F, -0.5F, -1F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		model.renderAll();
		GL11.glScalef(2F, 2F, 2F);
		GL11.glTranslated(-0.5F, 0.5F, 1.0F);
		GL11.glRotatef(-270F, 270F, 0F, 2F);

		GL11.glScalef(0.25F, 0.25F, 0.25F);
		//Item 1
		if(altar.getStackInSlot(0) != null && altar.getStackInSlot(0).getItem() instanceof ItemBlock){
			GL11.glTranslated(-2F, 0F, -2F);
			GL11.glScalef(4F, 4F, 4F);		
			renderItem(tile.getWorldObj(),altar, x+5, y, z, 0, -1F, 0.2F, -1F);
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			GL11.glTranslated(2F, 0F, 2F);
		}else{
			GL11.glTranslated(-2F, 0F, -2F);
			GL11.glScalef(4F, 4F, 4F);
			renderItem(tile.getWorldObj(),altar, x, y, z, 0, 1F, 0.2F, 1F);
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			GL11.glTranslated(2F, 0F, 2F);
		}
		
		if(altar.getStackInSlot(0) != null && altar.getStackInSlot(0).getItem() instanceof ItemBlock){
			GL11.glScalef(2F, 2F, 2F);
			if(altar.getStackInSlot(1) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 1, 1F, 0F, 0.5F);
			if(altar.getStackInSlot(2) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 2, 1F, 0F, 1.5F);
			if(altar.getStackInSlot(3) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 3, 0.5F, 0F, 1F);
			if(altar.getStackInSlot(4) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 4, 1.5F, 0F, 1F);
			if(altar.getStackInSlot(5) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 5, 0.66F, 0F, 0.66F);
			if(altar.getStackInSlot(6) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 6, 1.33F, 0F, 0.66F);
			if(altar.getStackInSlot(7) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 7, 1.33F, 0F, 1.33F);
			if(altar.getStackInSlot(8) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 8, 0.66F, 0F, 1.33F);
			
			GL11.glScalef(0.5F, 0.5F, 0.5F);
		}
		else{
			GL11.glScalef(2F, 2F, 2F);
			if(altar.getStackInSlot(1) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 1, 0.5F, 0F, 1F);
			if(altar.getStackInSlot(2) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 2, 1.5F, 0F, 1F);
			if(altar.getStackInSlot(3) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 3, 1F, 0F, 1.5F);
			if(altar.getStackInSlot(4) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 4, 1F, 0F, 0.5F);
			if(altar.getStackInSlot(5) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 5, 0.66F, 0F, 1.33F);
			if(altar.getStackInSlot(6) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 6, 0.66F, 0F, 0.66F);
			if(altar.getStackInSlot(7) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 7, 1.33F, 0F, 0.66F);
			if(altar.getStackInSlot(8) != null)renderItem(tile.getWorldObj(),altar, x, y, z, 8, 1.33F, 0F, 1.33F);
			
			GL11.glScalef(0.5F, 0.5F, 0.5F);
		}
		GL11.glScalef(1F, -1F, -1F);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		  
    }

    private static void setGLColorFromInt(int color)
    {
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;

        GL11.glColor4f(red, green, blue, 1.0F);
    }
    
    public void renderItem(World world, TilePaganAltar altar, double x, double y, double z, int slot, float x1, float y1, float z1){ 
    	if(altar.getStackInSlot(slot) != null){
    		ItemStack stack = altar.getStackInSlot(slot);
    		if(stack.getItem() instanceof ItemBlock){
    			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
    			Block block = Block.getBlockFromItem(stack.getItem());
    			//this.renderBlock.renderBlockAsItem(block, stack.getItemDamage(), 1F);
    			
    			entItem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
    			GL11.glPushMatrix();
    			Color color = new Color(stack.getItem().getColorFromItemStack(stack, 0));
    			GL11.glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
    			//GL11.glTranslatef(x1, y1, z1);
    			GL11.glDisable(GL11.GL_LIGHTING);
    			this.entItem.hoverStart = 0.0F;
    			//RenderItem.renderInFrame = true;
    			//GL11.glRotatef(2*altar.hoverItem, 0F, 1F, 0F);
    			RenderManager.instance.renderEntityWithPosYaw(this.entItem, 1.0D, 0.0D, 1.0D, 0.0F, 1.0F*altar.hoverItem);
    			//RenderItem.renderInFrame = false;
    			//ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 16F);
    			GL11.glColor3f(1F, 1F, 1F);
    			GL11.glEnable(GL11.GL_LIGHTING);
    			//GL11.glTranslatef(-x1, -y1, -z1);
    			GL11.glPopMatrix();
    		} else{
    	    	entItem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
        		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
    			IIcon icon = stack.getItem().getIcon(stack, 0);
        		if(icon != null) {
        			GL11.glPushMatrix();
        			Color color = new Color(stack.getItem().getColorFromItemStack(stack, 0));
        			GL11.glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
        			float f = icon.getMinU();
        			float f1 = icon.getMaxU();
        			float f2 = icon.getMinV();
        			float f3 = icon.getMaxV();
        			GL11.glTranslatef(x1, y1, z1);
        			GL11.glDisable(GL11.GL_LIGHTING);
        			this.entItem.hoverStart = 0.0F;
        			RenderItem.renderInFrame = true;
        			GL11.glRotatef(2*altar.hoverItem, 0F, 1F, 0F);
        			RenderManager.instance.renderEntityWithPosYaw(this.entItem, 0.0D, 0.0D, 0.0D, 0.0F, 2F*altar.hoverItem);
        			//RenderManager.instance.renderEntityWithPosYaw(this.entItem, 0.0D, 0.0D, 0.0D, 0.0F, 2.0F*altar.hoverItem);
        			RenderItem.renderInFrame = false;
        			GL11.glColor3f(1F, 1F, 1F);
        			GL11.glEnable(GL11.GL_LIGHTING);
        			GL11.glTranslatef(-x1, -y1, -z1);
        			GL11.glPopMatrix();
        		 }
    		}

    	}
    }
}
