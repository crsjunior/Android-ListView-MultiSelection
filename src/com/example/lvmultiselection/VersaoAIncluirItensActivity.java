package com.example.lvmultiselection;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.ListView;

import com.example.lvmultiselection.adapters.VersaoAItemAdapter;
import com.example.lvmultiselection.model.Item;

public class VersaoAIncluirItensActivity extends Activity
{
	private VersaoAItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_versao_a_incluir_itens);

		ListView ltvIncluirItens = (ListView) findViewById(R.id.VersaoAInlcuirItens_ltvItens);

		adapter = new VersaoAItemAdapter(
				this,
				R.layout.layout_versao_a_listview_itens,
				(ArrayList<Item>) VersaoAActivity.getItens(),
				VersaoAActivity.getSelecionados());
		ltvIncluirItens.setAdapter(adapter);
	}

	public void onClick(View view)
	{
		VersaoAActivity.setSelecionados(adapter.getSelecionados());
		NavUtils.navigateUpFromSameTask(this);
	}
}
