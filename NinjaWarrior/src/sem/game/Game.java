package sem.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.JFrame;

import sem.game.entity.Entity;
import sem.game.entity.Player;
import sem.game.gfx.Sprite;
import sem.game.gfx.SpriteSheet;
import sem.game.input.KeyInput;
import sem.game.tile.Wall;

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

	
	//------------- CAMERA ------------------
	
	public static Camera cam;
	
	//------------- SPRITESHEET -------------
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
	
	
	
	public Game()
	{
		
		Dimension velicina = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(velicina);
		setMaximumSize(velicina);
		setMinimumSize(velicina);
		
		try
		{
			
		
			
			bg = ImageIO.read(getClass().getResource("/BG.png"));

		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void init()
	{
		handler = new Handler();
		cam = new Camera();
		addKeyListener(new KeyInput());
		
		sheet64 = new SpriteSheet("/spritesheet64.png", 64, 64);
		simpleEnemy = new Sprite[6];
		
		for(int i=0;i<simpleEnemy.length;i++)
		{
			simpleEnemy[i] = new Sprite(sheet64,i+1,3);
		}
		
		
		try
		{
			level = ImageIO.read(getClass().getResource("/ninjalevel.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		handler.createLevel(level);
		
	
	}
	
	public synchronized void start()
	{
		
		if(running) return;
		else
		{
			running=true;
			nit = new Thread(this,"Nit");
			nit.start();
		}
		
	}
	
	public synchronized void stop()
	{
		if(!running) return;
		else
		{
			running=false;
			try
			{
				nit.join();
			} catch (InterruptedException e)
			{
			
				e.printStackTrace();
				
			};
		}
		
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(221,248,255));
		g.fillRect(0, 0,getWidth(), getHeight());
		
		
		
		g.translate(cam.getX(), cam.getY());
		g.drawImage(bg, -getWidth()+100, 0, getWidth()+100, getHeight()+100,null);
		
		for(int i=0;10>i;i++) 
		g.drawImage(bg, i*(getWidth()+100), 0, getWidth()+100, getHeight()+100,null);
		handler.render(g);
		g.dispose();
		bs.show();
		
	}
	
	public void update()
	{
		handler.update();
		
		for(Entity e:handler.entity)
		{
			if(e.getId()==Id.player)
			{
				cam.update(e);
			}
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
		int frames=0;
		int updates=0;
		
		
		while(running)
		{
			
			long sad = System.nanoTime();
			delta += (sad-pvreme)/ms;
			pvreme=sad;
			while(delta>=1)
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
				timer+=1000;
				System.out.println(frames + " FPS "+updates+" UPS");
				frames=0;
				updates=0;
			}
			
			
		}
		stop();
		
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
