package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	final int tileSize = 11;
	final int maxScreenRow = 46;
	final int maxScreenCol = 36;
	static int fireForce = 0;
	Thread gameThread;
	static boolean debugMode = false;
	static int fireDecay = 3;
	Fire[] fire;
	final int FPS = 30;
	final Color[] colors = {
		    new Color(7, 7, 7),
		    new Color(31, 7, 7),
		    new Color(47, 15, 7),
		    new Color(71, 15, 7),
		    new Color(87, 23, 7),
		    new Color(103, 31, 7),
		    new Color(119, 31, 7),
		    new Color(143, 39, 7),
		    new Color(159, 47, 7),
		    new Color(175, 63, 7),
		    new Color(191, 71, 7),
		    new Color(199, 71, 7),
		    new Color(223, 79, 7),
		    new Color(223, 87, 7),
		    new Color(223, 87, 7),
		    new Color(215, 95, 7),
		    new Color(215, 95, 7),
		    new Color(215, 103, 15),
		    new Color(207, 111, 15),
		    new Color(207, 119, 15),
		    new Color(207, 127, 15),
		    new Color(207, 135, 23),
		    new Color(199, 135, 23),
		    new Color(199, 143, 23),
		    new Color(199, 151, 31),
		    new Color(191, 159, 31),
		    new Color(191, 159, 31),
		    new Color(191, 167, 39),
		    new Color(191, 167, 39),
		    new Color(191, 175, 47),
		    new Color(183, 175, 47),
		    new Color(183, 183, 47),
		    new Color(183, 183, 55),
		    new Color(207, 207, 111),
		    new Color(223, 223, 159),
		    new Color(239, 239, 199),
		    new Color(255, 255, 255)
		};
	
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeigth = tileSize * maxScreenRow;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {
		creatFire();
    	calculeFire();
		  double drawInterval = 1000000000/FPS;
	        double delta = 0;
	        long lastTime = System.nanoTime();
	        long currentTime;
	        long timer = 0;
	        int drawCount = 0;
	        while(gameThread != null) {
	            currentTime = System.nanoTime();
	            delta += (currentTime - lastTime) /drawInterval;
	            timer += currentTime - lastTime;
	            lastTime = currentTime;
	            if(delta >= 1){
	            	
	                update();
	                repaint();
	                delta--;
	                drawCount++;
	            }
	            if(timer >= 1000000000){
	                System.out.println("FPS: "+drawCount);
	                drawCount = 0;
	                timer = 0;
	            }

	        }
		
	}
	
	
	public void update() {
		
		
	}
	
	
	public void creatFire() {
		 this.fire = new Fire[maxScreenCol * maxScreenRow];
		for(int row = 0;row < maxScreenRow;row++) {
			for(int col = 0 ; col < maxScreenCol;col++) {
				
	                int tileIndex = col + (row * maxScreenCol);
	                
	                this.fire[tileIndex] = new Fire();
	             
	              
			}
		}
	}
	
	
	
	
	public void calculeFire() {
		
		for(int col = 0; col < maxScreenCol;col++) {
			int p = maxScreenRow * maxScreenCol;
			int cont = (p - maxScreenCol) + col;
			
	            this.fire[cont].setFireForce(36);
	            
	        }
			
		}
	
	public void UpdateFire(int currentPixel) {
		 int nextPixel = currentPixel + maxScreenCol;
		 
		if(nextPixel >= maxScreenCol * maxScreenRow) {
			return;
		}
		
		Random random = new Random();
		int decay = random.nextInt(fireDecay);
		Fire newFire = this.fire[nextPixel];
		int newFireIntence = newFire.getFireForce() - decay;
		newFireIntence = (newFireIntence - decay <= 0) ? 0 : newFireIntence;
		if(currentPixel - decay >= 0) {
			this.fire[currentPixel - decay].setFireForce(newFireIntence);
		}
		
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Arial", Font.PLAIN, 6));
		
		
		
		for(int row = 0;row < maxScreenRow;row++) {
			for(int col = 0 ; col < maxScreenCol;col++) {
				
				int tileIndex = col + (row * maxScreenCol);
				UpdateFire(tileIndex);
				 int x = col * tileSize; // Posição X
	             int y = row * tileSize; // Posição Y
	                
	                
	             int c = this.fire[tileIndex].getFireForce();
	             
	             g2.setColor(colors[c]);
	             g2.fillRect(x, y, tileSize, tileSize);
	                
	                // Desenha o índice do tile
	               if(debugMode) {
	            	   g2.setColor(Color.WHITE);
		                g2.drawString(String.valueOf(this.fire[tileIndex].getFireForce()), x + tileSize / 2 - 5, y + tileSize / 2 + 5);  
	               }
	                
			}
		
		}
		
		g2.dispose();
	}
	
}
