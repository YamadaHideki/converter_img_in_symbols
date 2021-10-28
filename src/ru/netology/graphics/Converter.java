package ru.netology.graphics;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    private TextColorSchema schema = new Schema();
    private int maxWidth = 0;
    private int maxHeight = 0;
    private double maxRatio = 0;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        int width = img.getWidth();
        int height = img.getHeight();

        double ratioWidth = (double) width / height;
        double ratioHeight = (double) height / width;
        double ratio = Math.max(ratioWidth, ratioHeight);

        if (ratio > maxRatio && maxRatio > 0) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        // Ужмем картинку по найденому коэфициенту
        if ((maxWidth < width && maxWidth != 0) || (maxHeight < height && maxHeight != 0)) {
            double k = Math.max((double) width / maxWidth, (double) height / maxHeight);
            width = (int) (width / k);
            height = (int) (height / k);
        }

        Image scaledImage = img.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);

        ImageIO.write(bwImg, "png", new File("out.png"));

        WritableRaster bwRaster = bwImg.getRaster();
        int[] casheArray = new int[3];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= (bwRaster.getHeight() - 1); i++) {
            for (int j = 0; j < (bwRaster.getWidth() - 1); j++) {
                int color = bwRaster.getPixel(j, i, casheArray)[0];
                char c = schema.convert(color);
                sb.append(c).append(c);
            }
            sb.append("<br>");
        }
        return sb.toString(); // Возвращаем собранный текст.
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
