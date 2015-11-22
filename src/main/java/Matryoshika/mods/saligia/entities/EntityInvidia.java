package Matryoshika.mods.saligia.entities;

import java.util.List;

import Matryoshika.mods.saligia.items.saligia_Items;
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;


public class EntityInvidia extends EntityBoss{

	private static final double RANGE = 5F;
	
	public EntityInvidia(World world){
        super(world);
    }

	@Override
	public void onEntityUpdate(){
		copyPlayer(this);
		super.onEntityUpdate();
	}
	@Override
	public void onDeath(DamageSource source){
		for(int i = 0; i<5; i++){
		this.equipmentDropChances[i] = -1;
		}
		super.onDeath(source);
	}
		
	private void copyPlayer(Entity EntityInvidia){
		AxisAlignedBB box = EntityInvidia.boundingBox.expand(RANGE, RANGE, RANGE);
		Class<EntityPlayer> player = EntityPlayer.class;
		List<EntityPlayer> inbox = EntityInvidia.worldObj.getEntitiesWithinAABB(player, box);
		
		for (EntityPlayer entityplayer : inbox) {
			
			if(entityplayer.getCurrentArmor(3) != null){
				ItemStack helm = entityplayer.getCurrentArmor(3).copy();
				EntityInvidia.setCurrentItemOrArmor(4, helm);
			}
			if(entityplayer.getCurrentArmor(2) != null){
				ItemStack torso = entityplayer.getCurrentArmor(2).copy();
				EntityInvidia.setCurrentItemOrArmor(3, torso);
			}
			if(entityplayer.getCurrentArmor(1) != null){
				ItemStack leggings = entityplayer.getCurrentArmor(1).copy();
				EntityInvidia.setCurrentItemOrArmor(2, leggings);
			}
			if(entityplayer.getCurrentArmor(0) != null){
				ItemStack boots = entityplayer.getCurrentArmor(0).copy();
				EntityInvidia.setCurrentItemOrArmor(1, boots);
			}
			ItemStack weapon = new ItemStack(saligia_Items.SinDagger);
			EntityInvidia.setCurrentItemOrArmor(0, weapon);
		}
		
	}
}