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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;


public class EntityAvaritia extends EntityBoss{

	private static final double RANGE = 5F;
	private int counter;
	
	public EntityAvaritia(World world){
        super(world);
        
    }

	@Override
	public void onEntityUpdate(){
		stealItems(this);
		super.onEntityUpdate();
	}
	
	private void stealItems(Entity EntityAvaritia){
		AxisAlignedBB box = EntityAvaritia.boundingBox.expand(RANGE, RANGE, RANGE);
		Class<EntityPlayer> players = EntityPlayer.class;
		
		List<EntityPlayer> inbox = EntityAvaritia.worldObj.getEntitiesWithinAABB(players, box);
		
			for (EntityPlayer entityPlayer : inbox) {
				Random rand1 = new Random();
				int random1 = rand1.nextInt(32) + 1;
				Random rand2 = new Random();
				int random2 = rand2.nextInt(5) + 1;
				
				if(random1 == 1 && counter >= 20 && !worldObj.isRemote){
					if(random2 == 1 && entityPlayer.getCurrentArmor(0) != null){
						ItemStack helm = entityPlayer.getCurrentArmor(0).copy();
						entityPlayer.setCurrentItemOrArmor(1, null);
						entityPlayer.entityDropItem(helm, 1);
					}
					if(random2 == 2 && entityPlayer.getCurrentArmor(1) != null){
						ItemStack torso = entityPlayer.getCurrentArmor(1).copy();
						entityPlayer.setCurrentItemOrArmor(2, null);
						entityPlayer.entityDropItem(torso, 1);
					}
					if(random2 == 3 && entityPlayer.getCurrentArmor(2) != null){
						ItemStack leggings = entityPlayer.getCurrentArmor(2).copy();
						entityPlayer.setCurrentItemOrArmor(3, null);
						entityPlayer.entityDropItem(leggings, 1);
					}
					if(random2 == 4 && entityPlayer.getCurrentArmor(3) != null){
						ItemStack boots = entityPlayer.getCurrentArmor(3).copy();
						entityPlayer.setCurrentItemOrArmor(4, null);
						entityPlayer.entityDropItem(boots, 1);
					}
					if(random2 == 5 && entityPlayer.getHeldItem() != null){
						ItemStack hand = entityPlayer.getHeldItem().copy();
						entityPlayer.setCurrentItemOrArmor(0, null);
						entityPlayer.entityDropItem(hand, 1);
					}
					counter = 0;
				}
			}
	}
}
