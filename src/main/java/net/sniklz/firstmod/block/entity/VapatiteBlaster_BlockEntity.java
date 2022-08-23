package net.sniklz.firstmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.sniklz.firstmod.gui.VapatiteBlasterMenu;
import net.sniklz.firstmod.item.ModItems;
import net.sniklz.firstmod.recipe.VapatiteBlasterRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class VapatiteBlaster_BlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();

    private  final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private int fuelTime = 0;
    private  int maxFuelTime = 0;


    public VapatiteBlaster_BlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.VAPATITE_BLASTER.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return VapatiteBlaster_BlockEntity.this.progress;
                    case 1: return VapatiteBlaster_BlockEntity.this.maxProgress;
                    case 2: return VapatiteBlaster_BlockEntity.this.fuelTime;
                    case 3: return VapatiteBlaster_BlockEntity.this.maxFuelTime;
                    default:return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0: VapatiteBlaster_BlockEntity.this.progress = value; break;
                    case 1: VapatiteBlaster_BlockEntity.this.maxProgress = value; break;
                    case 2: VapatiteBlaster_BlockEntity.this.fuelTime = value; break;
                    case 3: VapatiteBlaster_BlockEntity.this.maxFuelTime = value; break;
                }
            }
            public int getCount() {return 4;}
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Vapatite Blaseter");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new VapatiteBlasterMenu(pContainerId,pInventory,this, this.data );
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()->itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("blaster.progress", progress);
        tag.putInt("blaster.fuelTime", fuelTime);
        tag.putInt("blaster.maxFuelTime", maxFuelTime);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("blaster.progress");
        fuelTime = nbt.getInt("blaster.fuelTime");
        maxFuelTime = nbt.getInt("blaster.maxFuelTime");
    }




    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, VapatiteBlaster_BlockEntity pBlockEntity) {
        if(isConsumingFuel(pBlockEntity)) {
            pBlockEntity.fuelTime--;
        }

        if(hasRecipe(pBlockEntity)) {
            if(hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity)) {
                pBlockEntity.consumeFuel();
                setChanged(pLevel, pPos,pState);
            }
            if(isConsumingFuel(pBlockEntity)) {
                pBlockEntity.progress++;
                setChanged(pLevel,pPos,pState);
                if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel,pPos,pState);
        }
    }

    private static boolean hasFuelInFuelSlot(VapatiteBlaster_BlockEntity pBlockEntity) {
        return !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean isConsumingFuel(VapatiteBlaster_BlockEntity entity) {
        return entity.fuelTime >0;
    }

    private static boolean hasNotReachedStackLimit(VapatiteBlaster_BlockEntity entity) {
        return  entity.itemHandler.getStackInSlot(3).getCount() < entity.itemHandler.getStackInSlot(3).getMaxStackSize();
    }

    private static boolean hasRecipe(VapatiteBlaster_BlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for(int i = 0; i<entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<VapatiteBlasterRecipe> match = level.getRecipeManager()
                .getRecipeFor(VapatiteBlasterRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(3).getItem() == output.getItem() || inventory.getItem(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(3).getMaxStackSize() >inventory.getItem(3).getCount();
    }

    private static void craftItem(VapatiteBlaster_BlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i <entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i,entity.itemHandler.getStackInSlot(i));
        }

        Optional<VapatiteBlasterRecipe> match = level.getRecipeManager()
                .getRecipeFor(VapatiteBlasterRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(1,1,false);
            entity.itemHandler.extractItem(2,1,false);

            entity.itemHandler.setStackInSlot(3,new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(3).getCount() + 1));

            entity.resetProgress();
        }
    }


    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i< itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private  void consumeFuel() {
        if(!itemHandler.getStackInSlot(0).isEmpty()) {
            this.fuelTime = ForgeHooks.getBurnTime(this.itemHandler.extractItem(0,1,false), RecipeType.SMELTING);
            this.maxFuelTime = this.fuelTime;
        }
    }


}
