package com.example.lvmultiselection;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lvmultiselection.adapters.VersaoAItemAdapter;
import com.example.lvmultiselection.model.Item;
import com.example.lvmultiselection.model.Item.Ordenacao;

public class VersaoAActivity extends Activity
{
	private static List<Item> itens;
	private static List<Item> selecionados;
	private static Item.Ordenacao ordenacao = Item.Ordenacao.ID;
	private VersaoAItemAdapter adapter;

	public static List<Item> getSelecionados()
	{
		return selecionados;
	}

	public static void setSelecionados(List<Item> selecionados)
	{
		VersaoAActivity.selecionados = selecionados;
	}

	public static List<Item> getItens()
	{
		return itens;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_versao_a);

		if (VersaoAActivity.itens == null) {
			gerarItens();
		}

		TextView txvTotalItensLista = (TextView) findViewById(R.id.VersaoA_txvTotalItensLista);
		Spinner spnOrdenacao = (Spinner) findViewById(R.id.VersaoA_spnOrdenacao);
		ListView ltvItens = (ListView) findViewById(R.id.VersaoA_ltvItens);

		txvTotalItensLista.setText("Total de itens : " + String.valueOf(VersaoAActivity.selecionados.size()));
		ordenarListaItens();

		adapter = new VersaoAItemAdapter(
				this,
				R.layout.layout_versao_a_listview_itens,
				(ArrayList<Item>) VersaoAActivity.selecionados);
		ltvItens.setAdapter(adapter);

		ArrayAdapter<CharSequence> spnAdapter = ArrayAdapter.createFromResource(
				this,
				R.array.Versao_spnOrdenacao,
				android.R.layout.simple_spinner_item);
		spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnOrdenacao.setAdapter(spnAdapter);
		spnOrdenacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				switch (pos) {
					case 1:
						VersaoAActivity.ordenacao = Ordenacao.NOME;
						break;
					case 2:
						VersaoAActivity.ordenacao = Ordenacao.DESCRICAO;
						break;
					default:
						VersaoAActivity.ordenacao = Ordenacao.ID;
						break;
				}
				ordenarListaItens();
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		int spnOrdenacaoPos;
		switch (VersaoAActivity.ordenacao) {
			case NOME:
				spnOrdenacaoPos = 1;
				break;
			case DESCRICAO:
				spnOrdenacaoPos = 2;
				break;
			default:
				spnOrdenacaoPos = 0;
				break;
		}
		spnOrdenacao.setSelection(spnOrdenacaoPos);
	}

	public void onClick(View view)
	{
		Intent intent = new Intent(this, VersaoAIncluirItensActivity.class);
		startActivity(intent);
	}

	private void ordenarListaItens()
	{
		Item.ordenarLista(VersaoAActivity.selecionados, VersaoAActivity.ordenacao);
	}

	private void gerarItens()
	{
		VersaoAActivity.itens = new ArrayList<Item>();
		VersaoAActivity.itens.add(new Item(
				1l, "Omo Dupla Ação", "Sabão em pó", R.drawable.img_omo_dupla_acao));
		VersaoAActivity.itens.add(new Item(
				2l, "Ipê Neutro", "Detergente liquido", R.drawable.img_detergente_ipe_neutro));
		VersaoAActivity.itens.add(new Item(
				3l, "Dôve Cremoso", "Sabonete", R.drawable.img_sabonete_dove_cremoso));
		VersaoAActivity.selecionados = new ArrayList<Item>();
	}
}
