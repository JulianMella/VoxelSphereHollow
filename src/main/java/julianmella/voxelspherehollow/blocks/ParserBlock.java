package julianmella.voxelspherehollow.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class ParserBlock extends Block {
    public static int radius = 1;

    public ParserBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            var item = player.getMainHandStack().getItem();

            if (item == net.minecraft.item.Items.DIAMOND_SWORD) {
                if (radius < 100) {
                    radius++;
                    Text increment = Text.of("Increased by 1 to radius: " + radius);
                    player.sendMessage(increment, false);
                }
            }

            if (item == net.minecraft.item.Items.DIAMOND_HOE) {
                if (radius > 0) {
                    radius--;
                    Text increment = Text.of("Decreased by 1 to radius: " + radius);
                    player.sendMessage(increment, false);
                }
            }

            if (item == net.minecraft.item.Items.DIAMOND_AXE) {
                radius = 100;
            }

            if (item == net.minecraft.item.Items.DIAMOND_PICKAXE) {
                StringBuilder sb = new StringBuilder();
                for (int dx = -radius; dx <= radius; dx++) {
                    for (int dy = -radius; dy <= radius; dy++) {
                        for (int dz = -radius; dz <= radius; dz++) {
                            if (dx == 0 && dy == 0 && dz == 0) continue; // skip center
                            BlockPos checkPos = pos.add(dx, dy, dz);
                            BlockState checkState = world.getBlockState(checkPos);
                            Block block = checkState.getBlock();
                            int normX = checkPos.getX() - pos.getX();
                            int normY = checkPos.getY() - pos.getY();
                            int normZ = checkPos.getZ() - pos.getZ();

                            // Example: Only register stone blocks
                            if (block == net.minecraft.block.Blocks.STONE) {
                                sb.append(block.getName().getString())
                                        .append(" at ")
                                        .append(normX).append(" ")
                                        .append(normY).append(" ")
                                        .append(normZ)
                                        .append("\n");
                            }
                        }
                    }
                }
                String pathname = "/Users/julianmella/Tensor Builder/Assets/SphereCoordinates/Sphere" + radius + ".txt"; // Change this to your desired path
                java.io.File file = new java.io.File(pathname);
                try (java.io.FileWriter writer = new java.io.FileWriter(file)) {
                    writer.write(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (item == net.minecraft.item.Items.NETHERITE_PICKAXE) {
                for (int dx = -radius; dx <= radius; dx++) {
                    for (int dy = -radius; dy <= radius; dy++) {
                        for (int dz = -radius; dz <= radius; dz++) {
                            if (dx == 0 && dy == 0 && dz == 0) continue;
                            BlockPos checkPos = pos.add(dx, dy, dz);
                            BlockState checkState = world.getBlockState(checkPos);
                            Block block = checkState.getBlock();

                            if (block == net.minecraft.block.Blocks.STONE) {
                                world.setBlockState(checkPos, net.minecraft.block.Blocks.DIAMOND_BLOCK.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}