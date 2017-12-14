import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /** Method to keep only blue */
    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    /** Method to negate the picture */
    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(255 - pixelObj.getRed());
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }

    /** Method to grayscale the picture */
    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int avg = (int)((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3); 
                pixelObj.setRed(avg);
                pixelObj.setGreen(avg);
                pixelObj.setBlue(avg);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from right to left */
    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a
     * horizontal mirror in the center of the picture
     * from top to bottom*/
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /** Method that mirrors the picture around a
     * horizontal mirror in the center of the picture
     * from bottom to top*/
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel botPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                topPixel = pixels[row][col];
                botPixel = pixels[height - 1 - row][col];
                topPixel.setColor(botPixel.getColor());
            }
        }
    }

    /** copy triangular area and mirror */
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topRightPixel = null;
        Pixel bottomLeftPixel = null;
        int maxLength;
        if (pixels.length < pixels[0].length) 
        {
            maxLength = pixels.length; 
        }
        else 
        {
            maxLength = pixels[0].length; 
        }

        for (int row = 0; row < maxLength; row++)
        {
            for (int col = row; col < maxLength; col++)
            {
                topRightPixel = pixels[row][col];
                bottomLeftPixel = pixels[col][row];
                bottomLeftPixel.setColor(topRightPixel.getColor());
            }
        }
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        Pixel[][] pixels = this.getPixels2D();int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        for (int row = 27; row < 97; row++)
        {
            for (int col = 13; col < mirrorPoint; col++)
            {
                leftPixel = pixels[row][col];      
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
        System.out.println(count);
    }

    /** Method to mirror the arms of the snowman to make a snowman with 4 arms */
    public void mirrorArms() 
    {
        Pixel[][] pixels = this.getPixels2D();
        int mirrorPoint = 200;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        Pixel topPixel2 = null;
        Pixel bottomPixel2 = null;
        for (int row = 158; row < mirrorPoint; row++) // left
        {
            for (int col = 103; col < 170; col++)
            {
                topPixel = pixels[row][col];      
                bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
        for (int row = 171; row < mirrorPoint; row++) // right
        {
            for (int col = 239; col < 294; col++)
            {
                topPixel2 = pixels[row][col];      
                bottomPixel2 = pixels[mirrorPoint - row + mirrorPoint][col];
                bottomPixel2.setColor(topPixel2.getColor());
            }
        }
    }

    /** Method to mirror the seagull to the right to make two seagulls on the beach. */
    public void mirrorGull() 
    {
        Pixel[][] pixels = this.getPixels2D();
        int mirror = 345;
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        for (int row = 236; row < 322; row++)
        {
            for (int col = 237; col < mirror; col++)
            {
                rightPixel = pixels[row][col];
                leftPixel = pixels[row][mirror - col + mirror];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to copy part of fromPic */
    public void secondCopy (Picture fromPic, int startRow, int endRow, int startCol, int endCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] fromPixels = fromPic.getPixels2D();
        Pixel[][] toPixels = this.getPixels2D();
        for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < endRow; fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length && toCol < endCol; fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1,0,0);
        this.copy(flower2,100,0);
        this.copy(flower1,200,0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue,300,0);
        this.copy(flower1,400,0);
        this.copy(flower2,500,0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /** My collage */
    public void myCollage()
    {
        Picture bb = new Picture("bb.jpg");
        Picture he = new Picture("he.jpg");
        Picture sad = new Picture("sad.jpg");
        this.copy(bb, 50, 50);
        he.negate();
        this.copy(he, 750, 234);
        this.copy(sad, 20, 600);
        this.mirrorVertical();
        this.write("best-collage");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                topPixel = pixels[row][col];
                bottomPixel = pixels[row + 1][col];
                if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist ||
                topPixel.colorDistance(bottomPixel.getColor()) > edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }
    
    public void edgeDetection2(int edgeDist)
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                topPixel = pixels[row][col];
                bottomPixel = pixels[row + 1][col];
                if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist ||
                topPixel.colorDistance(bottomPixel.getColor()) > edgeDist)
                    leftPixel.setColor(Color.WHITE);
                else
                    leftPixel.setColor(Color.BLACK);
            }
        }
    }
    
    /** Method for new edge detection*/
    

    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        int redAverage = 0;
        int greenAverage = 0;
        int blueAverage = 0;
        int totalPixels = 0;
        int maxRed = 0;
        int minRed = 255;
        int maxGreen = 0;
        int minGreen = 255;
        int maxBlue = 0;
        int minBlue = 255;
        for (int row = 26; row < 36; row++)
        {
            for (int col = 178; col < 198; col++)
            {
                totalPixels++;

                Pixel myPixel = pixels[row][col];

                redAverage += myPixel.getRed();
                greenAverage += myPixel.getGreen();
                blueAverage += myPixel.getBlue();
                if (myPixel.getRed() > maxRed) 
                { 
                    maxRed = myPixel.getRed(); 
                }
                if (myPixel.getRed() < minRed) 
                { 
                    minRed = myPixel.getRed(); 
                }
                if (myPixel.getGreen() > maxGreen) 
                { 
                    maxGreen = myPixel.getGreen(); 
                }
                if (myPixel.getGreen() < minGreen) 
                { 
                    minGreen = myPixel.getGreen(); 
                }
                if (myPixel.getBlue() > maxBlue) 
                { 
                    maxBlue = myPixel.getBlue(); 
                }
                if (myPixel.getGreen() < minBlue) 
                { 
                    minBlue = myPixel.getBlue(); 
                }

            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
