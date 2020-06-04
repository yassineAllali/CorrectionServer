package application;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconManipulator 
{
	
	public static ImageIcon resizeIcon(ImageIcon icon, int width, int height)
	{
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);  // transform it back
		return icon;
	}
}
