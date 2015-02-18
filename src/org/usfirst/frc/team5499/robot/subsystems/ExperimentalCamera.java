//package org.usfirst.frc.team5499.robot.subsystems;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfPoint;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.highgui.Highgui;
//import org.opencv.highgui.VideoCapture;
//import org.opencv.imgproc.Imgproc;
//
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
//
///*
// * @author: Elijah Kaufman and his team
// * 	
// * Modified from original by Berkeley High Robotics
// */
//
//
//public class ExperimentalCamera{
//	static NetworkTable table;
//	static Thread Capture;
//	static Thread Process;
//	static Thread Send;
//	//Color constants
//	public static final Scalar 
//	Red = new Scalar(0, 0, 255),
//	Blue = new Scalar(255, 0, 0),
//	Green = new Scalar(0, 255, 0),
//	Yellow = new Scalar(0, 255, 255),
//	//for tape
//	thresh_Lower = new Scalar(0,110,0),
//	thresh_Higher = new Scalar(255,255,134);
//
//	public static ArrayList<MatOfPoint> contours = new ArrayList<>();
//	public static Mat frame, original;
//	static int counter = 0;
//
//	static final int VIEW_ANGLE = 60; //Don't know how one gets this value. This is what they have in the advanced vision example for our camera in 640x480.
//	static final int Y_IMAGE_RES = 480;
//	static final int X_IMAGE_RES = 640;
//
//	public static double distToTarget;
//
//
//	public static void main(String[] args) {
//		//required for openCV to work -call before any functions of oCV are used
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		//initialize network tables
//		NetworkTable.setClientMode();
//		//the ip of the smartdashboard is "roborio-####.local" where #### is team number
//		NetworkTable.setIPAddress("roborio-5499.local");
//		table = NetworkTable.getTable("SmartDashboard");
//
//		//main loop of the program
//		while(true){
//			try {
//				while(table.isConnected()){
//					processImage();
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				break;
//			}
//		}
//	}
//
//	private static void getDistToTarget(Rect target){
//		//vertical height of the target in inches. target = retro-reflective tape;
//		final double targetHeight = 7;
//
//		//get the height of the found targets in px
//		int height = target.height;
//		//d = Tft*FOVpixel/(2*Tpixel*tanPheta)
//		distToTarget = Y_IMAGE_RES * targetHeight
//				/ (height * 12 * 2 * Math.tan(VIEW_ANGLE * Math.PI / (180 * 2)));
//	}
//
//	//connects to the usb camera and opens a new snapshot "instance"
//	public static void processImage(){
//		try {			
//			//comment if testing a regular file
//			VideoCapture capture = new VideoCapture(0);
//			if(!capture.isOpened()){
//				System.out.println("Camera not found!");
//			} else{
//				System.out.println("Found camera " + capture.toString());
//			}
//			capture.set(3, 640); //CV_CAP_PROP_FRAME_WIDTH    =3,
//			capture.set(4, 480); //CV_CAP_PROP_FRAME_HEIGHT   =4,
//			capture.retrieve(original);
//			Highgui.imwrite("original.png", original);
//
//			//time for the OpenCV fun!
//			frame = original.clone();
//			//applies a threshhold in the form of BlueGreenRed
//			Core.inRange(original, thresh_Lower, thresh_Higher, frame);
//			//find the cluster of particles
//			Imgproc.findContours(frame, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//			//iterating through the list of contours and removing the ones with an "area" less then 100
//			for (Iterator<MatOfPoint> iterator = contours.iterator(); iterator.hasNext();) {
//				MatOfPoint matOfPoint = (MatOfPoint) iterator.next();
//				if(matOfPoint.width() * matOfPoint.height() < 20){
//					iterator.remove();
//				}
//			}
//			//if theres only one contour dont do the silly math of the bounding rectangles
//			if(contours.size() == 1){
//				Rect rec1 = Imgproc.boundingRect(contours.get(0));
//				Core.rectangle(original, rec1.tl(), rec1.br(), Yellow);
//				String string = "TargetFound at X:" + (rec1.tl().x + rec1.br().x) / 2 + "Y:" + (rec1.tl().y + rec1.br().y) / 2;
//				Core.putText(original, string, new Point(200,frame.size().height-10), Core.FONT_HERSHEY_PLAIN, 1, Red);
//				getDistToTarget(rec1);
//			}
//			//i wonder if there are two totes...
//			else if(contours.size() == 2 || contours.size() == 4){
//				ArrayList<Rect> target1 = new ArrayList<Rect>();
//				ArrayList<Rect> target2 = new ArrayList<Rect>();
//				target1.add(Imgproc.boundingRect(contours.get(0)));
//				target1.add(Imgproc.boundingRect(contours.get(1)));
//				if(contours.size() == 4){
//					target2.add(Imgproc.boundingRect(contours.get(2)));
//					target2.add(Imgproc.boundingRect(contours.get(3)));
//				}
//				Point tl = target1.get(0).tl();
//				Point br = target1.get(0).br();
//				//outer bounds of one rectangle
//				for(Rect rec : target1){
//					if(tl.x > rec.tl().x){
//						tl.x = rec.tl().x;
//					}
//					if(tl.y > rec.tl().y){
//						tl.y = rec.tl().y;
//					}
//					if(br.x < rec.br().x){
//						br.x = rec.br().x;
//					}
//					if(br.y < rec.br().y){
//						br.y = rec.br().y;
//					}
//					table.putBoolean("TargetVisible", true);
//				}
//				Rect bb1 = new Rect(tl, br);
//				Core.rectangle(original, bb1.br(),bb1.tl(), Yellow);
//				Rect bb2 = null;
//				//the 4 L will make 4 contours
//				//if there was 4 contours then fill the outer bounds of the second rectangle
//				if(!target2.isEmpty()){
//					tl = target2.get(0).tl();
//					br = target2.get(0).br();
//					for(Rect rec : target2){
//						if(tl.x > rec.tl().x){
//							tl.x = rec.tl().x;
//						}
//						if(tl.y > rec.tl().y){
//							tl.y = rec.tl().y;
//						}
//						if(br.x < rec.br().x){
//							br.x = rec.br().x;
//						}
//						if(br.y < rec.br().y){
//							br.y = rec.br().y;
//						}
//					}
//					bb2 = new Rect(tl, br);
//					table.putBoolean("TargetVisible", true);
//				}
//				String string;
//				if(null != bb2){
//					Core.rectangle(original, bb2.br(),bb2.tl(), Yellow);
//					//checking to see if the totes line up, because stacked totes line up!
//					if(Math.abs(bb1.x-bb2.x) < 5){
//						string = "Two totes stacked";
//						Core.line(original, new Point(bb1.x+bb1.width/2,bb1.y+bb1.height/2),
//								new Point(bb2.x+bb2.width/2,bb2.y+bb2.height/2), Green);
//						getDistToTarget(bb1);
//					}
//					else{
//						string = "Two totes found";
//					}
//				}
//				else{
//					//knowing how far off the center of the image you can set your robot to align with it, essentially going towards it
//					//string = "string" + (x-coord of target + width of target/2 - horiz pixel count of img)
//					//|.......<---------|---------------|//
//					//^img^^^target     ^center^^^offset^imgBound
//					// Bound
//					//|---------------------------------|
//					//^-----------WidthOfImg------------^
//					
//					string = "Off center by " + (bb1.x + bb1.width/2 - X_IMAGE_RES) + " pixels";
//					table.putNumber("Off Center", (bb1.x + bb1.width/2 - X_IMAGE_RES));
//					table.putNumber("ToteToScreen", bb1.width / X_IMAGE_RES);
//					getDistToTarget(bb1);
//				}
//				//putting the data in a visible form
//				Core.putText(original, string, new Point(200,frame.size().height-10), Core.FONT_HERSHEY_PLAIN, 1, Red);
//				table.putBoolean("TargetVisible", true);
//			}
//			else if(contours.size() == 3){
//				ArrayList<Rect> rect = new ArrayList<>();
//				for(MatOfPoint mOP : contours){
//					rect.add(Imgproc.boundingRect(mOP));
//					Rect temp = rect.get(rect.size()-1);
//					Core.rectangle(original, temp.br(),temp.tl(), Red);
//				}
//				for(int i = 1; i < contours.size();i++){
//					if(Math.abs(rect.get(i).y - rect.get(i-1).y) < 5){
//
//						Rect r1 = rect.get(i);
//						Rect r2 = rect.get(i-1);
//						Point tl = r1.tl();
//						Point br = r1.br();
//						if(tl.x > r2.tl().x){
//							tl.x = r2.tl().x;
//						}
//						if(tl.y > r2.tl().y){
//							tl.y = r2.tl().y;
//						}
//						if(br.x < r2.br().x){
//							br.x = r2.br().x;
//						}
//						if(br.y < r2.br().y){
//							br.y = r2.br().y;
//						}
//						break;
//					}
//				}
//				table.putBoolean("TargetVisible", true);
//			}
//			else{
//				table.putBoolean("TargetVisible", false);
//				for(MatOfPoint mOP : contours){
//					Rect rec = Imgproc.boundingRect(mOP);
//					Core.rectangle(original, rec.tl(), rec.br(), Red);
//				}
//				contours.clear();
//			}
//			//gui on the image
//			Core.line(original, new Point(frame.width()/2,100),new Point(frame.width()/2,frame.height()-100), Blue);
//			Core.line(original, new Point(150,frame.height()/2),new Point(frame.width()-150,frame.height()/2), Blue);
//			//releases the mats from ram...frees up so much memory!
//			original.release();
//			frame.release();
//			//mostly for debugging but errors happen
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();	
//		}
//	}
//
//
//}
//
