/**
 * 
 */
package se.Matryoshika.Saligia.Content.Items.Armour;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Utils;
import se.Matryoshika.Saligia.API.Content.Materials;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Nov 1, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class SinArmour extends SaligiaBaseArmour{

	public SinArmour(String name, EntityEquipmentSlot equipmentSlot) {
		super(Materials.SIN_ARMOUR, 0, equipmentSlot);
		this.setRegistryName(Saligia.MODID, name);
		this.setUnlocalizedName(getRegistryName().toString());
		this.setCreativeTab(Saligia.saligiaTab);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onItemToolTip(ItemTooltipEvent event){
		if(event.getItemStack().getItem() == this){
			String tooltip = I18n.format(Saligia.MODID+".lore.armour.discount");
			tooltip = tooltip.replace("[xyz]", String.valueOf(4*5)+"%");
			event.getToolTip().add(TextFormatting.DARK_PURPLE + tooltip);
		}
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean adv) {
		list.add("∞ / ∞");
	}

}
