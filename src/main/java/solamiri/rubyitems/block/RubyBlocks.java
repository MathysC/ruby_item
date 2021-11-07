package solamiri.rubyitems.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import solamiri.rubyitems.RubyItemsMain;
import solamiri.rubyitems.item.RubyItemsGroup;

public class RubyBlocks {
 
 
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
    new Block(FabricBlockSettings.of(Material.METAL).strength(5.0F)
    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    /**
     * Register function for blocks
     * @param name  The name of the block
     * @param block The newly created block 
     * @return      The registry of the block
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(RubyItemsMain.MOD_ID, name), block);
    }

    /**
     * Register function for blockitems
     * @param name  The name of the block
     * @param block The newly created block 
     * @return      The registry of the blockitem
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(RubyItemsMain.MOD_ID, name), 
            new BlockItem(block, new FabricItemSettings().group(RubyItemsGroup.RUBY)));
    }

    /**
     * Print function just to symbolize the creation of blocks
     */
    public static void registerRubyItemsBlocks(){
        System.out.println("Registering RubyItem Blocks for " + RubyItemsMain.MOD_ID);
    }

    

}
