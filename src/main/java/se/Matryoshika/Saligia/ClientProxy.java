package se.Matryoshika.Saligia;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.API.Rendering.RenderingRegistryInjector;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Rendering.RenderRegister;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		RenderRegister.registerRenderers();
		RenderingRegistryInjector.reg(ContentRegistry.ritualCOTHBlock, new ModelResourceLocation(Saligia.MODID+":"+ContentRegistry.ritualCOTHBlock.getUnlocName(), "inventory"), 0);
	}

}
