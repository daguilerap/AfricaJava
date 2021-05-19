package Data;

public class CapAfri {
    int id = 0;
    String pais = "";
    String capital = "";

    public CapAfri(int id, String pais, String capital) {
        this.id = id;
        this.pais = pais;
        this.capital = capital;
    }

    public CapAfri(String pais, String capital) {
        this.pais = pais;
        this.capital = capital;
    }

    public CapAfri() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
