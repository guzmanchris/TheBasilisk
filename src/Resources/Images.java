package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage[] PlayAgain;
    public static BufferedImage[] ReturnToMenu;
    public static BufferedImage[] Resume;
    public static BufferedImage[] Restart;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] Options;
    public static ImageIcon icon;
    public static BufferedImage background;
    public static BufferedImage GameOver;

    public Images() {

        butstart = new BufferedImage[3];
        Resume = new BufferedImage[2];
        BTitle = new BufferedImage[2];
        Options = new BufferedImage[2];
        PlayAgain = new BufferedImage[2];
        ReturnToMenu = new BufferedImage[2];
        Restart = new BufferedImage[2];

        try {

            title = ImageIO.read(getClass().getResourceAsStream("/Sheets/menu.png"));
            background = ImageIO.read(getClass().getResourceAsStream("/Sheets/ChamberOfSecretsBG.png"));
            GameOver = ImageIO.read(getClass().getResourceAsStream("/Sheets/gameOver.png"));
            Pause = ImageIO.read(getClass().getResourceAsStream("/Buttons/Pause.png"));
            Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Resume.png"));
            Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
            BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
            BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
            Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/normal.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/hovered.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/pressed.png"));//clickbut
            PlayAgain[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/PlayAgain.png"));
            PlayAgain[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/PlayAgainP.png"));
            ReturnToMenu[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ReturnToMenu.png"));
            ReturnToMenu[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ReturnToMenuP.png"));
            Restart[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Restart.png"));
            Restart[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/RestartP.png"));
            
            icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));


        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
