package Matryoshika.mods.saligia.rendering.GUI;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GUILibroSaligia extends GuiContainer{
	
	public final int xSize = 512;
	public final int ySize = 512;
	
	public int bookX= 145;
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
		this.buttonList.add(new GuiButton(0, bookX+50, bookY+75, 40, 0, (StatCollector.translateToLocal("saligia.book.tab1"))));
		this.buttonList.add(new GuiButton(1, bookX+50, bookY+100, 40, 0, (StatCollector.translateToLocal("saligia.book.tab2"))));
		this.buttonList.add(new GuiButton(2, bookX+50, bookY+125, 40, 0, (StatCollector.translateToLocal("saligia.book.tab3"))));
		this.buttonList.add(new GuiButton(3, bookX+50, bookY+150, 40, 0, (StatCollector.translateToLocal("saligia.book.tab4"))));
		
		
		
		//this.buttonList.add(new GuiButton(1, 185, 55, 0, 0, "Libro Saligia"));
		//fontRendererObj.drawString("Libro Saligia", 185, 55, 4210752);
		
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
            //fontRendererObj.drawString("Libro Saligia", 8, 6, 4210752);
            //fontRendererObj.drawString(StatCollector.translateToLocal("container.librosaligia"), 8, ySize - 96 + 2, 4210752);
		
		//mc.renderEngine.getTexture(relics);
		GL11.glScalef(0.07F, 0.07F, 0.07F);
		this.mc.renderEngine.bindTexture((ResourceLocation) soul);
		drawTexturedModalRect((int) (bookX*2.65), bookY*20, 0, 0, 256, 256);
		this.mc.renderEngine.bindTexture((ResourceLocation) blood);
		drawTexturedModalRect((int) (bookX*2.65), bookY*29, 0, 0, 256, 256);
		this.mc.renderEngine.bindTexture((ResourceLocation) book);
		drawTexturedModalRect((int) (bookX*2.65), bookY*38, 0, 0, 256, 256);


		
		
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		
		mc.renderEngine.getTexture(res);
		
		
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture((ResourceLocation) res);

		
		drawTexturedModalRect(bookX, bookY, 0, 0, 200, 256);
		fontRendererObj.drawString("Libro Saligia", bookX+60, 55, 4210752);
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.1"), bookX+71, 85, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.2"), bookX+71, 95, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.3"), bookX+71, 105, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("saligia.book1.4"), bookX+71, 115, 4210752);
		
		if(mouseX >= (bookX*2.65) && mouseX <= (bookX*2.65)+250 && mouseY >=bookY*20 && mouseY <= bookY*20+250){
			System.out.println("It's here, Boss");
		}
		
		
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}
	
	
	
	
	

}
