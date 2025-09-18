package julianmella.voxelspherehollow;

import julianmella.voxelspherehollow.blocks.VoxelViewerBlock;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoxelSphereHollow implements ModInitializer {
	public static final String MOD_ID = "voxel-sphere-hollow";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		VoxelViewerBlock.initialize();
	}
}