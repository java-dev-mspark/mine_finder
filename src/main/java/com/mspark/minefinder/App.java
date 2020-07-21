package com.mspark.minefinder;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * MineFinder for the Socar pre-project
 * 
 *
 * @author MSPARK
 * @version $Revision$
 * @since 2020. 7. 22
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "   =Welcome The MineFinder= ");
        printMineBoard();
    }
    
    private static void printMineBoard() {
		int[][] mineBoard = new int[10][10];

		Random ran = new Random();
		
		Set<Integer> randomMineSet = new HashSet<Integer>();
		
		while(randomMineSet.size() < 10) {
			randomMineSet.add(ran.nextInt(100));
		}
		
		int pos = 0;
		
		System.out.println("   ===The Mine Positions===   ");
		for(int y = 0; y < 10 ; y++) {
			for(int x = 0; x < 10; x++) {
				if(randomMineSet.contains(pos)) {
					mineBoard[x][y] = 1;
					System.out.print(" x ");
				}else {
					mineBoard[x][y] = 0;
					System.out.print(" - ");
				}
				pos++;
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
		
		System.out.println("    ===The Mine Answer===   ");
		Direction[] directions = Direction.values();
		for(int y = 0; y < 10 ; y++) {
			for(int x = 0; x < 10; x++) {
				int cnt = 0;
				for(Direction direction : directions) {
					if(direction.isPossible(x, y)) {
						int newX = x+direction.getDx();
						int newY = y+direction.getDy();
						if(mineBoard[newX][newY] == 1) {
							cnt++;
						}
					}
				}
				System.out.print(" "+cnt+" ");
			}
			System.out.println();
		}
		
	}
	
	
	public enum Direction{
		LEFT_UP(-1, -1), UP(0, -1), RIGHT_UP(1, -1), RIGHT(1, 0), RIGHT_DOWN(1,1), DOWN(0, 1), LEFT_DOWN(-1, 1), LEFT(-1, 0);
		
		Direction(int dx, int dy){
			this.dx = dx;
			this.dy = dy;
		}
		
		private int dx;
		
		private int dy;

		public int getDx() {
			return dx;
		}

		public int getDy() {
			return dy;
		}
		
		public boolean isPossible(int x, int y) {
			if(x+dx < 0 || x+dx > 9) {
				return false;
			}
			
			if(y+dy < 0 || y+dy > 9) {
				return false;
			}
			
			return true;
		}
	}
}
