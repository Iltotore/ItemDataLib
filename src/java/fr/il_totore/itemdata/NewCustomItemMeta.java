package fr.il_totore.itemdata;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class NewCustomItemMeta implements CustomItemMeta{

    private JavaPlugin javaPlugin;
    private ItemMeta meta;

    public NewCustomItemMeta(JavaPlugin javaPlugin, ItemMeta meta){
        Preconditions.checkNotNull(javaPlugin);
        Preconditions.checkNotNull(meta);
        this.javaPlugin = javaPlugin;
        this.meta = meta;
    }

    public void updateMeta(String label){
        //Only <1.14 Versions
    }

    public void updateMap(){
        //Only <1.14 Versions
    }

    public ItemMeta getItemMeta(){
        return meta;
    }

    public boolean containsKey(String key){
        return meta.getPersistentDataContainer().has(new NamespacedKey(javaPlugin, key), PersistentDataType.STRING);
    }

    public void remove(String key){
        meta.getPersistentDataContainer().remove(new NamespacedKey(javaPlugin, key));
    }

    public String getString(String key){
        return meta.getPersistentDataContainer().get(new NamespacedKey(javaPlugin, key), PersistentDataType.STRING);
    }

    public int getInt(String key){
        return Integer.parseInt(getString(key));
    }

    public double getDouble(String key){
        return Double.parseDouble(getString(key));
    }

    public float getFloat(String key){
        return Float.parseFloat(getString(key));
    }

    public long getLong(String key){
        return Long.parseLong(getString(key));
    }

    public short getShort(String key){
        return Short.parseShort(getString(key));
    }

    public byte getByte(String key){
        return Byte.parseByte(getString(key));
    }

    public boolean getBoolean(String key){
        return Boolean.parseBoolean(getString(key));
    }

    public UUID getUniqueId(String key){
        return UUID.fromString(getString(key));
    }

    public void setString(String key, String value){
        Preconditions.checkNotNull(value);
        meta.getPersistentDataContainer().set(new NamespacedKey(javaPlugin, key), PersistentDataType.STRING, value);
    }

    public void setInt(String key, int value){
        setString(key, value + "");
    }

    public void setDouble(String key, double value){
        setString(key, value + "");
    }

    public void setFloat(String key, float value){
        setString(key, value + "");
    }

    public void setLong(String key, long value){
        setString(key, value + "");
    }

    public void setShort(String key, short value){
        setString(key, value + "");
    }

    public void setByte(String key, byte value){
        setString(key, value + "");
    }

    public void setBoolean(String key, boolean value){
        setString(key, value + "");
    }

    public void setUniqueId(String key, UUID value){
        setString(key, value.toString());
    }
}
