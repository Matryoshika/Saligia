/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Oct 27, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemCreeperAsh extends Item{
	
	public ItemCreeperAsh(){
		this.setRegistryName(Saligia.MODID, "creeperash");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void dropAsh(ExplosionEvent event){
		if(event.getExplosion().getExplosivePlacedBy() != null && event.getExplosion().getExplosivePlacedBy() instanceof EntityCreeper){
			if(event.getExplosion().getExplosivePlacedBy().worldObj.isRemote)
				return;
			
			Random rand = new Random();
			if(rand.nextInt(6) != 1)
				return;
			
			EntityCreeper creeper = (EntityCreeper) event.getExplosion().getExplosivePlacedBy();
			EntityPlayer player = creeper.worldObj.getClosestPlayer(creeper.posX, creeper.posY, creeper.posZ, 10, false);
			if(player == null || player instanceof FakePlayer)
				return;
			
			EntityItem item = new EntityItem(creeper.worldObj, creeper.posX, creeper.posY, creeper.posZ, new ItemStack(ContentRegistry.CREEPERASH));
			item.motionX = item.motionZ = 0;
			creeper.worldObj.spawnEntityInWorld(item);
		}
	}

}
