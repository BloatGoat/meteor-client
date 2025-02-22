import java.util.Iterator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("mc")
@Implements("IterableDualNodeQueue")
public class IterableDualNodeQueue implements Iterable {
    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "Lpi;"
    )
    public DualNode sentinel = new DualNode();
    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "Lpi;"
    )
    DualNode head;

   public IterableDualNodeQueue() {
      this.sentinel.previousDual = this.sentinel;
      this.sentinel.nextDual = this.sentinel;
   }

    @ObfuscatedName("f")
    public void clear() {
      while(this.sentinel.previousDual != this.sentinel) {
         this.sentinel.previousDual.removeDual();
      }

   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(Lpi;)V"
    )
    public void add(DualNode var1) {
      if (var1.nextDual != null) {
         var1.removeDual();
      }

      var1.nextDual = this.sentinel.nextDual;
      var1.previousDual = this.sentinel;
      var1.nextDual.previousDual = var1;
      var1.previousDual.nextDual = var1;
   }

    @ObfuscatedName("s")
    @ObfuscatedSignature(
            descriptor = "()Lpi;"
    )
    public DualNode removeLast() {
      DualNode var1 = this.sentinel.previousDual;
      if (var1 == this.sentinel) {
         return null;
      } else {
         var1.removeDual();
         return var1;
      }
   }

    @ObfuscatedName("z")
    @ObfuscatedSignature(
            descriptor = "()Lpi;"
    )
    public DualNode last() {
      return this.previousOrLast((DualNode)null);
   }

    @ObfuscatedName("j")
    @ObfuscatedSignature(
            descriptor = "(Lpi;)Lpi;"
    )
    DualNode previousOrLast(DualNode var1) {
      DualNode var2;
      if (var1 == null) {
         var2 = this.sentinel.previousDual;
      } else {
         var2 = var1;
      }

      if (var2 == this.sentinel) {
         this.head = null;
         return null;
      } else {
         this.head = var2.previousDual;
         return var2;
      }
   }

    @ObfuscatedName("i")
    @ObfuscatedSignature(
            descriptor = "()Lpi;"
    )
    public DualNode previous() {
      DualNode var1 = this.head;
      if (var1 == this.sentinel) {
         this.head = null;
         return null;
      } else {
         this.head = var1.previousDual;
         return var1;
      }
   }

    @ObfuscatedName("iterator")
    public Iterator iterator() {
      return new IterableDualNodeQueueIterator(this);
   }

    @ObfuscatedName("v")
    @ObfuscatedSignature(
            descriptor = "(Lpi;Lpi;)V"
    )
    public static void DualNodeDeque_addBefore(DualNode var0, DualNode var1) {
      if (var0.nextDual != null) {
         var0.removeDual();
      }

      var0.nextDual = var1;
      var0.previousDual = var1.previousDual;
      var0.nextDual.previousDual = var0;
      var0.previousDual.nextDual = var0;
   }
}
