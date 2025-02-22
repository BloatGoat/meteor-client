import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("bm")
@Implements("AudioFilter")
public class AudioFilter {
   @ObfuscatedName("j")
   static float[][] field313 = new float[2][8];
    @ObfuscatedName("i")
    static int[][] coefficients = new int[2][8];
   @ObfuscatedName("n")
   static float field310;
    @ObfuscatedName("l")
    static int forwardMultiplier;
    @ObfuscatedName("f")
    int[] pairs = new int[2];
   @ObfuscatedName("v")
   int[][][] field311 = new int[2][2][4];
   @ObfuscatedName("s")
   int[][][] field309 = new int[2][2][4];
   @ObfuscatedName("z")
   int[] field312 = new int[2];

   @ObfuscatedName("f")
   float method310(int var1, int var2, float var3) {
      float var4 = (float)this.field309[var1][0][var2] + var3 * (float)(this.field309[var1][1][var2] - this.field309[var1][0][var2]);
      var4 *= 0.0015258789F;
      return 1.0F - (float)Math.pow(10.0D, (double)(-var4 / 20.0F));
   }

   @ObfuscatedName("v")
   float method313(int var1, int var2, float var3) {
      float var4 = (float)this.field311[var1][0][var2] + var3 * (float)(this.field311[var1][1][var2] - this.field311[var1][0][var2]);
      var4 *= 1.2207031E-4F;
      return normalize(var4);
   }

    @ObfuscatedName("s")
    int compute(int var1, float var2) {
      float var3;
      if (var1 == 0) {
         var3 = (float)this.field312[0] + (float)(this.field312[1] - this.field312[0]) * var2;
         var3 *= 0.0030517578F;
         field310 = (float)Math.pow(0.1D, (double)(var3 / 20.0F));
         forwardMultiplier = (int)(field310 * 65536.0F);
      }

      if (this.pairs[var1] == 0) {
         return 0;
      } else {
         var3 = this.method310(var1, 0, var2);
         field313[var1][0] = var3 * -2.0F * (float)Math.cos((double)this.method313(var1, 0, var2));
         field313[var1][1] = var3 * var3;

         int var4;
         for(var4 = 1; var4 < this.pairs[var1]; ++var4) {
            var3 = this.method310(var1, var4, var2);
            float var5 = var3 * -2.0F * (float)Math.cos((double)this.method313(var1, var4, var2));
            float var6 = var3 * var3;
            field313[var1][var4 * 2 + 1] = field313[var1][var4 * 2 - 1] * var6;
            field313[var1][var4 * 2] = field313[var1][var4 * 2 - 1] * var5 + field313[var1][var4 * 2 - 2] * var6;

            for(int var7 = var4 * 2 - 1; var7 >= 2; --var7) {
               field313[var1][var7] += field313[var1][var7 - 1] * var5 + field313[var1][var7 - 2] * var6;
            }

            field313[var1][1] += field313[var1][0] * var5 + var6;
            field313[var1][0] += var5;
         }

         if (var1 == 0) {
            for(var4 = 0; var4 < this.pairs[0] * 2; ++var4) {
               field313[0][var4] *= field310;
            }
         }

         for(var4 = 0; var4 < this.pairs[var1] * 2; ++var4) {
            coefficients[var1][var4] = (int)(field313[var1][var4] * 65536.0F);
         }

         return this.pairs[var1] * 2;
      }
   }

   @ObfuscatedName("z")
   @ObfuscatedSignature(
      descriptor = "(Lrd;Lau;)V"
   )
   final void method314(Buffer var1, SoundEnvelope var2) {
      int var3 = var1.readUnsignedByte();
      this.pairs[0] = var3 >> 4;
      this.pairs[1] = var3 & 15;
      if (var3 != 0) {
         this.field312[0] = var1.readUnsignedShort();
         this.field312[1] = var1.readUnsignedShort();
         int var4 = var1.readUnsignedByte();

         int var5;
         int var6;
         for(var5 = 0; var5 < 2; ++var5) {
            for(var6 = 0; var6 < this.pairs[var5]; ++var6) {
               this.field311[var5][0][var6] = var1.readUnsignedShort();
               this.field309[var5][0][var6] = var1.readUnsignedShort();
            }
         }

         for(var5 = 0; var5 < 2; ++var5) {
            for(var6 = 0; var6 < this.pairs[var5]; ++var6) {
               if ((var4 & 1 << var5 * 4 << var6) != 0) {
                  this.field311[var5][1][var6] = var1.readUnsignedShort();
                  this.field309[var5][1][var6] = var1.readUnsignedShort();
               } else {
                  this.field311[var5][1][var6] = this.field311[var5][0][var6];
                  this.field309[var5][1][var6] = this.field309[var5][0][var6];
               }
            }
         }

         if (var4 != 0 || this.field312[1] != this.field312[0]) {
            var2.decodeSegments(var1);
         }
      } else {
         int[] var7 = this.field312;
         this.field312[1] = 0;
         var7[0] = 0;
      }

   }

    @ObfuscatedName("w")
    static float normalize(float var0) {
      float var1 = 32.703197F * (float)Math.pow(2.0D, (double)var0);
      return var1 * 3.1415927F / 11025.0F;
   }
}
