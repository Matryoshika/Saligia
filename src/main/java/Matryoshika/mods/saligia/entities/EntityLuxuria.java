package Matryoshika.mods.saligia.entities;

import java.util.List;
import java.util.Random;

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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;


public class EntityLuxuria extends EntityBoss{

	private static final double RANGE = 5F;
	private int timer;
	private int regen;
	private int teleport;
	public boolean meeting;

	public EntityLuxuria(World world) {
        super(world);
    }
	
	@Override
	public void onEntityUpdate(){
		timer++;
		regen++;
		teleport++;
		Random randx = new Random();
		int randomx = randx.nextInt(8) + 1;
		Random randz = new Random();
		int randomz = randx.nextInt(8) + 1;
		
		Random rand3 = new Random();
		int random3 = rand3.nextInt(2) + 1;
		int invert;
		if(random3 == 1){
			invert = 1;
		}
		else{
			invert = -1;
		}
		
		drugPlayers(this);
		super.onEntityUpdate();
		if(meeting == false){
			this.hurtResistantTime = 30;
		}
		if(this.getHealth() < this.getMaxHealth()){
			if(regen >=10 && this.getHealth() > 1){
				float currentHealth = this.getHealth();
				this.setHealth(currentHealth+15);
				regen = 0;
			}
		}
		if(teleport >= 600){
			this.newPosX= (this.posX + (randomx * invert));
			this.newPosZ= (this.posZ + (randomz * invert));
		}
	}

	private void drugPlayers(Entity EntityLuxuria){
		AxisAlignedBB box = EntityLuxuria.boundingBox.expand(RANGE, RANGE, RANGE);
		Class<EntityPlayer> players = EntityPlayer.class;
		
		List<EntityPlayer> inbox = EntityLuxuria.worldObj.getEntitiesWithinAABB(players, box);
		for (EntityPlayer entityPlayer : inbox) {
			if(timer >= 400){
				entityPlayer.addPotionEffect(new PotionEffect(Potion.weakness.id, 200,1));
				entityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 250,3));
				entityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 200,1));
				entityPlayer.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 250,1));
				entityPlayer.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200,1));
				timer = 0;
				meeting = true;
			}
		}
	}
}
