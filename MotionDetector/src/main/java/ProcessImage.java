import java.awt.image.BufferedImage;
import java.util.Formatter;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProcessImage {

    public static class WriteFile {
        private Formatter toInsert;

        public boolean openFile() {
            try {
                this.toInsert = new Formatter("matrix");
                return true;
            } catch (Exception var2) {
                System.out.println("File not found");
                return false;
            }
        }

        public void add(byte value) {
            if(value == 1)
                this.toInsert.format("0");
            else
                this.toInsert.format(" ");
        }

        public void addNewLine() {
            this.toInsert.format("\n");
        }

        public void closeFile() {
            this.toInsert.close();
        }
    }

    private static boolean drawRectangle(BufferedImage currentFrame, byte[][] matrix, int displayMode) {
        int leftLimit, upperLimit, bottomLimit, rightLimit;
        leftLimit = upperLimit = 999999;
        rightLimit = bottomLimit = -1;

        for(int x = 0; x < currentFrame.getWidth(); x++) {
            for (int y = 0; y < currentFrame.getHeight(); y++) {
                if(matrix[x][y] == 1) {

                    if(displayMode == 1)
                        currentFrame.setRGB(x, y, 16711680);

                    if(leftLimit > x)
                        leftLimit = x;
                    if(upperLimit > y)
                        upperLimit = y;
                    if(rightLimit < x)
                        rightLimit = x;
                    if(bottomLimit < y)
                        bottomLimit = y;
                }
            }
        }

        System.out.println(rightLimit+" "+leftLimit+" "+upperLimit+" "+bottomLimit+" ");

        if( rightLimit < 0 || rightLimit >= currentFrame.getWidth() || leftLimit >= currentFrame.getWidth() || upperLimit >= currentFrame.getHeight() || bottomLimit < 0 || bottomLimit >= currentFrame.getHeight())
            return false;

        for(int i = leftLimit; i < rightLimit; i++) {
            currentFrame.setRGB(i, upperLimit, 255);
            currentFrame.setRGB(i, bottomLimit, 255);
        }

        for(int i = upperLimit; i < bottomLimit; i++) {
            currentFrame.setRGB(rightLimit, i, 255);
            currentFrame.setRGB(leftLimit, i, 255);
        }
        return true;
    }

    private static byte[][] clearFalseMovement(BufferedImage currentFrame, byte[][] matrix) {
        for(int x = 0; x < currentFrame.getWidth(); x++) {
            for (int y = 0; y < currentFrame.getHeight(); y++) {
                if(matrix[x][y] == 1){
                    byte neighboursOfPixel = 0;
                    try{
                        if(matrix[x-1][y] == 0)
                            neighboursOfPixel++;
                        if(matrix[x+1][y] == 0)
                            neighboursOfPixel++;
                        if(matrix[x][y-1] == 0)
                            neighboursOfPixel++;
                        if(matrix[x][y+1] == 0)
                            neighboursOfPixel++;

                        if(neighboursOfPixel >= 3) {
                            matrix[x][y] = 0;
                        }
                    }catch (Exception e) {

                    }
                }
            }
        }
        return matrix;
    }

    public static boolean compareFrames(BufferedImage currentFrame, BufferedImage previousFrame, int differenceBetweenFramesConsideredAsMovement, int displayMode) {
        byte[][] matrix = new byte[currentFrame.getWidth()][currentFrame.getHeight()];
        for(int x = 0; x < currentFrame.getWidth(); x++) {
            for (int y = 0; y < currentFrame.getHeight(); y++) {

                int dif = Math.abs(currentFrame.getRGB(x, y) - previousFrame.getRGB(x, y));

                if (dif > differenceBetweenFramesConsideredAsMovement) {
                    //currentFrame.setRGB(x, y, 16711680);
                    matrix[x][y] = 1;
                }
                else
                    matrix[x][y] = 0;
            }
        }

        for(int i = 0; i < 5; i++)
            matrix = clearFalseMovement(currentFrame, matrix);

        return drawRectangle(currentFrame, matrix, displayMode);
    }
}
