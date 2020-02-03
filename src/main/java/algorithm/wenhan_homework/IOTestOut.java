package algorithm.wenhan_homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOTestOut {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input an integer for Length:");
		int l = Integer.parseInt(in.readLine());
		System.out.println("Input an integer for Width:");
		int w = Integer.parseInt(in.readLine());


		Rectangle rec = new Rectangle(l, w);
		rec.Area(rec);
	}

}
