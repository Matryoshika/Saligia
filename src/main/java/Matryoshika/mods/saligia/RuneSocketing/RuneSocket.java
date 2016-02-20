package Matryoshika.mods.saligia.RuneSocketing;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public abstract class RuneSocket{
	/** The maximum amount of allowed Rune-effects. Each Rune carries 3 (Maximum of 85 Runes possible)*/
    public static final RuneSocket[] RuneSocketList = new RuneSocket[256];
    
   
    public final int effectId;
    private final int weight;
    /** The EnumRuneSocketType given to this Rune-Effect. */
    public EnumRuneSocketType type;
    /** Used in localisation and stats. */
    protected String name;
    //private static final String __OBFID = "CL_00000105";

    protected RuneSocket(int effectId, int weight, EnumRuneSocketType enumType)
    {
        this.effectId = effectId;
        this.weight = weight;
        this.type = enumType;

        if (RuneSocketList[effectId] != null)
        {
            throw new IllegalArgumentException("Duplicate Rune-effect id! " + this.getClass() + " and " + RuneSocketList[effectId].getClass() + " Rune-Effect ID:" + effectId);
        }
        else
        {
            RuneSocketList[effectId] = this;
        }
    }

    public int getWeight()
    {
        return this.weight;
    }

    /**
     * Returns the minimum Tier that the Rune-Effect can have.
     */
    public int getMinLevel()
    {
        return 1;
    }

    /**
     * Returns the maximum Tier that the Rune-Effect can have.
     */
    public int getMaxLevel()
    {
        return 1;
    }

    /**
     * Returns the minimal Item-Level needed for the Rune to be applicable.
     */
    public int getMinItemLevel(int minLevel)
    {
        return minLevel;
    }

    /**
     * Returns the maximal Item-Level needed for the Rune to be applicable.
     */
    public int getMaxItemlevel(int maxLevel)
    {
        return this.getMinItemLevel(maxLevel) + 33;
    }
    /**
     * Returns the minimum Rune-Sockets any Item can have
     */
    public int getMinRuneSockets(int amount){
    	return 0;
    }
    /**
     * Returns the maximum Rune-Sockets any Item can have
     */
    public int getMaxRuneSockets(int amount){
    	return 3;
    }

    /**
     * Calculates de damage protection of the enchantment based on level and damage source passed.
     */
    public int calcModifierDamage(int amount, DamageSource damage)
    {
        return 0;
    }

    public float func_152376_a(int p_152376_1_, EnumCreatureAttribute p_152376_2_)
    {
        return 0.0F;
    }

    /**
     * Determines if the Rune-Effect passed can be applied together with this Rune-Effect.
     */
    public boolean canApplyTogether(RuneSocket runeSocket)
    {
        return this != runeSocket;
    }

    /**
     * Sets the Rune-Effect's name
     */
    public RuneSocket setName(String name)
    {
        this.name = name;
        return this;
    }

    /**
     * Return the name of key in translation table of this enchantment.
     */
    public String getName()
    {
        return "saligia.runeeffect." + this.name;
    }

    /**
     * Returns the correct translated name of the Rune-Effect and the Tier in roman numerals.
     */
    public String getTranslatedName(int tier)
    {
        String s = StatCollector.translateToLocal(this.getName());
        return s + " " + StatCollector.translateToLocal("saligia.rune.tier" + tier);
    }

    public boolean canApply(ItemStack stack)
    {
        return this.type.canSocketItem(stack.getItem());
    }

    public void func_151368_a(EntityLivingBase p_151368_1_, Entity p_151368_2_, int p_151368_3_) {}

    public void func_151367_b(EntityLivingBase p_151367_1_, Entity p_151367_2_, int p_151367_3_) {}

 
}
