package solamiri.rubyitems.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import solamiri.rubyitems.RubyItemsMain;

public class RubyItemGroup {
    public static final ItemGroup RUBY = FabricItemGroupBuilder.build(new Identifier(RubyItemsMain.MOD_ID,"ruby"),
    () -> new ItemStack(RubyItems.RUBY));
}
