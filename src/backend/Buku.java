package backend;

import java.sql.*;
import java.util.ArrayList;

public class Buku {

    private int idbuku;
    private Kategori kategori = new Kategori();
    private String judul;
    private String penerbit;
    private String penulis;

    public Buku() {
    }

    public Buku(Kategori kategori, String judul, String penerbit, String penulis) {
        this.kategori = kategori;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }

    // GETTER SETTER ----------------------------------
    public int getIdbuku() {
        return idbuku;
    }

    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    // GET BY ID (JOIN) ---------------------------------
    public Buku getById(int id) {
        Buku b = new Buku();

        String sql = "SELECT b.idbuku, b.judul, b.penerbit, b.penulis, " +
                     "k.idkategori, k.nama, k.keterangan " +
                     "FROM buku b LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
                     "WHERE b.idbuku = '" + id + "'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                b = new Buku();

                b.setIdbuku(rs.getInt("idbuku"));
                b.setJudul(rs.getString("judul"));
                b.setPenerbit(rs.getString("penerbit"));
                b.setPenulis(rs.getString("penulis"));

                Kategori k = new Kategori();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));

                b.setKategori(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    // GET ALL (JOIN) ------------------------------------
    public ArrayList<Buku> getAll() {
        ArrayList<Buku> ListBuku = new ArrayList<>();

        String sql = "SELECT b.idbuku, b.judul, b.penerbit, b.penulis, " +
                     "k.idkategori, k.nama, k.keterangan " +
                     "FROM buku b LEFT JOIN kategori k ON b.idkategori = k.idkategori";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Buku b = new Buku();

                b.setIdbuku(rs.getInt("idbuku"));
                b.setJudul(rs.getString("judul"));
                b.setPenerbit(rs.getString("penerbit"));
                b.setPenulis(rs.getString("penulis"));

                Kategori k = new Kategori();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));

                b.setKategori(k);

                ListBuku.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListBuku;
    }

    // SEARCH (JOIN) --------------------------------------
    public ArrayList<Buku> search(String keyword) {
        ArrayList<Buku> ListBuku = new ArrayList<>();

        String sql = "SELECT b.idbuku, b.judul, b.penerbit, b.penulis, " +
                     "k.idkategori, k.nama, k.keterangan " +
                     "FROM buku b LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
                     "WHERE b.judul LIKE '%" + keyword + "%' " +
                     "OR b.penulis LIKE '%" + keyword + "%' " +
                     "OR b.penerbit LIKE '%" + keyword + "%' " +
                     "OR k.nama LIKE '%" + keyword + "%'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Buku b = new Buku();

                b.setIdbuku(rs.getInt("idbuku"));
                b.setJudul(rs.getString("judul"));
                b.setPenerbit(rs.getString("penerbit"));
                b.setPenulis(rs.getString("penulis"));

                Kategori k = new Kategori();
                k.setIdkategori(rs.getInt("idkategori"));
                k.setNama(rs.getString("nama"));
                k.setKeterangan(rs.getString("keterangan"));

                b.setKategori(k);

                ListBuku.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListBuku;
    }

    // SAVE ----------------------------------------------
    public void save() {
        if (getById(idbuku).getIdbuku() == 0) {
            // INSERT
            String SQL = "INSERT INTO buku (idkategori, judul, penerbit, penulis) VALUES ("
                       + " '" + this.getKategori().getIdkategori() + "', "
                       + " '" + this.judul + "', "
                       + " '" + this.penerbit + "', "
                       + " '" + this.penulis + "' "
                       + ")";

            this.idbuku = DBHelper.insertQueryGetId(SQL);
        } else {
            // UPDATE
            String SQL = "UPDATE buku SET "
                       + " idkategori = '" + this.getKategori().getIdkategori() + "', "
                       + " judul = '" + this.judul + "', "
                       + " penerbit = '" + this.penerbit + "', "
                       + " penulis = '" + this.penulis + "' "
                       + " WHERE idbuku = '" + this.idbuku + "'";

            DBHelper.executeQuery(SQL);
        }
    }

    // DELETE --------------------------------------------
    public void delete() {
        String SQL = "DELETE FROM buku WHERE idbuku = '" + this.idbuku + "'";
        DBHelper.executeQuery(SQL);
    }
}
