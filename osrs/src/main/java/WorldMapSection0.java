import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ir")
@Implements("WorldMapSection0")
public class WorldMapSection0 implements WorldMapSection {
    @ObfuscatedName("f")
    int oldZ;
    @ObfuscatedName("w")
    int newZ;
    @ObfuscatedName("v")
    int oldX;
    @ObfuscatedName("s")
    int oldY;
    @ObfuscatedName("z")
    int newX;
    @ObfuscatedName("j")
    int newY;
    @ObfuscatedName("i")
    int oldChunkXLow;
    @ObfuscatedName("n")
    int oldChunkYLow;
    @ObfuscatedName("l")
    int oldChunkXHigh;
    @ObfuscatedName("k")
    int oldChunkYHigh;
    @ObfuscatedName("c")
    int newChunkXLow;
    @ObfuscatedName("r")
    int newChunkYLow;
    @ObfuscatedName("b")
    int newChunkXHigh;
    @ObfuscatedName("m")
    int newChunkYHigh;

    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "(Lii;I)V",
            garbageValue = "1606915966"
    )
    public void expandBounds(WorldMapArea var1) {
      if (var1.regionLowX > this.newX) {
         var1.regionLowX = this.newX;
      }

      if (var1.regionHighX < this.newX) {
         var1.regionHighX = this.newX;
      }

      if (var1.regionLowY > this.newY) {
         var1.regionLowY = this.newY;
      }

      if (var1.regionHighY < this.newY) {
         var1.regionHighY = this.newY;
      }

   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(IIII)Z",
            garbageValue = "1059252076"
    )
    public boolean containsCoord(int var1, int var2, int var3) {
      if (var1 >= this.oldZ && var1 < this.newZ + this.oldZ) {
         return var2 >= (this.oldX << 6) + (this.oldChunkXLow << 3) && var2 <= (this.oldX << 6) + (this.oldChunkXHigh << 3) + 7 && var3 >= (this.oldY << 6) + (this.oldChunkYLow << 3) && var3 <= (this.oldY << 6) + (this.oldChunkYHigh << 3) + 7;
      } else {
         return false;
      }
   }

    @ObfuscatedName("v")
    @ObfuscatedSignature(
            descriptor = "(III)Z",
            garbageValue = "1209851870"
    )
    public boolean containsPosition(int var1, int var2) {
      return var1 >= (this.newX << 6) + (this.newChunkXLow << 3) && var1 <= (this.newX << 6) + (this.newChunkXHigh << 3) + 7 && var2 >= (this.newY << 6) + (this.newChunkYLow << 3) && var2 <= (this.newY << 6) + (this.newChunkYHigh << 3) + 7;
   }

    @ObfuscatedName("s")
    @ObfuscatedSignature(
            descriptor = "(IIII)[I",
            garbageValue = "-1188894867"
    )
    public int[] getBorderTileLengths(int var1, int var2, int var3) {
      if (!this.containsCoord(var1, var2, var3)) {
         return null;
      } else {
         int[] var4 = new int[]{var2 + (this.newX * 64 - this.oldX * 64) + (this.newChunkXLow * 8 - this.oldChunkXLow * 8), var3 + (this.newY * 64 - this.oldY * 64) + (this.newChunkYLow * 8 - this.oldChunkYLow * 8)};
         return var4;
      }
   }

    @ObfuscatedName("z")
    @ObfuscatedSignature(
            descriptor = "(III)Lko;",
            garbageValue = "-1946236139"
    )
    public Coord coord(int var1, int var2) {
      if (!this.containsPosition(var1, var2)) {
         return null;
      } else {
         int var3 = this.oldX * 64 - this.newX * 64 + (this.oldChunkXLow * 8 - this.newChunkXLow * 8) + var1;
         int var4 = var2 + (this.oldY * 64 - this.newY * 64) + (this.oldChunkYLow * 8 - this.newChunkYLow * 8);
         return new Coord(this.oldZ, var3, var4);
      }
   }

    @ObfuscatedName("j")
    @ObfuscatedSignature(
            descriptor = "(Lrd;I)V",
            garbageValue = "158933591"
    )
    public void read(Buffer var1) {
      this.oldZ = var1.readUnsignedByte();
      this.newZ = var1.readUnsignedByte();
      this.oldX = var1.readUnsignedShort();
      this.oldChunkXLow = var1.readUnsignedByte();
      this.oldChunkXHigh = var1.readUnsignedByte();
      this.oldY = var1.readUnsignedShort();
      this.oldChunkYLow = var1.readUnsignedByte();
      this.oldChunkYHigh = var1.readUnsignedByte();
      this.newX = var1.readUnsignedShort();
      this.newChunkXLow = var1.readUnsignedByte();
      this.newChunkXHigh = var1.readUnsignedByte();
      this.newY = var1.readUnsignedShort();
      this.newChunkYLow = var1.readUnsignedByte();
      this.newChunkYHigh = var1.readUnsignedByte();
      this.postRead();
   }

    @ObfuscatedName("i")
    @ObfuscatedSignature(
            descriptor = "(I)V",
            garbageValue = "808293169"
    )
    void postRead() {
   }
}
