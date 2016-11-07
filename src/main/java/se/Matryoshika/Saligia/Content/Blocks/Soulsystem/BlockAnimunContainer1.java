package se.Matryoshika.Saligia.Content.Blocks.Soulsystem;

import org.apache.commons.codec.binary.Base64;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;
import se.Matryoshika.Saligia.Utils.Constants;

/**
 * This class was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockAnimunContainer1 extends AnimunContainer{

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
		return false;
	}

	@Override
	public boolean repairable() {
		return false;
	}

	@Override
	public void tryRepair(EntityPlayer owner, ItemStack stack, int animunRequirement) {}


}
