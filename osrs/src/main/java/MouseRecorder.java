import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("cc")
@Implements("MouseRecorder")
public class MouseRecorder implements Runnable {
    @ObfuscatedName("f")
    boolean isRunning = true;
    @ObfuscatedName("w")
    Object lock = new Object();
    @ObfuscatedName("v")
    int index = 0;
    @ObfuscatedName("s")
    int[] xs = new int[500];
    @ObfuscatedName("z")
    int[] ys = new int[500];
    @ObfuscatedName("j")
    long[] millis = new long[500];

    @ObfuscatedName("run")
    public void run() {
      for(; this.isRunning; Clock.method911(50L)) {
         Object var1 = this.lock;
         synchronized(this.lock) {
            if (this.index < 500) {
               this.xs[this.index] = MouseHandler.MouseHandler_x;
               this.ys[this.index] = MouseHandler.MouseHandler_y;
               this.millis[this.index] = MouseHandler.MouseHandler_millis;
               ++this.index;
            }
         }
      }

   }

    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "(II)Lgg;",
            garbageValue = "-2134315347"
    )
    public static SpotAnimationDefinition SpotAnimationDefinition_get(int var0) {
      SpotAnimationDefinition var1 = (SpotAnimationDefinition)SpotAnimationDefinition.SpotAnimationDefinition_cached.get((long)var0);
      if (var1 != null) {
         return var1;
      } else {
         byte[] var2 = SpotAnimationDefinition.SpotAnimationDefinition_archive.takeFile(13, var0);
         var1 = new SpotAnimationDefinition();
         var1.id = var0;
         if (var2 != null) {
            var1.decode(new Buffer(var2));
         }

         SpotAnimationDefinition.SpotAnimationDefinition_cached.put(var1, (long)var0);
         return var1;
      }
   }

   @ObfuscatedName("av")
   @ObfuscatedSignature(
      descriptor = "(IB)I",
      garbageValue = "-83"
   )
   static int method465(int var0) {
      return (int)((Math.log((double)var0) / Interpreter.field713 - 7.0D) * 256.0D);
   }

    @ObfuscatedName("jb")
    @ObfuscatedSignature(
            descriptor = "(IIIII)V",
            garbageValue = "-795941365"
    )
    static final void addSceneMenuOptions(int var0, int var1, int var2, int var3) {
      if (Client.isItemSelected == 0 && !Client.isSpellSelected) {
         WorldMapManager.insertMenuItemNoShift("Walk here", "", 23, 0, var0 - var2, var1 - var3);
      }

      long var4 = -1L;
      long var6 = -1L;
      int var8 = 0;

      while(true) {
         int var10 = ViewportMouse.ViewportMouse_entityCount;
         if (var8 >= var10) {
            if (var4 != -1L) {
               var8 = WallDecoration.method1305(var4);
               int var9 = class140.method751(var4);
               Player var25 = Client.players[Client.combatTargetPlayerIndex];
               BufferedSource.addPlayerToMenu(var25, Client.combatTargetPlayerIndex, var8, var9);
            }

            return;
         }

         long var11 = VertexNormal.method1298(var8);
         if (var6 != var11) {
            label309: {
               var6 = var11;
               int var13 = Players.method600(var8);
               int var14 = WorldMapElement.method928(var8);
               int var15 = class121.method672(ViewportMouse.ViewportMouse_entityTags[var8]);
               int var17 = class124.Entity_unpackID(ViewportMouse.ViewportMouse_entityTags[var8]);
               int var18 = var17;
               int var22;
               if (var15 == 2 && WorldMapAreaData.scene.getObjectFlags(class103.Client_plane, var13, var14, var11) >= 0) {
                  ObjectComposition var19 = class463.getObjectDefinition(var17);
                  if (var19.transforms != null) {
                     var19 = var19.transform();
                  }

                  if (var19 == null) {
                     break label309;
                  }

                  PendingSpawn var20 = null;

                  for(PendingSpawn var21 = (PendingSpawn)Client.pendingSpawns.last(); var21 != null; var21 = (PendingSpawn)Client.pendingSpawns.previous()) {
                     if (var21.plane == class103.Client_plane && var13 == var21.x && var14 == var21.y && var18 == var21.id) {
                        var20 = var21;
                        break;
                     }
                  }

                  if (Client.isItemSelected == 1) {
                     WorldMapManager.insertMenuItemNoShift("Use", Client.selectedItemName + " " + "->" + " " + class149.colorStartTag(65535) + var19.name, 1, var18, var13, var14);
                  } else if (Client.isSpellSelected) {
                     if ((class149.selectedSpellFlags & 4) == 4) {
                        WorldMapManager.insertMenuItemNoShift(Client.selectedSpellActionName, Client.selectedSpellName + " " + "->" + " " + class149.colorStartTag(65535) + var19.name, 2, var18, var13, var14);
                     }
                  } else {
                     String[] var33 = var19.actions;
                     if (var33 != null) {
                        for(var22 = 4; var22 >= 0; --var22) {
                           if ((var20 == null || var20.method497(var22)) && var33[var22] != null) {
                              short var23 = 0;
                              if (var22 == 0) {
                                 var23 = 3;
                              }

                              if (var22 == 1) {
                                 var23 = 4;
                              }

                              if (var22 == 2) {
                                 var23 = 5;
                              }

                              if (var22 == 3) {
                                 var23 = 6;
                              }

                              if (var22 == 4) {
                                 var23 = 1001;
                              }

                              WorldMapManager.insertMenuItemNoShift(var33[var22], class149.colorStartTag(65535) + var19.name, var23, var18, var13, var14);
                           }
                        }
                     }

                     WorldMapManager.insertMenuItemNoShift("Examine", class149.colorStartTag(65535) + var19.name, 1002, var19.id, var13, var14);
                  }
               }

               int var26;
               Player var27;
               NPC var34;
               int[] var35;
               if (var15 == 1) {
                  NPC var29 = Client.npcs[var18];
                  if (var29 == null) {
                     break label309;
                  }

                  if (var29.definition.size == 1 && (var29.x & 127) == 64 && (var29.y & 127) == 64) {
                     for(var26 = 0; var26 < Client.npcCount; ++var26) {
                        var34 = Client.npcs[Client.npcIndices[var26]];
                        if (var34 != null && var34 != var29 && var34.definition.size == 1 && var34.x == var29.x && var29.y == var34.y) {
                           class204.addNpcToMenu(var34, Client.npcIndices[var26], var13, var14);
                        }
                     }

                     var26 = Players.Players_count;
                     var35 = Players.Players_indices;

                     for(var22 = 0; var22 < var26; ++var22) {
                        var27 = Client.players[var35[var22]];
                        if (var27 != null && var29.x == var27.x && var29.y == var27.y) {
                           BufferedSource.addPlayerToMenu(var27, var35[var22], var13, var14);
                        }
                     }
                  }

                  class204.addNpcToMenu(var29, var18, var13, var14);
               }

               if (var15 == 0) {
                  Player var30 = Client.players[var18];
                  if (var30 == null) {
                     break label309;
                  }

                  if ((var30.x & 127) == 64 && (var30.y & 127) == 64) {
                     for(var26 = 0; var26 < Client.npcCount; ++var26) {
                        var34 = Client.npcs[Client.npcIndices[var26]];
                        if (var34 != null && var34.definition.size == 1 && var34.x == var30.x && var30.y == var34.y) {
                           class204.addNpcToMenu(var34, Client.npcIndices[var26], var13, var14);
                        }
                     }

                     var26 = Players.Players_count;
                     var35 = Players.Players_indices;

                     for(var22 = 0; var22 < var26; ++var22) {
                        var27 = Client.players[var35[var22]];
                        if (var27 != null && var27 != var30 && var27.x == var30.x && var30.y == var27.y) {
                           BufferedSource.addPlayerToMenu(var27, var35[var22], var13, var14);
                        }
                     }
                  }

                  if (var18 != Client.combatTargetPlayerIndex) {
                     BufferedSource.addPlayerToMenu(var30, var18, var13, var14);
                  } else {
                     var4 = var11;
                  }
               }

               if (var15 == 3) {
                  NodeDeque var31 = Client.groundItems[class103.Client_plane][var13][var14];
                  if (var31 != null) {
                     for(TileItem var32 = (TileItem)var31.first(); var32 != null; var32 = (TileItem)var31.next()) {
                        ItemComposition var36 = TileItem.ItemDefinition_get(var32.id);
                        if (Client.isItemSelected == 1) {
                           WorldMapManager.insertMenuItemNoShift("Use", Client.selectedItemName + " " + "->" + " " + class149.colorStartTag(16748608) + var36.name, 16, var32.id, var13, var14);
                        } else if (Client.isSpellSelected) {
                           if ((class149.selectedSpellFlags & 1) == 1) {
                              WorldMapManager.insertMenuItemNoShift(Client.selectedSpellActionName, Client.selectedSpellName + " " + "->" + " " + class149.colorStartTag(16748608) + var36.name, 17, var32.id, var13, var14);
                           }
                        } else {
                           String[] var28 = var36.groundActions;

                           for(int var37 = 4; var37 >= 0; --var37) {
                              if (var32.method591(var37)) {
                                 if (var28 != null && var28[var37] != null) {
                                    byte var24 = 0;
                                    if (var37 == 0) {
                                       var24 = 18;
                                    }

                                    if (var37 == 1) {
                                       var24 = 19;
                                    }

                                    if (var37 == 2) {
                                       var24 = 20;
                                    }

                                    if (var37 == 3) {
                                       var24 = 21;
                                    }

                                    if (var37 == 4) {
                                       var24 = 22;
                                    }

                                    WorldMapManager.insertMenuItemNoShift(var28[var37], class149.colorStartTag(16748608) + var36.name, var24, var32.id, var13, var14);
                                 } else if (var37 == 2) {
                                    WorldMapManager.insertMenuItemNoShift("Take", class149.colorStartTag(16748608) + var36.name, 20, var32.id, var13, var14);
                                 }
                              }
                           }

                           WorldMapManager.insertMenuItemNoShift("Examine", class149.colorStartTag(16748608) + var36.name, 1004, var32.id, var13, var14);
                        }
                     }
                  }
               }
            }
         }

         ++var8;
      }
   }
}
