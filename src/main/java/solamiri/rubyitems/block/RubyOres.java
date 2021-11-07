package solamiri.rubyitems.block;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.GenerationStep;
import solamiri.rubyitems.RubyItemsMain;
import solamiri.rubyitems.item.RubyItemsGroup;


public class RubyOres extends Block{

    public static final RubyOres RUBY_ORE = registerBlock("ruby_ore",
    new RubyOres(FabricBlockSettings.of(Material.STONE).strength(3.0F)
    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    private static 	ConfiguredFeature<?, ?> RUBY_ORE_OVERWORLD = registerOre("rubyoreoverworld", RUBY_ORE, 9, 0, 80, 10, "Overworld");
  
   
    /**
     * Constructor of RubyOres
     * @param settings
     */
    public RubyOres(Settings settings) {
        super(settings);
        //TODO Auto-generated constructor stub
    }
    
    /**
     * Function to make the block drop experience
     */
    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            int experience = MathHelper.nextInt(world.random, 1, 4); // between 1 and 4 xp
            this.dropExperience(world, pos, experience);
        }
    }

    /**
     * Print function just to symbolize the creation of ores
     */
    public static void registerRubyOres(){
        System.out.println("Registering RubyBlock Ores for " + RubyItemsMain.MOD_ID);
    }

    /**
     * Register function for blocks used in Ores
     * @param name  The name of the block
     * @param block The newly created block 
     * @return      The registry of the block
     */
    private static RubyOres registerBlock(String name, RubyOres block) {
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(RubyItemsMain.MOD_ID, name), block);
    }

    /**
     * Register function for blockitems
     * @param name  The name of the block
     * @param block The newly created block 
     * @return      The registry of the blockitem
     */
    private static Item registerBlockItem(String name, RubyOres block) {
        return Registry.register(Registry.ITEM, new Identifier(RubyItemsMain.MOD_ID, name), 
            new BlockItem(block, new FabricItemSettings().group(RubyItemsGroup.RUBY)));
    }
    
    /**
     * Register function for Ores
     * @param name      The name of the block
     * @param block     The block used in the ore
     * @param veinSize  The max size of the vein
     * @param minHeight The minimum height for the ore to appear
     * @param maxHeight The maximum height for the ore to appear
     * @param repeat    The number of time the vein appears in a chunch
     * @param foundIn   In which work this ore can be found
     * @return The Configuration of the Ore
     */
    private static ConfiguredFeature<?, ?> registerOre(String name, Block block, int veinSize, int minHeight, int maxHeight, int repeat, String foundIn) {
        ConfiguredFeature<?, ?> res;
        res = Feature.ORE.configure(
		new OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, 
			block.getDefaultState(),
			veinSize))	//Vein Max Size
		.range(new RangeDecoratorConfig(
			// You can also use one of the other height providers if you don't want a uniform distribution
			UniformHeightProvider.create(YOffset.aboveBottom(minHeight), YOffset.fixed(maxHeight)))) // Inclusive min and max height
		.spreadHorizontally()
		.repeat(repeat);// Number of veins per chunk

        RegistryKey<ConfiguredFeature<?, ?>> regKey = registerOreRegistryKey(name);
        registerBuiltin(regKey, res);
        ModifyBiome(regKey,foundIn);
        return res;
    }

    /**
     * Register the registry key of the Ore
     * @param name  The name of the block used in the Ore
     * @return      The RegistryKey of the block
     */
    private static  RegistryKey<ConfiguredFeature<?, ?>> registerOreRegistryKey(String name) {
        return  RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(RubyItemsMain.MOD_ID, name));
    }
       
    /**
     * Register the built in 
     * @param oreRegKey The Registry key of the Ore
     * @param oreCfg    The Configuration of the Ore
     * @return The registry of the Built in
     */
    private static ConfiguredFeature<?, ?> registerBuiltin(RegistryKey<ConfiguredFeature<?, ?>> oreRegKey, ConfiguredFeature<?, ?> oreCfg) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreRegKey.getValue(), oreCfg);
    }
    
    /**
     * Modify the biomes of the game
     * @param oreRegKey The Registry key of the Ore
     * @param foundIn   In which work this ore can be found
     */
    private static void ModifyBiome(RegistryKey<ConfiguredFeature<?, ?>> oreRegKey, String foundIn) {
        switch (foundIn.toLowerCase()) {
            case "overworld":
                BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreRegKey);
                break;
        
            default:
                break;
        }
        // BiomeModifications line is crossed because the biomes modification is still in "beta" and Mojang don't want people to touch it if they don’t know what they’re doing
        // let’s just ignore it :)
    }

}