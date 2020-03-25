package io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines;

import io.github.thebusybiscuit.cscorelib2.item.CustomItem;
import io.github.thebusybiscuit.cscorelib2.protection.ProtectableAction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.energy.ChargableBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class ItemDecompressor extends AContainer {

    public ItemDecompressor(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getID(), getInventoryTitle()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.ACCESS_INVENTORIES);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.WITHDRAW) return getOutputSlots();

                List<Integer> slots = new ArrayList<>();

                for (int slot : getInputSlots()) {
                    if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), item, true)) {
                        slots.add(slot);
                    }
                }

                if (slots.isEmpty()) {
                    return getInputSlots();
                } else {
                    Collections.sort(slots, compareSlots(menu));
                    int[] array = new int[slots.size()];

                    for (int i = 0; i < slots.size(); i++) {
                        array[i] = slots.get(i);
                    }

                    return array;
                }
            }
        };

        this.registerDefaultRecipes();
    }

    private Comparator<Integer> compareSlots(DirtyChestMenu menu) {
        return (slot1, slot2) -> menu.getItemInSlot(slot1).getAmount() - menu.getItemInSlot(slot2).getAmount();
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ACACIA_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.ACACIA_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BIRCH_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.BIRCH_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLAZE_ROD}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLAZE_ROD), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BLUE_ICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BLUE_ICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_BONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.BONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CHEST}, new ItemStack[]{new CustomItem(new ItemStack(Material.CHEST), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_CLAY}, new ItemStack[]{new CustomItem(new ItemStack(Material.CLAY), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COAL}, new ItemStack[]{new CustomItem(new ItemStack(Material.COAL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COAL_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.COAL_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_COBBLESTONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.COBBLESTONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DARK_OAK_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.DARK_OAK_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DIAMOND_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DIAMOND_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DRIED_KELP}, new ItemStack[]{new CustomItem(new ItemStack(Material.DRIED_KELP), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_DRIED_KELP_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.DRIED_KELP_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_EMERALD_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.EMERALD_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_END_STONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.END_STONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_FLINT}, new ItemStack[]{new CustomItem(new ItemStack(Material.FLINT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLD_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.GOLD_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GRAVEL}, new ItemStack[]{new CustomItem(new ItemStack(Material.GRAVEL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_HAY_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.HAY_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_ICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.ICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_IRON_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.IRON_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_JUNGLE_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.JUNGLE_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LAPIS_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.LAPIS_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_LEATHER}, new ItemStack[]{new CustomItem(new ItemStack(Material.LEATHER), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHERRACK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHERRACK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_BRICK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_BRICK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_STAR}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_STAR), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_NETHER_WART_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.NETHER_WART_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OAK_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.OAK_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_OBSIDIAN}, new ItemStack[]{new CustomItem(new ItemStack(Material.OBSIDIAN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PACKED_ICE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PACKED_ICE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_POTATO}, new ItemStack[]{new CustomItem(new ItemStack(Material.POTATO), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PRISMARINE}, new ItemStack[]{new CustomItem(new ItemStack(Material.PRISMARINE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_PUMPKIN}, new ItemStack[]{new CustomItem(new ItemStack(Material.PUMPKIN), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_QUARTZ_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.QUARTZ_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_REDSTONE_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.REDSTONE_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SAND}, new ItemStack[]{new CustomItem(new ItemStack(Material.SAND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SCAFFOLDING}, new ItemStack[]{new CustomItem(new ItemStack(Material.SCAFFOLDING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SLIME_BLOCK}, new ItemStack[]{new CustomItem(new ItemStack(Material.SLIME_BLOCK), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SOUL_SAND}, new ItemStack[]{new CustomItem(new ItemStack(Material.SOUL_SAND), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPONGE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPONGE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SPRUCE_LOG}, new ItemStack[]{new CustomItem(new ItemStack(Material.SPRUCE_LOG), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STONE}, new ItemStack[]{new CustomItem(new ItemStack(Material.STONE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_STRING}, new ItemStack[]{new CustomItem(new ItemStack(Material.STRING), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_SUGAR_CANE}, new ItemStack[]{new CustomItem(new ItemStack(Material.SUGAR_CANE), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHEAT}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHEAT), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WHEAT_SEEDS}, new ItemStack[]{new CustomItem(new ItemStack(Material.WHEAT_SEEDS), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_WITHER_SKELETON_SKULL}, new ItemStack[]{new CustomItem(new ItemStack(Material.WITHER_SKELETON_SKULL), 32)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.COMPRESSED_GOLD_DUST}, new ItemStack[]{new CustomItem(SlimefunItems.GOLD_DUST, 32)});

    }

    @Override
    public String getInventoryTitle() {
        return "&cItem Decompressor";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{19, 20};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{24, 25};
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                ItemDecompressor.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    @Override
    protected void tick(Block b) {
        BlockMenu menu = BlockStorage.getInventory(b);

        if (isProcessing(b)) {
            int timeleft = progress.get(b);

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(menu, 22, timeleft, processing.get(b).getTicks(), getProgressBar());

                if (ChargableBlock.isChargable(b)) {
                    if (ChargableBlock.getCharge(b) < getEnergyConsumption()) return;
                    ChargableBlock.addCharge(b, -getEnergyConsumption());
                    progress.put(b, timeleft - 1);
                } else progress.put(b, timeleft - 1);
            } else {
                menu.replaceExistingItem(22, new CustomItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE), " "));
                menu.pushItem(processing.get(b).getOutput()[0], getOutputSlots());

                progress.remove(b);
                processing.remove(b);

                if (timeleft != -1)
                    tick(b);
            }
        } else {
            Map<Integer, Integer> found = new HashMap<>();
            MachineRecipe recipe = findRecipe(menu, found);

            if (recipe != null) {
                if (!menu.fits(recipe.getOutput()[0], getOutputSlots())) {
                    return;
                }

                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    menu.consumeItem(entry.getKey(), entry.getValue());
                }

                processing.put(b, recipe);
                progress.put(b, recipe.getTicks() == 0 ? -1 : recipe.getTicks());
                tick(b);
            }
        }
    }

    private MachineRecipe findRecipe(BlockMenu menu, Map<Integer, Integer> found) {
        for (MachineRecipe recipe : recipes) {
            for (ItemStack input : recipe.getInput()) {
                for (int slot : getInputSlots()) {
                    if (SlimefunManager.isItemSimilar(menu.getItemInSlot(slot), input, true)) {
                        found.put(slot, input.getAmount());
                        break;
                    }
                }
            }

            if (found.size() == recipe.getInput().length) {
                return recipe;
            } else found.clear();
        }

        return null;
    }

    @Override
    public String getMachineIdentifier() {
        return "ITEM_DECOMPRESSOR";
    }

}
