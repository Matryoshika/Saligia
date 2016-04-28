package Matryoshika.mods.saligia.rendering.models;

import Matryoshika.mods.saligia.saligia;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class modelSoulNexus{

	private IModelCustom modelAltar_Pagan;

	public static final ResourceLocation obj = new ResourceLocation(saligia.MODID, "models/Storage_nexus.obj");
    public modelSoulNexus()
    {
        modelAltar_Pagan = AdvancedModelLoader.loadModel(obj);
    }
}
