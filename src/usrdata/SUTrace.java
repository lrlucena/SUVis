package usrdata;

/*
 * SUTrace.java
 *
 * Created on 25 de Agosto de 2007, 11:30
 *
 * Project: BotoSeis
 *
 * Federal University of Para. Department of Geophysics
 */

import java.io.*;
import static java.lang.Float.SIZE;
import static java.lang.System.arraycopy;
import static java.lang.System.out;
import static usrdata.NumericIO.readFloat;
import static usrdata.NumericIO.readSwapFloat;
import static usrdata.NumericIO.writeFloat;
import static usrdata.NumericIO.writeSwapFloat;

/**
 * The SUTrace class represents a SU trace (Header plus data)
 *
 * @author Williams Lima
 */
public class SUTrace {

    /**
     * Creates a new instance of SUTrace
     */
    public SUTrace() {
    }

    public void setData(float pData[]) {
        m_data = new float[pData.length];
        arraycopy(pData, 0, m_data, 0, m_data.length);
    }

    public float[] getData() {
        float ret[] = null;

        if (m_data != null) {
            if (m_data.length > 0) {
                ret = new float[m_data.length];
                arraycopy(m_data, 0, ret, 0, ret.length);
            }
        }

        return ret;
    }

    public void setHeader(SUHeader pHeader) {
        m_header = pHeader;
    }

    public SUHeader getHeader() {
        return m_header;
    }

    public void readFromFile(InputStream pInput, boolean pSkipData) {
        try {
            m_header.readFromFile(pInput);
            char ns = m_header.ns;
            if (ns > 0) {
                if (pSkipData) {
                    pInput.skip(ns * SIZE / 8);
                } else {
                    m_data = new float[ns];
                    for (char i = 0; i < ns; i++) {

                        m_data[i] = readFloat(pInput);
                    }
                }
            }
        } catch (IOException e) {
            out.println("SUTrace.readFromFile");
            out.println("\t" + e.toString());
        }
    }

    public void readFromFile(InputStream pInput, boolean pSkipData, boolean xdrFlag) {
        try {
            if (xdrFlag) {
                m_header.readFromFileXDR(pInput);
            } else {
                m_header.readFromFile(pInput);
            }
            char ns = m_header.ns;
            if (ns > 0) {
                if (pSkipData) {
                    pInput.skip(ns * SIZE / 8);
                } else {
                    m_data = new float[ns];
                    for (char i = 0; i < ns; i++) {
                        if (xdrFlag) {
                            m_data[i] = readSwapFloat(pInput);
                        } else {
                            m_data[i] = readFloat(pInput);
                        }
                    }
                }
            }
        } catch (IOException e) {
            out.println("SUTrace.readFromFile");
            out.println("\t" + e.toString());
        }
    }

    public void writeToFile(FileOutputStream pOutput) {
        m_header.writeToFile(pOutput);
        char ns = m_header.ns;
        try {
            for (char i = 0; i < ns; i++) {
                writeFloat(pOutput, m_data[i]);
            }
        } catch (NullPointerException ex) {
        }
    }

    public void writeToFile(FileOutputStream pOutput, boolean xdrFlag) {
        if (xdrFlag) {
            m_header.writeToFileXDR(pOutput);
        } else {
            m_header.writeToFile(pOutput);
        }
        char ns = m_header.ns;
        try {
            for (char i = 0; i < ns; i++) {
                if (xdrFlag) {
                    writeSwapFloat(pOutput, m_data[i]);
                } else {
                    writeFloat(pOutput, m_data[i]);
                }
            }
        } catch (NullPointerException ex) {
        }
    }
    // Variables declaration
    private SUHeader m_header = new SUHeader();
    private float m_data[] = null;
}
