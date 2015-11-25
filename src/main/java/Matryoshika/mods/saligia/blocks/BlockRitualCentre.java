package Matryoshika.mods.saligia.blocks;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.items.saligia_Items;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class BlockRitualCentre extends Block {
	
	public BlockRitualCentre (Block BlockRitualCentre){
		super(Material.rock);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":BlockRitualCentre");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockRitualCentre");
	}
}