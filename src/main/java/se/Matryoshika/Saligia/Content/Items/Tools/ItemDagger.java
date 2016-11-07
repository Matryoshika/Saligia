/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items.Tools;

import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.Materials;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;
import se.Matryoshika.Saligia.API.Tools.ISaligiaTool;
import se.Matryoshika.Saligia.API.Tools.SaligiaBaseSword;

/**
 * This class was created by Matryoshika Oct 29, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ItemDagger extends SaligiaBaseSword implements IAnimun{

    private final ToolMaterial material = Materials.VILE;

	public ItemDagger() {
		super(Materials.VILE);
		this.setRegistryName(Saligia.MODID, "daggervile");
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
	}
	
	@Override
    public float getDamageVsEntity(){
        return this.material.getDamageVsEntity();
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
	public int getToolTier(){
		return 1;
	}

}
