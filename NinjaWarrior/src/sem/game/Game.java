package sem.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import sem.game.entity.Entity;
import sem.game.gfx.Sprite;
import sem.game.gfx.SpriteSheet;
import sem.game.gfx.gui.Launcher;
import sem.game.input.KeyInput;
import sem.game.input.MouseInput;

public class Game extends Canvas implements Runnable
{
	
	//---------- PUBLIC STATIC  -------------------
	
		private static final long serialVersionUID = 1L;
		public static int WIDTH = 270;
		public static int HEIGHT = WIDTH/14*10-26;
		public static int SCALE = 4;
	
	//------------- HANDLER ----------------
	
		public static Handler handler;
	
	//------------- SPRITESHEET -------------
	
		public static SpriteSheet sheetPlayer;
		public static SpriteSheet sheet;
		public static SpriteSheet sheet64;
		public static SpriteSheet coinsSheet;
		
	//------------- IMAGE -------------
		
		public static Image box ;
		public static Image teleport; 
		public static Image deathScreenBackground;
		
	//------------- CAMERA, LAUNCHER ------------------ 
	
		public static Camera cam;
		public static Launcher launcher;
		public static MouseInput mouseInput = new MouseInput();
		
	//------------- SPRITE -------------
		
		public static Sprite player[] = new Sprite[30];
		public static Sprite grass;
		public static Sprite[] simpleEnemy;
	
	//---------- BOOLEAN -------------------
		
		public boolean running = false;
		public boolean t = true;
	
	//---------- THREAD -------------------
		
		private Thread nit;
	
	//---------- BUFFEREDIMAGE ------------
	
		private BufferedImage level;
		public static BufferedImage bg;
		
		public static BufferedImage kunai_l;
		public static BufferedImage kunai_r;
		public static BufferedImage kunai_coin;
		
		public static BufferedImage playerJumpRight[] = new BufferedImage[10];
		public static BufferedImage playerJumpLeft[] = new BufferedImage[10];

		public static BufferedImage playerThrowRight[] = new BufferedImage[10];
		public static BufferedImage playerThrowLeft[] = new BufferedImage[10];
		
	//----------  COINS, LIVES, GAME OVER AND MENU  ------------
	
		public static Sprite coin;
		public static int coins = 0; 
		
		public static int lives = 3;
		
		public static int deathScreenTime = 0;
		public static boolean showDeathScreen = true;
		public static boolean begining = true;
		
		public static boolean gameOver = false;
		
		public static boolean playing = false;
		
	
	//-------- SMALL PALYER --------
		
		public static Sprite smallPlayer;
		public static Sprite invBorder;
		
	//-------- CONSTRUCTOR ---------	
	
	public Game()
	{
		
		Dimension velicina = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(velicina);
		setMaximumSize(velicina);
		setMinimumSize(velicina);
		
		try
		{
			bg = ImageIO.read(getClass().getResource("/BG.png"));

		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void init()
	{
		handler = new Handler();
		cam = new Camera();
		launcher = new Launcher();
		
		addKeyListener(new KeyInput());
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		
		sheet64 = new SpriteSheet("/spritesheet64.png", 64, 64);
		simpleEnemy = new Sprite[6];
		
		for(int i=0;i<simpleEnemy.length;i++)
		{
			simpleEnemy[i] = new Sprite(sheet64,i+1,3);
		}
		
		// jump
		
		for(int i=0; 10>i;i++)
		{
		    try 
		    {
		    	playerJumpRight[i] = ImageIO.read(getClass().getResource("/idle/jump__00"+i+"-min.png"));
		    	playerJumpLeft[i] = ImageIO.read(getClass().getResource("/idle/Jump__10"+i+".png"));
			   
		    	playerThrowRight[i] = ImageIO.read(getClass().getResource("/idle/Throw__00"+i+".png"));
		    } 
		    catch (IOException e) 
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
		
		smallPlayer = new Sprite(sheet64, 1, 4);
		invBorder = new Sprite(sheet64, 16, 1);
		
		coinsSheet = new SpriteSheet("/coins_g.png", 64, 64); 
		coin = new Sprite(coinsSheet, 1, 1);
		
		// novoooo
		
		try 
		{
			deathScreenBackground = ImageIO.read(getClass().getResource("/BG2.png")); //aaa
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		   
		
		// teleport slika
		
		try
		{
			teleport = ImageIO.read(getClass().getResource("/teleport.png"));
		}
		catch (IOException e2)
		{
			e2.printStackTrace();
		}
		
		// kunai slike
		
		try
		{
			kunai_r = ImageIO.read(getClass().getResource("/Kunai.png"));
			kunai_l = ImageIO.read(getClass().getResource("/Kunai_l.png"));
			kunai_coin = ImageIO.read(getClass().getResource("/kunai_coin.jpg")); // novo
		} 
		
		catch (IOException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		// level slike
		
		try
		{
				level = ImageIO.read(getClass().getResource("/ninjalevel.png"));
		} 
		
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //samo ubaci coins
		
		
		// kutija slika
		
		try
		{
			box = ImageIO.read(getClass().getResource("/box.png"));
		} catch (IOException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		//sto dva puta? o.O
		
		/*
		try
		{
			level = ImageIO.read(getClass().getResource("/ninjalevel.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	//	handler.createLevel(level);
	}
	
	public synchronized void start()
	{
		if (running) return;
		else
		{
			running = true;
			nit = new Thread(this,"Nit");
			nit.start();
		}
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		else
		{
			running = false;
			try
			{
				nit.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();	
			};
		}
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(221,248,255));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// deathScreen, lives ...
		
		if (playing && !gameOver) g.translate(cam.getX(), cam.getY());
		
		g.drawImage(bg, -getWidth()+400, 530, getWidth()+400, getHeight()+520,null);
		
		for(int i=0;30>i;i++) 
		g.drawImage(bg, i*(getWidth()+400), 530, getWidth()+400, getHeight()+520,null);
		
		if (playing) handler.render(g);
		else launcher.render(g);
		
		if (!showDeathScreen) 
		{
			
		//coins	
			if (cam.xPlayer > 560) g.drawImage(Game.coin.getBufferedImage(), cam.xPlayer - 500,  cam.yPlayer - 250 + cam.pomY, 50, 50, null);
			else g.drawImage(Game.coin.getBufferedImage(), 70,  cam.yPlayer - 250 + cam.pomY, 50, 50, null);
				
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.CENTER_BASELINE, 55));
			if (cam.xPlayer > 560) g.drawString("x" + coins, cam.xPlayer - 440,  cam.yPlayer - 210 + cam.pomY);
			else g.drawString("x" + coins, 130,  cam.yPlayer - 210 + cam.pomY);
			
		//zivoti	
			if (cam.xPlayer > 560) g.drawImage(Game.smallPlayer.getBufferedImage(), cam.xPlayer - 340,  cam.yPlayer - 260 + cam.pomY, 60, 60, null);
			else g.drawImage(Game.smallPlayer.getBufferedImage(), 230,  cam.yPlayer - 260 + cam.pomY, 60, 60, null);
				
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", Font.CENTER_BASELINE, 55));
			if (cam.xPlayer > 560) g.drawString("x" + lives, cam.xPlayer - 290,  cam.yPlayer - 210 + cam.pomY);
			else g.drawString("x" + lives, 280,  cam.yPlayer - 210 + cam.pomY);
			
		//municija	
			if (cam.shooting)
			{
				if (cam.xPlayer > 560) g.drawImage(Game.kunai_coin, cam.xPlayer - 180,  cam.yPlayer - 260 + cam.pomY, 60, 60, null);
				else g.drawImage(Game.kunai_coin, 380,  cam.yPlayer - 260 + cam.pomY, 60, 60, null);
					
				g.setColor(Color.WHITE);
				g.setFont(new Font("Courier", Font.CENTER_BASELINE, 55));
				if (cam.xPlayer > 560) g.drawString("x" + cam.munition, cam.xPlayer - 110,  cam.yPlayer - 210 + cam.pomY);
				else g.drawString("x" + cam.munition, 450,  cam.yPlayer - 210 + cam.pomY);
			}
			
		}
		
		if (showDeathScreen)
		{	
			if (!gameOver && begining)
			{
				deathScreenTime = 180;
				begining = false;
				System.out.println("proslo");
			}
			else if (!gameOver)
			{
				System.out.println("proslo1");
				g.setColor(Color.white);
				g.setFont(new Font("Courier", Font.CENTER_BASELINE, 60));
				g.drawImage(Game.smallPlayer.getBufferedImage(), cam.xPlayer - 80, cam.yPlayer - 150, 70, 70, null); // igrac treba
				g.drawString("x" + lives, cam.xPlayer, cam.yPlayer - 100);
			}
			else 
			{
				System.out.println("proslo2");
				// ne radi nista osim launcher -.-
				g.setColor(Color.white);
				g.setFont(new Font("Courier", Font.CENTER_BASELINE, 60));
			//	g.drawImage(Game.smallPlayer.getBufferedImage(), cam.xPlayer - 80, cam.yPlayer - 150, 70, 70, null); // igrac treba
				g.drawString("Game over", cam.xPlayer, cam.yPlayer - 100);
				launcher.render(g);
			}
		}
			
		g.dispose();
		bs.show();
	}
	
	public void update()
	{
		if (playing) handler.update();
		
		for(Entity e:handler.entity)
		{
			if(e.getId()==Id.player)
			{
				cam.update(e);
			}
		}
		
		if (showDeathScreen && !gameOver) deathScreenTime++;
		
		if (deathScreenTime >= 180)
		{
			showDeathScreen = false;
			deathScreenTime = 0;
			handler.clearLevel();
			handler.createLevel(level);
		}
	}
	
	@Override
	public void run()
	{
		init();
		requestFocus();
	
		long pvreme = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0 ;
		double ms  = 1000000000.0/60.0;
		int frames = 0;
		int updates = 0;
		
		while(running)
		{
			
			long sad = System.nanoTime();
			delta += (sad - pvreme)/ms;
			pvreme = sad;
			
			while (delta >= 1)
			{
				update();
				updates++;
				delta--;
				render();
				frames++;
				update();
			}
			
			if(System.currentTimeMillis()-timer>1000)
			{
				timer += 1000;
				System.out.println(frames + " FPS "+updates+" UPS");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	// dodala za lepotu i logicnije je nekako
	
	public static void newGame()
	{
		coins = 0;
		
		playing = true;
		lives = 3;
		gameOver = false;
		
		deathScreenTime = 0;
		showDeathScreen = true;
		
		begining = true;
	}

	//---------- MAIN -------------------
	
	public static void main(String[] args)
	{
		Game g = new Game();
		JFrame frame = new JFrame("Game");
		frame.add(g);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		g.start();
	}
}