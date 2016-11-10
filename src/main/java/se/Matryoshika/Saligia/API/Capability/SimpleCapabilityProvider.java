/**
 * 
 */
package se.Matryoshika.Saligia.API.Capability;

import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * BIG THANKS to Choonster and his TestMod3
 * My head still spins right round whenever I deal with
 * capabilities. All code in this class belongs fully to him.
 */

/**
 * A simple implementation of {@link ICapabilitySerializable} that supports a single {@link Capability} handler instance.
 * <p>
 * Uses the {@link Capability}'s {@link IStorage} to serialise/deserialise NBT.
 *
 * @author Choonster
 */
public class SimpleCapabilityProvider<HANDLER> implements ICapabilitySerializable<NBTBase> {
	/**
	 * The {@link Capability} instance to provide the handler for.
	 */
	private final Capability<HANDLER> capability;

	/**
	 * The {@link EnumFacing} to provide the handler for.
	 */
	private final EnumFacing facing;

	/**
	 * The handler instance to provide.
	 */
	private final HANDLER instance;

	/**
	 * Create a provider for the default handler instance.
	 *
	 * @param capability The Capability instance to provide the handler for
	 * @param facing     The EnumFacing to provide the handler for
	 */
	public SimpleCapabilityProvider(Capability<HANDLER> capability, @Nullable EnumFacing facing) {
		this(capability, facing, capability.getDefaultInstance());
	}

	/**
	 * Create a provider for the specified handler instance.
	 *
	 * @param capability The Capability instance to provide the handler for
	 * @param facing     The EnumFacing to provide the handler for
	 * @param instance   The handler instance to provide
	 */
	public SimpleCapabilityProvider(Capability<HANDLER> capability, @Nullable EnumFacing facing, HANDLER instance) {
		this.capability = capability;
		this.instance = instance;
		this.facing = facing;
	}

	/**
	 * Determines if this object has support for the capability in question on the specific side.
	 * The return value of this MIGHT change during runtime if this object gains or looses support
	 * for a capability.
	 * <p>
	 * Example:
	 * A Pipe getting a cover placed on one side causing it loose the Inventory attachment function for that side.
	 * <p>
	 * This is a light weight version of getCapability, intended for metadata uses.
	 *
	 * @param capability The capability to check
	 * @param facing     The Side to check from:
	 *                   CAN BE NULL. Null is defined to represent 'internal' or 'self'
	 * @return True if this object supports the capability.
	 */
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == getCapability();
	}

	/**
	 * Retrieves the handler for the capability requested on the specific side.
	 * The return value CAN be null if the object does not support the capability.
	 * The return value CAN be the same for multiple faces.
	 *
	 * @param capability The capability to check
	 * @param facing     The Side to check from:
	 *                   CAN BE NULL. Null is defined to represent 'internal' or 'self'
	 * @return The handler if this object supports the capability.
	 */
	@Override
	@Nullable
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == getCapability()) {
			return getCapability().cast(getInstance());
		}

		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return getCapability().writeNBT(getInstance(), getFacing());
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		getCapability().readNBT(getInstance(), getFacing(), nbt);
	}

	/**
	 * Get the {@link Capability} instance to provide the handler for.
	 *
	 * @return The Capability instance
	 */
	public final Capability<HANDLER> getCapability() {
		return capability;
	}

	/**
	 * Get the {@link EnumFacing} to provide the handler for.
	 *
	 * @return The EnumFacing to provide the handler for
	 */
	@Nullable
	public EnumFacing getFacing() {
		return facing;
	}

	/**
	 * Get the handler instance.
	 *
	 * @return The handler instance
	 */
	public final HANDLER getInstance() {
		return instance;
	}
	
	/**
	 * Get a capability handler from an {@link ICapabilityProvider} if it exists.
	 *
	 * @param provider   The provider
	 * @param capability The capability
	 * @param facing     The facing
	 * @param <T>        The handler type
	 * @return The handler, if any.
	 */
	@Nullable
	public static <T> T getCapability(@Nullable ICapabilityProvider provider, Capability<T> capability, @Nullable EnumFacing facing) {
		return provider != null && provider.hasCapability(capability, facing) ? provider.getCapability(capability, facing) : null;
	}
}
