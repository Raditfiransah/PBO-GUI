package backend;

import java.util.ArrayList;
import java.sql.*;

public class Peminjaman {
    private int idpeminjaman;
    private Anggota anggota; 
    private Buku buku;       
    private String tanggalPinjam;
    private String tanggalKembali;

    public Peminjaman() {
    }

    public Peminjaman(Anggota anggota, Buku buku, String tanggalPinjam, String tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    // GETTER SETTER
    public int getIdpeminjaman() { return idpeminjaman; }
    public void setIdpeminjaman(int idpeminjaman) { this.idpeminjaman = idpeminjaman; }

    public Anggota getAnggota() { return anggota; }
    public void setAnggota(Anggota anggota) { this.anggota = anggota; }

    public Buku getBuku() { return buku; }
    public void setBuku(Buku buku) { this.buku = buku; }

    public String getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(String tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }

    public String getTanggalKembali() { return tanggalKembali; }
    public void setTanggalKembali(String tanggalKembali) { this.tanggalKembali = tanggalKembali; }

    // -------------------------------------------------------------
    // METHOD SQL STANDAR (SESUAI TABEL YANG BARU DIBUAT)
    // -------------------------------------------------------------

    public Peminjaman getById(int id) {
        Peminjaman p = new Peminjaman();
        // Query disesuaikan dengan nama kolom yang benar: idpeminjaman, idanggota, idbuku
        String sql = "SELECT p.*, " +
                     "a.idanggota as id_anggota, a.nama as nama_anggota, a.alamat, a.telepon, " +
                     "b.idbuku as id_buku, b.judul as judul_buku, b.penerbit, b.penulis " +
                     "FROM peminjaman p " +
                     "LEFT JOIN anggota a ON p.idanggota = a.idanggota " +
                     "LEFT JOIN buku b ON p.idbuku = b.idbuku " +
                     "WHERE p.idpeminjaman = '" + id + "'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                p = new Peminjaman();
                p.setIdpeminjaman(rs.getInt("idpeminjaman"));
                p.setTanggalPinjam(rs.getString("tanggalpinjam"));
                p.setTanggalKembali(rs.getString("tanggalkembali"));

                // Set Object Anggota
                Anggota ang = new Anggota();
                ang.setIdanggota(rs.getInt("id_anggota"));
                ang.setNama(rs.getString("nama_anggota"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setTelepon(rs.getString("telepon"));
                p.setAnggota(ang);

                // Set Object Buku
                Buku buk = new Buku();
                buk.setIdbuku(rs.getInt("id_buku"));
                buk.setJudul(rs.getString("judul_buku"));
                buk.setPenerbit(rs.getString("penerbit"));
                buk.setPenulis(rs.getString("penulis"));
                p.setBuku(buk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList<>();
        String sql = "SELECT p.*, " +
                     "a.idanggota as id_anggota, a.nama as nama_anggota, a.alamat, a.telepon, " +
                     "b.idbuku as id_buku, b.judul as judul_buku, b.penerbit, b.penulis " +
                     "FROM peminjaman p " +
                     "LEFT JOIN anggota a ON p.idanggota = a.idanggota " +
                     "LEFT JOIN buku b ON p.idbuku = b.idbuku";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Peminjaman p = new Peminjaman();
                p.setIdpeminjaman(rs.getInt("idpeminjaman"));
                p.setTanggalPinjam(rs.getString("tanggalpinjam"));
                p.setTanggalKembali(rs.getString("tanggalkembali"));

                Anggota ang = new Anggota();
                ang.setIdanggota(rs.getInt("id_anggota"));
                ang.setNama(rs.getString("nama_anggota"));
                ang.setAlamat(rs.getString("alamat"));
                ang.setTelepon(rs.getString("telepon"));
                p.setAnggota(ang);

                Buku buk = new Buku();
                buk.setIdbuku(rs.getInt("id_buku"));
                buk.setJudul(rs.getString("judul_buku"));
                buk.setPenerbit(rs.getString("penerbit"));
                buk.setPenulis(rs.getString("penulis"));
                p.setBuku(buk);

                ListPeminjaman.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }

    public void save() {
        if (getById(idpeminjaman).getIdpeminjaman() == 0) {
            // INSERT
            String sql = "INSERT INTO peminjaman (idanggota, idbuku, tanggalpinjam, tanggalkembali) VALUES (" +
                         "'" + this.getAnggota().getIdanggota() + "', " +
                         "'" + this.getBuku().getIdbuku() + "', " +
                         "'" + this.tanggalPinjam + "', " +
                         "'" + this.tanggalKembali + "'" +
                         ")";
            this.idpeminjaman = DBHelper.insertQueryGetId(sql);
        } else {
            // UPDATE
            String sql = "UPDATE peminjaman SET " +
                         "idanggota = '" + this.getAnggota().getIdanggota() + "', " +
                         "idbuku = '" + this.getBuku().getIdbuku() + "', " +
                         "tanggalpinjam = '" + this.tanggalPinjam + "', " +
                         "tanggalkembali = '" + this.tanggalKembali + "' " +
                         "WHERE idpeminjaman = '" + this.idpeminjaman + "'";
            DBHelper.executeQuery(sql);
        }
    }

    public void delete() {
        String sql = "DELETE FROM peminjaman WHERE idpeminjaman = '" + this.idpeminjaman + "'";
        DBHelper.executeQuery(sql);
    }
}