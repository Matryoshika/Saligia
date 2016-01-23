package Matryoshika.mods.saligia.RuneSocketing;

import java.util.ArrayList;

import Matryoshika.mods.saligia.RuneSocketing.AcediaRunes.RuneSocketAcediaFreeze;
import Matryoshika.mods.saligia.RuneSocketing.AcediaRunes.RuneSocketAcediaKnockback;
import Matryoshika.mods.saligia.RuneSocketing.AcediaRunes.RuneSocketAcediaRegen;
import Matryoshika.mods.saligia.RuneSocketing.AvaritiaRunes.RuneSocketAvaritiaGain;
import Matryoshika.mods.saligia.RuneSocketing.AvaritiaRunes.RuneSocketAvaritiaMagnet;
import Matryoshika.mods.saligia.RuneSocketing.AvaritiaRunes.RuneSocketAvaritiaOreSight;
import Matryoshika.mods.saligia.RuneSocketing.GulaRunes.RuneSocketGulaRegen;
import Matryoshika.mods.saligia.RuneSocketing.GulaRunes.RuneSocketGulaStore;
import Matryoshika.mods.saligia.RuneSocketing.GulaRunes.RuneSocketGulaTheft;
import Matryoshika.mods.saligia.RuneSocketing.InvidiaRunes.RuneSocketInvidiaArmourBypass;
import Matryoshika.mods.saligia.RuneSocketing.InvidiaRunes.RuneSocketInvidiaSight;
import Matryoshika.mods.saligia.RuneSocketing.InvidiaRunes.RuneSocketInvidiaStatistics;
import Matryoshika.mods.saligia.RuneSocketing.IraRunes.RuneSocketIraFlatDamage;
import Matryoshika.mods.saligia.RuneSocketing.IraRunes.RuneSocketIraPercentageDamage;
import Matryoshika.mods.saligia.RuneSocketing.IraRunes.RuneSocketIraThorns;
import Matryoshika.mods.saligia.RuneSocketing.LuxuriaRunes.RuneSocketLuxuriaGrowth;
import Matryoshika.mods.saligia.RuneSocketing.LuxuriaRunes.RuneSocketLuxuriaHeal;
import Matryoshika.mods.saligia.RuneSocketing.LuxuriaRunes.RuneSocketLuxuriaSpeed;
import Matryoshika.mods.saligia.RuneSocketing.SuperbiaRunes.RuneSocketSuperbiaBlock;
import Matryoshika.mods.saligia.RuneSocketing.SuperbiaRunes.RuneSocketSuperbiaFlatAbsorb;
import Matryoshika.mods.saligia.RuneSocketing.SuperbiaRunes.RuneSocketSuperbiaPercentageAbsorb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public abstract class RuneSocket{
	/** The maximum amount of allowed Rune-effects. Each Rune carries 3 (Maximum of 85 Runes possible)*/
    public static final RuneSocket[] RuneSocketList = new RuneSocket[256];
    
    /** The list of enchantments applicable by the anvil from a book */
    //public static final RuneSocket[] enchantmentsBookList;
    
    //All Runes increase in range/percentage/effect with higher tiers
    /** Regenerates health if standing still */
    public static final RuneSocket acediaRegen = new RuneSocketAcediaRegen(0, 1, EnumRuneSocketType.itemInst);
    /** Freezes all entities within range*/
    public static final RuneSocket acediaFreeze = new RuneSocketAcediaFreeze(1, 1, EnumRuneSocketType.tool);
    /** Gain Knockback resistance*/
    public static final RuneSocket acediaKnockback = new RuneSocketAcediaKnockback(2, 1, EnumRuneSocketType.armor);
    
    /** Draws items towards the player*/
    public static final RuneSocket avaritiaMagnet = new RuneSocketAvaritiaMagnet(3, 1, EnumRuneSocketType.itemInst);
    /** Chance to gain more loot/resources. Does not work on self-dropping ores (like Iron, gold) without auto-smelting*/
    public static final RuneSocket avaritiaGain = new RuneSocketAvaritiaGain(4, 1, EnumRuneSocketType.tool);
    /** Highlights ores within range to the player, even if blocks exist between*/
    public static final RuneSocket avaritiaOreSight = new RuneSocketAvaritiaOreSight(5, 1, EnumRuneSocketType.armor);
    
    /** Regenerates shanks passively*/
    public static final RuneSocket gulaRegen = new RuneSocketGulaRegen(6, 1, EnumRuneSocketType.itemInst);
    /** Steals hunger from foe*/
    public static final RuneSocket gulaTheft = new RuneSocketGulaTheft(7, 1, EnumRuneSocketType.tool);
    /** Stores food, automatically distributes it as needed*/
    public static final RuneSocket gulaStore = new RuneSocketGulaStore(8, 1, EnumRuneSocketType.armor);
    
    /** Highlight all entities within range, even invisible ones*/
    public static final RuneSocket invidiaEntitySight = new RuneSocketInvidiaSight(9, 1, EnumRuneSocketType.itemInst);
    /** Chance to bypass armour entirely*/
    public static final RuneSocket invidiaArmourBypass = new RuneSocketInvidiaArmourBypass(10, 1, EnumRuneSocketType.tool);
    /** Displays a HUD with statistics of the entity viewed*/
    public static final RuneSocket invidiaStatistics = new RuneSocketInvidiaStatistics(11, 1, EnumRuneSocketType.armor);
    
    /** Adds a flat damage*/
    public static final RuneSocket iraFlatDamage = new RuneSocketIraFlatDamage(12, 1, EnumRuneSocketType.itemInst);
    /** Adds damage based on a percentage of foe's max health*/
    public static final RuneSocket iraPercentageDamage = new RuneSocketIraPercentageDamage(13, 1, EnumRuneSocketType.tool);
    /** Adds a thorn-effect, dealing a percentage of damage taken back to attacker*/
    public static final RuneSocket iraThorns = new RuneSocketIraThorns(14, 1, EnumRuneSocketType.armor);

    /** Heals instead of damages your foe, low cooldown*/
    public static final RuneSocket luxuriaHeal = new RuneSocketLuxuriaHeal(15, 1, EnumRuneSocketType.itemInst);
    /** Speeds Crop-growth and makes animals breed, if in range*/
    public static final RuneSocket luxuriaGrowth = new RuneSocketLuxuriaGrowth(16, 1, EnumRuneSocketType.tool);
    /** Gains speed-bonus if near another player*/
    public static final RuneSocket luxuriaSpeed = new RuneSocketLuxuriaSpeed(17, 1, EnumRuneSocketType.armor);
    
    /** Absorbs a flat (low) amount of damage*/
    public static final RuneSocket superbiaFlatAbsorb = new RuneSocketSuperbiaFlatAbsorb(18, 1, EnumRuneSocketType.itemInst);
    /** Chance to completely block an attack*/
    public static final RuneSocket superbiaBlock = new RuneSocketSuperbiaBlock(19, 1, EnumRuneSocketType.tool);
    /** Absorbs a percentage of damage taken*/
    public static final RuneSocket superbiaPercentageAbsorb = new RuneSocketSuperbiaPercentageAbsorb(20, 1, EnumRuneSocketType.armor);
    
   
    public final int effectId;
    private final int weight;
    /** The EnumRuneSocketType given to this Rune-Effect. */
    public EnumRuneSocketType type;
    /** Used in localisation and stats. */
    protected String name;
    private static final String __OBFID = "CL_00000105";

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
    public int getMaxEnchantability(int maxLevel)
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
