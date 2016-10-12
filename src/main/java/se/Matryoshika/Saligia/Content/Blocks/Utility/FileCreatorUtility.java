/**
 * 
 */
package se.Matryoshika.Saligia.Content.Blocks.Utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Hex;

import net.minecraft.block.Block;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.UtilityTiles.UtilityTileRegistry;
import se.Matryoshika.Saligia.Content.Tiles.CustomTileClass;

/**
 * This class was created by Matryoshika Oct 11, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
@SideOnly(Side.CLIENT)
public class FileCreatorUtility {
	
	private static boolean devEnv = false;
	
	public static void createFiles(){
		devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		if(devEnv)
		for(UtilityBlock block : BlockRegistryInjector.utilityList){
			UtilityBlock util = (UtilityBlock) block;
			String name = util.getRegistryName().toString();
			String alt = name.replace(Saligia.MODID+":", "");
			createBlockState(alt);
			createObjModel(alt);
			
			
			
			Class clazz = UtilityTileRegistry.getTile(util.getRegistryName().toString());
			
			if(clazz == null)
				return;
			try {
				CustomTileClass tile = (CustomTileClass) clazz.newInstance();
				double[] scheme = tile.colourScheme();
				createMTL(alt, scheme);
				createParticleTexture(alt, scheme);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			System.out.println("Created files for: " + name);
		}
	}
	
	
	private static void createBlockState(String name){
		File file = new File(Saligia.getDirectory()       +"/../../src/main/resources/assets/saligia/blockstates/blockutility.json");
		File destination = new File(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/blockstates/"+name+".json");
		copyFile(file, destination);
		
		Path path = Paths.get(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/blockstates/"+name+".json");
		Charset charset = StandardCharsets.UTF_8;

		try {
			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll("blockutility", name);
			Files.write(path, content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file_actual = new File(Saligia.getDirectory()       +"/../../src/main/resources/assets/saligia/blockstates/blockutility_actual.json");
		File destination_actual = new File(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/blockstates/"+name+"_actual.json");
		copyFile(file_actual, destination_actual);
		
		Path path_other = Paths.get(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/blockstates/"+name+"_actual.json");
		Charset charset_other = StandardCharsets.UTF_8;

		try {
			String content = new String(Files.readAllBytes(path_other), charset_other);
			content = content.replaceAll("BlockUtility", name);
			Files.write(path_other, content.getBytes(charset_other));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void createObjModel(String name){
		File file = new File(Saligia.getDirectory()       +"/../../src/main/resources/assets/saligia/models/block/"+"BlockUtility"+".obj");
		File destination = new File(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/models/block/"+name+".obj");
		copyFile(file, destination);
		
		Path path = Paths.get(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/models/block/"+name+".obj");
		Charset charset = StandardCharsets.UTF_8;

		try {
			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll("mtllib BlockUtility.mtl", "mtllib "+name+".mtl");
			Files.write(path, content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void createMTL(String name, double[] scheme){
		File file = new File(Saligia.getDirectory()       +"/../../src/main/resources/assets/saligia/models/block/"+"BlockUtility"+".mtl");
		File destination = new File(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/models/block/"+name+".mtl");
		copyFile(file, destination);
		
		Path path = Paths.get(Saligia.getDirectory()+"/../../src/main/resources/assets/saligia/models/block/"+name+".mtl");
		Charset charset = StandardCharsets.UTF_8;

		try {
			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll("Kd 0.640000 0.640000 0.640000", "Kd "+ new DecimalFormat("#.######").format(scheme[0]/200) + " "+ new DecimalFormat("#.######").format(scheme[1]/200) + " " + new DecimalFormat("#.######").format(scheme[2]/200));
			Files.write(path, content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void createParticleTexture(String name, double[] scheme){
		int x = 0, y = 0, w = 16, h = 16;

	    BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	    Graphics g = img.getGraphics();

	    g = img.getGraphics();
	    g.setColor(new Color((float)scheme[0]/200, (float)scheme[1]/200, (float)scheme[2]/200, 0.75f));
	    g.fillRect(x, y, w, h);

	    try {
			ImageIO.write(img, "png", new File(Saligia.getDirectory() +"/../../src/main/resources/assets/saligia/textures/blocks/"+name+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage modAlpha(BufferedImage img, double modAmount) {
		
	    for (int x = 0; x < img.getWidth(); x++) {      
	    	
	        for (int y = 0; y < img.getHeight(); y++) {
	        	
	            int argb = img.getRGB(x, y);
	            int alpha = (argb >> 24) & 0xff;
	
	            alpha *= modAmount;
	            alpha &= 0xff;
	
	            argb &= 0x00ffffff;
	            argb |= (alpha << 24);
	            img.setRGB(x, y, argb);            
	        }
	    }
	    return img;
	}
	
	
	@SuppressWarnings("resource")
	private static void copyFile(File sourceFile, File destFile){
	if(!destFile.exists()) {
		System.out.println("Creating file for: " + destFile.getName());
		try {
			destFile.createNewFile();
				
			FileChannel source = null;
			FileChannel destination = null;
			try {
				source = new RandomAccessFile(sourceFile,"rw").getChannel();
				destination = new RandomAccessFile(destFile,"rw").getChannel();
				
				long position = 0;
				long count    = source.size();
				
				source.transferTo(position, count, destination);
				}
			finally {
				if(source != null) {
					source.close();
				}
				if(destination != null) {
						destination.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
