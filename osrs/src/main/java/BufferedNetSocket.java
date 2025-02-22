import java.io.IOException;
import java.net.Socket;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("oc")
@Implements("BufferedNetSocket")
public class BufferedNetSocket extends AbstractSocket {
    @ObfuscatedName("f")
    Socket socket;
    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "Lox;"
    )
    BufferedSource source;
    @ObfuscatedName("v")
    @ObfuscatedSignature(
            descriptor = "Los;"
    )
    BufferedSink sink;

   public BufferedNetSocket(Socket var1, int var2, int var3) throws IOException {
      this.socket = var1;
      this.socket.setSoTimeout(30000);
      this.socket.setTcpNoDelay(true);
      this.socket.setReceiveBufferSize(65536);
      this.socket.setSendBufferSize(65536);
      this.source = new BufferedSource(this.socket.getInputStream(), var2);
      this.sink = new BufferedSink(this.socket.getOutputStream(), var3);
   }

    @ObfuscatedName("f")
    @ObfuscatedSignature(
            descriptor = "(II)Z",
            garbageValue = "-38626951"
    )
    public boolean isAvailable(int var1) throws IOException {
      return this.source.isAvailable(var1);
   }

    @ObfuscatedName("w")
    @ObfuscatedSignature(
            descriptor = "(B)I",
            garbageValue = "50"
    )
    public int available() throws IOException {
      return this.source.available();
   }

    @ObfuscatedName("v")
    @ObfuscatedSignature(
            descriptor = "(I)I",
            garbageValue = "-1455058201"
    )
    public int readUnsignedByte() throws IOException {
      return this.source.readUnsignedByte();
   }

    @ObfuscatedName("s")
    @ObfuscatedSignature(
            descriptor = "([BIII)I",
            garbageValue = "2061560315"
    )
    public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.source.read(var1, var2, var3);
   }

    @ObfuscatedName("z")
    @ObfuscatedSignature(
            descriptor = "([BIIS)V",
            garbageValue = "26113"
    )
    public void write(byte[] var1, int var2, int var3) throws IOException {
      this.sink.write(var1, var2, var3);
   }

    @ObfuscatedName("j")
    @ObfuscatedSignature(
            descriptor = "(B)V",
            garbageValue = "2"
    )
    public void close() {
      this.sink.close();

      try {
         this.socket.close();
      } catch (IOException var2) {
         ;
      }

      this.source.close();
   }

    @ObfuscatedName("finalize")
    protected void finalize() {
      this.close();
   }
}
