/******************************************************8
 * Programmers: Matthew Wiener, Kevin Hine
 * Date: 2/17/2012
 * TIC_TAC_TOEActivity
 ***********************************************************/

package com.KevinAndMatt.www;

import com.KevinAndMatt.www.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Tic_Tac_ToeActivity extends Activity {
    
	private Game game;
	
	private ImageView boardButton[];
	private Button creditB, scoreB, resetB, quitB;
	private TextView playerTurn;
	boolean player1Wins = false;
	boolean player2Wins = false;
	private static int player1Count = 0;
	private static int player2Count = 0;
	private static int totalCount = 0;
	
	private boolean whoseTurn = true;
	private boolean isGameOver = false;
	Intent i, c;
	
	/** Called when the activity is first created. */
    final static public int ACTIVITY_Score_REQUESTCODE = 100;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);     
        
     
        boardButton = new ImageView[Game.getSize()];
        
        boardButton[0] = (ImageView)findViewById(R.id.view1);
        boardButton[1] = (ImageView) findViewById(R.id.view2);
        boardButton[2] = (ImageView) findViewById(R.id.view3);
        boardButton[3] = (ImageView) findViewById(R.id.view4);
        boardButton[4] = (ImageView) findViewById(R.id.view5);
        boardButton[5] = (ImageView) findViewById(R.id.view6);
        boardButton[6] = (ImageView) findViewById(R.id.view7);
        boardButton[7] = (ImageView) findViewById(R.id.view8);
        boardButton[8] = (ImageView) findViewById(R.id.view9);
       
        creditB = (Button) findViewById(R.id.Credit);
        scoreB = (Button) findViewById(R.id.Score);
        resetB = (Button) findViewById(R.id.Reset);
        quitB = (Button) findViewById(R.id.quit);
       
        i = new Intent(getBaseContext(), ScoreActivity.class);
        c = new Intent(getBaseContext(), CreditsActivity.class);
        playerTurn = (TextView) findViewById(R.id.PlayerTurn);
       
        game = new Game();
        newGame();
        
        creditB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(c);
			}
		});
        
        scoreB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i.putExtra("P1Wins", player1Wins);
				i.putExtra("P2Wins", player2Wins);
				i.putExtra("p1Count", player1Count);
				i.putExtra("p2Count", player2Count);
				i.putExtra("total", totalCount);
				i.putExtra("gOver", isGameOver);
				startActivity(i);	
			}
		});

        resetB.setOnClickListener(new View.OnClickListener() {
	
        	@Override
        	public void onClick(View v) {
        		whoseTurn = true;
        		isGameOver = false;
        		newGame();
        	}
        });

        quitB.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		finish();
        		System.exit(0);
		
        	}
        });
    }  
	
    private void newGame() {
    	game.clearBoard();
    	player1Wins = false;
    	player2Wins = false;
    	for (int i = 0; i < Game.getSize(); i++) {
    		boardButton[i].setImageResource(R.drawable.empty);
    		boardButton[i].setEnabled(true);
    		boardButton[i].setOnClickListener(new ImageClickListener(i));
    	}
    	if(whoseTurn) {
    		playerTurn.setText(R.string.Player1_Go);
    		whoseTurn = false;
    	}
    	else {
    		
    		int go = game.getAIMove();
    		setMove(game.P2, go);
    		playerTurn.setText(R.string.Player2_Go);
    		whoseTurn = true;
    	}
    }
    
    private void setMove(int play1, int box)
    {
    	game.setMove(play1, box);
    	boardButton[box].setEnabled(false);
    	boardButton[box].setImageResource(play1);
    	if (play1 == game.P1) {
    		boardButton[box].setImageResource(R.drawable.letterx);
    	}
    	else {
    		boardButton[box].setImageResource(R.drawable.lettero);
    	}
    }
    
    private class ImageClickListener implements View.OnClickListener {
    	
    	int b;
    	public ImageClickListener(int box) {
    		b = box;
    	}
    	public void onClick(View view) {
    		if(!isGameOver) {
    			
    			if(boardButton[b].isEnabled()) {
    				
    				setMove(game.P1, b);
    			
    				int win = game.checkForWinner();
    				
    				if (win == 0) {
    					playerTurn.setText(R.string.Player2_Go);
    					int go = game.getAIMove();
    					setMove(game.P2, go);
    					win = game.checkForWinner();
    				}
    				
    				if (win == 0) {
    					playerTurn.setText(R.string.Player1_Go);
    				}
    				
    				else if (win == 1) {
    					playerTurn.setText(R.string.player1Wins);
    					totalCount++;
    					player1Count++;
    					player1Wins = true;
    					isGameOver = true;	
    					i.putExtra("P1Wins", player1Wins);
    					i.putExtra("P2Wins", player2Wins);
    					i.putExtra("p1Count", player1Count);
    					i.putExtra("p2Count", player2Count);
    					i.putExtra("total", totalCount);
    					i.putExtra("gOver", isGameOver);
    					startActivityForResult(i, ACTIVITY_Score_REQUESTCODE);
    				
    				}
    				
    				else if (win == 2) {
    					playerTurn.setText(R.string.player2Wins);
    					totalCount++;
    					player2Count++;
    					isGameOver = true;
    					player2Wins = true;
    					i.putExtra("P1Wins", player1Wins);
    					i.putExtra("P2Wins", player2Wins);
    					i.putExtra("p1Count", player1Count);
    					i.putExtra("p2Count", player2Count);
    					i.putExtra("total", totalCount);
    					i.putExtra("gOver", isGameOver);
    				    startActivityForResult(i, ACTIVITY_Score_REQUESTCODE);
    					
    				}
    				
    				else  {
    					playerTurn.setText(R.string.tie);
    					totalCount++;
    					isGameOver = true;
    				}
    			}
    		}
    	}
    }
}

