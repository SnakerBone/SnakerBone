package bytesnek.tq.rego;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import bytesnek.tq.Tourniqueted;

/**
 * Created by SnakerBone on 15/08/2023
 **/
public class Tabs
{
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tourniqueted.MODID);

    public static final RegistryObject<CreativeModeTab> ITEMS = register("items", () -> Items.ITEM_TAB_DISPLAY.get().getDefaultInstance());
    public static final RegistryObject<CreativeModeTab> BLOCKS = register("blocks", () -> Items.BLOCK_TAB_DISPLAY.get().getDefaultInstance());
    public static final RegistryObject<CreativeModeTab> MOBS = register("mobs", () -> Items.ENTITY_TAB_DISPLAY.get().getDefaultInstance());

    static RegistryObject<CreativeModeTab> register(String name, Supplier<ItemStack> icon)
    {
        return REGISTER.register(name.toLowerCase() + "_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + name)).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(icon).build());
    }
}