# ItemDataLib
A simple Spigot library to store metadata in ItemStack (1.8+). Supports 1.14's PersistentDataContainer !

## Description
ItemDataLib is a Spigot (Java) library which store metadata in ItemStack. Data is persistent even after the server restart.

### How it works
ItemDataLib uses two differents ways to store metadata. The better way will be chosen by the library itself depending of the server version.

| Version | Storing Process | Result |
|--|--|--|
| 1.14+ | Use of 1.14's PersistentDataContainer | Metadata is invisible |
| 1.8-1.13.2 | Use of the item's lore | A new line in the lore with a label (Default: "&9+Custom Data") ![](http://image.noelshack.com/fichiers/2019/34/1/1566211724-2019-08-14-10-22-41.png)Note: Due to a Minecraft limitation, this method doesn't support big String. For big metadata use a Map and store the key|
## Add to project
This library can be added to your buildpath locally using your IDE (Eclipse: Java Build Path → Depedencies, IntelliJ IDEA: Project Structure → Librairies)

You can also use Maven or Gradle to add this library.

[Jitpack](https://jitpack.io/#Iltotore/ItemDataLib) will be used in these examples.

Maven:
- Repository:
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
- Dependency:
```xml
<dependency>
	<groupId>com.github.Iltotore</groupId>
	<artifactId>ItemDataLib</artifactId>
	<version>Tag</version>
</dependency>
```

Gradle:
- Repository
```groovy
repositories {
	...
	maven { url 'https://jitpack.io' }
}
```
- Dependency
```groovy
dependencies {
	implementation 'com.github.Iltotore:ItemDataLib:Tag'
}
```
## Usage
### Step 1: Create a new instance of CustomMetaFactory
The CustomMetaFactory's constructor require a JavaPlugin.
This is an example in a JavaPlugin's method.
```java
public void onEnable(){
	customMetaFactory = new CustomMetaFactory(this);
}
```
### Step 2: Create a new CustomItemMeta
You need to create a CustomItemMeta to edit/read ItemStack's metadata.
To create a new CustomItemMeta, use the method CustomMetaFactory#createCustomMeta(ItemMeta) where the parameter is the ItemMeta to edit.
```java
ItemStack item = new ItemStack(Material.STONE);
ItemMeta meta = item.getItemMeta();
CustomItemMeta customMeta = customMetaFactory.createCustomMeta(meta);
```

### Step 3: Usage of CustomItemMeta
Supported data:
primitives, String, UUID
- Reading
To read metadata of a CustomItemMeta, call these methods:

| Type | Method |
|--|--|
| Primitives | get{PrimitiveName}(String) |
| String | getString(String) |
| UUID | getUniqueId(String) |

- Editing
To Edit CustomItemMeta's metadata, use these methods:

| Type | Method |
|--|--|
| Primitives | set{PrimitiveName}(String) |
| String | setString(String) |
| UUID | setUniqueId(String) |

Note: **Don't forget to use CustomItemMeta#updateMeta to apply changes !**
You can reset changes by using CustomItemMeta#updateMap
