package usrdata;

/*
 * NumericIO.java
 *
 * Created on 28 de Agosto de 2007, 14:50
 *
 * Project: BotoSeis
 *
 * Federal University of Para. Department of Geophysics
 */
import java.io.*;
import static java.lang.Float.floatToIntBits;
import static java.lang.Float.floatToRawIntBits;
import static java.lang.Float.intBitsToFloat;
import static java.lang.System.out;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.wrap;
import static java.nio.ByteOrder.BIG_ENDIAN;

/**
 * The NumericIO is a utility class to read/write raw numeric data from/to disk
 * s. Types supported: float (4 bytes), int(4 bytes), short(2 bytes), unsigned
 * short(2 bytes).
 *
 * @author Williams Lima
 */
public class NumericIO {

    /**
     * Creates a new instance of NumericIO
     */
    public NumericIO() {
    }

    public static int readInt(InputStream pInput) {
        int ret;
        try {
            byte buff[] = new byte[4];
            pInput.read(buff);
            ret = bytesToInt(buff);
        } catch (IOException e) {
            ret = 0;
        }

        return ret;
    }

    public static int readSwapInt(InputStream pInput) {
        int ret;
        try {
            byte buff[] = new byte[4];
            pInput.read(buff);
            ByteBuffer byteBuffer = wrap(buff);
            ret = byteBuffer.order(BIG_ENDIAN).getInt();
        } catch (IOException e) {
            ret = 0;
        }

        return ret;
    }

    public static short readSwapShort(InputStream pInput) {
        short ret;
        try {
            byte buff[] = new byte[2];
            pInput.read(buff);
            ByteBuffer byteBuffer;
            byteBuffer = wrap(buff);
            ret = byteBuffer.order(BIG_ENDIAN).getShort();
        } catch (IOException e) {
            ret = 0;
        }
        return ret;
    }

    public static float readSwapFloat(InputStream pInput) {
        float ret;
        try {
            byte buff[] = new byte[4];
            pInput.read(buff);
            ByteBuffer byteBuffer = wrap(buff);
            ret = byteBuffer.order(BIG_ENDIAN).getFloat();
        } catch (IOException e) {
            ret = 0;
        }

        return ret;
    }

    public static void writeInt(OutputStream pOutput, int v) {
        try {
            pOutput.write(intToBytes(v));
        } catch (IOException e) {
            out.println("NumericIO.writeInt");
            out.println(e.toString());
        }
    }

    public static short readShort(InputStream pInput) {
        short ret;
        try {
            byte buff[] = new byte[2];
            pInput.read(buff);
            ret = bytesToShort(buff);
        } catch (IOException e) {
            ret = 0;
        }

        return ret;
    }

    public static void writeShort(OutputStream pOutput, short v) {
        try {
            pOutput.write(shortToBytes(v));
        } catch (IOException e) {
            out.println("NumericIO.writeShort");
            out.println(e.toString());
        }
    }

    public static char readUShort(InputStream pInput) {
        char ret;

        try {
            byte buff[] = new byte[2];
            pInput.read(buff);
            ret = bytesToUShort(buff);
        } catch (IOException e) {
            ret = 0;
        }

        return ret;
    }

    public static char readSwapUShort(InputStream pInput) {
        char ret;

        try {
            byte buff[] = new byte[2];
            pInput.read(buff);
            ByteBuffer byteBuffer = wrap(buff);
            ret = byteBuffer.order(BIG_ENDIAN).getChar();
        } catch (IOException e) {
            ret = 0;
        }

        return ret;
    }

    public static void writeUShort(OutputStream pOutput, char v) {
        try {
            pOutput.write(ushortToBytes(v));
        } catch (IOException e) {
            out.println("NumericIO.writeShort");
            out.println(e.toString());
        }
    }

    public static float readFloat(InputStream pInput) {
        float ret;

        try {
            byte buff[] = new byte[4];
            pInput.read(buff);
            ret = bytesToFloat(buff);
        } catch (IOException e) {
            ret = 0.0F;
        }

        return ret;
    }

    public static void writeFloat(OutputStream pOutput, float v) {
        try {
            pOutput.write(floatToBytes(v));
        } catch (IOException e) {
            out.println("NumericIO.writeFloat");
            out.println(e.toString());
        }
    }

    public static void writeSwapInt(OutputStream pOutput, int v) {
        try {
            ByteBuffer byteBuffer = wrap(intToBytes(v));
            byte[] ret = intToBytes(byteBuffer.order(BIG_ENDIAN).getInt());

            pOutput.write(ret);
        } catch (IOException e) {
            out.println("NumericIO.writeFloat");
            out.println(e.toString());
        }
    }

    public static void writeSwapShort(OutputStream pOutput, short v) {
        try {
            ByteBuffer byteBuffer = wrap(shortToBytes(v));
            byte[] ret = shortToBytes(byteBuffer.order(BIG_ENDIAN).getShort());

            pOutput.write(ret);
        } catch (IOException e) {
            out.println("NumericIO.writeFloat");
            out.println(e.toString());
        }
    }

    public static void writeSwapUShort(OutputStream pOutput, char v) {
        try {
            ByteBuffer byteBuffer = wrap(ushortToBytes(v));
            byte[] ret = ushortToBytes(byteBuffer.order(BIG_ENDIAN).getChar());

            pOutput.write(ret);
        } catch (IOException e) {
            out.println("NumericIO.writeFloat");
            out.println(e.toString());
        }
    }

    public static void writeSwapFloat(OutputStream pOutput, float v) {
        try {
            byte[] ret = floatBEToBytes(v);
            pOutput.write(ret);
        } catch (IOException e) {
            out.println("NumericIO.writeFloat");
            out.println(e.toString());
        }
    }

    public static byte[] intToBytes(int v) {
        byte buff[] = new byte[4];

        buff[0] = (byte) (0xff & (v));
        buff[1] = (byte) (0xff & (v >> 8));
        buff[2] = (byte) (0xff & (v >> 16));
        buff[3] = (byte) (0xff & (v >> 24));

        return buff;
    }

    public static int bytesToInt(byte buff[]) {
        int val = (buff[0] & 0xff) | ((buff[1] & 0xff) << 8)
                | ((buff[2] & 0xff) << 16) | ((buff[3] & 0xff) << 24);
        return val;
    }

    public static byte[] shortToBytes(short v) {
        byte buff[] = new byte[2];

        buff[0] = (byte) (0xff & (v));
        buff[1] = (byte) (0xff & (v >> 8));

        return buff;
    }

    public static short bytesToShort(byte buff[]) {
        return (short) ((buff[0] & 0xff) | ((buff[1] & 0xff) << 8));
    }

    public static byte[] ushortToBytes(char v) {
        byte buff[] = new byte[2];

        buff[0] = (byte) (0xff & (v));
        buff[1] = (byte) (0xff & (v >> 8));

        return buff;
    }

    public static char bytesToUShort(byte buff[]) {
        return (char) ((buff[0] & 0xff) | ((buff[1] & 0xff) << 8));
    }

    public static char bytesToUSwapShort(byte buff[]) {
        return (char) ((buff[0] & 0xff) << 8 | ((buff[1] & 0xff)));
    }

    public static byte[] floatBEToBytes(float v) {
        return intToByteArrayBE(floatToRawIntBits(v));
    }

    public static byte[] floatToBytes(float v) {
        int value = floatToIntBits(v);
        return intToBytes(value);
    }

    public static float bytesToFloat(byte buff[]) {
        int val = (buff[0] & 0xff) | ((buff[1] & 0xff) << 8)
                | ((buff[2] & 0xff) << 16) | ((buff[3] & 0xff) << 24);
        return intBitsToFloat(val);
    }

    public static byte[] intToByteArrayBE(int data) {
        return new byte[]{(byte) ((data >> 24) & 0xff), (byte) ((data >> 16) & 0xff), (byte) ((data >> 8) & 0xff),
            (byte) ((data >> 0) & 0xff),};
    }

}
