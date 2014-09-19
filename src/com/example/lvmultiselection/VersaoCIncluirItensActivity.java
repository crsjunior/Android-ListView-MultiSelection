package com.example.lvmultiselection;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.ListView;

import com.example.lvmultiselection.adapters.VersaoCItemAdapter;
import com.example.lvmultiselection.model.Item;

public class VersaoCIncluirItensActivity extends Activity
{
	private VersaoCItemAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_versao_c_incluir_itens);

		ListView ltvIncluirItens = (ListView) findViewById(R.id.VersaoCInlcuirItens_ltvItens);

		adapter = new VersaoCItemAdapter(
				this,
				R.layout.layout_versao_c_listview_itens,
				(ArrayList<Item>) VersaoCActivity.getItens(),
				VersaoCActivity.getSelecionados()
				);
		ltvIncluirItens.setAdapter(adapter);
	}

	public void onClick(View view)
	{
		VersaoCActivity.setSelecionados(adapter.getSelecionados());
		NavUtils.navigateUpFromSameTask(this);
	}
}
