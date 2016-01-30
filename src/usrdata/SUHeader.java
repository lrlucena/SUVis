package usrdata;

/*
 * SUHeader.java
 *
 * Created on 23 de Agosto de 2007, 19:51
 *
 * Project: BotoSeis
 * 
 * Federal University of Para.
 * Department of Geophysics
 */

import java.io.*;
import static usrdata.NumericIO.readFloat;
import static usrdata.NumericIO.readInt;
import static usrdata.NumericIO.readShort;
import static usrdata.NumericIO.readSwapFloat;
import static usrdata.NumericIO.readSwapInt;
import static usrdata.NumericIO.readSwapShort;
import static usrdata.NumericIO.readSwapUShort;
import static usrdata.NumericIO.readUShort;
import static usrdata.NumericIO.writeFloat;
import static usrdata.NumericIO.writeInt;
import static usrdata.NumericIO.writeShort;
import static usrdata.NumericIO.writeSwapFloat;
import static usrdata.NumericIO.writeSwapInt;
import static usrdata.NumericIO.writeSwapShort;
import static usrdata.NumericIO.writeSwapUShort;
import static usrdata.NumericIO.writeUShort;

/**
 * The SUHeader class represents a SU(segy) trace header.
 *
 * @author Williams Lima
 */
public class SUHeader {

    /**
     * Creates a new instance of SUHeader
     */
    public SUHeader() {
        scalco = 0;
        tracl = 0;
        tracr = 0;
        fldr = 0;
        tracf = 0;
        ep = 0;
        cdp = 0;
        cdpt = 0;
        trid = 0;
        nvs = 0;
        nhs = 0;
        duse = 0;
        offset = 0;
        gelev = 0;
        selev = 0;
        sdepth = 0;
        gdel = 0;
        sdel = 0;
        swdep = 0;
        gwdep = 0;
        scalel = 0;
        sx = 0;
        sy = 0;
        gx = 0;
        gy = 0;
        counit = 0;
        wevel = 0;
        swevel = 0;
        sut = 0;
        gut = 0;
        sstat = 0;
        gstat = 0;
        tstat = 0;
        laga = 0;
        lagb = 0;
        delrt = 0;
        muts = 0;
        mute = 0;
        ns = 0;
        dt = 0;
        gain = 0;
        igc = 0;
        igi = 0;
        corr = 0;
        sfs = 0;
        sfe = 0;
        slen = 0;
        styp = 0;
        stas = 0;
        stae = 0;
        tatyp = 0;
        afilf = 0;
        afils = 0;
        /*     nofilf;
        nofils;
        lcf;
        hcf;
        lcs;
        hcs;
        year;
        day;
        hour;
        minute;
        sec;
        timbas;
        trwf;
        grnors;
        grnofr;
        grnlof;
        gaps;
        otrav;
        d1;
        f1;
        d2;
        f2;
        ungpow;
        unscale;
         */
        ntr = 0;
        mark = 0;
        shortpad = 0;
    }

    public void readFromFile(InputStream pIn) {
        tracl = readInt(pIn);
        tracr = readInt(pIn);
        fldr = readInt(pIn);
        tracf = readInt(pIn);
        ep = readInt(pIn);
        cdp = readInt(pIn);
        cdpt = readInt(pIn);
        trid = readShort(pIn);
        nvs = readShort(pIn);
        nhs = readShort(pIn);
        duse = readShort(pIn);
        offset = readInt(pIn);
        gelev = readInt(pIn);
        selev = readInt(pIn);
        sdepth = readInt(pIn);
        gdel = readInt(pIn);
        sdel = readInt(pIn);
        swdep = readInt(pIn);
        gwdep = readInt(pIn);
        scalel = readShort(pIn);
        scalco = readShort(pIn);
        sx = readInt(pIn);
        sy = readInt(pIn);
        gx = readInt(pIn);
        gy = readInt(pIn);
        counit = readShort(pIn);
        wevel = readShort(pIn);
        swevel = readShort(pIn);
        sut = readShort(pIn);
        gut = readShort(pIn);
        sstat = readShort(pIn);
        gstat = readShort(pIn);
        tstat = readShort(pIn);
        laga = readShort(pIn);
        lagb = readShort(pIn);
        delrt = readShort(pIn);
        muts = readShort(pIn);
        mute = readShort(pIn);
        ns = readUShort(pIn);
        dt = readUShort(pIn);
        gain = readShort(pIn);
        igc = readShort(pIn);
        igi = readShort(pIn);
        corr = readShort(pIn);
        sfs = readShort(pIn);
        sfe = readShort(pIn);
        slen = readShort(pIn);
        styp = readShort(pIn);
        stas = readShort(pIn);
        stae = readShort(pIn);
        tatyp = readShort(pIn);
        afilf = readShort(pIn);
        afils = readShort(pIn);
        nofilf = readShort(pIn);
        nofils = readShort(pIn);
        lcf = readShort(pIn);
        hcf = readShort(pIn);
        lcs = readShort(pIn);
        hcs = readShort(pIn);
        year = readShort(pIn);
        day = readShort(pIn);
        hour = readShort(pIn);
        minute = readShort(pIn);
        sec = readShort(pIn);
        timbas = readShort(pIn);
        trwf = readShort(pIn);
        grnors = readShort(pIn);
        grnofr = readShort(pIn);
        grnlof = readShort(pIn);
        gaps = readShort(pIn);
        otrav = readShort(pIn); //
        /* cwp local assignments */
        d1 = readFloat(pIn);
        f1 = readFloat(pIn);
        d2 = readFloat(pIn);
        f2 = readFloat(pIn);
        ungpow = readFloat(pIn);
        unscale = readFloat(pIn);
        ntr = readInt(pIn);
        mark = readShort(pIn);
        shortpad = readShort(pIn);

        for (int i = 0; i < 14; i++) {
            unass[i] = readShort(pIn);
        }

    }

    public void readFromFileXDR(InputStream pIn) {
        tracl = readSwapInt(pIn);
        tracr = readSwapInt(pIn);
        fldr = readSwapInt(pIn);
        tracf = readSwapInt(pIn);
        ep = readSwapInt(pIn);
        cdp = readSwapInt(pIn);
        cdpt = readSwapInt(pIn);
        trid = readSwapShort(pIn);
        nvs = readSwapShort(pIn);
        nhs = readSwapShort(pIn);
        duse = readSwapShort(pIn);
        offset = readSwapInt(pIn);
        gelev = readSwapInt(pIn);
        selev = readSwapInt(pIn);
        sdepth = readSwapInt(pIn);
        gdel = readSwapInt(pIn);
        sdel = readSwapInt(pIn);
        swdep = readSwapInt(pIn);
        gwdep = readSwapInt(pIn);
        scalel = readSwapShort(pIn);
        scalco = readSwapShort(pIn);
        sx = readSwapInt(pIn);
        sy = readSwapInt(pIn);
        gx = readSwapInt(pIn);
        gy = readSwapInt(pIn);
        counit = readSwapShort(pIn);
        wevel = readSwapShort(pIn);
        swevel = readSwapShort(pIn);
        sut = readSwapShort(pIn);
        gut = readSwapShort(pIn);
        sstat = readSwapShort(pIn);
        gstat = readSwapShort(pIn);
        tstat = readSwapShort(pIn);
        laga = readSwapShort(pIn);
        lagb = readSwapShort(pIn);
        delrt = readSwapShort(pIn);
        muts = readSwapShort(pIn);
        mute = readSwapShort(pIn);
        ns = readSwapUShort(pIn);
        dt = readSwapUShort(pIn);
        gain = readSwapShort(pIn);
        igc = readSwapShort(pIn);
        igi = readSwapShort(pIn);
        corr = readSwapShort(pIn);
        sfs = readSwapShort(pIn);
        sfe = readSwapShort(pIn);
        slen = readSwapShort(pIn);
        styp = readSwapShort(pIn);
        stas = readSwapShort(pIn);
        stae = readSwapShort(pIn);
        tatyp = readSwapShort(pIn);
        afilf = readSwapShort(pIn);
        afils = readSwapShort(pIn);
        nofilf = readSwapShort(pIn);
        nofils = readSwapShort(pIn);
        lcf = readSwapShort(pIn);
        hcf = readSwapShort(pIn);
        lcs = readSwapShort(pIn);
        hcs = readSwapShort(pIn);
        year = readSwapShort(pIn);
        day = readSwapShort(pIn);
        hour = readSwapShort(pIn);
        minute = readSwapShort(pIn);
        sec = readSwapShort(pIn);
        timbas = readSwapShort(pIn);
        trwf = readSwapShort(pIn);
        grnors = readSwapShort(pIn);
        grnofr = readSwapShort(pIn);
        grnlof = readSwapShort(pIn);
        gaps = readSwapShort(pIn);
        otrav = readSwapShort(pIn); //
        /* cwp local assignments */
        d1 = readSwapFloat(pIn);
        f1 = readSwapFloat(pIn);
        d2 = readSwapFloat(pIn);
        f2 = readSwapFloat(pIn);
        ungpow = readSwapFloat(pIn);
        unscale = readSwapFloat(pIn);
        ntr = readSwapInt(pIn);
        mark = readSwapShort(pIn);
        shortpad = readSwapShort(pIn);

        for (int i = 0; i < 14; i++) {
            unass[i] = readSwapShort(pIn);
        }

    }

    public void writeToFile(FileOutputStream pOut) {
        writeInt(pOut, tracl);
        writeInt(pOut, tracr);
        writeInt(pOut, fldr);
        writeInt(pOut, tracf);
        writeInt(pOut, ep);
        writeInt(pOut, cdp);
        writeInt(pOut, cdpt);
        writeShort(pOut, trid);
        writeShort(pOut, nvs);
        writeShort(pOut, nhs);
        writeShort(pOut, duse);
        writeInt(pOut, offset);
        writeInt(pOut, gelev);
        writeInt(pOut, selev);
        writeInt(pOut, sdepth);
        writeInt(pOut, gdel);
        writeInt(pOut, sdel);
        writeInt(pOut, swdep);
        writeInt(pOut, gwdep);
        writeShort(pOut, scalel);
        writeShort(pOut, scalco);
        writeInt(pOut, sx);
        writeInt(pOut, sy);
        writeInt(pOut, gx);
        writeInt(pOut, gy);
        writeShort(pOut, counit);
        writeShort(pOut, wevel);
        writeShort(pOut, swevel);
        writeShort(pOut, sut);
        writeShort(pOut, gut);
        writeShort(pOut, sstat);
        writeShort(pOut, gstat);
        writeShort(pOut, tstat);
        writeShort(pOut, laga);
        writeShort(pOut, lagb);
        writeShort(pOut, delrt);
        writeShort(pOut, muts);
        writeShort(pOut, mute);
        writeUShort(pOut, ns); // unsigned
        writeUShort(pOut, dt); // unsigned
        writeShort(pOut, gain);
        writeShort(pOut, igc);
        writeShort(pOut, igi);
        writeShort(pOut, corr);
        writeShort(pOut, sfs);
        writeShort(pOut, sfe);
        writeShort(pOut, slen);
        writeShort(pOut, styp);
        writeShort(pOut, stas);
        writeShort(pOut, stae);
        writeShort(pOut, tatyp);
        writeShort(pOut, afilf);
        writeShort(pOut, afils);
        writeShort(pOut, nofilf);
        writeShort(pOut, nofils);
        writeShort(pOut, lcf);
        writeShort(pOut, hcf);
        writeShort(pOut, lcs);
        writeShort(pOut, hcs);
        writeShort(pOut, year);
        writeShort(pOut, day);
        writeShort(pOut, hour);
        writeShort(pOut, minute);
        writeShort(pOut, sec);
        writeShort(pOut, timbas);
        writeShort(pOut, trwf);
        writeShort(pOut, grnors);
        writeShort(pOut, grnofr);
        writeShort(pOut, grnlof);
        writeShort(pOut, gaps);
        writeShort(pOut, otrav);
        /* cwp local assignments */
        writeFloat(pOut, d1);
        writeFloat(pOut, f1);
        writeFloat(pOut, d2);
        writeFloat(pOut, f2);
        writeFloat(pOut, ungpow);
        writeFloat(pOut, unscale);
        writeInt(pOut, ntr);
        writeShort(pOut, mark);
        writeShort(pOut, shortpad);

        for (int i = 0; i < 14; i++) {
            writeShort(pOut, unass[i]);
        }

    }

    public void writeToFileXDR(FileOutputStream pOut) {
        writeSwapInt(pOut, tracl);
        writeSwapInt(pOut, tracr);
        writeSwapInt(pOut, fldr);
        writeSwapInt(pOut, tracf);
        writeSwapInt(pOut, ep);
        writeSwapInt(pOut, cdp);
        writeSwapInt(pOut, cdpt);
        writeSwapShort(pOut, trid);
        writeSwapShort(pOut, nvs);
        writeSwapShort(pOut, nhs);
        writeSwapShort(pOut, duse);
        writeSwapInt(pOut, offset);
        writeSwapInt(pOut, gelev);
        writeSwapInt(pOut, selev);
        writeSwapInt(pOut, sdepth);
        writeSwapInt(pOut, gdel);
        writeSwapInt(pOut, sdel);
        writeSwapInt(pOut, swdep);
        writeSwapInt(pOut, gwdep);
        writeSwapShort(pOut, scalel);
        writeSwapShort(pOut, scalco);
        writeSwapInt(pOut, sx);
        writeSwapInt(pOut, sy);
        writeSwapInt(pOut, gx);
        writeSwapInt(pOut, gy);
        writeSwapShort(pOut, counit);
        writeSwapShort(pOut, wevel);
        writeSwapShort(pOut, swevel);
        writeSwapShort(pOut, sut);
        writeSwapShort(pOut, gut);
        writeSwapShort(pOut, sstat);
        writeSwapShort(pOut, gstat);
        writeSwapShort(pOut, tstat);
        writeSwapShort(pOut, laga);
        writeSwapShort(pOut, lagb);
        writeSwapShort(pOut, delrt);
        writeSwapShort(pOut, muts);
        writeSwapShort(pOut, mute);
        writeSwapUShort(pOut, ns); // unsigned
        writeSwapUShort(pOut, dt); // unsigned
        writeSwapShort(pOut, gain);
        writeSwapShort(pOut, igc);
        writeSwapShort(pOut, igi);
        writeSwapShort(pOut, corr);
        writeSwapShort(pOut, sfs);
        writeSwapShort(pOut, sfe);
        writeSwapShort(pOut, slen);
        writeSwapShort(pOut, styp);
        writeSwapShort(pOut, stas);
        writeSwapShort(pOut, stae);
        writeSwapShort(pOut, tatyp);
        writeSwapShort(pOut, afilf);
        writeSwapShort(pOut, afils);
        writeSwapShort(pOut, nofilf);
        writeSwapShort(pOut, nofils);
        writeSwapShort(pOut, lcf);
        writeSwapShort(pOut, hcf);
        writeSwapShort(pOut, lcs);
        writeSwapShort(pOut, hcs);
        writeSwapShort(pOut, year);
        writeSwapShort(pOut, day);
        writeSwapShort(pOut, hour);
        writeSwapShort(pOut, minute);
        writeSwapShort(pOut, sec);
        writeSwapShort(pOut, timbas);
        writeSwapShort(pOut, trwf);
        writeSwapShort(pOut, grnors);
        writeSwapShort(pOut, grnofr);
        writeSwapShort(pOut, grnlof);
        writeSwapShort(pOut, gaps);
        writeSwapShort(pOut, otrav);
        /* cwp local assignments */
        writeSwapFloat(pOut, d1);
        writeSwapFloat(pOut, f1);
        writeSwapFloat(pOut, d2);
        writeSwapFloat(pOut, f2);
        writeSwapFloat(pOut, ungpow);
        writeSwapFloat(pOut, unscale);
        writeSwapInt(pOut, ntr);
        writeSwapShort(pOut, mark);
        writeSwapShort(pOut, shortpad);

        for (int i = 0; i < 14; i++) {
            writeSwapShort(pOut, unass[i]);
        }

    }
    /* header keys */
    public int tracl;
    public int tracr;
    public int fldr;
    public int tracf;
    public int ep;
    public int cdp;
    public int cdpt;
    public short trid;
    public short nvs;
    public short nhs;
    public short duse;
    public int offset;
    public int gelev;
    public int selev;
    public int sdepth;
    public int gdel;
    public int sdel;
    public int swdep;
    public int gwdep;
    public short scalel;
    public short scalco;
    public int sx;
    public int sy;
    public int gx;
    public int gy;
    public short counit;
    public short wevel;
    public short swevel;
    public short sut;
    public short gut;
    public short sstat;
    public short gstat;
    public short tstat;
    public short laga;
    public short lagb;
    public short delrt;
    public short muts;
    public short mute;
    public char ns; // unsigned short
    public char dt; // unsigned short
    public short gain;
    public short igc;
    public short igi;
    public short corr;
    public short sfs;
    public short sfe;
    public short slen;
    public short styp;
    public short stas;
    public short stae;
    public short tatyp;
    public short afilf;
    public short afils;
    public short nofilf;
    public short nofils;
    public short lcf;
    public short hcf;
    public short lcs;
    public short hcs;
    public short year;
    public short day;
    public short hour;
    public short minute;
    public short sec;
    public short timbas;
    public short trwf;
    public short grnors;
    public short grnofr;
    public short grnlof;
    public short gaps;
    public short otrav;
    /* cwp local assignments */
    public float d1;
    public float f1;
    public float d2;
    public float f2;
    public float ungpow;
    public float unscale;
    public int ntr;
    public short mark;
    public short shortpad;
    public short unass[] = new short[14];

    public int getValue(String pkey) {
        if (pkey == null) {
            return fldr;
        }
        switch (pkey) {
            case "cdp":
                return cdp;
            case "offset":
                return offset;
            case "tracf":
                return tracf;
            case "ep":
                return ep;
            case "fldr":
                return fldr;
            default:
                break;
        }
        return fldr;
    }
}
