package ru.job4j.bmp;

import java.io.*;

import static java.lang.System.arraycopy;

/**
 * Header of bmp 1078 bytes. After goes
 */

public class BmpRotate {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("1.bmp"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("6.bmp"));
        byte[] header = new byte[1078];
        byte[] image = new byte[262144];
        for (int i = 0; i < header.length; i++) {
            int s = bis.read();
            header[i] = (byte) s;
        }
        for (int i = 0; i < image.length; i++) {
            int s = bis.read();
            if (s == -1) {
                break;
            }
            image[i] = (byte) s;
        }
        byte[] rotated = rotate(90, image, 512, 512);

        for (int i = 0; i < header.length; i++) {
            bos.write(header[i]);
        }
        for (int i = 0; i < image.length; i++) {
            bos.write(rotated[i]);
        }
    }

    private static byte[] rotate(double angle, byte[] pixels, int width, int height) {
        final double radians = Math.toRadians(angle), cos = Math.cos(radians), sin = Math.sin(radians);
        final byte[] pixels2 = new byte[pixels.length];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int
                        centerx = width / 2,
                        centery = height / 2,
                        m = x - centerx,
                        n = y - centery,
                        j = ((int) (m * cos + n * sin)) + centerx,
                        k = ((int) (n * cos - m * sin)) + centery;
                if (j >= 0 && j < width && k >= 0 && k < height)
                    pixels2[(y * width + x)] = pixels[(k * width + j)];
            }
        }
        arraycopy(pixels2, 0, pixels, 0, pixels.length);
        return pixels2;
    }
}
