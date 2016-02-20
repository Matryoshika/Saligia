package Matryoshika.mods.saligia.RuneSocketing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import Matryoshika.mods.saligia.entities.misc.customLightningBolt;
import Matryoshika.mods.saligia.entities.misc.saligiaAvaritiaParticle;
import Matryoshika.mods.saligia.items.runes.ItemRune;
import Matryoshika.mods.saligia.utils.math;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;

public class RuneEffectEventHandler {

	public boolean acediaKnockback = false;
	public int regentimer = 0;
	public boolean isCountingRegen = false;
	double acediaPlayerX;
	double acediaPlayerY;
	double acediaPlayerZ;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//Acedia Effects
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** Freezes all mobs within range, when foe has been hit*/
	@SubscribeEvent
	public void acediaFreeze (AttackEntityEvent attacker){
		EntityPlayer player = attacker.entityPlayer;
		Entity target = attacker.target;
		if(player.inventory.getCurrentItem() != null){
			ItemStack stack = player.inventory.getCurrentItem();
			if((checkTool(stack) == true) && stack.hasTagCompound()){
		
				if(stack.getTagCompound().getString("saligiaElement").equals("Acedia")){
					int tier = stack.getTagCompound().getInteger("saligiaTier");
					int range = (int) Math.pow(4, tier);
					int duration = tier*20;
					
					AxisAlignedBB box = player.boundingBox.expand(range, range, range);
					Class<Entity> entities = Entity.class;
					
					List<Entity> inbox = player.worldObj.getEntitiesWithinAABB(entities, box);
					
					for (Entity entity : inbox) {
						if(entity instanceof EntityLiving && !(entity instanceof IBossDisplayData || entity == player)){
							((EntityLiving)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration, 20));
						}
					}
				}
				else{
					return;
				}	
				
			}
			else{
				return;
			}
		}
		else{
			return;
		}
	}
	
	/** Grants a Knockback % resistance.*/
	@SubscribeEvent
	public void  acediaKnockback (LivingHurtEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(player.inventory.getCurrentItem() != null){
				ItemStack stack = player.inventory.getCurrentItem();
				if(checkArmour(stack) == true){
					if(stack.getTagCompound().getString("saligiaElement").equals("Acedia")){
						int tier = stack.getTagCompound().getInteger("saligiaTier");
						double base = player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue();
						player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue((base+(((base+1)*0.33)*tier)/2));
						acediaKnockback = true;
						
					}
				}
			}
		}
	}
	
	
	/** Grants a low regen if holding item for 48-(Tier^3) seconds */
	@SubscribeEvent
	public void acediaRegen(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(player.inventory.getCurrentItem() != null){
				ItemStack stack = player.inventory.getCurrentItem();
				if(checkItem(stack) == true){
					if(stack.getTagCompound().getString("saligiaElement").equals("Acedia") && player.isPotionActive(Potion.regeneration.id) == false){
						int tier = stack.getTagCompound().getInteger("saligiaTier");
						String uuiid = player.getUniqueID().toString();
						regentimer++;
						System.out.println(regentimer);
						if(regentimer >= (48-(Math.pow(tier, 3)))*20){
							player.addPotionEffect(new PotionEffect(Potion.regeneration.id, (int) Math.pow(tier+1, 3)*20, 0));
							regentimer = 0;
						}
					}
				}
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//Avaritia Effects
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** Highlights ores within range of the player*/
	@SubscribeEvent
	public void avaritiaOreSight(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
			if(player.inventory.armorItemInSlot(3) != null){
				ItemStack stack = player.inventory.armorItemInSlot(3);
				if(checkArmour(stack) == true){
					if(stack.getTagCompound().getString("saligiaElement").equals("Avaritia")){
						int tier = stack.getTagCompound().getInteger("saligiaTier");
						int range = ((8*tier)/2);
						World world = player.worldObj;
						for(int x = (int) (player.posX-range); x < player.posX+range; x++){
							for(int y = (int) (player.posY-range); y < player.posY+range; y++){
								for(int z = (int) (player.posZ-range); z < player.posZ+range; z++){
									if(world.getBlock(x, y, z)!= Blocks.air && world.getBlock(x, y, z) instanceof BlockOre){
										if(world.isRemote){
											Random rand = new Random();
											double motionX = rand.nextGaussian() * 0.02D;
											double motionY = rand.nextGaussian() * 0.02D;
											double motionZ = rand.nextGaussian() * 0.02D;
											EntityFX avaritiaOreFinder = new saligiaAvaritiaParticle(world, (x+0.5), (y+0.5)+((player.posY-y)*0.5), (z+0.5), motionX*20, motionY*0.8, motionZ*20 );
											Minecraft.getMinecraft().effectRenderer.addEffect(avaritiaOreFinder);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/** Draws items to the player, at (3*Tier) blocks range*/
	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void avaritiaMagnet(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
			if(player.inventory.getCurrentItem() != null && !(player.inventory.getCurrentItem().getItem() instanceof ItemRune)){
				ItemStack stack = player.inventory.getCurrentItem();
				if(checkItem(stack) == true){
					if(stack.getTagCompound().getString("saligiaElement").equals("Avaritia")){
						int tier = stack.getTagCompound().getInteger("saligiaTier");
						int range = tier * 3;
						World world = player.worldObj;
						
						AxisAlignedBB box = player.boundingBox.expand(range, range, range);
						Class<EntityItem> items = EntityItem.class;
						List<EntityItem> inbox = world.getEntitiesWithinAABB(items, box);
						for (EntityItem entityItem : inbox) {
							
							double dx = (player.posX- entityItem.posX);
							double dy = (player.posY - entityItem.posY);
							double dz = (player.posZ - entityItem.posZ);
							double ddt = math.py3d(dx, dy, dz);
							entityItem.motionX += (dx/ddt/ddt)*0.25;
							entityItem.motionY += (dy/ddt/ddt)*0.15;
							entityItem.motionZ += (dz/ddt/ddt)*0.25;
							
							if(!world.isRemote){
								if (entityItem.posY +1 > player.posY+4){
									entityItem.motionY = 0;
								}
								if (entityItem.posY -1 < player.posY){
									entityItem.motionY = 0.2;
								}
							}
						}
					}
				}
			}
		}
	}
	
	/** (tier*25)% chance to gain (tier*2)x loot/resources*/
	//Ore-gain
	public void avaritiaOreGain(BlockEvent.HarvestDropsEvent event){
		if(event.harvester instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.harvester;
			if(player.inventory.getCurrentItem() != null){
				ItemStack stack = player.inventory.getCurrentItem();
				if(checkTool(stack) == true){
					if(stack.getTagCompound().getString("saligiaElement").equals("Avaritia")){
						int tier = stack.getTagCompound().getInteger("saligiaTier");
						World world = player.worldObj;
						Block block = world.getBlock(event.x, event.y, event.z);
						
						Random rnd = new Random();
						
						double chance = Math.random()*100;
						if(chance < tier*25){
							//ArrayList drops = block.getDrops(world, event.x, event.y, event.z, 0, 0);
							block.quantityDropped(block.getDamageValue(world, event.x, event.y, event.z), 10, rnd);
						}
					}
				}
			}
		}
	}
	
	
	
	//Gula Effects
	
	//Invidia Effects
	
	//Ira Effects
	
	//Luxuria Effects
	
	//Superbia Effects

	
	
	
	public boolean checkItem(ItemStack stack){
		if(((stack.getItem() instanceof Item && !(stack.getItem() instanceof ItemArmor || (stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemFishingRod || stack.getItem() instanceof ItemShears || stack.getItem() instanceof ItemBow))) ) && stack.hasTagCompound()){
			if(stack.getTagCompound().hasKey("saligiaTier") && stack.getTagCompound().hasKey("saligiaElement")){
				return true;
			}
			else{
				return false;
			}
		}
		
		return false;
	}
	
	public boolean checkArmour(ItemStack stack){
		if(stack.getItem() instanceof ItemArmor || stack.getItem() instanceof ItemRune){
			if(stack.getTagCompound().hasKey("saligiaTier") && stack.getTagCompound().hasKey("saligiaElement")){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	
	public boolean checkTool(ItemStack stack){
		if(stack.getItem() instanceof ItemTool || stack.getItem() instanceof ItemFishingRod || stack.getItem() instanceof ItemShears || stack.getItem() instanceof ItemBow || stack.getItem() instanceof ItemRune){
			if(stack.getTagCompound().hasKey("saligiaTier") && stack.getTagCompound().hasKey("saligiaElement")){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
}
