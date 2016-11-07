/**
 * 
 */
package se.Matryoshika.Saligia.Rendering.Tiles;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import se.Matryoshika.Saligia.Content.Blocks.BlockCustomCauldron;
import se.Matryoshika.Saligia.Content.Tiles.TileCauldron;
import se.Matryoshika.Saligia.Utils.PseudoPoint;

/**
 * This class was created by Matryoshika Nov 4, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TileCauldronTESR extends TileEntitySpecialRenderer<TileCauldron>{
	
	private static final ResourceLocation ICON_MISSING = new ResourceLocation("textures/misc/unknown_server.png");
	
	float base = 360f/7f;
	public List<PseudoPoint> smallSeptagon = new ArrayList<PseudoPoint>();
	public List<PseudoPoint> bigSeptagon = new ArrayList<PseudoPoint>();
	public List<List<PseudoPoint>> symbols = new ArrayList<List<PseudoPoint>>();
	
	final List<PseudoPoint> sigma = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> othala = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> iwaz = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> neu = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> jeran_one = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> jeran_two = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> yama = new ArrayList<PseudoPoint>();
	final List<PseudoPoint> bord = new ArrayList<PseudoPoint>();
	
	public boolean goUp = true;
	
	float height;
	
	BlockCustomCauldron cauldron = null;
	
	public TileCauldronTESR(){
		super();
		for(float i = 0; i < 8; i++){
	    	if(i*base <= 360f){
	    		double dx1 = (4*(16/10)) * Math.cos((i*base) * (Math.PI / 180));
		        double dz1 = (4*(16/10)) * Math.sin((i*base) * (Math.PI / 180));
		        smallSeptagon.add(new PseudoPoint(dx1, dz1));
	    	}
	    }
		for(float i = 0; i < 8; i++){
	    	if(i*base <= 360f){
	    		double dx1 = (3.25*(24/10)) * Math.cos((i*base) * (Math.PI / 180));
		        double dz1 = (3.25*(24/10)) * Math.sin((i*base) * (Math.PI / 180));
		        bigSeptagon.add(new PseudoPoint(dx1, dz1));
	    	}
	    }
		sigma.add(new PseudoPoint(1,2)); 
		sigma.add(new PseudoPoint(-1,2));
		sigma.add(new PseudoPoint(1,0)); 
		sigma.add(new PseudoPoint(-1,-2)); 
		sigma.add(new PseudoPoint(1,-2));
		
		othala.add(new PseudoPoint(-1,-1));
		othala.add(new PseudoPoint(1,1));
		othala.add(new PseudoPoint(0,2));
		othala.add(new PseudoPoint(-1,1));
		othala.add(new PseudoPoint(1,-1));
		
		iwaz.add(new PseudoPoint(1,0.5));
		iwaz.add(new PseudoPoint(0,1.5));
		iwaz.add(new PseudoPoint(0,-1.5));
		iwaz.add(new PseudoPoint(-1,-0.5));
		
		neu.add(new PseudoPoint(0.5,-1));
		neu.add(new PseudoPoint(-0.5,0));
		neu.add(new PseudoPoint(0,1));
		neu.add(new PseudoPoint(-1,-1));
		
		jeran_one.add(new PseudoPoint(0,2));
		jeran_one.add(new PseudoPoint(-1.5,0.5));
		jeran_one.add(new PseudoPoint(0,-0.5));
		jeran_two.add(new PseudoPoint(0,0.5));
		jeran_two.add(new PseudoPoint(1.5,-0.5));
		jeran_two.add(new PseudoPoint(0,-2));
		
		yama.add(new PseudoPoint(-1.5,0.5));
		yama.add(new PseudoPoint(-1.5,-1));
		yama.add(new PseudoPoint(0,-1));
		yama.add(new PseudoPoint(0,1));
		yama.add(new PseudoPoint(0,-1));
		yama.add(new PseudoPoint(1.5,-1));
		yama.add(new PseudoPoint(1.5,0.5));
		
		bord.add(new PseudoPoint(-2,0.5));
		bord.add(new PseudoPoint(-0.5,0.5));
		bord.add(new PseudoPoint(-1,-0.5));
		bord.add(new PseudoPoint(-0.5,0.5));
		bord.add(new PseudoPoint(0.5,0.5));
		bord.add(new PseudoPoint(1,-0.5));
		bord.add(new PseudoPoint(0.5,0.5));
		bord.add(new PseudoPoint(2,0.5));
	
	
	}
		
				
	
	public void renderTileEntityAt(TileCauldron te, double x, double y, double z, float partialTicks, int destroyStage){
		
		long activeAgeTicks = ((System.currentTimeMillis() - te.getClientAge() ) / 20);
		
		if(cauldron == null){
			cauldron = (BlockCustomCauldron) te.getWorld().getBlockState(te.getPos()).getBlock();
		}
		if(te.getWorld().getBlockState(te.getPos()).getValue(cauldron.LEVEL).intValue() != 2){
			te.setClientAge(System.currentTimeMillis());
			return;
		}
		
		if(te.getWorld().getBlockState(te.getPos().down()).getMaterial() != Material.FIRE){
			te.setClientAge(System.currentTimeMillis());
			return;
		}
		
		if(!te.gotTables()){
			te.setClientAge(System.currentTimeMillis());
			drawTables(te, x, y, z);
			return;
		}

		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x + 0.5F, (float)y+0.001, (float)z + 0.5F);
		
		if(te.STATES+1 < te.jars.size())
			drawCurrentItem(te);
		
		GlStateManager.rotate(90, 1, 0, 0);
        GlStateManager.rotate(180, 0, 1, 0);
        
        GlStateManager.color(0, 0, 0, 1f);
        
        if(activeAgeTicks <= 720){
        	GlStateManager.scale(activeAgeTicks/720f, activeAgeTicks/720f, activeAgeTicks/720f);
        	GlStateManager.rotate(activeAgeTicks/2f, 0, 0, 1);
        	GlStateManager.color(0, 0, 0, activeAgeTicks/720f*0.75f);
        }

        GlStateManager.enableBlend();
	    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	    
	    //System.out.println(r);


	    GlStateManager.glLineWidth(3f);
 
        drawFromList(smallSeptagon);
        drawFromList(bigSeptagon);
        drawSeptacle(x, y, z, 26);
        GlStateManager.rotate(90, 0, 0, 1);
        GlStateManager.rotate(((activeAgeTicks*(te.STATES+2)) % 3600) / 2f, 0, 0, 1);
        GlStateManager.scale(0.33, 0.33, 0.33);
        GlStateManager.glLineWidth(4f);
        
        GlStateManager.translate(smallSeptagon.get(0).getX()*3.5f, smallSeptagon.get(0).getY()*3.5f, 0);
        drawFromList(othala);
        GlStateManager.translate(-smallSeptagon.get(0).getX()*3.5f, -smallSeptagon.get(0).getY()*3.5f, 0);
        GlStateManager.translate(smallSeptagon.get(1).getX()*3.5f, smallSeptagon.get(1).getY()*3.5f, 0);
        GlStateManager.scale(0.8, 0.8, 0.8);
        GlStateManager.rotate(45, 0, 0, 1);
        drawFromList(sigma);
        GlStateManager.rotate(-45, 0, 0, 1);
        GlStateManager.scale(1.25, 1.25, 1.25);
        GlStateManager.translate(-smallSeptagon.get(1).getX()*3.5f, -smallSeptagon.get(1).getY()*3.5f, 0);
        GlStateManager.translate(smallSeptagon.get(2).getX()*3.5f, smallSeptagon.get(2).getY()*3.5f, 0);
        drawFromList(iwaz);
        GlStateManager.translate(-smallSeptagon.get(2).getX()*3.5f, -smallSeptagon.get(2).getY()*3.5f, 0);
        GlStateManager.translate(smallSeptagon.get(3).getX()*3.5f, smallSeptagon.get(3).getY()*3.5f, 0);
        GlStateManager.rotate(60, 0, 0, 1);
        drawFromList(neu);
        GlStateManager.rotate(-60, 0, 0, 1);
        GlStateManager.translate(-smallSeptagon.get(3).getX()*3.5f, -smallSeptagon.get(3).getY()*3.5f, 0);
        GlStateManager.translate(smallSeptagon.get(4).getX()*3.5f, smallSeptagon.get(4).getY()*3.5f, 0);
        drawFromList(jeran_one);
        drawFromList(jeran_two);
        GlStateManager.translate(-smallSeptagon.get(4).getX()*3.5f, -smallSeptagon.get(4).getY()*3.5f, 0);
        GlStateManager.translate(smallSeptagon.get(5).getX()*3.5f, smallSeptagon.get(5).getY()*3.5f, 0);
        GlStateManager.rotate(170, 0, 0, 1);
        drawFromList(yama);
        GlStateManager.rotate(-170, 0, 0, 1);
        GlStateManager.translate(-smallSeptagon.get(5).getX()*3.5f, -smallSeptagon.get(5).getY()*3.5f, 0);
        GlStateManager.translate(smallSeptagon.get(6).getX()*3.5f, smallSeptagon.get(6).getY()*3.5f, 0);
        GlStateManager.rotate(-140, 0, 0, 1);
        drawFromList(bord);
        GlStateManager.rotate(140, 0, 0, 1);
        GlStateManager.translate(-smallSeptagon.get(6).getX()*3.5f, -smallSeptagon.get(6).getY()*3.5f, 0);
        
		GlStateManager.popMatrix();
	   
	   
	    

    }
	
	
	
	public void drawCurrentItem(TileCauldron te){
		GlStateManager.translate(0, 1, 0);
		GlStateManager.scale(0.5, 0.5, 0.5);
    	GlStateManager.rotate((System.currentTimeMillis() / 40) % (360), 0, 1, 0);
    	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(te.jars.get(te.STATES+1)), ItemCameraTransforms.TransformType.NONE);
    	GlStateManager.rotate(-(System.currentTimeMillis() / 40) % (360), 0, 1, 0);
    	GlStateManager.scale(2, 2, 2);
    	GlStateManager.translate(0, -1, 0);
	}
	
	public void drawTables(TileCauldron te, double x, double y, double z){
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x + 0.5F, (float)y+0.001, (float)z + 0.5F);
		for(BlockPos position : te.tables){
			GlStateManager.translate(position.getX(), 0.5, position.getZ());
			GlStateManager.scale(0.5, 0.5, 0.5);
	    	GlStateManager.rotate((System.currentTimeMillis() / 40) % (360), 0, 1, 0);
	    	Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(Blocks.ENCHANTING_TABLE), ItemCameraTransforms.TransformType.NONE);
	    	GlStateManager.rotate(-(System.currentTimeMillis() / 40) % (360), 0, 1, 0);
	    	GlStateManager.scale(2, 2, 2);
	    	GlStateManager.translate(-position.getX(), -0.5, -position.getZ());
		}
		GlStateManager.translate(-((float)x + 0.5F), -((float)y+0.001), -((float)z + 0.5F));
		GlStateManager.popMatrix();
		
	}
	
	public void drawFromList(List<PseudoPoint> list){

		GlStateManager.disableTexture2D();
	    GL11.glBegin(GL11.GL_LINE_STRIP);
	    
	    for(int i = 0; i < list.size(); i++){
	    	GL11.glVertex3d(list.get(i).getX(),list.get(i).getY(),0);
	    }
	    GL11.glEnd();
		GlStateManager.enableTexture2D();
		
	}
	
	public void drawSeptacle(double x, double y, double z, double range){
        
        double radius = 2.5f;
		GlStateManager.disableTexture2D();
	    GL11.glBegin(GL11.GL_LINE_LOOP);
	    
	    float base = 360f/7f;
	    
	    int[] vertices = new int[]{1,4,7,3,6,2,5};
	    
	    for(int vertice : vertices){
	    	double dx1 = (radius*(range/10)) * Math.cos((vertice*base) * (Math.PI / 180));
	        double dz1 = (radius*(range/10)) * Math.sin((vertice*base) * (Math.PI / 180));
	        GL11.glVertex3d(dx1,dz1,0);
	    }
        
	    GL11.glEnd();
	    
		GlStateManager.enableTexture2D();
	}

}
