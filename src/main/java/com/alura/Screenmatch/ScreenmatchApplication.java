package com.alura.Screenmatch;

import com.alura.Screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			//ya esta migrado
//		var consumoApi= new ConsumoAPI();
//		var json = consumoApi.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=630989c3");
//		System.out.println(json);
////TODO add --------------
//		ConvierteDatos conversor = new ConvierteDatos();
//		var datos = conversor.obtenerDatos(json, DatosSerie.class);
//		System.out.println(datos);
//		json= consumoApi.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=630989c3");
//		DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
//		System.out.println(episodios);
		//fin de bloque
		Principal principal= new Principal();
		principal.muestraElmenu();


	}

	public static class LaserShooter extends JFrame {
		private int startX, startY, endX, endY;
		private boolean shooting;

		public LaserShooter() {
			setTitle("Laser Shooter");
			setSize(800, 600);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setResizable(false);

			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					startX = e.getX();
					startY = e.getY();
					endX = startX;
					endY = startY;
					shooting = true;
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					shooting = false;
					repaint();
				}
			});

			addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					if (shooting) {
						endX = e.getX();
						endY = e.getY();
						repaint();
					}
				}
			});

			setVisible(true);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if (shooting) {
				g.setColor(Color.RED);
				g.drawLine(startX, startY, endX, endY);
			}
		}

		public static void main(String[] args) {
			SwingUtilities.invokeLater(() -> new LaserShooter());
		}
	}
}
