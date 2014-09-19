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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvmultiselection.R;
import com.example.lvmultiselection.model.Item;

public class VersaoCItemAdapter extends ArrayAdapter<Item>
{
	/**
	 * Classe que mantem os widgets e dados de uma linha de uma lista (ListView).
	 */
	private static class ItemHolder
	{
		TextView txvNome;
		TextView txvDescricao;
		ImageView imvImagem;
		Button btnSelecionar;
		boolean selecionado;
	}

	private Context context;
	private int layoutResourceId;
	private ArrayList<Item> itens;
	private List<Item> selecionados;

	/**
	 * Instancia um novo <code>VersaoCItemAdapter</code>. <br>
	 * Visando somente a exibicao dos itens, nao exibindo o botao de selecao.
	 * @param context O Context atual.
	 * @param layoutResourceId O ID de recurso para o arquivo de layout
	 *        que sera utilizado quando instanciar as Views.
	 * @param itens A lista de items (List<Item>) que contem os itens que
	 *        serao exibidos na Views.
	 */
	public VersaoCItemAdapter(Context context, int layoutResourceId, ArrayList<Item> itens)
	{
		this(context, layoutResourceId, itens, null);
	}

	/**
	 * Instancia um novo <code>VersaoCItemAdapter</code>. <br>
	 * Visando a exibicao e a selecao dos itens, exibindo o botao de selecao.
	 * @param context O Context atual.
	 * @param layoutResourceId O ID de recurso para o arquivo de layout
	 *        que sera utilizado quando instanciar as Views.
	 * @param itens A lista de itens (List<Item>) que contem os itens que
	 *        serao exibidos na Views.
	 * @param selecionados A lista de itens (List<Item>) que contem os itens que
	 *        ja devem estar previamente selecionados quando as Views forem geradas.
	 */
	public VersaoCItemAdapter(Context context, int layoutResourceId, ArrayList<Item> itens, List<Item> selecionados)
	{
		super(context, layoutResourceId, itens);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.itens = itens;
		this.selecionados = selecionados;
	}

	/**
	 * Retorna os itens da lista que estao selecionados.
	 * @return Os itens da lista que estao selecionados.
	 */
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
			holder.txvNome = (TextView) row.findViewById(R.id.c_txvNome);
			holder.txvDescricao = (TextView) row.findViewById(R.id.c_txvDescricao);
			holder.imvImagem = (ImageView) row.findViewById(R.id.c_imvImagem);

			row.setTag(holder);
		} else {
			holder = (ItemHolder) row.getTag();
		}

		item = itens.get(position);

		holder.txvNome.setText(item.getNome());
		holder.txvDescricao.setText(item.getDescricao());
		holder.imvImagem.setImageResource(item.getImagemResourceId());

		if (selecionados != null) {
			holder.btnSelecionar = (Button) row.findViewById(R.id.c_btnSelecionar);
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
		} else {
			row.setBackgroundResource(R.drawable.list_item_normal);
		}

		return row;
	}

	/**
	 * Altera o status de exibicao de uma linha da ListView, marcando-o como selecionado.
	 * @param row A linha da ListView que sera marcada.
	 */
	private void selecionarItem(View row)
	{
		ItemHolder holder = (ItemHolder) row.getTag();
		//		row.setBackgroundResource(R.drawable.list_gradient_selected);
		row.setBackgroundResource(R.drawable.list_selected);
		holder.btnSelecionar.setText(R.string.item_selecionado);
	}

	/**
	 * Altera o status de exibicao de uma linha da ListView, marcando-o como deselecionando.
	 * @param row A linha da ListView que sera desmarcada.
	 */
	private void deselecionarItem(View row)
	{
		ItemHolder holder = (ItemHolder) row.getTag();
		//		row.setBackgroundResource(R.drawable.list_gradient);
		row.setBackgroundResource(R.drawable.list_normal);
		holder.btnSelecionar.setText(R.string.item_deselecionado);
	}
}
