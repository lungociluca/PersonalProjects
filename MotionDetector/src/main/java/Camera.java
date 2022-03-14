import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Camera implements Runnable {

    private BufferedImage previousFrame, currentFrame;
    private AtomicBoolean run = new AtomicBoolean(true);
    private static final int differenceBetweenFramesConsideredAsMovement = 1160000;
    private static final int waitingTimeBetweenFrames = 3000;
    private File file = new File("C:\\Users\\lungo\\IdeaProjects\\AngularProject\\scs\\src\\assets\\file.txt");
    private Sound sound = new Sound();
    private boolean previousAlert = false;

    public Camera() throws FileNotFoundException {
    }

    public void endThread() {
        run.set(false);
    }

    @Override
    public void run() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        while (run.get()) {
            Scanner scanner = null;
            try {
                 scanner= new Scanner(file);
            } catch (IOException e) {
                System.out.println("Io exception");
            }


            int value1 = scanner.nextInt();
            int displayMode = scanner.nextInt();
            System.out.println(value1 + " <->" + displayMode);

            previousFrame = webcam.getImage();
            currentFrame = webcam.getImage();

            boolean alert = ProcessImage.compareFrames(currentFrame, previousFrame, differenceBetweenFramesConsideredAsMovement, displayMode);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("err");
            }

            if(alert && !previousAlert)
                sound.play();
            else if(!alert)
                sound.stop();

           previousAlert = alert;

            try {
                ImageIO.write(currentFrame, ImageUtils.FORMAT_JPG, new File("C:\\Users\\lungo\\IdeaProjects\\AngularProject\\scs\\src\\assets\\picture.jpg"));
            } catch (IOException e) {
                System.out.println("Io exception");
            }
        }
    }
}
