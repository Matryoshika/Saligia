package Matryoshika.mods.saligia.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.*;

public class damageSourceSin extends DamageSource{
	public static DamageSource SinDamage = (new damageSourceSin("SinnersPain")).setDamageBypassesArmor();
	public damageSourceSin(String label) {
	super(label);
	}
	
	@Override
    public IChatComponent func_151519_b (EntityLivingBase par1EntityLivingBase)
    {
        return new ChatComponentTranslation("death.attack.SinDagger", par1EntityLivingBase.func_145748_c_());
    }

}
