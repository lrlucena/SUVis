package usrdata;
/*
 * NumericIO.java
 *
 * Created on 28 de Agosto de 2007, 14:50
 *
 * Project: BotoSeis
 * 
 * Federal University of Para.
 * Department of Geophysics
 */

import java.io.*;

/**
 * The NumericIO is a utility class to read/write
 * raw numeric data from/to disk s. Types supported:
 *  float (4 bytes), int(4 bytes), short(2 bytes), unsigned short(2 bytes).
 * 
 * @author Williams Lima
 */
public class NumericIO {
    
    /** Creates a new instance of NumericIO */
    public NumericIO() {
    }
    
    public static int readInt(InputStream pInput) {
        int ret = 0;
        try{
            byte buff[] = new byte[4];
            pInput.read(buff);
            ret = bytesToInt(buff);
        }catch(IOException e){
            ret = 0;
        }
        
        return ret;
    }
    
    public static void writeInt(OutputStream pOutput, int v) {
        try{
            pOutput.write(intToBytes(v));
        }catch (IOException e){
            System.out.println("NumericIO.writeInt");
            System.out.println(e.toString());
        }
    }
    
    public static short readShort(InputStream pInput) {
        short ret = 0;
        
        try{
            byte buff[] = new byte[2];
            pInput.read(buff);
            ret = bytesToShort(buff);
        }catch(IOException e){
            ret = 0;
        }
        
        return ret;
    }
    
    public static void writeShort(OutputStream pOutput, short v) {
        try{
            pOutput.write(shortToBytes(v));
        }catch (IOException e){
            System.out.println("NumericIO.writeShort");
            System.out.println(e.toString());
        }
    }
    
    public static char readUShort(InputStream pInput) {
        char ret = 0;
        
        try{
            byte buff[] = new byte[2];
            pInput.read(buff);
            ret = bytesToUShort(buff);
        }catch(IOException e){
            ret = 0;
        }
        
        return ret;
    }
    
    public static void writeUShort(OutputStream pOutput, char v) {
        try{
            pOutput.write(ushortToBytes(v));
        }catch (IOException e){
            System.out.println("NumericIO.writeShort");
            System.out.println(e.toString());
        }
    }
    
    public static float readFloat(InputStream pInput) {
        float ret = 0.0F;
        
        try{
            byte buff[] = new byte[4];
            pInput.read(buff);
            ret = bytesToFloat(buff);
        }catch(IOException e){
            ret = 0.0F;
        }
        
        return ret;
    }
    
    public static void writeFloat(OutputStream pOutput, float v) {
        try{
            pOutput.write(floatToBytes(v));
        }catch (IOException e){
            System.out.println("NumericIO.writeFloat");
            System.out.println(e.toString());
        }
    }
    
    public static byte[] intToBytes(int v) {
        byte buff[] = new byte[4];
        
        buff[0] = (byte)(0xff & (v));
        buff[1] = (byte)(0xff & (v >> 8));
        buff[2] = (byte)(0xff & (v >> 16));
        buff[3] = (byte)(0xff & (v >> 24));
        
        return buff;
    }
    
    public static int bytesToInt(byte buff[]) {
        int val = (buff[0] & 0xff) | ((buff[1] & 0xff) << 8) |
                ((buff[2] & 0xff) << 16) | ((buff[3] & 0xff) << 24);
        return val;
    }
    
    public static byte[] shortToBytes(short v) {
        byte buff[] = new byte[2];
        
        buff[0] = (byte)(0xff & (v));
        buff[1] = (byte)(0xff & (v >> 8));
        
        return buff;
    }
    
    public static short bytesToShort(byte buff[]) {
        return (short)((buff[0] & 0xff) | ((buff[1] & 0xff) << 8));
    }
    
    public static byte[] ushortToBytes(char v) {
        byte buff[] = new byte[2];
        
        buff[0] = (byte)(0xff & (v));
        buff[1] = (byte)(0xff & (v >> 8));
        
        return buff;
    }
    
    public static char bytesToUShort(byte buff[]) {
        return (char)((buff[0] & 0xff) | ((buff[1] & 0xff) << 8));
    }
    
    public static byte[] floatToBytes(float v) {
        int value = Float.floatToIntBits(v);
        return intToBytes(value);
    }
    
    public static float bytesToFloat(byte buff[]) {
        int val = (buff[0] & 0xff) | ((buff[1] & 0xff) << 8) |
                ((buff[2] & 0xff) << 16) | ((buff[3] & 0xff) << 24);
        return Float.intBitsToFloat(val);
    }
}
