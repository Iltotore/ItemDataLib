# ItemDataLib
A simple Spigot librairy to store metadata in ItemStack (1.8+). Supports 1.14's PersistentDataContainer !

## Description
ItemDataLib is a Spigot (Java) librairy that store metadata in ItemStack. Data are persistent even after the server restart.

### How it works
ItemDataLib use two differents ways to store metadata. The better way will be chose by the librairy itself depending of the server version.

| Version | Storing Process | Result |
|--|--|--|
| 1.14 | Use of 1.14's PersistentDataContainer | Metadata are invisible |
| 1.8-1.13.2 | Use of the item's lore | A new line in the lore with a label (Default: "&9+Custom Data") ![](http://image.noelshack.com/fichiers/2019/34/1/1566211724-2019-08-14-10-22-41.png)Note: Due to a Minecraft limitation, this method doesn't support big String. Be aware !|
