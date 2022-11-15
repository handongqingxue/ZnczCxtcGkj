package com.znczCxtcGkj.print;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;

import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.znczCxtcGkj.util.APIUtil;

//ִ�д�ӡʱ��Ҫ����commons-io-1.4.jar
/**
 * 	�ô�ӡ����ӡ��ά��
 * @author lhb
 *
 */
public class QrcodePrint {

	static Logger logger = LoggerFactory.getLogger(QrcodePrint.class);

	/**
	 * ����ͼƬ����·����ӡ��ͼƬ(���ڴ�ӡ����ͼƬ)
	 * @param fileName
	 * @param count
	 */
	public static void drawImage(String fileName, int count) {
		//https://www.cnblogs.com/Java-Starter/p/9283575.html
		FileInputStream fin = null;
		try {
			DocFlavor dof = null;
			if (fileName.endsWith(".gif")) {
				dof = DocFlavor.INPUT_STREAM.GIF;
			} else if (fileName.endsWith(".jpg")) {
				dof = DocFlavor.INPUT_STREAM.JPEG;
			} else if (fileName.endsWith(".png")) {
				dof = DocFlavor.INPUT_STREAM.PNG;
			}

			PrintService ps = PrintServiceLookup.lookupDefaultPrintService();

			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(OrientationRequested.PORTRAIT);

			pras.add(new Copies(count));
			pras.add(PrintQuality.HIGH);
			DocAttributeSet das = new HashDocAttributeSet();
			das.add(MediaSize.findMedia(80, 80, MediaPrintableArea.MM));
			// ���ô�ӡֽ�ŵĴ�С���Ժ���Ϊ��λ��
			// das.add(new MediaPrintableArea(0,1000, 5, 5, MediaPrintableArea.MM));
			fin = new FileInputStream(fileName);

			Doc doc = new SimpleDoc(fin, dof, das);

			DocPrintJob job = ps.createPrintJob();

			job.print(doc, pras);
			fin.close();
			System.out.println("��ӡ�ɹ����ļ���" + fileName + "����Ϊ��" + count);
			// logger.info("��ӡ�ɹ����ļ���"+fileName+"����Ϊ��"+count);
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (PrintException pe) {
			pe.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fin);
		}
	}

	public static void drawImage(byte[] data) {
		try {
			PrintService ps = PrintServiceLookup.lookupDefaultPrintService();

			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(OrientationRequested.PORTRAIT);

			pras.add(new Copies(1));
			pras.add(PrintQuality.HIGH);
			DocAttributeSet das = new HashDocAttributeSet();
			das.add(MediaSize.findMedia(80, 80, MediaPrintableArea.MM));
			DocFlavor dof = DocFlavor.BYTE_ARRAY.PNG;
			Doc doc = new SimpleDoc(data, dof, das);
			DocPrintJob job = ps.createPrintJob();
			logger.warn("��ӡ���");

			job.print(doc, pras);
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("��ӡʧ��");
		}
	}

	public static void drawImage(InputStream data) {
		if (data == null) {
			logger.warn("û�н��յ���ӡ��Ϣ");
			return;
		}
		try {
			PrintService ps = PrintServiceLookup.lookupDefaultPrintService();

			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(OrientationRequested.PORTRAIT);

			pras.add(new Copies(1));
			pras.add(PrintQuality.HIGH);
			DocAttributeSet das = new HashDocAttributeSet();
			das.add(MediaSize.findMedia(80, 80, MediaPrintableArea.MM));
			DocFlavor dof = DocFlavor.INPUT_STREAM.PNG;
			Doc doc = new SimpleDoc(data, dof, das);
			DocPrintJob job = ps.createPrintJob();
			logger.warn("��ӡ���");

			job.print(doc, pras);
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.warn("��ӡʧ��");
		}
	}
	
	/**
	 * 	����ͼƬurl ��ӡ��ͼƬ��Ϣ(���ڴ�ӡ����ͼƬ)
	 * @param url
	 */
	public static void drawImage(String url) {
		if (url == null) {
			logger.warn("url����Ϊ��");
		}
		InputStream fin = null;
		try {
			fin = APIUtil.getPngFile(url);
			drawImage(fin);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	

	public static void main(String[] args) {
		//String fileName="D:/resource/ZnczCxtc/CheLiang/Zp/1667615487681.jpg";
		//QrcodePrint.drawImage(fileName, 1);
		String fileName="http://localhost:8080/ZnczCxtc/upload/CheLiang/Xsz/1665128472945.jpg";
		QrcodePrint.drawImage(fileName);
//		try {
//			FileInputStream fin = new FileInputStream("D:/3dcode.png");
//			byte[] byt = new byte[fin.available()];
//			fin.read(byt);
//			drawImage(byt);
//			fin.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		drawImage("download-files/f08a9df6e273d40eeebaf08d839f9008/3dcode.png");
		
		//String url = "./download-files/f08a9df6e273d40eeebaf08d839f9008/3dcode.png";
		
		//System.out.println("[" + url.substring(1, url.length()) + "]");
		
	}
}