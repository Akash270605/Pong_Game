/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ponggame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/**
 *
 * @author darknight
 */
public class Paddle {
    private int x, y;
	private int vel = 0;
	private int speed = 10;
	private int width = 25, height = 100;
	private int score;
	private Color color;
	private boolean left;
	
	public Paddle(Color c, boolean left) {
		color = c;
		this.left = left;
		if (this.left) {
			x = 0;
		}
		else {
			x = Game.WIDTH - width;
		}
		y = Game.HEIGHT/2 - height/2;
	}

  
	public void addPoint() {
		score++;
	}

	public void draw(Graphics g) {
		
		// draw paddle
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		// draw score
		String scoreText = Integer.toString(score);
		int sx;
		Font font = new Font("ROBOTO", Font.PLAIN, 50);
		int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
		int padding = 25;
		
		if(left) {
			sx = Game.WIDTH/2 - strWidth - padding;
		}
		else {
			sx = Game.WIDTH/2 + padding;
		}
		
		g.setFont(font);
		g.drawString(scoreText, sx, 50);
		
	}

	public void update(Ball ball) {
		// paddle movement
		if(y > Game.HEIGHT - height) { y = Game.HEIGHT - height; }
		else if(y <0) { y = 0; }
		else { y += vel; }
		
		int ballX = ball.getX();
		int ballY = ball.getY();
		
		// paddle hits
		if(left) {
			if(ballX <= width && ballY >= y-Ball.SIZE && ballY <= y+height) {
				ball.changeXDir();
			}
		}
		else {
			if(ballX >= x - Ball.SIZE && ballY >= y-Ball.SIZE && ballY <= y+height) {
				ball.changeXDir();
			}
		}
		
	}
	
	public void switchDirection(int dir) {
		
		vel = speed * dir;
		
	}
	
	public void stop() {
		
		vel = 0;
		
	}


}
