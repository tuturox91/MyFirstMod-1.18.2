package net.sniklz.firstmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.sniklz.firstmod.block.entity.ModBlockEntities;
import net.sniklz.firstmod.block.entity.VapatiteBlaster_BlockEntity;
import org.jetbrains.annotations.Nullable;

public class BlasterBlock extends BaseEntityBlock {


    protected BlasterBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()) {
           BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
           if(blockEntity instanceof VapatiteBlaster_BlockEntity) {
               ((VapatiteBlaster_BlockEntity) blockEntity).drops();
           }
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof  VapatiteBlaster_BlockEntity) {
                NetworkHooks.openGui(((ServerPlayer) pPlayer), (VapatiteBlaster_BlockEntity)entity,pPos);
            } else {
                throw new IllegalStateException("Our conteiner provider is missing!");
            }
        }
        return  InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new VapatiteBlaster_BlockEntity(pPos,pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.VAPATITE_BLASTER.get(), VapatiteBlaster_BlockEntity::tick);
    }
}
