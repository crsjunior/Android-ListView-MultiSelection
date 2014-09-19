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

import com.example.lvmultiselection.adapters.VersaoBItemAdapter;
import com.example.lvmultiselection.model.Item;
import com.example.lvmultiselection.model.Item.Ordenacao;

public class VersaoBActivity extends Activity
{
	private static List<Item> itens;
	private static List<Item> selecionados;
	private static Item.Ordenacao ordenacao = Item.Ordenacao.ID;
	private VersaoBItemAdapter adapter;

	public static List<Item> getSelecionados()
	{
		return selecionados;
	}

	public static void setSelecionados(List<Item> selecionados)
	{
		VersaoBActivity.selecionados = selecionados;
	}

	public static List<Item> getItens()
	{
		return itens;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_versao_b);

		if (VersaoBActivity.itens == null) {
			gerarItens();
		}

		TextView txvTotalItensLista = (TextView) findViewById(R.id.VersaoB_txvTotalItensLista);
		Spinner spnOrdenacao = (Spinner) findViewById(R.id.VersaoB_spnOrdenacao);
		ListView ltvItens = (ListView) findViewById(R.id.VersaoB_ltvItens);

		txvTotalItensLista.setText("Total de itens : " + String.valueOf(VersaoBActivity.selecionados.size()));
		ordenarListaItens();

		adapter = new VersaoBItemAdapter(
				this,
				R.layout.layout_versao_b_listview_itens,
				(ArrayList<Item>) VersaoBActivity.selecionados);
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
						VersaoBActivity.ordenacao = Ordenacao.NOME;
						break;
					case 2:
						VersaoBActivity.ordenacao = Ordenacao.DESCRICAO;
						break;
					default:
						VersaoBActivity.ordenacao = Ordenacao.ID;
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
		switch (VersaoBActivity.ordenacao) {
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
		Intent intent = new Intent(this, VersaoBIncluirItensActivity.class);
		startActivity(intent);
	}

	private void ordenarListaItens()
	{
		Item.ordenarLista(VersaoBActivity.selecionados, VersaoBActivity.ordenacao);
	}

	private void gerarItens()
	{
		VersaoBActivity.itens = new ArrayList<Item>();
		VersaoBActivity.itens.add(new Item(
				1l, "Omo Dupla Ação", "Sabão em pó", R.drawable.img_omo_dupla_acao));
		VersaoBActivity.itens.add(new Item(
				2l, "Ipê Neutro", "Detergente liquido", R.drawable.img_detergente_ipe_neutro));
		VersaoBActivity.itens.add(new Item(
				3l, "Dôve Cremoso", "Sabonete", R.drawable.img_sabonete_dove_cremoso));
		VersaoBActivity.selecionados = new ArrayList<Item>();
	}
}
