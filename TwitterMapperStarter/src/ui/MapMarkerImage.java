package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import java.awt.*;
import java.awt.image.BufferedImage;

import twitter4j.Status;
import twitter4j.User;
import util.Util;

public class MapMarkerImage extends MapMarkerCircle implements MapMarker {
    //public static final double defaultMarkerSize = 5.0;

    public static int defaultMarkerSize = 45;
    public Color color;
    public static final Color defaultColor = Color.white;
    private BufferedImage img;
    private User user;
    private Status status;


    public MapMarkerImage(Layer layer, Coordinate coord, Color color, String imageUrl, Status status) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        this.color = color;
        this.img= util.Util.imageFromURL(imageUrl);
        this.user = status.getUser();
        this.status = status;
        setColor(Color.BLACK);
        setBackColor(color);
    }

    public User getUser(){
        return user;
    }

    public Status getStatus(){
        return status;
    }

    public void paint(Graphics g, Point position, int radio) {
        int imgSize = defaultMarkerSize-10;
        int halfSizeImage = imgSize / 2;
        int xImage = position.x - halfSizeImage;
        int yImage = position.y - halfSizeImage;

        if (g instanceof Graphics2D && getBackColor()!=null) {
            Graphics2D g2 = (Graphics2D) g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g2.setPaint(getBackColor());
            g.fillOval(xImage-5, yImage-5, defaultMarkerSize, defaultMarkerSize);
            g.drawImage(img, xImage, yImage, imgSize, imgSize, null);
            g2.setComposite(oldComposite);
        }
        g.setColor(getColor());

        if(getLayer()==null||getLayer().isVisibleTexts()) paintText(g, position);
    }

}
