/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Oct 27, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemZombieTooth extends Item{
	
	public ItemZombieTooth(){
		this.setRegistryName(Saligia.MODID, "zombietooth");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	@SubscribeEvent
	public void zombieTeethDrops(LivingDropsEvent event){
		if(event.getEntityLiving() instanceof EntityZombie && event.isRecentlyHit()){
			
			EntityPlayer player = event.getEntityLiving().worldObj.getClosestPlayer(event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, 10, false);
			if(player != null && !(player instanceof FakePlayer)){
				
				Random rand = new Random();
				if(rand.nextInt(8) != 1)
					return;
				
				if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() == Items.DIAMOND_SWORD){
					
					List<EntityItem> drops = event.getDrops();
					event.getDrops().add(new EntityItem(event.getEntityLiving().worldObj,
							event.getEntityLiving().posX, 
							event.getEntityLiving().posY, 
							event.getEntityLiving().posZ, 
							new ItemStack(ContentRegistry.ZOMBIETOOTH)));
				}
			}
		}
	}

}
