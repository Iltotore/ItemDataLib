package fr.il_totore.itemdata;

import fr.il_totore.itemdata.util.ServerVersion;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TreeMap;
import java.util.function.BiFunction;

public class CustomMetaFactory {

    private static TreeMap<ServerVersion, BiFunction<CustomMetaFactory, ItemMeta, CustomItemMeta>> functionMap = new TreeMap<>(ServerVersion::compareTo);

    static {
        functionMap.put(ServerVersion.v1_8, (metaFactory, meta) -> new LegacyCustomItemMeta(meta));
        functionMap.put(ServerVersion.v1_14, (metaFactory, meta) -> new NewCustomItemMeta(metaFactory.getJavaPlugin(), meta));
    }

    private JavaPlugin javaPlugin;
    private BiFunction<CustomMetaFactory, ItemMeta, CustomItemMeta> biFunction;

    public CustomMetaFactory(JavaPlugin javaPlugin){
        this.javaPlugin = javaPlugin;
        this.biFunction = functionMap.floorEntry(ServerVersion.fromServer(javaPlugin.getServer())).getValue();
    }

    public JavaPlugin getJavaPlugin(){
        return javaPlugin;
    }

    public void setJavaPlugin(JavaPlugin javaPlugin){
        this.javaPlugin = javaPlugin;
    }

    public CustomItemMeta createCustomMeta(ItemMeta meta){
        return biFunction.apply(this, meta);
    }
}
