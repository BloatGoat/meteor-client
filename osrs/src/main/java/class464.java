import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("qv")
final class class464 implements class459 {
    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "(Ljava/lang/Object;Lrd;B)V",
            garbageValue = "10"
    )
    public void vmethod8274(Object var1, Buffer var2) {
      this.method2345((String)var1, var2);
   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(Lrd;B)Ljava/lang/Object;",
            garbageValue = "4"
    )
    public Object vmethod8273(Buffer var1) {
      return var1.readStringCp1252NullTerminated();
   }

   @ObfuscatedName("i")
   @ObfuscatedSignature(
      descriptor = "(Ljava/lang/String;Lrd;I)V",
      garbageValue = "220138400"
   )
   void method2345(String var1, Buffer var2) {
      var2.writeStringCp1252NullTerminated(var1);
   }
}
