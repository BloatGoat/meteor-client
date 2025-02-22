import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("fb")
@Implements("Clock")
public abstract class Clock {
    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "(I)V",
            garbageValue = "-1619845116"
    )
    public abstract void mark();

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(III)I",
            garbageValue = "1661663667"
    )
    public abstract int wait(int var1, int var2);

   @ObfuscatedName("f")
   public static final void method911(long var0) {
      if (var0 > 0L) {
         if (var0 % 10L == 0L) {
            long var2 = var0 - 1L;

            try {
               Thread.sleep(var2);
            } catch (InterruptedException var8) {
               ;
            }

            try {
               Thread.sleep(1L);
            } catch (InterruptedException var7) {
               ;
            }
         } else {
            try {
               Thread.sleep(var0);
            } catch (InterruptedException var6) {
               ;
            }
         }

      }
   }

   @ObfuscatedName("ms")
   @ObfuscatedSignature(
      descriptor = "(IB)Lqc;",
      garbageValue = "-1"
   )
   static class467 method910(int var0) {
      class467 var1 = (class467)Client.Widget_cachedFonts.get((long)var0);
      if (var1 == null) {
         var1 = new class467(Canvas.field75, var0);
      }

      return var1;
   }
}
