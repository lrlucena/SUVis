/*
 * AxisPanel.java
 *
 * Created on April 28, 2008, 1:50 PM
 * 
 * Project: BotoSeis
 * 
 * Federal University of Para.
 * Department of Geophysics.
 */
package gfx; 

/**
 *
 * @author Williams Lima
 */
public class AxisPanel extends javax.swing.JPanel {

    public AxisPanel(gfx.SVAxis axis) {
        m_axis = axis;
    }

    @Override
    public void paint(java.awt.Graphics g) {
      try {
            int w = getWidth();
            int h = getHeight();

            int axisX = 0;
            int axisY = 0;
            int axisW = 0;
            int axisH = 0;           

            switch (m_axis.getAxisSide()) {
                case gfx.SVAxis.AXIS_LEFT:
                    axisX = w;
                    axisY = 0;
                    axisW = w;
                    axisH = h;
                    break;
                case gfx.SVAxis.AXIS_TOP:
                    axisX = 0;
                    axisY = h;
                    axisW = w;
                    axisH = h;
                    break;
                case gfx.SVAxis.AXIS_RIGHT:
                    axisX = 0;
                    axisY = 0;
                    axisW = w;
                    axisH = h;
                    break;
                case gfx.SVAxis.AXIS_BOTTOM:
                    axisX = 0;
                    axisY = 0;
                    axisW = w;
                    axisH = h;
                    break;
            }
            
            m_axis.draw((java.awt.Graphics2D) g, axisX, axisY, axisW, axisH);
            
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, e);
        }
    }
    // Variables declaration
    gfx.SVAxis m_axis = null;
}
