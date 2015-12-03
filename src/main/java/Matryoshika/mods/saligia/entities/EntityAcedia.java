package Matryoshika.mods.saligia.entities;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;


public class EntityAcedia extends EntityBoss{

	private static final double RANGE = 20F;
	private int timer;
	
	public EntityAcedia(World world){
        super(world);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(saligia.bossSpeed/30);
    }
	
	@Override
	public void onEntityUpdate(){
		spawnMinions(this);
		timer++;
		super.onEntityUpdate();
	}
	
	public void spawnMinions(Entity EntityAcedia){
		Random rand1 = new Random();
		int random1 = rand1.nextInt(5) + 1;
		
		Random randx = new Random();
		int randomx = randx.nextInt(11) + 1;
		Random randz = new Random();
		int randomz = randz.nextInt(11) + 1;
		
		Random rand3 = new Random();
		int random3 = rand3.nextInt(2) + 1;
		
		int invert;
		
		if(random3 == 1){
			invert = 1;
		}
		else{
			invert = -1;
		}
		
		AxisAlignedBB box = EntityAcedia.boundingBox.expand(RANGE, RANGE, RANGE);
		Class<EntityMob> mobs = EntityMob.class;
		
		boolean canSpawn;
		
		List<EntityMob> inbox = EntityAcedia.worldObj.getEntitiesWithinAABB(mobs, box);
		if(inbox.size() >= saligia.acediaMinionMax){
			canSpawn = false;
		}
		else{
			canSpawn = true;
		}
		if(canSpawn == false && inbox.size() < saligia.acediaMinionMax){
			if(timer >= 600){
				canSpawn = true;
				timer = 0;
			}
		}
		if(random1 == 1 && canSpawn == true && !worldObj.isRemote){
			EntityZombie ez = new EntityZombie(worldObj);
			ez.setLocationAndAngles(EntityAcedia.posX +(randomx * invert), EntityAcedia.posY+2, EntityAcedia.posZ +(randomz *invert), 0.0F, 0.0F);
			EntityAcedia.worldObj.spawnEntityInWorld(ez);
		}
		if(random1 == 2 && canSpawn == true && !worldObj.isRemote){
			EntityCreeper ec = new EntityCreeper(worldObj);
			ec.setLocationAndAngles(EntityAcedia.posX +(randomx * invert), EntityAcedia.posY+2, EntityAcedia.posZ +(randomz *invert), 0.0F, 0.0F);
			EntityAcedia.worldObj.spawnEntityInWorld(ec);
		}
		if(random1 == 3 && canSpawn == true && !worldObj.isRemote){
			EntitySkeleton es = new EntitySkeleton(worldObj);
			es.setLocationAndAngles(EntityAcedia.posX +(randomx * invert),EntityAcedia.posY+2, EntityAcedia.posZ +(randomz *invert), 0.0F, 0.0F);
			EntityAcedia.worldObj.spawnEntityInWorld(es);
		}
		if(random1 == 4 && canSpawn == true && !worldObj.isRemote){
			EntitySpider esp = new EntitySpider(worldObj);
			esp.setLocationAndAngles(EntityAcedia.posX +(randomx * invert), EntityAcedia.posY+2, EntityAcedia.posZ +(randomz *invert), 0.0F, 0.0F);
			EntityAcedia.worldObj.spawnEntityInWorld(esp);
		}
		if(random1 == 5 && canSpawn == true && !worldObj.isRemote){
			EntityBlaze eb = new EntityBlaze(worldObj);
			eb.setLocationAndAngles(EntityAcedia.posX +(randomx * invert), EntityAcedia.posY+2, EntityAcedia.posZ +(randomz *invert), 0.0F, 0.0F);
			EntityAcedia.worldObj.spawnEntityInWorld(eb);
		}
	}
}
