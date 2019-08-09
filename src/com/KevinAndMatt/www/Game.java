package com.KevinAndMatt.www;

import java.util.Random;

public class Game {
	private Random r;
	public final int EMPTY = R.drawable.empty;
	public final int P1 = R.drawable.letterx;
	public final int P2 = R.drawable.lettero;
	
	private int gameBoard[];
	private final static int SIZE = 9;
	
	
	public Game() {
		gameBoard = new int[SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			gameBoard[i] = EMPTY;
		}
		
		r = new Random();	
	}
	
	public static int getSize() {
	
		return SIZE; 
	}
	
	public void clearBoard() {
		for (int i = 0; i < SIZE; i++) {
			gameBoard[i] = EMPTY;
		}
	}
	
	public int getAIMove() {	
		
		for (int i = 0; i < getSize(); i++) {
			if (gameBoard[i] != P1 && gameBoard[i] != P2) {
				int temp = gameBoard[i];
				gameBoard[i] = P2;
				
				if(checkForWinner() == 2) {
					setMove(P2, i);
					return i;
				}
				else {
					gameBoard[i] = temp;
				}
			}
		}
		
		for (int i = 0; i <getSize(); i++) {
			
			if (gameBoard[i] != P1 && gameBoard[i] != P2) {
				int temp = gameBoard[i];
				gameBoard[i] = P1;
				
				if(checkForWinner() == 1) {
					setMove(P2, i);
					return i;
				}
				else {
					gameBoard[i] = temp;
				}
			}
		}
			
		int aiLoc;
		
		do {
			aiLoc = r.nextInt(getSize());
		}while (gameBoard[aiLoc] == P1 || gameBoard[aiLoc] == P2);
	
		setMove(P2, aiLoc);
		return aiLoc;	
	}

	public void setMove(int player, int box) {
		gameBoard[box] = player;
	}
	
	public int checkForWinner() {
		for (int i = 0; i < 6; i+=3) {
			if (gameBoard[i] == P1 && gameBoard[i+1] ==P1 && gameBoard[i+2] == P1)
			{
				return 1;
			}
			
			if (gameBoard[i] == P2 && gameBoard[i+1] ==P2 && gameBoard[i+2] == P2) {
				return 2;
			}	
		}
		
		for (int i = 0; i <=2; i++) {
			if (gameBoard[i] == P1 && gameBoard[i+3] == P1 && gameBoard[i+6] == P1) {
				return 1;
			}
			
			if (gameBoard[i] == P2 && gameBoard[i+3] == P2 && gameBoard[i+6] == P2) {
					
				return 2;
			}
		}
		
		if (gameBoard[0] == P1 && gameBoard[4] == P1 &&gameBoard[8] == P1 ||
			gameBoard[2] == P1 && gameBoard[4] == P1 &&gameBoard[6] == P1) {
			return 1;
		}
		
		if (gameBoard[0] == P2 && gameBoard[4] == P2 && gameBoard[8] == P2 ||
			gameBoard[2] == P2 && gameBoard[4] == P2 && gameBoard[6] == P2) {
			return 2;
		}
		
		for (int i = 0; i < getSize(); i++) {
			
			if(gameBoard[i] != P1 && gameBoard[i] != P2) {
				return 0;
			}
		}
		return 3;
	}	
}
