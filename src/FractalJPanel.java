import java.awt.*;

import javax.swing.JPanel;

public class FractalJPanel extends JPanel {

	//Color of fractal
	private Color color;
	
	//Level of depth for this recursion
	private int level;
	
	//Constructor method
	public FractalJPanel(int currentLevel) {
		
		//Set the initial values for the color and level
		color = Color.blue;
		level = currentLevel;
		
		//Setup the panel
		setBackground(Color.white);          
		setPreferredSize(new Dimension(800,600));
		
	}
	
	//Dragon Curve
	public void drawDragonCurveFractal(int level, int x1, int y1, int x2, int y2, Graphics g) {
		
		//Base Case
		if(level==0)
			g.drawLine(x1,y1,x2,y2);
		
		//Recursive Call
		else {
			
			//Calculate the x and y coordinates to draw the new line
			int xn = (x1+x2)/2 + (y2-y1)/2;
			int yn = (y1+y2)/2 - (x2-x1)/2;
			
			//Recursively draw two lines connecting the new coordinate
			drawDragonCurveFractal(level-1,x1,y1,xn,yn,g);
			drawDragonCurveFractal(level-1,x2,y2,xn,yn,g);
		}
	}
	
	//Lo Fractal
	public void drawLoFractal(int level, int x1, int y1, int x2, int y2, Graphics g) {
		
		//Base Case: draw a line connecting two given points
		if(level==0)
			g.drawLine(x1, y1, x2, y2);
		
		//Recursion step: Determine new points, draw next level
		else {
			
			//Calculate midpoint between (x1,y1 and x2,y2)
			int x3 = (x1+x2)/2;
			int y3 = (y1+y2)/2;
			
			//Calculate the fourth point (x4, y4) which forms an isosceles right triangle between(x1,y1) and (x3,y3)
			int x4 = x1+(x3-x1)/2 - (y3-y1)/2;
			int y4 = y1+(y3-y1)/2 + (x3-x1)/2;
			
			//Recursively draw the Fractal
			drawLoFractal(level-1, x4,y4,x1,y1,g);
			drawLoFractal(level-1, x4,y4,x3,y3,g);
			drawLoFractal(level-1, x4,y4,x2,y2,g);
		}
			
	}
	
	//Star Fractal
	public void drawStarFractal(int level, int midx, int midy, int x1, int y1, int x2, int y2, int x3,int y3,int x4, int y4,int x5,int y5, Graphics g) {
				
			drawLoFractal(level,midx, midy, x1, y1,g);
			drawLoFractal(level,midx, midy, x2, y2,g);
			drawLoFractal(level,midx, midy, x3, y3,g);
			drawLoFractal(level,midx, midy, x4, y4,g);
			drawLoFractal(level,midx, midy, x5, y5,g);
		}
	
	
	public void drawKochSnowflake(int level, int x1, int y1, int x2, int y2, int x3, int y3, Graphics g) {
		
		drawKochCurve(level,x1,y1,x2,y2,g);
		drawKochCurve(level,x2,y2,x3,y3,g);
		drawKochCurve(level,x3,y3,x1,y1,g);
			
		
	}
	
	
	public void drawKochCurve(int level, int x1, int y1, int x2, int y2, Graphics g) {
			
			if(level==0) {
				g.drawLine(x1, y1, x2, y2);
			}
			
			else {
				int distanceX=(x2-x1)/3;
				int distanceY=(y2-y1)/3;
				
				int cx=x1+distanceX;
				int cy=y1+distanceY;
				
				int dx=x2-distanceX;
				int dy=y2-distanceY;
				
				int ex=cx+(int)((distanceX*0.5) + (distanceY*-Math.sin(Math.PI/3)));
				int ey=cy+(int)((distanceY*0.5)-(distanceX*-Math.sin(Math.PI/3)));
				
				drawKochCurve(level-1,x1, y1, cx, cy,g);
				drawKochCurve(level-1,cx,cy,ex,ey,g);
				drawKochCurve(level-1,ex,ey,dx,dy,g);
				drawKochCurve(level-1,dx, dy, x2, y2,g);
				
			}
				
			
		}
	//This method draws the graphics onto the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//set the rendering color
		g.setColor(color);
		
		//Depending on which fractal type is selected begin the corresponding 
		if(FractalFrame.fractalType.equals("Dragon Curve"))
			drawDragonCurveFractal(level,500,200,100,200,g);
		
		if(FractalFrame.fractalType.equals("Lo Fractal"))
			drawLoFractal(level, 100,90,390,300,g);
		
		if(FractalFrame.fractalType.equals("Lo Star Fractal"))
			drawStarFractal(level, 390,300, 160,120, 500, 70, 660,300, 500, 510, 150, 450,g);
		
		if(FractalFrame.fractalType.equals("Koch Snowflake")) {
			drawKochSnowflake(level, 400,100,200,400,600,400,g);
			
		}
	}

	public void setColor(Color c) {
		color=c;
	}
	
	public void setLevel(int currentLevel) {
		level=currentLevel;
	}
	
	public int getLevel() {
		return level;
	}
}
