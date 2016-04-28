package Matryoshika.mods.saligia.rendering;

import java.util.Random;

import javax.vecmath.Vector3d;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.tile.soulsystem.TileLeyLineCrystal;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulNexus;
import Matryoshika.mods.saligia.tile.soulsystem.TileSoulObelisk;
import Matryoshika.mods.saligia.utils.math.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class tileLeyLineCrystalRenderer extends TileEntitySpecialRenderer{
	
	ResourceLocation laserbeams[] = new ResourceLocation[4];
	ResourceLocation halo = new ResourceLocation(saligia.MODID, "textures/effects/halo.png");
	Random random = new Random();
	
	
	int SOURCE_X;
	int SOURCE_Y;
	int SOURCE_Z;
	float SOURCE_RED = 0.1F;
	float SOURCE_GREEN = 0.2F;
	float SOURCE_BLUE = 0.5F;
	float SOURCE_ALPHA = 0.5F;
	
	int DEST_X;
	int DEST_Y;
	int DEST_Z;
	float DEST_RED = SOURCE_RED + (getRandom() / 10);
	float DEST_GREEN = SOURCE_GREEN + (getRandom() / 10);
	float DEST_BLUE = SOURCE_BLUE + (getRandom() / 10);
	float DEST_ALPHA = SOURCE_ALPHA + (getRandom() / 10);

	int addedY = 0;
	
	public static int currentPass = 0;
	
	
	 public tileLeyLineCrystalRenderer() {
	        laserbeams[0] = new ResourceLocation(saligia.MODID, "textures/effects/laserbeam1.png");
	        laserbeams[1] = new ResourceLocation(saligia.MODID, "textures/effects/laserbeam2.png");
	        laserbeams[2] = new ResourceLocation(saligia.MODID, "textures/effects/laserbeam3.png");
	        laserbeams[3] = new ResourceLocation(saligia.MODID, "textures/effects/laserbeam4.png");
	    }
	
	
	
	@Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float ticks){
		
		TileLeyLineCrystal crystal = (TileLeyLineCrystal) tile;
		
		
		
		if(crystal.SOURCE_EXISTS){
			SOURCE_X = crystal.getSourceTile().xCoord;
			SOURCE_Y = crystal.getSourceTile().yCoord;
			SOURCE_Z = crystal.getSourceTile().zCoord;
		}
		if(crystal.DEST_EXISTS){
			DEST_X = crystal.getDestTile().xCoord;
			if(crystal.getDestTile() instanceof TileSoulNexus) {DEST_Y = (int) (crystal.getDestTile().yCoord +2.5);}
			else if(crystal.getDestTile() instanceof TileSoulObelisk){DEST_Y = (int) (crystal.getDestTile().yCoord +1.5);}
			else {DEST_Y = crystal.getDestTile().yCoord;}
			DEST_Z = crystal.getDestTile().zCoord;
		}
		
		
		if(crystal.SOURCE_EXISTS && crystal.DEST_EXISTS){
			
			if(crystal.sourceAmount() > 0 && crystal.destinationCanRecieve() > 0){
				
				GL11.glPushAttrib(GL11.GL_CURRENT_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_ENABLE_BIT | GL11.GL_LIGHTING_BIT | GL11.GL_TEXTURE_BIT);

			       
				GL11.glDepthMask(false);

	            GL11.glEnable(GL11.GL_BLEND);
	            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

	            GL11.glPushMatrix();
	            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
	            GL11.glScalef(0.5F, 0.5F, 0.5F);
	            this.bindTexture(halo);
	            renderBillboardQuad(1.0f, 1D);
	            GL11.glScalef(2f, 2f, 2f);
	            GL11.glPopMatrix();

	            Minecraft mc = Minecraft.getMinecraft();
	            EntityClientPlayerMP p = mc.thePlayer;
	            double doubleX = p.lastTickPosX + (p.posX - p.lastTickPosX) * ticks;
	            double doubleY = p.lastTickPosY + (p.posY - p.lastTickPosY) * ticks;
	            double doubleZ = p.lastTickPosZ + (p.posZ - p.lastTickPosZ) * ticks;

	            Vector start = new Vector(crystal.xCoord + .5f, crystal.yCoord + .5f, crystal.zCoord + .5f);
	            Vector end = new Vector(DEST_X + .5f, DEST_Y + .5f, DEST_Z + .5f);
	            Vector player = new Vector((float) doubleX, (float) doubleY, (float) doubleZ);

	            GL11.glPushMatrix();
	            GL11.glTranslated(-doubleX, -doubleY, -doubleZ);

	            Tessellator tessellator = Tessellator.instance;

	            // ----------------------------------------

	            this.bindTexture(laserbeams[random.nextInt(4)]);

	            tessellator.startDrawingQuads();
	            tessellator.setBrightness(240);

	                
	            drawBeam(start, end, player, .05f);
	            
	                
	            

	            tessellator.draw();

	            GL11.glPopMatrix();

	            GL11.glPopAttrib();
				
			}

		}
		
	}

	
	public int getRandom(){
		Random rand = new Random();
		return rand.nextInt(10);
	}
	
	public void renderBillboardQuad(float rot, double scale)
    {
        GL11.glPushMatrix();

        GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);

        GL11.glPushMatrix();

        GL11.glRotatef(rot, 0, 0, 1);

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        GL11.glColor3f(1, 1, 1);
        tessellator.setColorRGBA(255, 255, 255, 255);
        tessellator.addVertexWithUV(-scale, -scale, 0, 0, 0);
        tessellator.addVertexWithUV(-scale, scale, 0, 0, 1);
        tessellator.addVertexWithUV(scale, scale, 0, 1, 1);
        tessellator.addVertexWithUV(scale, -scale, 0, 1, 0);
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
	
	public static void drawBeam(Vector S, Vector E, Vector P, float width) {
		Vector PS = Matryoshika.mods.saligia.utils.math.Sub(S, P);
		Vector SE = Matryoshika.mods.saligia.utils.math.Sub(E, S);

		Vector normal = Matryoshika.mods.saligia.utils.math.Cross(PS, SE);
        normal = normal.normalize();

        Vector half = Matryoshika.mods.saligia.utils.math.Mul(normal, width);
        Vector p1 = Matryoshika.mods.saligia.utils.math.Add(S, half);
        Vector p2 = Matryoshika.mods.saligia.utils.math.Sub(S, half);
        Vector p3 = Matryoshika.mods.saligia.utils.math.Add(E, half);
        Vector p4 = Matryoshika.mods.saligia.utils.math.Sub(E, half);

        drawQuad(Tessellator.instance, p1, p3, p4, p2);
    }
	
	
    
    public static void drawQuad(Tessellator tessellator, Vector p1, Vector p2, Vector p3, Vector p4) {
        tessellator.addVertexWithUV(p1.getX(), p1.getY(), p1.getZ(), 0, 0);
        tessellator.addVertexWithUV(p2.getX(), p2.getY(), p2.getZ(), 1, 0);
        tessellator.addVertexWithUV(p3.getX(), p3.getY(), p3.getZ(), 1, 1);
        tessellator.addVertexWithUV(p4.getX(), p4.getY(), p4.getZ(), 0, 1);
    }
	
}
