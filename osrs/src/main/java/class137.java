import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("es")
public class class137 extends class139 {
   @ObfuscatedName("r")
   static int field1297;
   @ObfuscatedName("f")
   long field1298;
   @ObfuscatedName("w")
   String field1296;
   // $FF: synthetic field
   @ObfuscatedSignature(
      descriptor = "Lex;"
   )
   final class142 this$0;

   @ObfuscatedSignature(
      descriptor = "(Lex;)V"
   )
   class137(class142 var1) {
      this.this$0 = var1;
      this.field1298 = -1L;
      this.field1296 = null;
   }

    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "(Lrd;S)V",
            garbageValue = "-5782"
    )
    void vmethod3254(Buffer var1) {
      if (var1.readUnsignedByte() != 255) {
         --var1.offset;
         this.field1298 = var1.readLong();
      }

      this.field1296 = var1.readStringCp1252NullTerminatedOrNull();
   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(Leb;B)V",
            garbageValue = "-26"
    )
    void vmethod3248(ClanSettings var1) {
      var1.method792(this.field1298, this.field1296, 0);
   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(IS)Lgo;",
            garbageValue = "-3663"
    )
    public static NPCComposition getNpcDefinition(int var0) {
      NPCComposition var1 = (NPCComposition)NPCComposition.NpcDefinition_cached.get((long)var0);
      if (var1 != null) {
         return var1;
      } else {
         byte[] var2 = NPCComposition.NpcDefinition_archive.takeFile(9, var0);
         var1 = new NPCComposition();
         var1.id = var0;
         if (var2 != null) {
            var1.decode(new Buffer(var2));
         }

         var1.postDecode();
         NPCComposition.NpcDefinition_cached.put(var1, (long)var0);
         return var1;
      }
   }

    @ObfuscatedName("z")
    @ObfuscatedSignature(
            descriptor = "(Lru;II)V",
            garbageValue = "-1171621468"
    )
    static void readPlayerUpdate(PacketBuffer var0, int var1) {
      boolean var2 = var0.readBits(1) == 1;
      if (var2) {
         Players.Players_pendingUpdateIndices[++Players.Players_pendingUpdateCount - 1] = var1;
      }

      int var3 = var0.readBits(2);
      Player var4 = Client.players[var1];
      if (var3 == 0) {
         if (var2) {
            var4.hasMovementPending = false;
         } else if (Client.localPlayerIndex == var1) {
            throw new RuntimeException();
         } else {
            Players.Players_regions[var1] = (var4.plane << 28) + (ParamComposition.baseX + var4.pathX[0] >> 13 << 14) + (Client.baseY + var4.pathY[0] >> 13);
            if (var4.movingOrientation != -1) {
               Players.Players_orientations[var1] = var4.movingOrientation;
            } else {
               Players.Players_orientations[var1] = var4.orientation;
            }

            Players.Players_targetIndices[var1] = var4.targetIndex;
            Client.players[var1] = null;
            if (var0.readBits(1) != 0) {
               class285.updateExternalPlayer(var0, var1);
            }

         }
      } else {
         int var5;
         int var6;
         int var7;
         if (var3 == 1) {
            var5 = var0.readBits(3);
            var6 = var4.pathX[0];
            var7 = var4.pathY[0];
            if (var5 == 0) {
               --var6;
               --var7;
            } else if (var5 == 1) {
               --var7;
            } else if (var5 == 2) {
               ++var6;
               --var7;
            } else if (var5 == 3) {
               --var6;
            } else if (var5 == 4) {
               ++var6;
            } else if (var5 == 5) {
               --var6;
               ++var7;
            } else if (var5 == 6) {
               ++var7;
            } else if (var5 == 7) {
               ++var6;
               ++var7;
            }

            if (Client.localPlayerIndex != var1 || var4.x >= 1536 && var4.y >= 1536 && var4.x < 11776 && var4.y < 11776) {
               if (var2) {
                  var4.hasMovementPending = true;
                  var4.tileX = var6;
                  var4.tileY = var7;
               } else {
                  var4.hasMovementPending = false;
                  var4.move(var6, var7, Players.playerMovementSpeeds[var1]);
               }
            } else {
               var4.resetPath(var6, var7);
               var4.hasMovementPending = false;
            }

         } else if (var3 == 2) {
            var5 = var0.readBits(4);
            var6 = var4.pathX[0];
            var7 = var4.pathY[0];
            if (var5 == 0) {
               var6 -= 2;
               var7 -= 2;
            } else if (var5 == 1) {
               --var6;
               var7 -= 2;
            } else if (var5 == 2) {
               var7 -= 2;
            } else if (var5 == 3) {
               ++var6;
               var7 -= 2;
            } else if (var5 == 4) {
               var6 += 2;
               var7 -= 2;
            } else if (var5 == 5) {
               var6 -= 2;
               --var7;
            } else if (var5 == 6) {
               var6 += 2;
               --var7;
            } else if (var5 == 7) {
               var6 -= 2;
            } else if (var5 == 8) {
               var6 += 2;
            } else if (var5 == 9) {
               var6 -= 2;
               ++var7;
            } else if (var5 == 10) {
               var6 += 2;
               ++var7;
            } else if (var5 == 11) {
               var6 -= 2;
               var7 += 2;
            } else if (var5 == 12) {
               --var6;
               var7 += 2;
            } else if (var5 == 13) {
               var7 += 2;
            } else if (var5 == 14) {
               ++var6;
               var7 += 2;
            } else if (var5 == 15) {
               var6 += 2;
               var7 += 2;
            }

            if (Client.localPlayerIndex != var1 || var4.x >= 1536 && var4.y >= 1536 && var4.x < 11776 && var4.y < 11776) {
               if (var2) {
                  var4.hasMovementPending = true;
                  var4.tileX = var6;
                  var4.tileY = var7;
               } else {
                  var4.hasMovementPending = false;
                  var4.move(var6, var7, Players.playerMovementSpeeds[var1]);
               }
            } else {
               var4.resetPath(var6, var7);
               var4.hasMovementPending = false;
            }

         } else {
            var5 = var0.readBits(1);
            int var8;
            int var9;
            int var10;
            int var11;
            if (var5 == 0) {
               var6 = var0.readBits(12);
               var7 = var6 >> 10;
               var8 = var6 >> 5 & 31;
               if (var8 > 15) {
                  var8 -= 32;
               }

               var9 = var6 & 31;
               if (var9 > 15) {
                  var9 -= 32;
               }

               var10 = var8 + var4.pathX[0];
               var11 = var9 + var4.pathY[0];
               if (Client.localPlayerIndex != var1 || var4.x >= 1536 && var4.y >= 1536 && var4.x < 11776 && var4.y < 11776) {
                  if (var2) {
                     var4.hasMovementPending = true;
                     var4.tileX = var10;
                     var4.tileY = var11;
                  } else {
                     var4.hasMovementPending = false;
                     var4.move(var10, var11, Players.playerMovementSpeeds[var1]);
                  }
               } else {
                  var4.resetPath(var10, var11);
                  var4.hasMovementPending = false;
               }

               var4.plane = (byte)(var7 + var4.plane & 3);
               if (Client.localPlayerIndex == var1) {
                  class103.Client_plane = var4.plane;
               }

            } else {
               var6 = var0.readBits(30);
               var7 = var6 >> 28;
               var8 = var6 >> 14 & 16383;
               var9 = var6 & 16383;
               var10 = (var8 + ParamComposition.baseX + var4.pathX[0] & 16383) - ParamComposition.baseX;
               var11 = (var9 + Client.baseY + var4.pathY[0] & 16383) - Client.baseY;
               if (Client.localPlayerIndex != var1 || var4.x >= 1536 && var4.y >= 1536 && var4.x < 11776 && var4.y < 11776) {
                  if (var2) {
                     var4.hasMovementPending = true;
                     var4.tileX = var10;
                     var4.tileY = var11;
                  } else {
                     var4.hasMovementPending = false;
                     var4.move(var10, var11, Players.playerMovementSpeeds[var1]);
                  }
               } else {
                  var4.resetPath(var10, var11);
                  var4.hasMovementPending = false;
               }

               var4.plane = (byte)(var7 + var4.plane & 3);
               if (Client.localPlayerIndex == var1) {
                  class103.Client_plane = var4.plane;
               }

            }
         }
      }
   }
}
