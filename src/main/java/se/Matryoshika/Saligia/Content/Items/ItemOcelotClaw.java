/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeJungle;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Utils.ConfigHandler;

/**
 * This class was created by Matryoshika Oct 28, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemOcelotClaw extends Item{
	
	
	
	public ItemOcelotClaw(){
		this.setRegistryName(Saligia.MODID, "ocelotclaw");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	@SubscribeEvent
	public void dropClaws(LivingUpdateEvent event){
		if(event.getEntityLiving() instanceof EntityOcelot){
			if(event.getEntityLiving().worldObj.isRemote)
				return;
			if(event.getEntityLiving().worldObj.rand.nextInt(4096) == 1){
				EntityItem drop = new EntityItem(event.getEntityLiving().worldObj, 
						event.getEntityLiving().posX, 
						event.getEntityLiving().posY, 
						event.getEntityLiving().posZ, 
						new ItemStack(ContentRegistry.OCELOTCLAW));
				event.getEntityLiving().worldObj.spawnEntityInWorld(drop);
			}
		}
	}
	
	//To make it easier to get your hands on an Ocelot. I know the pains of getting one on servers ;_;
	@SubscribeEvent
	public void spawnOcelot(PlayerInteractEvent event){
		if(event.getEntityPlayer().worldObj.isRemote)
			return;
		
		if(event.getItemStack() != null && event.getItemStack().getItem() == Items.RECORD_CAT){
			
			if(ConfigHandler.requireJungleForOcelotDiscSpawn && !(event.getEntityPlayer().worldObj.getBiomeForCoordsBody(event.getPos()) instanceof BiomeJungle))
				return;
			
			if(event.getEntityPlayer().worldObj.getBlockState(event.getPos()) == Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.TYPE, EnumType.JUNGLE)){
				event.getItemStack().stackSize--;
				event.getEntityPlayer().worldObj.setBlockToAir(event.getPos());
				EntityOcelot ocelot = new EntityOcelot(event.getEntityPlayer().worldObj);
				ocelot.setLocationAndAngles(event.getPos().getX() + 0.5, event.getPos().getY() + 1, event.getPos().getZ() + 0.5, 0, 0);
				event.getEntityPlayer().worldObj.spawnEntityInWorld(ocelot);
			}
		}
	}

}
