package Matryoshika.mods.saligia.rendering.GUI;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import Matryoshika.mods.saligia.items.saligia_Items;
import cpw.mods.fml.client.config.GuiButtonExt;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GUILibroSaligia extends GuiContainer{
	
	public final int xSize = 512;
	public final int ySize = 512;
	
	public int bookX= 217;
	public int bookY= 40;
	
	public static ResourceLocation res = new ResourceLocation(saligia.MODID + ":" + "textures/gui/GUILibroSaligia.png");
	public static ResourceLocation blood = new ResourceLocation(saligia.MODID + ":" + "textures/items/BloodStone.png");
	public static ResourceLocation book = new ResourceLocation(saligia.MODID + ":" + "textures/items/LibroSaligia.png");
	public static ResourceLocation soul = new ResourceLocation(saligia.MODID + ":" + "textures/items/soul.png");
	
	
	public GUILibroSaligia() {
		super(new ContainerLibroSaligia());
		


	}
	
	public void initGui(){
		super.initGui();
		int posX = (xSize);
		int posY = (this.height - ySize);
		this.buttonList.clear();
		
		this.buttonList.add(new GuiButtonExt(0, (int)(bookX*1.3), (int)(bookY*2.9), 45, 15, (StatCollector.translateToLocal("saligia.book.tab1"))));
		this.buttonList.add(new GuiButtonExt(1, (int)(bookX*1.3), (int)(bookY*3.5), 45, 15, (StatCollector.translateToLocal("saligia.book.tab2"))));
		this.buttonList.add(new GuiButtonExt(2, (int)(bookX*1.3), (int)(bookY*4.1), 45, 15, (StatCollector.translateToLocal("saligia.book.tab3"))));
		this.buttonList.add(new GuiButtonExt(3, (int)(bookX*1.3), (int)(bookY*4.7), 45, 15, (StatCollector.translateToLocal("saligia.book.tab4"))));
		
		
		
		//this.buttonList.add(new GuiButton(1, 185, 55, 0, 0, "Libro Saligia"));
		//fontRendererObj.drawString("Libro Saligia", 185, 55, 4210752);
		
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
            //fontRendererObj.drawString("Libro Saligia", 8, 6, 4210752);
            //fontRendererObj.drawString(StatCollector.translateToLocal("container.librosaligia"), 8, ySize - 96 + 2, 4210752);
		
		//mc.renderEngine.getTexture(relics);
		


		
		
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		
		mc.renderEngine.getTexture(res);
		
		
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture((ResourceLocation) res);

		
		drawTexturedModalRect(bookX, bookY, 0, 0, 200, 256);
		fontRendererObj.drawString("Libro Saligia", bookX+60, 55, 4210752);
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.1"),(int) (bookX*1.42), 85, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.2"), (int) (bookX*1.42), 95, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.3"), (int) (bookX*1.42), 105, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.4"), (int) (bookX*1.42), 115, 4210752);
		
		/*if(mouseX >= (bookX*1.2) && mouseX <= (bookX*1.5) && mouseY >=bookY*2.9 && mouseY <= bookY*3.2){
			System.out.println("It's here, Boss");
		}*/
		
		
		GL11.glScalef(1.2F, 1.2F, 1.2F);
		
		
		Tessellator t = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(Item.getItemFromBlock(saligia_Blocks.AltarPagan)), (int) (bookX*1.2), (int) (bookY*2.9));
		RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(saligia_Items.BloodStone), (int) (bookX*1.2), (int) (bookY*3.5));
		RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(saligia_Items.LibroSaligia), (int) (bookX*1.2), (int) (bookY*4.1));
		RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(saligia_Items.SoulCrucible), (int) (bookX*1.2), (int) (bookY*4.7));
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
		
		
		
		//myDrawTexturedModalRect((int) (bookX*1.2), (int) (bookY*2.9), bookX*2, (int)(bookY*0.4));
	}
	
	
	public void actionPerformed(GuiButton button){
		switch(button.id){
			case 0:{
			//open Relics tab
			break;
			}
			case 1:{
			//open Altar Tab
			break;
			}	
			case 2:{
			//open Recipes Tab
			break;
			}
			case 3:{
			//open Rituals Tab
			break;
			}
		}
	}
	
	

}
