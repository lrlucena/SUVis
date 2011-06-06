/*
 * SciVisColormapRGB.java
 *
 * Created on 12 de Agosto de 2007, 08:36
 *
 * Project: BotoSeis
 * 
 * Federal University of Para, Brazil.
 * Department of Geophysics
 */
package gfx;

/**
 * The SVColorMaps class represents various colormaps that
 * can be used for data visualization using color scale like
 * the SU program suximage.
 * 
 * @author Williams Lima
 */
public class SVColorMaps {

    /** Creates a new instance of SciVisColormapRGB */
    public SVColorMaps() {
    }

    public int [] getRGBTrueColorPixels(int pCMap) {
        
        int red = 0;
        int green = 0;
        int blue = 0;

        /* Build the 1st ramp                                           */
        int ih;
        int npixels = 256;
        int half = npixels/2;
        int truecolor_pixel[] = new int[256];
        
        for (ih = 0; ih < half; ++ih) {
            red = (int) (c_rgb[pCMap][0][0] + (c_rgb[pCMap][1][0] - c_rgb[pCMap][0][0]) * ((float) ih) / ((float) half));
            green = (int) (c_rgb[pCMap][0][1] + (c_rgb[pCMap][1][1] - c_rgb[pCMap][0][1]) * ((float) ih) / ((float) half));
            blue = (int) (c_rgb[pCMap][0][2] + (c_rgb[pCMap][1][2] - c_rgb[pCMap][0][2]) * ((float) ih) / ((float) half));

            truecolor_pixel[ih] = 0;
            truecolor_pixel[ih] = blue | (green << 8) | (red << 16);
        }

        /* Build the 2nd ramp                                           */
        for (ih = half; ih < npixels; ++ih) {
            red = (int) (c_rgb[pCMap][1][0] +
                    (c_rgb[pCMap][2][0] - c_rgb[pCMap][1][0]) * ((float) (ih - half)) / ((float) half));
            green = (int) (c_rgb[pCMap][1][1] +
                    (c_rgb[pCMap][2][1] - c_rgb[pCMap][1][1]) * ((float) (ih - half)) / ((float) half));
            blue = (int) (c_rgb[pCMap][1][2] +
                    (c_rgb[pCMap][2][2] - c_rgb[pCMap][1][2]) * ((float) (ih - half)) / ((float) half));

            truecolor_pixel[ih] = 0;
            truecolor_pixel[ih] = blue | (green << 8) | (red << 16);
        }

        return truecolor_pixel;
    }

    public int [] getHSVTrueColorPixels(int pCMap) {        

        float [] hsv = new float[3];
        float [] rgb = new float[3];    
        
        /* Build the 1st ramp  */
        int ih;
        int npixels = 256;        
        int half = npixels/2;
        int [] truecolor_pixel = new int[npixels];
        
        int r,g,b;
        for (ih = 0; ih < half; ++ih) {
            hsv[0] = (c_hsv[pCMap][0][0] + (c_hsv[pCMap][1][0] - c_hsv[pCMap][0][0]) * ((float) ih) / ((float) half));
            hsv[1] = (c_hsv[pCMap][0][1] + (c_hsv[pCMap][1][1] - c_hsv[pCMap][0][1]) * ((float) ih) / ((float) half));
            hsv[2] = (c_hsv[pCMap][0][2] + (c_hsv[pCMap][1][2] - c_hsv[pCMap][0][2]) * ((float) ih) / ((float) half));
           
            hsv2rgb(hsv, rgb);                     
            
            r = (int)(rgb[0]*255);
            g = (int)(rgb[1]*255);
            b = (int)(rgb[2]*255);
            truecolor_pixel[ih] = 0;
            truecolor_pixel[ih] = b | (g << 8) | (r << 16);
        }

        /* Build the 2nd ramp                                           */
        for (ih = half; ih < npixels; ++ih) {
            hsv[0] = (c_hsv[pCMap][1][0] +
                    (c_hsv[pCMap][2][0] - c_hsv[pCMap][1][0]) * ((float) (ih - half)) / ((float) half));
            hsv[1] = (c_hsv[pCMap][1][1] +
                    (c_hsv[pCMap][2][1] - c_hsv[pCMap][1][1]) * ((float) (ih - half)) / ((float) half));
            hsv[2] = (c_hsv[pCMap][1][2] +
                    (c_hsv[pCMap][2][2] - c_hsv[pCMap][1][2]) * ((float) (ih - half)) / ((float) half));
            
            hsv2rgb(hsv, rgb);                     
            
            r = (int)(rgb[0]*255);
            g = (int)(rgb[1]*255);
            b = (int)(rgb[2]*255);
            
            truecolor_pixel[ih] = 0;
            truecolor_pixel[ih] = b | (g << 8) | (r << 16);
        }

        return truecolor_pixel;
    }

    private void hsv2rgb(float [] in, float [] out) {
        float m1, m2;

        if (in[2] <= 0.5f) {
            m2 = in[2] * (1.0f + in[1]);
        } else {
            m2 = in[2] + in[1] - in[2] * in[1];
        }
        
        m1 = 2 * in[2] - m2;

        if (in[1] == .0f) {
            out[0] = out[1] = out[2] = in[2];
        }else{
            out[0] = rgbvalue(m1, m2, in[0] + 120.0f);
            out[1] = rgbvalue(m1, m2, in[0]);
            out[2] = rgbvalue(m1, m2, in[0] - 120.0f);
            
            if(out[0] > 1.0f){
                out[0] = 1.0f;
            }
            if(out[1] > 1.0f){
                out[1] = 1.0f;
            }
            if(out[2] > 1.0f){
                out[2] = 1.0f;
            }
        }
    }

    private float rgbvalue(float n1, float n2, float hue) {
        while (hue > 360.0f) {
            hue -= 360.0f;
        }
        while (hue < 0.0f) {
            hue += 360.0f;
        }
        if (hue < 60.0f) {
            return n1 + (n2 - n1) * hue / 60.0f;
        } else if (hue < 180.0f) {
            return n2;
        } else if (hue < 240.0f) {
            return n1 + (n2 - n1) * (240.0f - hue) / 60.0f;
        }

        return n1;
    }
    // Variables declaration
    //
    /* define hue, saturation, lightness values */
    private final float HSV_BLACK[] = {0.0f, 0.00f, 0.00f};
    private final float HSV_GRAY[] = {0.0f, 0.00f, 0.50f};
    private final float HSV_WHITE[] = {0.0f, 0.00f, 1.00f};
    private final float HSV_HUE1[] = {240.0f, 1.00f, 0.50f};
    private final float HSV_HUE2[] = {120.0f, 1.00f, 0.50f};
    private final float HSV_HUE3[] = {0.0f, 1.00f, 0.50f};
    private final float HSV_DRED[] = {0.0f, 1.00f, 0.50f};
    private final float HSV_BROWN[] = {30.0f, 1.00f, 0.30f};
    private final float HSV_GREEN[] = {140.0f, 1.00f, 0.50f};
    private final float HSV_BLUE[] = {240.0f, 1.00f, 0.70f};
    private final float HSV_YELLOW[] = {70.0f, 1.00f, 0.50f};
    private final float c_hsv[][][] = {
        {HSV_WHITE, HSV_GRAY, HSV_BLACK},
        {HSV_HUE1, HSV_HUE2, HSV_HUE3},
        {HSV_HUE3, HSV_HUE2, HSV_HUE1},
        {HSV_BROWN, HSV_GREEN, HSV_BLUE},
        {HSV_DRED, HSV_WHITE, HSV_BLUE},
        {HSV_BLUE, HSV_WHITE, HSV_DRED},
        {HSV_WHITE, HSV_DRED, HSV_BLUE},
        {HSV_WHITE, HSV_GREEN, HSV_BLUE},
        {HSV_BLUE, HSV_DRED, HSV_WHITE},
        {HSV_BLUE, HSV_GREEN, HSV_WHITE},
        {HSV_BLUE, HSV_WHITE, HSV_GREEN},
        {HSV_YELLOW, HSV_DRED, HSV_BROWN},
        {HSV_BROWN, HSV_DRED, HSV_YELLOW},
        {HSV_DRED, HSV_YELLOW, HSV_BROWN}};
    /* define red, green, blue values */
    private final int RGB_BLACK[] = {0x00, 0x00, 0x00};
    private final int RGB_WHITE[] = {0xff, 0xff, 0xff};
    private final int RGB_GRAY[] = {0x80, 0x80, 0x80};
    private final int RGB_ORANGE[] = {0xff, 0x80, 0x00};
    private final int RGB_RED[] = {0xe0, 0x00, 0x50};
    private final int RGB_BLUE[] = {0x00, 0x40, 0xc0};
    private final int RGB_GREEN[] = {0x06, 0x5b, 0x3f};
    private final int RGB_BROWN[] = {0x72, 0x5b, 0x3f};
    private final int RGB_REDBROWN[] = {0xa0, 0x40, 0x00};
    private final int RGB_GRAY2[] = {0xb0, 0xb0, 0xb0};
    private final int RGB_LGRAY[] = {0xf0, 0xf0, 0xf0};
    private final int RGB_LBLUE[] = {0x55, 0x9c, 0xe0};
    private final int RGB_YELLOW[] = {0xd0, 0xb0, 0x20};
    private final int c_rgb[][][] = {
        {RGB_BLACK, RGB_GRAY, RGB_WHITE},
        {RGB_RED, RGB_LGRAY, RGB_BLUE},
        {RGB_RED, RGB_LGRAY, RGB_GREEN},
        {RGB_BROWN, RGB_LGRAY, RGB_BLUE},
        {RGB_BROWN, RGB_LGRAY, RGB_GREEN},
        {RGB_REDBROWN, RGB_LGRAY, RGB_BLUE},
        {RGB_REDBROWN, RGB_LGRAY, RGB_GREEN},
        {RGB_ORANGE, RGB_LGRAY, RGB_BLUE},
        {RGB_ORANGE, RGB_LGRAY, RGB_GREEN},
        {RGB_BROWN, RGB_GRAY2, RGB_GREEN},
        {RGB_BROWN, RGB_GRAY2, RGB_BLUE},
        {RGB_BROWN, RGB_YELLOW, RGB_BLUE}};
}

