package ukdw.com.prototypeprogmob.Model;

public class Mahasiswa {
    private String nim;
    private String nama_mhs;
    private String email_mhs;
    private String alamat_mhs;

    public Mahasiswa(String nim, String nama_mhs, String email_mhs, String alamat_mhs) {
        this.nim = nim;
        this.nama_mhs = nama_mhs;
        this.email_mhs = email_mhs;
        this.alamat_mhs = alamat_mhs;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMhs() { return nama_mhs;
    }

    public void setNamaMhs(String nama_mhs) {
        this.nama_mhs = nama_mhs;
    }

    public String getEmailMhs() {
        return email_mhs;
    }

    public void setEmailMhs(String email_mhs) {
        this.email_mhs = email_mhs;
    }

    public String getAlamatMhs() {
        return alamat_mhs;
    }

    public void setAlamatMhs(String alamat_mhs) {
        this.alamat_mhs = alamat_mhs;
    }
}
