package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
    private final int radius;
    private final Color borderColor;

    public RoundedBorder(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
    }

    @Override
    public void paintBorder(java.awt.Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(borderColor);
        g2d.setStroke(new java.awt.BasicStroke(10)); // Border thickness
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(java.awt.Component c) {
        return new Insets(10, 10, 10, 10); // Adjust as needed
    }
}