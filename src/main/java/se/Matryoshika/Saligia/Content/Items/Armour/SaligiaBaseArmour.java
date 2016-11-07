/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items.Armour;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import se.Matryoshika.Saligia.API.Utils;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;

/**
 * This class was created by Matryoshika Nov 2, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class SaligiaBaseArmour extends ItemArmor implements IAnimun{
	
	public static int TIER = 0;

	public SaligiaBaseArmour(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void input() {}

	@Override
	public void output() {}

	@Override
	public void transfer(IAnimun start, IAnimun finish) {}

	@Override
	public void set(int amount) {}

	@Override
	public float maxAmount() {
		return 0;
	}

	@Override
	public int getCurrentAmount() {
		return 0;
	}

	@Override
	public int outputPacket() {
		return 0;
	}

	@Override
	public int inputPacket() {
		return 0;
	}

	@Override
	public float getFillAmountPercentage() {
		return 0;
	}

	@Override
	public float getTier() {
		return 0;
	}

	@Override
	public boolean isHandHeld() {
		return true;
	}

	@Override
	public boolean repairable() {
		return true;
	}

	@Override
	public void tryRepair(EntityPlayer owner, ItemStack stack, int animunRequirement) {
		if(owner.worldObj.isRemote)
			return;
		
		
		if(stack.getItemDamage() <= 0)
			return;
		
		//You are NOT a real boy, Pinocchio!
		if(owner instanceof FakePlayer)
			stack.damageItem(stack.getMaxDamage(), owner);
		
		IAnimun animunHolder = (IAnimun) Utils.searchPlayerInventoryForAnimun(owner).getItem();
		if(animunHolder == null)
			return;
		

		
		if(animunHolder.getCurrentAmount() >= animunRequirement){
			stack.setItemDamage(stack.getItemDamage()-1);
			animunHolder.set(animunHolder.getCurrentAmount() - animunRequirement);
		}
	}

}
