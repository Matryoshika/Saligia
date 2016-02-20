package Matryoshika.mods.saligia.items;

import java.util.List;
import java.util.Random;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.utils.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemMobHarness extends Item{
	
	public Entity entity;

	public ItemMobHarness(ToolMaterial sin) {
		super();
		this.maxStackSize = 1;
		this.setUnlocalizedName("ItemMobHarness");
		this.setTextureName(saligia.MODID+":mobHarness");
	}
	
	public String getItemStackDisplayName(ItemStack stack) {
		return StatCollector.translateToLocal("item.ItemMobHarness.name");
	}
	

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof IBossDisplayData || entity instanceof EntityPlayer){
			return false;
		}
		ItemStack stack1 = player.getHeldItem();
		if (stack1 == null) return true;
		if (NBTHelper.getBoolean(stack1, "IsSet", false)) return true;
		NBTTagCompound compound = NBTHelper.getCompound(stack1);
		entity.writeToNBT(compound);
		if (EntityList.getEntityString(entity) != null)compound.setString("EntityName", EntityList.getEntityString(entity));
		stack1.setTagCompound(compound);
		NBTHelper.setBoolean(stack1, "IsSet", true);
		entity.setDead();
		return true;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		NBTTagCompound compound = (NBTTagCompound)NBTHelper.getCompound(stack).copy();

		if (compound == null) return false;

		String name = compound.getString("EntityName");

		Entity entity = EntityList.createEntityByName(name, world);
		compound.removeTag("EntityName");
		compound.removeTag("IsSet");
		if (entity instanceof EntityLivingBase) ((EntityLivingBase)entity).readFromNBT(compound);
		double sX = x + ForgeDirection.getOrientation(side).offsetX + 0.5;
		double sY = y + ForgeDirection.getOrientation(side).offsetY + 0.5;
		double sZ = z + ForgeDirection.getOrientation(side).offsetZ + 0.5;
		if (entity == null) {
			return false;
		}
		entity.setLocationAndAngles(sX, sY, sZ, player.rotationYaw, 0F);

		if (entity instanceof EntityLivingBase && !world.isRemote) {
			world.spawnEntityInWorld(entity);
			if (!player.capabilities.isCreativeMode){
				stack.stackSize--;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		String name = NBTHelper.getString(stack, "EntityName", "null");
		if (NBTHelper.getBoolean(stack, "IsSet", false)) list.add("Contains: "+ name);
		else list.add("Room for one...");
	}
}
