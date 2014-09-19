package com.example.lvmultiselection.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvmultiselection.R;
import com.example.lvmultiselection.model.Item;

public class VersaoBItemAdapter extends ArrayAdapter<Item>
{
	private static class ItemHolder
	{
		TextView txvNome;
		TextView txvDescricao;
		ImageView imvImagem;
		boolean selecionado;
	}

	private Context context;
	private int layoutResourceId;
	private ArrayList<Item> itens;
	private List<Item> selecionados;

	public VersaoBItemAdapter(Context context, int layoutResourceId, ArrayList<Item> itens)
	{
		this(context, layoutResourceId, itens, null);
	}

	public VersaoBItemAdapter(Context context, int layoutResourceId, ArrayList<Item> itens, List<Item> selecionados)
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
			holder.txvNome = (TextView) row.findViewById(R.id.b_txvNome);
			holder.txvDescricao = (TextView) row.findViewById(R.id.b_txvDescricao);
			holder.imvImagem = (ImageView) row.findViewById(R.id.b_imvImagem);

			row.setTag(holder);
		} else {
			holder = (ItemHolder) row.getTag();
		}

		item = itens.get(position);

		holder.txvNome.setText(item.getNome());
		holder.txvDescricao.setText(item.getDescricao());
		holder.imvImagem.setImageResource(item.getImagemResourceId());

		if (selecionados != null) {
			holder.selecionado = selecionados.contains(item);
			if (holder.selecionado) {
				selecionarItem(row);
			} else {
				deselecionarItem(row);
			}

			row.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view)
				{
					if (holder.selecionado) {
						selecionados.remove(item);
						deselecionarItem(view);
					} else {
						selecionados.add(item);
						selecionarItem(view);
					}
					holder.selecionado = !holder.selecionado;
				}
			});
		}

		return row;
	}

	private void selecionarItem(View row)
	{
		row.setBackgroundResource(R.color.color_item_selecionado);
	}

	private void deselecionarItem(View row)
	{
		row.setBackgroundResource(R.color.color_item_deselecionado);
	}
}
