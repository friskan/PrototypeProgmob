package ukdw.com.prototypeprogmob.Model;

public class Dosen {
    private String nidn;
    private String nama;
    private String gelar;
    private String email;
    private String alamat;

    public Dosen(String nidn, String nama, String gelar, String email, String alamat) {
        this.nidn = nidn;
        this.nama = nama;
        this.gelar = gelar;
        this.email = email;
        this.alamat = alamat;
    }

    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
