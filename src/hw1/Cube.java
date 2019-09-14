package hw1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
public class Cube {
	
	static int testcase;
	static int N;
	static int h;
	
	
	public static void main(String[] args) {
		try {
			
			File file = new File("C:\\hw1\\input.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			
			File file2 = new File("C:\\hw1\\2014147042.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
			
			
			
			
			String line = "";
			
			testcase = Integer.parseInt(bufReader.readLine());
			
		for(int zd = 0; zd<testcase; zd++) {
			String[] s = bufReader.readLine().split("\\s");
			N = Integer.parseInt(s[0]); 
			h = Integer.parseInt(s[1]);	
			String s1 = bufReader.readLine();
			
			
			String[] s2 = s1.split("\\s");
			String[][] inputfake = new String[h][3];
			
			
			
			
			int[][] input = new int[h][3];
			
			int count = 0;
			while(h-count != 0) {
				
				for(int i = 0; i<3; i++) {
					inputfake[count][i] = s2[i+count*3];
					
				
				}
				
				count++;
			}
			
			for(int i =0; i<inputfake.length; i++) {
				for(int j =0; j<inputfake[i].length; j++) {
					input[i][j] = Integer.parseInt(inputfake[i][j]);
				}
			}
		
			
				int[] answer = counting(input);
				int answer2 = countingall(answer,input);

				if(file2.isFile() && file.canWrite()) {
					bufferedWriter.write("#"+(zd+1)+" "+answer2);
					bufferedWriter.newLine();
					
				}

			
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
	
public static int[] counting(int[][] input) {
	int[] answer = new int[3];
	int counts1 = 0;
	int counts2 = 0;
	int counts3 = 0;
	for(int i = 0; i<h; i++) {
			if(input[i][2] == 0) {
				counts1++;
			}
			else if(input[i][1] == 0) {
				counts2++;
			}
			else if(input[i][0] ==3) {
				counts3++;
			}	
	}
	
	answer[0] = counts1;
	answer[1] = counts2;
	answer[2] = counts3;
	return answer;
}
		
public static int countingall(int[] sizes,int[][] input ) {	

	
int answer = h*N;
		

int[][] z1 = new int[sizes[0]*4][3];
int[][] z2 = new int[sizes[1]*4][3];
int[][] z3 = new int[sizes[2]*4][3];


int flag1 =0;
int flag2 =0;
int flag3 =0;
for(int i = 0; i<h; i++) {
	if(input[i][2] == 0) {
		
			for(int k =0; k<4; k++) {
				z1[k+flag1*4][0] = input[i][0]; 
				z1[k+flag1*4][1] = input[i][1];
				z1[k+flag1*4][2] = input[i][2]+k;
			
			}
		
		
		flag1++;
	}
	if(input[i][1] == 0) {
		for(int k =0; k<4; k++) {
				z2[k+flag2*4][0] = input[i][0]; 
				z2[k+flag2*4][1] = input[i][1]+k;
				z2[k+flag2*4][2] = input[i][2];
			
			}
		
		flag2++;
	}
	
	if(input[i][0] == 3) {
			for(int k = 0; k<4; k++) {
				z3[k+flag3*4][0] = input[i][0]-k; 
				z3[k+flag3*4][1] = input[i][1];
				z3[k+flag3*4][2] = input[i][2];
			}
		
		flag3++;
	}
}

for(int i =0; i<z1.length; i++) {
	
	for(int j =0; j<z2.length; j++)
	if(Arrays.equals(z1[i], z2[j])) {
		z2[j] = null;
}
}
for(int i =0; i<z2.length; i++) {
	
	for(int j =0; j<z3.length; j++)
	if(Arrays.equals(z2[i], z3[j])) {
		z3[j] = null;
}
}

for(int i =0; i<z3.length; i++) {
	
	for(int j =0; j<z1.length; j++)
	if(Arrays.equals(z3[i], z1[j])) {
		z1[j] =null;
		
}
}
int lengths = 0;
 
for(int i =0;i<z1.length; i++) {
	if(z1[i] != null) {
		lengths++;
	}
}
for(int i =0;i<z2.length; i++) {
	if(z2[i] != null) {
		lengths++;
	}
}
for(int i =0;i<z3.length; i++) {
	if(z3[i] != null) {
		lengths++;
	}
}





answer = (N*N*N)-lengths;


return answer;
}
		
	
	}

