import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("z")
public final class class4 {
    @ObfuscatedName("j")
    @ObfuscatedSignature(
            descriptor = "Lln;"
    )
    public static AbstractArchive ItemDefinition_archive;
    @ObfuscatedName("ib")
    static byte[][] regionLandArchives;

    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "([Lmb;II)Lmb;",
            garbageValue = "1096060078"
    )
    public static class349 findEnumerated(class349[] var0, int var1) {
      class349[] var2 = var0;

      for(int var3 = 0; var3 < var2.length; ++var3) {
         class349 var4 = var2[var3];
         if (var1 == var4.rsOrdinal()) {
            return var4;
         }
      }

      return null;
   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(II)Lgk;",
            garbageValue = "1400820827"
    )
    public static WorldMapElement WorldMapElement_get(int var0) {
      return var0 >= 0 && var0 < GrandExchangeOfferNameComparator.WorldMapElement_cached.length && GrandExchangeOfferNameComparator.WorldMapElement_cached[var0] != null ? GrandExchangeOfferNameComparator.WorldMapElement_cached[var0] : new WorldMapElement(var0);
   }

   @ObfuscatedName("z")
   @ObfuscatedSignature(
      descriptor = "(IB)I",
      garbageValue = "103"
   )
   public static int method5(int var0) {
      --var0;
      var0 |= var0 >>> 1;
      var0 |= var0 >>> 2;
      var0 |= var0 >>> 4;
      var0 |= var0 >>> 8;
      var0 |= var0 >>> 16;
      return var0 + 1;
   }

   @ObfuscatedName("b")
   @ObfuscatedSignature(
      descriptor = "(IIII)I",
      garbageValue = "-669376956"
   )
   static final int method4(int var0, int var1, int var2) {
      int var3 = var0 / var2;
      int var4 = var0 & var2 - 1;
      int var5 = var1 / var2;
      int var6 = var1 & var2 - 1;
      int var7 = Message.method333(var3, var5);
      int var8 = Message.method333(var3 + 1, var5);
      int var9 = Message.method333(var3, var5 + 1);
      int var10 = Message.method333(var3 + 1, var5 + 1);
      int var11 = class123.method693(var7, var8, var4, var2);
      int var12 = class123.method693(var9, var10, var4, var2);
      return class123.method693(var11, var12, var6, var2);
   }

   @ObfuscatedName("as")
   @ObfuscatedSignature(
      descriptor = "(ILba;ZI)I",
      garbageValue = "1899720097"
   )
   static int method6(int var0, Script var1, boolean var2) {
      if (var0 == 7463) {
         boolean var3 = Interpreter.Interpreter_intStack[--class302.Interpreter_intStackSize] == 1;
         class194.method981(var3);
         return 1;
      } else {
         return 2;
      }
   }
}
