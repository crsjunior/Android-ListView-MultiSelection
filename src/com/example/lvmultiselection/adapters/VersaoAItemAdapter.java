package com.example.lvmultiselection.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lvmultiselection.R;
import com.example.lvmultiselection.model.Item;

public class VersaoAItemAdapter extends ArrayAdapter<Item>
{
	private static class ItemHolder
	{
		TextView txvNome;
		TextView txvDescricao;
		Button btnSelecionar;
		boolean selecionado;
	}

	private Context context;
	private int layoutResourceId;
	private ArrayList<Item> itens;
	private List<Item> selecionados;

	public VersaoAItemAdapter(Context context, int layoutResourceId, ArrayList<Item> itens)
	{
		super(context, layoutResourceId, itens);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.itens = itens;
		this.selecionados = null;
	}

	public VersaoAItemAdapter(Context context, int layoutResourceId, ArrayList<Item> itens, List<Item> selecionados)
	{
		super(context, layoutResourceId, itens);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.itens = itens;
		this.selecionados = selecionados;
	}

	public List<Item> getSelecionados()
	{
		return selecionados;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View row = convertView;
		final ItemHolder holder;
		final Item item;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new ItemHolder();
			holder.txvNome = (TextView) row.findViewById(R.id.a_txvNome);
			holder.txvDescricao = (TextView) row.findViewById(R.id.a_txvDescricao);

			row.setTag(holder);
		} else {
			holder = (ItemHolder) row.getTag();
		}

		item = itens.get(position);

		holder.txvNome.setText(item.getNome());
		holder.txvDescricao.setText(item.getDescricao());

		if (selecionados != null) {
			holder.btnSelecionar = (Button) row.findViewById(R.id.a_btnSelecionar);
			holder.btnSelecionar.setVisibility(View.VISIBLE);

			holder.selecionado = selecionados.contains(item);
			if (holder.selecionado) {
				selecionarItem(row);
			} else {
				deselecionarItem(row);
			}

			holder.btnSelecionar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view)
				{
					View parent = (View) view.getParent();
					if (holder.selecionado) {
						selecionados.remove(item);
						deselecionarItem(parent);
					} else {
						selecionados.add(item);
						selecionarItem(parent);
					}
					holder.selecionado = !holder.selecionado;
				}
			});
		}

		return row;
	}

	private void selecionarItem(View row)
	{
		ItemHolder holder = (ItemHolder) row.getTag();
		holder.btnSelecionar.setText(R.string.item_selecionado);
	}

	private void deselecionarItem(View row)
	{
		ItemHolder holder = (ItemHolder) row.getTag();
		holder.btnSelecionar.setText(R.string.item_deselecionado);
	}
}
