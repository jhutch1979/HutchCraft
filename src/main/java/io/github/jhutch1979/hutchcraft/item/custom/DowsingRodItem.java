package io.github.jhutch1979.hutchcraft.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class DowsingRodItem extends Item {
    private Player player;
    Block blockType = null;
    public static final BlockPos[] NEIGHBOR_POSITIONS = new BlockPos[26];

    static {
        NEIGHBOR_POSITIONS[0] = new BlockPos(1, 0, 0);
        NEIGHBOR_POSITIONS[1] = new BlockPos(-1, 0, 0);
        NEIGHBOR_POSITIONS[2] = new BlockPos(0, 0, 1);
        NEIGHBOR_POSITIONS[3] = new BlockPos(0, 0, -1);
        NEIGHBOR_POSITIONS[4] = new BlockPos(0, 1, 0);
        NEIGHBOR_POSITIONS[5] = new BlockPos(0, -1, 0);

        NEIGHBOR_POSITIONS[6] = new BlockPos(1, 0, 1);
        NEIGHBOR_POSITIONS[7] = new BlockPos(1, 0, -1);
        NEIGHBOR_POSITIONS[8] = new BlockPos(-1, 0, 1);
        NEIGHBOR_POSITIONS[9] = new BlockPos(-1, 0, -1);

        NEIGHBOR_POSITIONS[10] = new BlockPos(1, 1, 0);
        NEIGHBOR_POSITIONS[11] = new BlockPos(-1, 1, 0);
        NEIGHBOR_POSITIONS[12] = new BlockPos(0, 1, 1);
        NEIGHBOR_POSITIONS[13] = new BlockPos(0, 1, -1);

        NEIGHBOR_POSITIONS[14] = new BlockPos(1, -1, 0);
        NEIGHBOR_POSITIONS[15] = new BlockPos(-1, -1, 0);
        NEIGHBOR_POSITIONS[16] = new BlockPos(0, -1, 1);
        NEIGHBOR_POSITIONS[17] = new BlockPos(0, -1, -1);

        NEIGHBOR_POSITIONS[18] = new BlockPos(1, 1, 1);
        NEIGHBOR_POSITIONS[19] = new BlockPos(1, 1, -1);
        NEIGHBOR_POSITIONS[20] = new BlockPos(-1, 1, 1);
        NEIGHBOR_POSITIONS[21] = new BlockPos(-1, 1, -1);

        NEIGHBOR_POSITIONS[22] = new BlockPos(1, -1, 1);
        NEIGHBOR_POSITIONS[23] = new BlockPos(1, -1, -1);
        NEIGHBOR_POSITIONS[24] = new BlockPos(-1, -1, 1);
        NEIGHBOR_POSITIONS[25] = new BlockPos(-1, -1, -1);
    }

    public DowsingRodItem(Properties pProperties) {
        super(pProperties);

    }



    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if(pContext.getLevel().isClientSide()){

            BlockPos positionClicked = pContext.getClickedPos();
            player = pContext.getPlayer();
            boolean foundBlock = false;


            if(!player.isShiftKeyDown()){
                if(blockType != null) {
                    for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                        Block blockToCheck = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                        if (isValuableBlock(blockToCheck)) {
                            outputValuableCoordinates(positionClicked.below(i), player, blockToCheck);
                            foundBlock = true;
                            walk(positionClicked.below(i), pContext.getLevel());
                            break;

                        }


                    }

                    if (!foundBlock) {
                        player.sendMessage(new TranslatableComponent("item.hutchcraft.dowsing_rod.no_valubles"),
                                player.getUUID());

                    }
                }
            } else {
                blockType = pContext.getLevel().getBlockState(positionClicked).getBlock();

                player.sendMessage(new TextComponent("Dowsing Set to  " +
                        blockType.asItem().getRegistryName().toString()
                ), player.getUUID());
            }

        }
        return super.useOn(pContext);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendMessage(new TextComponent("Found " + blockBelow.asItem().getRegistryName().toString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"), player.getUUID());
    }

    private boolean isValuableBlock(Block block) {
        return block == blockType;
    }

    private void walk(BlockPos pos, Level level) {
        HashSet<BlockPos> known = new HashSet<>();
        Set<BlockPos> traversed = new HashSet<>();
        Deque<BlockPos> openSet = new ArrayDeque<>();
        openSet.add(pos);
        traversed.add(pos);

        while (!openSet.isEmpty()) {
            BlockPos ptr = openSet.pop();

            if (check(level, ptr) && known.add(ptr)) {
                if (known.size() >= 32) {
                    player.sendMessage(new TextComponent("Found more than 32 " +
                            blockType.asItem().getRegistryName().toString()), player.getUUID());
                    return;
                }

                for (BlockPos side : NEIGHBOR_POSITIONS) {
                    BlockPos offset = ptr.offset(side);

                    if (traversed.add(offset)) {
                        openSet.add(offset);
                    }
                }
            }
        }
        player.sendMessage(new TextComponent("Found " + known.size() + " " +
                blockType.asItem().getRegistryName().toString()), player.getUUID());
    }

    public boolean check(Level level, BlockPos pos) {
        Block blockToCheck = level.getBlockState(pos).getBlock();
        return isValuableBlock(blockToCheck);
    }


}
