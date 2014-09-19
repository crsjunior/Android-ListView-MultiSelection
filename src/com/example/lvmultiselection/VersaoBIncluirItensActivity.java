package com.example.lvmultiselection;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.ListView;

import com.example.lvmultiselection.adapters.VersaoBItemAdapter;
import com.example.lvmultiselection.model.Item;

public class VersaoBIncluirItensActivity extends Activity
{
	private VersaoBItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_versao_b_incluir_itens);

		ListView ltvIncluirItens = (ListView) findViewById(R.id.VersaoBInlcuirItens_ltvItens);

		adapter = new VersaoBItemAdapter(
				this,
				R.layout.layout_versao_b_listview_itens,
				(ArrayList<Item>) VersaoBActivity.getItens(),
				VersaoBActivity.getSelecionados());
		ltvIncluirItens.setAdapter(adapter);
	}

	public void onClick(View view)
	{
		VersaoBActivity.setSelecionados(adapter.getSelecionados());
		NavUtils.navigateUpFromSameTask(this);
	}
}
