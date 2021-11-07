package solamiri.rubyitems.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import solamiri.rubyitems.RubyItemsMain;

public class RubyItems{
 
    //Add the ruby item
    public static final Item RUBY = registerItem("ruby", 
    new Item(new FabricItemSettings().group(RubyItemsGroup.RUBY)));
 
    //Add the ruby royal armor
    // slightly better than gold armor
    public static final Item RUBY_ROYAL_CROWN = registerItem("ruby_royal_crown", 
    new ArmorItem(RubyItemsArmorMaterial.RUBY,EquipmentSlot.HEAD,
    new FabricItemSettings().group(RubyItemsGroup.RUBY)));

    public static final Item RUBY_ROYAL_CHESTPLATE = registerItem("ruby_royal_chestplate", 
    new ArmorItem(RubyItemsArmorMaterial.RUBY,EquipmentSlot.CHEST,
    new FabricItemSettings().group(RubyItemsGroup.RUBY)));

    public static final Item RUBY_ROYAL_LEGS = registerItem("ruby_royal_legs", 
    new ArmorItem(RubyItemsArmorMaterial.RUBY,EquipmentSlot.LEGS,
    new FabricItemSettings().group(RubyItemsGroup.RUBY)));

    public static final Item RUBY_ROYAL_BOOTS = registerItem("ruby_royal_boots", 
    new ArmorItem(RubyItemsArmorMaterial.RUBY,EquipmentSlot.FEET,
    new FabricItemSettings().group(RubyItemsGroup.RUBY)));


    /**
     * Register function for items
     * @param name  The name of the item
     * @param item  The newly created item 
     * @return      The registry of the item
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(RubyItemsMain.MOD_ID,name),item);
    }


    /**
     * Print function just to symbolize the creation of blocks
     */
    public static void registerRubyItemsItems(){
        System.out.println("Registering RubyItem Items for " + RubyItemsMain.MOD_ID);
    }

    

}
