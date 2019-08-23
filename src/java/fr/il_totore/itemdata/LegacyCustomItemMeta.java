package fr.il_totore.itemdata;

import fr.il_totore.itemdata.util.HiddenStringUtil;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class LegacyCustomItemMeta implements CustomItemMeta {


    private static final String SEQUENCE_HEADER = "CUSTOMMETAHEADER";
    private static final String SEQUENCE_FOOTER = "CUSTOMMETAFOOTER";
    private static final String SEQUENCE_SEPARATOR = "CUSTOMMETASEPARATOR";
    private static final String SEQUENCE_EQUALS_KEY = "CUSTOMMETAEQUALS";

    private ItemMeta meta;
    private Map<String, String> map = new HashMap<>();

    protected LegacyCustomItemMeta(ItemMeta meta){
        this.meta = meta;
        updateMap();
    }

    public void updateMeta(String label){
        List<String> lore = meta.getLore() == null ? new ArrayList<>() : meta.getLore();
        lore.removeIf(line -> HiddenStringUtil.hasHiddenString(line) && HiddenStringUtil.extractHiddenString(line).startsWith(SEQUENCE_HEADER));

        String data = SEQUENCE_HEADER;
        for(Map.Entry<String, String> entry : map.entrySet()){
            data = data + entry.getKey() + SEQUENCE_EQUALS_KEY + entry.getValue() + SEQUENCE_SEPARATOR;
        }

        data = data.substring(0, data.length()-SEQUENCE_SEPARATOR.length()) + SEQUENCE_FOOTER;

        lore.add(lore.isEmpty() ? 0 : lore.size()-1, HiddenStringUtil.encodeString(data) + label);
        meta.setLore(lore);
    }

    public void updateMap(){
        map.clear();
        System.out.println("hasLore" + meta.hasLore());
        for(String line : meta.getLore() == null ? new ArrayList<String>() : meta.getLore()){
            if(!HiddenStringUtil.hasHiddenString(line)) continue;
            if(HiddenStringUtil.extractHiddenString(line).startsWith(SEQUENCE_HEADER)){
                String extracted = HiddenStringUtil.extractHiddenString(line).substring(SEQUENCE_HEADER.length()).split(SEQUENCE_FOOTER)[0];
                for(String string : extracted.split(SEQUENCE_SEPARATOR)){
                    String[] data = string.split(SEQUENCE_EQUALS_KEY);
                    if(data.length < 2) continue;
                    map.put(data[0], data[1]);
                }
                break;
            }
        }
    }

    public ItemMeta getItemMeta(){
        return meta;
    }

    public boolean containsKey(String key){
        return map.containsKey(key);
    }

    public void remove(String key){
        map.remove(key);
    }

    public String getString(String key){
        return map.get(key);
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
        map.put(key, value);
    }

    public void setInt(String key, int value){
        map.put(key, value + "");
    }

    public void setDouble(String key, double value){
        map.put(key, value + "");
    }

    public void setFloat(String key, float value){
        map.put(key, value + "");
    }

    public void setLong(String key, long value){
        map.put(key, value + "");
    }

    public void setShort(String key, short value){
        map.put(key, value + "");
    }

    public void setByte(String key, byte value){
        map.put(key, value + "");
    }

    public void setBoolean(String key, boolean value){
        map.put(key, value + "");
    }

    public void setUniqueId(String key, UUID value){
        map.put(key, value.toString());
    }
}
