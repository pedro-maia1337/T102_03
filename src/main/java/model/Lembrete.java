package model;

//Model para lembretes já pensando no projeto

public class Lembrete {
    private int codigo;
	private String titulo;
	private String descricao;
	private double valor;
	private String data_lembrete;


    //Construtores
    public Lembrete() {
		this.codigo = -1;
		this.titulo = "";
		this.descricao = "";
		this.valor = 0.0;
		this.data_lembrete = ""; 
	}
	
	public Lembrete(int codigo, String titulo, String descricao, double valor, String data_lembrete) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valor = valor;
		this.data_lembrete = data_lembrete;
	}

    //Getters e Setters
	public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataLembrete() {
        return data_lembrete;
    }

    public void setDataLembrete(String data_lembrete) {
        this.data_lembrete = data_lembrete;
    }



	@Override
	public String toString() {
		return "Lembrete [codigo=" + codigo + ", titulo=" + titulo + ", descrição=" + descricao + ", valor=" + valor + ", data=" + data_lembrete +"]";
	}	
}
