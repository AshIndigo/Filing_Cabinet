package com.ashindigo.storagecabinet;

import com.ashindigo.storagecabinet.blocks.CabinetManagerBlock;
import com.ashindigo.storagecabinet.blocks.StorageCabinetBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static StorageCabinetBlock WOOD_CABINET;
    public static StorageCabinetBlock IRON_CABINET;
    public static StorageCabinetBlock GOLD_CABINET;
    public static StorageCabinetBlock DIAMOND_CABINET;
    public static StorageCabinetBlock EMERALD_CABINET;
    public static CabinetManagerBlock CABINET_MANAGER;

    // TODO Note: Mining levels are currently not functional, and if fixed may require diamond tier+ tools to mine. Will have to fix when I can actually test
    public static void init() {
        WOOD_CABINET = addCabinet(0, FabricBlockSettings.of(Material.WOOD, MaterialColor.WOOD).breakByTool(FabricToolTags.AXES), "wood");
        IRON_CABINET = addCabinet(1, FabricBlockSettings.of(Material.METAL, MaterialColor.IRON).breakByTool(FabricToolTags.PICKAXES, 2), "iron");
        GOLD_CABINET = addCabinet(2, FabricBlockSettings.of(Material.METAL, MaterialColor.GOLD).breakByTool(FabricToolTags.PICKAXES, 3), "gold");
        DIAMOND_CABINET = addCabinet(3, FabricBlockSettings.of(Material.METAL, MaterialColor.DIAMOND).breakByTool(FabricToolTags.PICKAXES, 3), "diamond");
        EMERALD_CABINET = addCabinet(4, FabricBlockSettings.of(Material.METAL, MaterialColor.EMERALD).breakByTool(FabricToolTags.PICKAXES, 3), "emerald");
        CABINET_MANAGER = new CabinetManagerBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.IRON).requiresTool().breakByTool(FabricToolTags.PICKAXES, 3).breakByHand(false).strength(3, 5));
        Registry.register(Registry.BLOCK, new Identifier(StorageCabinet.MODID, "cabinet_manager"), CABINET_MANAGER);
        Registry.register(Registry.ITEM, new Identifier(StorageCabinet.MODID, "cabinet_manager"), new BlockItem(CABINET_MANAGER, new Item.Settings().group(StorageCabinet.CABINET_GROUP)));

    }

    public static StorageCabinetBlock addCabinet(int tier, FabricBlockSettings settings, String suffix) {
        StorageCabinetBlock storageCabinetBlock = new StorageCabinetBlock(settings.strength(3.0F, 5.0F).breakByHand(false).requiresTool(), tier);
        Registry.register(Registry.BLOCK, new Identifier(StorageCabinet.MODID, StorageCabinet.MODID + "_" + suffix), storageCabinetBlock);
        Registry.register(Registry.ITEM, new Identifier(StorageCabinet.MODID, StorageCabinet.MODID + "_" + suffix), new BlockItem(storageCabinetBlock, new Item.Settings().group(StorageCabinet.CABINET_GROUP)));
        return storageCabinetBlock;
    }

    public static StorageCabinetBlock getByTier(int tier) {
        switch (tier) {
            case 0: return WOOD_CABINET;
            case 1: return IRON_CABINET;
            case 2: return GOLD_CABINET;
            case 3: return DIAMOND_CABINET;
            case 4: return EMERALD_CABINET;
            default: return WOOD_CABINET;
        }
    }
}
