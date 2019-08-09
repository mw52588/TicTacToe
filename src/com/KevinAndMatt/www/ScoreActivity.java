package com.KevinAndMatt.www;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScoreActivity extends Activity  {

	
	int p1p = 0;
	int p2p = 0;
	private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score);
		Button goBack;
		goBack = (Button) findViewById(R.id.goBack);

		
		boolean gameOver = getIntent().getExtras().getBoolean("gOver");
		boolean player1Wins = getIntent().getExtras().getBoolean("P1Wins");
		boolean player2Wins = getIntent().getExtras().getBoolean("P2Wins");
		
		
		
		
		
		new Thread(new Runnable() {
        	public void run() {
        		while(mProgressStatus < 100) {
        			mProgressStatus = doWork();
        			
        		}
        		mHandler.post(new Runnable() {
        			public void run() {
        				mProgress.setProgress(mProgressStatus);
        			}
        		});
        	}

        	
        	private int doWork() {
        		int totalCount = getIntent().getExtras().getInt("total");
				TextView p1percent = (TextView) findViewById(R.id.p1percentage);
				TextView p2percent = (TextView) findViewById(R.id.p2percentage);
				TextView gameString = (TextView) findViewById(R.id.textView2);
				int player1Count = getIntent().getExtras().getInt("p1Count");
				int player2Count = getIntent().getExtras().getInt("p2Count");
				ProgressBar progress1, progress2;
				
				progress1 = (ProgressBar) findViewById(R.id.progressBar1);
				progress2 = (ProgressBar) findViewById(R.id.progressBar2);
				
				if(totalCount>0){
					
		        	p1p = (player1Count/totalCount)*100;
		        	p2p = (player1Count/totalCount)*100;
		        	p1percent.setText("" + p1p+ "%");
		            p2percent.setText("" + p2p + "%");
		        	progress1.setProgress((int)p1p);
		        	progress2.setProgress((int)p2p);
		        }else{
		        	progress1.setProgress((int)p1p);
		        	progress2.setProgress((int)p2p);
		            p1percent.setText("%");
		            p2percent.setText("%");
		        }
				return p1p;
        	}
			
        }).start();
		
		new Thread(new Runnable() {
        	public void run() {
        		while(mProgressStatus < 100) {
        			mProgressStatus = doWork();
        			
        		}
        		mHandler.post(new Runnable() {
        			public void run() {
        				mProgress.setProgress(mProgressStatus);
        			}
        		});
        	}

        	
        	private int doWork() {
        		int totalCount = getIntent().getExtras().getInt("total");
				TextView p1percent = (TextView) findViewById(R.id.p1percentage);
				TextView p2percent = (TextView) findViewById(R.id.p2percentage);
				TextView gameString = (TextView) findViewById(R.id.textView2);
				int player1Count = getIntent().getExtras().getInt("p1Count");
				int player2Count = getIntent().getExtras().getInt("p2Count");
				ProgressBar progress1, progress2;
				
				progress1 = (ProgressBar) findViewById(R.id.progressBar1);
				progress2 = (ProgressBar) findViewById(R.id.progressBar2);
				
				if(totalCount>0){
					
		        	p1p = (player1Count/totalCount)*100;
		        	p2p = (player1Count/totalCount)*100;
		        	p1percent.setText("" + p1p+ "%");
		            p2percent.setText("" + p2p + "%");
		        	progress1.setProgress((int)p1p);
		        	progress2.setProgress((int)p2p);
		        }else{
		        	progress1.setProgress((int)p1p);
		        	progress2.setProgress((int)p2p);
		            p1percent.setText("%");
		            p2percent.setText("%");
		        }
				return p2p;
        	}
			
        }).start();
       
       
		
        
		
		TextView congrats;
		congrats = (TextView) findViewById(R.id.congratulations);
		
		if(gameOver && player1Wins) {
			congrats.setText(R.string.congratsP1);
		}
		else if (gameOver && player2Wins) {
			congrats.setText(R.string.congratsP2);
		}
		
		else {
			congrats.setText(R.string.noWin);
		}
		
			
		goBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), Tic_Tac_ToeActivity.class);
				i.putExtra("returnValue", "here");
				setResult(RESULT_OK, i);
				finish();
			}
		});
		
	}
	
	
}
