import java.time.LocalDateTime;

public class Evento {
    private String nome;
    private String tipo;
    private String local;
    private LocalDateTime data;
    private String descricao;

    public Evento(String nome, String tipo, String local, String descricao, LocalDateTime data){
        this.nome = nome;
        this.tipo = tipo;
        this.local = local;
        this.descricao = descricao;
        this.data = data; 
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getLocal(){
        return local;
    }
    public void setLocal(String local){
        this.local = local;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }    
    public LocalDateTime getData(){
        return data;
    }
    public void setData(LocalDateTime data){
        this.data = data;
    }
    
    public String toString(){
        return "Evento"+ "\n" +
                "Nome do evento: " + nome + "\n" +
                "Tipo do evento: " + tipo + "\n" +
                "Local do evento: " + local + "\n" +
                "Descricao do evento: " + descricao + "\n" +
                "Data do evento: " + data;
    }
}
