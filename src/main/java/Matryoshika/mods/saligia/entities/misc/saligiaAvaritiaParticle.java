package Matryoshika.mods.saligia.entities.misc;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class saligiaAvaritiaParticle extends EntityFX{
	
	public static final ResourceLocation particles = new ResourceLocation(saligia.MODID+":");
	
    private float portalParticleScale;
    private double portalPosX;
    private double portalPosY;
    private double portalPosZ;
    //private static final String __OBFID = "CL_00000921";

    public saligiaAvaritiaParticle(World p_i1222_1_, double p_i1222_2_, double p_i1222_4_, double p_i1222_6_, double p_i1222_8_, double p_i1222_10_, double p_i1222_12_){
        super(p_i1222_1_, p_i1222_2_, p_i1222_4_, p_i1222_6_, p_i1222_8_, p_i1222_10_, p_i1222_12_);
        this.motionX = p_i1222_8_;
        this.motionY = p_i1222_10_;
        this.motionZ = p_i1222_12_;
        this.portalPosX = this.posX = p_i1222_2_;
        this.portalPosY = this.posY = p_i1222_4_;
        this.portalPosZ = this.posZ = p_i1222_6_;
        float f = this.rand.nextFloat() * 0.6F + 0.4F;
        this.portalParticleScale = this.particleScale = this.rand.nextFloat() * 5.9F + 0.5F;
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F * f;
        this.particleGreen *= 0.005F;
        this.particleRed *= 0.005F;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.noClip = true;
        this.setParticleTextureIndex((int)(Math.random() * 8.0D));
    }

    public void renderParticle(Tessellator tesselator, float par2, float par3, float par4, float par5, float par6, float par7){
    	tesselator.draw();
		GL11.glPushMatrix();
		GL11.glDepthFunc(GL11.GL_ALWAYS);

		GL11.glColor3f(1.0F, 1.0F, 1.0F);
		tesselator.startDrawingQuads();
		
		tesselator.setBrightness(256);
		
		float var8 = ((float)this.particleAge + par2) / (float)this.particleMaxAge * 2.0F;

		if (var8 < 0.0F){
			var8 = 0.0F;
		}

		if (var8 > 1.0F){
			var8 = 1.0F;
		}


		float f6 = (float)this.particleTextureIndexX / 16.0F;
		float f7 = f6+0.03F;
		float f8 = (float)this.particleTextureIndexY / 16.0F;
		float f9 = f8+0.03F;
		float f10 = 0.1F;// * this.particleScale;

		float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
		float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY) *3;
		float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
		float f14 = 1.0F;

		tesselator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
		tesselator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f7, (double)f9);
		tesselator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f7, (double)f8);
		tesselator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f6, (double)f8);
		tesselator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f6, (double)f9);
		tesselator.draw();


		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glPopMatrix();
		//Minecraft.getMinecraft().renderEngine.bindTexture(ParticleUtils.getParticleTexture());
		
		tesselator.startDrawingQuads();
		tesselator.setBrightness(0);
    }

    public int getBrightnessForRender(float p_70070_1_){
        int i = super.getBrightnessForRender(p_70070_1_);
        float f1 = (float)this.particleAge / (float)this.particleMaxAge;
        f1 *= f1;
        f1 *= f1;
        int j = i & 255;
        int k = i >> 16 & 255;
        k += (int)(f1 * 15.0F * 16.0F);

        if (k > 240)
        {
            k = 240;
        }

        return j | k << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_){
        float f1 = super.getBrightness(p_70013_1_);
        float f2 = (float)this.particleAge / (float)this.particleMaxAge;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate(){
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        float f1 = f;
        f = -f + f * f * 2.0F;
        f = 1.0F - f;
        this.posX = this.portalPosX + this.motionX * (double)f;
        this.posY = this.portalPosY + this.motionY * (double)f + (double)(1.0F - f1);
        this.posZ = this.portalPosZ + this.motionZ * (double)f;

        if (this.particleAge++ >= this.particleMaxAge){
            this.setDead();
        }
    }
}