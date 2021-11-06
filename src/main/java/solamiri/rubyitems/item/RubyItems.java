package solamiri.rubyitems.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import solamiri.rubyitems.RubyItemsMain;

public class RubyItems{
 
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings().group(RubyItemsGroup.RUBY)));
  
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(RubyItemsMain.MOD_ID,name),item);
    }


    public static void registerRubyItemsItems(){
        System.out.println("Registering RubyItem Items for " + RubyItemsMain.MOD_ID);
    }

    

}
