package com.example.lvmultiselection.model;

import java.util.Collections;
import java.util.List;

public class Item implements Comparable<Item>
{
	public static enum Ordenacao
	{
		ID,
		NOME,
		DESCRICAO;
	}

	private Long id;
	private String nome;
	private String descricao;
	private Integer imagemResourceId;
	private static Ordenacao ordenacao;

	public Item()
	{
		this(null, null, null, null);
	}

	public Item(Long id, String nome, String descricao)
	{
		this(id, nome, descricao, null);
	}

	public Item(Long id, String nome, String descricao, Integer imagemResourceId)
	{
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.imagemResourceId = imagemResourceId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public Integer getImagemResourceId()
	{
		return imagemResourceId;
	}

	public void setImagemResourceId(Integer imagemResourceId)
	{
		this.imagemResourceId = imagemResourceId;
	}

	public static void ordenarLista(List<Item> listaItens, Ordenacao ordenacao)
	{
		Item.ordenacao = ordenacao;
		Collections.sort(listaItens);
	}

	@Override
	public boolean equals(Object o)
	{
		return (o instanceof Item && ((Item) o).getId() == this.id);
	}

	@Override
	public int compareTo(Item oItem)
	{
		int resultado = 0;
		switch (Item.ordenacao) {
			case ID:
				resultado = this.id.compareTo(oItem.getId());
				break;
			case NOME:
				resultado = this.nome.compareTo(oItem.getNome());
				break;
			case DESCRICAO:
				resultado = this.descricao.compareTo(oItem.getDescricao());
				break;
		}
		return resultado;
	}
}
