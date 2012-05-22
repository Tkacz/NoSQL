/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Rafał Tkaczyk
 */

public final class MapReduceView extends JPanel {

    private BufferedImage bg;
    private ArrayList<BufferedImage> elements;
    
    private final Color lv9 = new Color(144, 238, 144);
    private final Color lv8 = new Color(168, 186, 113);
    private final Color lv7 = new Color(179, 163, 98);
    private final Color lv6 = new Color(188, 144, 87);
    private final Color lv5 = new Color(204, 109, 66);
    private final Color lv4 = new Color(216, 82, 49);
    private final Color lv3 = new Color(225, 66, 39);
    private final Color lv2 = new Color(233, 47, 29);
    private final Color lv1 = new Color(255, 0, 0);
    
    private final int width = 800;
    private final int height = 574;
    
    public MapReduceView(ArrayList<MRresult> args) {
        try {
            String fileName;
            int val;
            bg = ImageIO.read(new File("gfx/bg.jpg"));
            //this.setBackground(Color.white);
            elements = new ArrayList<BufferedImage>();
            for(int i = 0, size = args.size(); i < size; i++) {
                fileName = "gfx/map/" + args.get(i).getState() + ".png";
                elements.add(ImageIO.read(new File(fileName)));
                val = args.get(i).getVal();
                if(val < 100) {//lv 9
                    setColor(elements.get(i), lv9);
                } else if(val >= 100 && val < 500) {//lv 8
                    setColor(elements.get(i), lv8);
                } else if(val >= 500 && val < 1000) {//lv 7
                    setColor(elements.get(i), lv7);
                } else if(val >= 1000 && val < 1500) {//lv 6
                    setColor(elements.get(i), lv6);
                } else if(val >= 1500 && val < 2000) {//lv 5
                    setColor(elements.get(i), lv5);
                } else if(val >= 2000 && val < 2500) {//lv 4
                    setColor(elements.get(i), lv4);
                } else if(val >= 2500 && val < 3000) {//lv 3
                    setColor(elements.get(i), lv3);
                } else if(val >= 3000 && val < 3500) {//lv 2
                    setColor(elements.get(i), lv2);
                } else {//lv 1
                    setColor(elements.get(i), lv1);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bg, null, null);
        for(int i = 0, size = elements.size(); i < size; i++) {
            g2.drawImage(elements.get(i), null, null);
        }
        
        
        String slv9 = "0 - 99";
        String slv8 = "100 - 499";
        String slv7 = "500 - 999";
        String slv6 = "1000 - 1499";
        String slv5 = "1500 - 1999";
        String slv4 = "2000 - 2499";
        String slv3 = "2500 - 2999";
        String slv2 = "3000 - 3499";
        String slv1 = "3500 < ";
        
        int x = 680, y = 550;
        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("Times", Font.BOLD, 14);
        g2.setColor(lv9);
        TextLayout tl = new TextLayout(slv9, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv8);
        tl = new TextLayout(slv8, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv7);
        tl = new TextLayout(slv7, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv6);
        tl = new TextLayout(slv6, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv5);
        tl = new TextLayout(slv5, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv4);
        tl = new TextLayout(slv4, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv3);
        tl = new TextLayout(slv3, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv2);
        tl = new TextLayout(slv2, f, frc);
        tl.draw(g2, x, y);
        y -= 20;
        
        g2.setColor(lv1);
        tl = new TextLayout(slv1, f, frc);
        tl.draw(g2, x, y);
    }
    
    public void addElement(String name, int val) {
        if (name.length() == 2) {
            try {
                name = "gfx/map/" + name + ".png";
                System.out.println(name);
                elements.add(ImageIO.read(new File(name)));
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        repaint();
    }
    
    private void setColor(BufferedImage image, Color color) {
        for (int x = 0, size1 = image.getWidth(); x < size1; x++) {
            for (int y = 0, size2 = image.getHeight(); y < size2; y++) {
                if(image.getRGB(x, y) == -1){//16777215
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }
        repaint();//
    }
}
