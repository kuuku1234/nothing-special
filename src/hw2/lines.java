package hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class lines {

			static int testcase;
			static int N;
			static Pos[] input;
			static outputs[] notyet;
			static Pos[] a;
	public static void main(String[] args) {
		try {
			

			File file = new File("C:\\hw2\\input.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			
			File file2 = new File("C:\\hw2\\2014147042.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
		
			String line = "";
			
			testcase = Integer.parseInt(bufReader.readLine());		
			for(int i =0; i<testcase; i++) {
				bufReader.readLine();
				N = Integer.parseInt(bufReader.readLine());
				notyet = new outputs[N*N*N];
				outputs[] notyet2 = new outputs[N*N*N];
				System.arraycopy(notyet,0, notyet2, 0, N*N);
				input = new Pos[N];
				for(int j =0; j<N; j++) {
					String[] s = bufReader.readLine().split("\\s");
					int x = Integer.parseInt(s[0]);
					int y = Integer.parseInt(s[1]);
					input[j]= new Pos(x,y);				
				}
				/*
				for(int ii = 0; ii<N; ii++) {
				System.out.println(input[ii].x + ", "+ input[ii].y);
				}
				*/
				point();
				for(int ii = 0; ii<N; ii++) {
				small();//겹치는거 제거 겹치는게 너무많음  
				}
				
				a = givemeans();
				for(int i3 = 0; i3<N; i3++) {
					smaller();//겹치는 거 제거
				}
				
				int countt = 0;
				try {
					while(a[countt] != null) {
			
						countt++;
						
					}
				}
				catch (NullPointerException e) {
					
				}
				
				
				
				
				if(file2.isFile() && file.canWrite()) {
					bufferedWriter.write(countt+"");
					bufferedWriter.newLine();
					for(int z =0; z<countt; z++) {
						bufferedWriter.write(a[z].x+" "+a[z].y);
						bufferedWriter.newLine();
						
					}
					bufferedWriter.newLine();
				
				}
				
				/*
				try {
					int count = 0;
					while(a[count] != null) {
						System.out.print(a[count].x +" ,"+a[count].y);
						System.out.println();
						count++;
					}
				}
				catch (NullPointerException e){
					
				}
				*/
				/*for(int k = 0; k<notyet.length; k++) {
					int count = 0;
					try {
					while(notyet[k].z[count] != null) {
						System.out.print(notyet[k].m +", "+notyet[k].n +", "+ notyet[k].z[count].x+ ", " + notyet[k].z[count].y+"    "+k);
						System.out.println();
						count++;
					}
					}
					catch (NullPointerException e){
						
					}
				}*/

				
				
				System.out.println("next");
				notyet = notyet2;
			}
		
		
		
		bufferedWriter.close();
		bufReader.close();
		
		}
		catch (FileNotFoundException e) {
			
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
public static class Pos{
		double x;
		double y;
		Pos(double x, double y){
			this.x = x;
			this.y = y;
		}
	}

public static class outputs{
	double m;
	double n;
	Pos[] z = new Pos[N];
	outputs() {};
	outputs(double m,double n) {
		this.m = m;
		this.n = n;
		
	}
}
public static void point() {
	int nycount = 0;
	for(int i =0; i<N; i++) {
		for(int j =i+1; j<N; j++) {
			if(input[i].x - input[j].x != 0) {
				int count = 0;
				double a = ((double)(input[i].y-input[j].y))/((double)( input[i].x-input[j].x));
				double b = (double)(input[i].y) - a *(double)(input[i].x); 	
				
				outputs s = new outputs(a,b);
				for(int k = 0; k<N; k++) {
					if(a*(double)input[k].x +b == (double)input[k].y) {
						s.z[count] = new Pos(input[k].x,input[k].y);
						count++;
					}
					if(count>=3) {
						notyet[nycount] = s;
						nycount++;
					}
				}
			}
			else if(input[i].x - input[j].x == 0) {
				int count = 0;
				outputs s = new outputs(input[i].x,-999999999);
				for(int k =0; k<N; k++) {
					if(input[i].x == input[k].x) {
						s.z[count] = new Pos(input[k].x,input[k].y);
						count++;
					}
					if(count>=3) {
						notyet[nycount] = s;
						nycount++;
					}
				}
			}
		}
	}
}





public static void smaller() {
	for(int i =0; i<a.length; i++) {
		for(int j =i+1; j<a.length; j++) {
		try{
			if(a[i].x == a[j].x && a[i].y == a[j].y) {
			for(int k =i; k<a.length-1; k++) {
				a[k] = a[k+1];
			}
		}
		}
		catch (NullPointerException e) {
			
		}
		}
	}
}
public static void small() {
	for(int i =0; i<notyet.length; i++) {
		for(int j = i+1; j<notyet.length; j++) {
		try {
		if(notyet[i].m == notyet[j].m && notyet[i].n == notyet[j].n) {
			for(int k =i; k<notyet.length-1; k++) {
				notyet[k] = notyet[k+1];
				
			}
		
		}
		
		
		
		}
		catch (NullPointerException e) {
			
		}
		}
	}
}



public static double[] calinfinity(int index) {
	double[] limitedline = new double[3];
	int count = 0;
	double smally = 999999999;
	double bigy = -999999999;
	try {
	while(notyet[index].z[count] != null) {
		
		if(smally>notyet[index].z[count].y) {
			smally = notyet[index].z[count].y;
		}
		
		if(bigy<notyet[index].z[count].y) {
			bigy = notyet[index].z[count].y;
		}
		count++;
	}
	limitedline[0] = notyet[index].m;
	limitedline[1] = smally;
	limitedline[2] = bigy;
	
	}
	catch (NullPointerException e) {
		
	}
	return limitedline;
}


public static double[] calnormal(int index) {
	double[] limitedline = new double[6];

	if(notyet[index].n != -999999999) {
		double tilt = notyet[index].m;
		double b = notyet[index].n;
		int count = 0;
		double smallx = 999999999;
		double smally = 999999999;
		double bigx = -999999999;
		double bigy = -999999999;
		try {
		while(notyet[index].z[count] != null) {
			if(smallx>notyet[index].z[count].x) {
				smallx = notyet[index].z[count].x;
			}
			if(smally>notyet[index].z[count].y) {
				smally = notyet[index].z[count].y;
			}
			if(bigx<notyet[index].z[count].x) {
				bigx = notyet[index].z[count].x;
			}
			if(bigy<notyet[index].z[count].y) {
				bigy = notyet[index].z[count].y;
			}
			count++;
		}
		limitedline[0] = smallx;
		limitedline[1] = smally;
		limitedline[2] = bigx;
		limitedline[3] = bigy;
		limitedline[4] = tilt;
		limitedline[5] = b;
		
		}
		catch (NullPointerException e) {
			
		}
		}
	return limitedline;
}

public static Pos[] givemeans() {
	double[] compare;
	double[] compare2;
	double[] compare3;
	Pos[] answer = new Pos[N*N*N];
	int count = 0;
	for(int i =0; i<notyet.length; i++) {
		try {
		if(notyet[i].n == -999999999) {
			compare= calinfinity(i);
			for(int j =0; j<notyet.length; j++) {
			try {	
				if(notyet[j].n != -999999999) {
					compare2 = calnormal(j);
					if(compare2[0]<=compare[0] && compare[0]<=compare2[2]) {
						if( compare2[4]*compare[0] +compare2[5] <=compare[2] && compare2[4]*compare[0] +compare2[5] >=compare[1]) {
							answer[count] = new Pos(compare[0],compare2[4]*compare[0] +compare2[5] );
							count++;
						}
					}
				}
			}
			catch (NullPointerException e) {
				
			}
				
			}
		}
		else {
			compare2 = calnormal(i);
			for(int j =0; j<notyet.length; j++) {
				if(notyet[j].n != -999999999) {
					compare3 = calnormal(j);
					if(compare2[4] != compare3[4] &&compare2[5] != compare3[5]) {
						if(compare2[4] != compare3[4]) {
										
										double ansx = compare3[5]-compare2[5]/(compare2[4]-compare3[4]);
										double ansy = compare2[4]*ansx + compare2[5];
										if(ansx<=compare2[2]&&compare2[0]<=ansx && compare2[1]<=ansy &&  ansy<=compare2[3] ) {
										answer[count] = new Pos(ansx, ansy); 
										count++;
										}
										
								
							}
						}
					}
				}
			}
		}
	
	catch (NullPointerException e) {
		
	}
		
		
	}
	return answer;
}
}