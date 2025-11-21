package backend;

import java.sql.*;
import java.util.ArrayList;

public class Buku {
    
    private int idbuku;
    private Kategori kategori; // Relasi ke class Kategori
    private String judul;
    private String penerbit;
    private String penulis;

    // -------------------------------------
    // CONSTRUCTORS
    // -------------------------------------
    public Buku() {
    }

    public Buku(Kategori kategori, String judul, String penerbit, String penulis) {
        this.kategori = kategori;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
    }

    // -------------------------------------
    // GETTER & SETTER
    // -------------------------------------
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

    // -------------------------------------
    // GET BY ID (Menggunakan JOIN)
    // -------------------------------------
    public Buku getById(int id) {
        Buku buku = new Buku();
        // Query Join untuk mengambil data buku beserta detail kategorinya
        String sql = "SELECT b.idbuku, b.judul, b.penerbit, b.penulis, " +
                     "k.idkategori, k.nama, k.keterangan " +
                     "FROM buku b " +
                     "LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
                     "WHERE b.idbuku = '" + id + "'";
        
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                buku = new Buku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                
                // Mengisi objek Kategori di dalam Buku
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                
                buku.setKategori(kat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buku;
    }

    // -------------------------------------
    // GET ALL (Menggunakan JOIN)
    // -------------------------------------
    public ArrayList<Buku> getAll() {
        ArrayList<Buku> ListBuku = new ArrayList<>();
        
        // Query Join untuk semua data
        String sql = "SELECT b.idbuku, b.judul, b.penerbit, b.penulis, " +
                     "k.idkategori, k.nama, k.keterangan " +
                     "FROM buku b " +
                     "LEFT JOIN kategori k ON b.idkategori = k.idkategori";
        
        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                
                // Mengisi objek Kategori
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                
                buku.setKategori(kat);

                ListBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListBuku;
    }

    // -------------------------------------
    // SEARCH (Menggunakan JOIN)
    // -------------------------------------
    public ArrayList<Buku> search(String keyword) {
        ArrayList<Buku> ListBuku = new ArrayList<>();

        // Query Search mencakup kolom di tabel buku dan nama kategori
        String sql = "SELECT b.idbuku, b.judul, b.penerbit, b.penulis, " +
                     "k.idkategori, k.nama, k.keterangan " +
                     "FROM buku b " +
                     "LEFT JOIN kategori k ON b.idkategori = k.idkategori " +
                     "WHERE b.judul LIKE '%" + keyword + "%' " +
                     "OR b.penerbit LIKE '%" + keyword + "%' " +
                     "OR b.penulis LIKE '%" + keyword + "%' " +
                     "OR k.nama LIKE '%" + keyword + "%' "; // Bisa cari berdasarkan nama kategori juga

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Buku buku = new Buku();
                buku.setIdbuku(rs.getInt("idbuku"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                
                buku.setKategori(kat);

                ListBuku.add(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListBuku;
    }

    // -------------------------------------
    // SAVE (INSERT / UPDATE)
    // -------------------------------------
    public void save() {
        if (getById(idbuku).getIdbuku() == 0) {
            // INSERT
            // Perhatikan pemanggilan this.getKategori().getIdkategori()
            String SQL = "INSERT INTO buku (judul, idkategori, penerbit, penulis) VALUES ("
                       + " '" + this.judul + "', "
                       + " '" + this.getKategori().getIdkategori() + "', " 
                       + " '" + this.penerbit + "', "
                       + " '" + this.penulis + "' "
                       + " )";
            this.idbuku = DBHelper.insertQueryGetId(SQL);
        } else {
            // UPDATE
            String SQL = "UPDATE buku SET "
                       + " judul = '" + this.judul + "', "
                       + " idkategori = '" + this.getKategori().getIdkategori() + "', "
                       + " penerbit = '" + this.penerbit + "', "
                       + " penulis = '" + this.penulis + "' "
                       + " WHERE idbuku = '" + this.idbuku + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    // -------------------------------------
    // DELETE
    // -------------------------------------
    public void delete() {
        String SQL = "DELETE FROM buku WHERE idbuku = '" + this.idbuku + "'";
        DBHelper.executeQuery(SQL);
    }
}