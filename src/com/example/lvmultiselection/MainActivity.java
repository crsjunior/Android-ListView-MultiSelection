package com.example.lvmultiselection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view)
	{
		Intent intent;
		switch (view.getId()) {
			case R.id.Main_btnVersaoA:
				intent = new Intent(this, VersaoAActivity.class);
				startActivity(intent);
				break;
			case R.id.Main_btnVersaoB:
				intent = new Intent(this, VersaoBActivity.class);
				startActivity(intent);
				break;
			case R.id.Main_btnVersaoC:
				intent = new Intent(this, VersaoCActivity.class);
				startActivity(intent);
				break;
		}
	}
}
