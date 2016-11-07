/**
 * 
 */
package se.Matryoshika.Saligia.API.Tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.FakePlayer;
import se.Matryoshika.Saligia.API.Utils;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;

/**
 * This class was created by Matryoshika Oct 30, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class SaligiaBaseSword extends ItemSword implements ISaligiaTool, IAnimun{

	public SaligiaBaseSword(ToolMaterial material) {
		super(material);
	}

	@Override
	public int getToolTier() {
		return 0;
	}

	@Override
	public int durabilityModifier() {
		return 1;
	}

	@Override
	public boolean infiniteDurability() {
		return false;
	}

	@Override
	public int speedModifier() {
		return 1;
	}

	@Override
	public int damageModifier() {
		return 1;
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

}
