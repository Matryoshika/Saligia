/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items.Animun;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Soulsystem.CreativeAnimunBase;

/**
 * This class was created by Matryoshika Oct 31, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemCreativeSoulCrucible extends CreativeAnimunBase{
	
	public ItemCreativeSoulCrucible(){
		this.setRegistryName(Saligia.MODID, "creativesoulcrucible");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			if(!player.isCreative())
				boom(player);
		}
    }
	
	public void boom(EntityPlayer cheater){
		if(cheater.worldObj.isRemote)
			return;
		
		cheater.inventory.dropAllItems();
		cheater.experience = 0;
		cheater.experienceLevel = 0;
		cheater.worldObj.createExplosion(cheater, cheater.posX, cheater.posY, cheater.posZ, 16, true);
		cheater.onKillCommand();
		if(!cheater.isDead)
			cheater.setDead();
		
		List<EntityItem> inbox = cheater.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(
				new BlockPos(cheater.posX, cheater.posY, cheater.posZ).add(-8, -8, -8), 
				new BlockPos(cheater.posX, cheater.posY, cheater.posZ).add(8+1, 8+1, 8+1)));
		
		for(EntityItem item : inbox){
			item.setDead();
		}
			
		ITextComponent component = new TextComponentString(cheater.getName()+" "+I18n.format("death."+Saligia.MODID+".creative"));
		cheater.addChatComponentMessage(component.setStyle(new Style().setColor(TextFormatting.GRAY)));
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		String name = getRegistryName().getResourcePath();
		if(!player.isCreative())
			tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.format(Saligia.MODID+".lore.advanced."+name));
	}

}
