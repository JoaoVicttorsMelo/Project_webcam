/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cam;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Joao
 */
public class CamTeste extends javax.swing.JPanel implements Runnable {
        private VideoCapture video;
	private Mat frame;
	private BufferedImage buff;
    public CamTeste() {
        initComponents();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new Thread(this).start();

    }
    @Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (buff == null) {
			return;
		}
		g.drawImage(buff,0,0,buff.getWidth(), buff.getHeight(), null);
	}
        @Override
	public void run() {
		this.video = new VideoCapture(0);
		this.frame = new Mat();
		if (video.isOpened()) {
			while (true) {
				video.read(frame);
				if (!frame.empty()) {
					MatToBufferedImage(frame);
					this.repaint();
				}
			}
		}
	}
        private void MatToBufferedImage(Mat mat) {
		int height = mat.height();
		int width = mat.width();
		int channel = mat.channels();
		byte[] source = new byte[height * width * channel];
		mat.get(0, 0, source);
		buff = new BufferedImage(width,height, BufferedImage.TYPE_3BYTE_BGR);
		final byte[] saida = ((DataBufferByte) buff.getRaster().getDataBuffer()).getData();
		System.arraycopy(source, 0, saida, 0, source.length);
	}



 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
