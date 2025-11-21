package backend;

import java.sql.*;
import java.util.ArrayList;

public class Anggota {
    
    private int idanggota;
    private String nama;
    private String alamat;
    private String telepon;

    public Anggota() {
    }

    public Anggota(String nama, String alamat, String telepon) {
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    // -------------------------------------
    // GETTER & SETTER
    // -------------------------------------
    public int getIdanggota() {
        return idanggota;
    }

    public void setIdanggota(int idanggota) {
        this.idanggota = idanggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    // -------------------------------------
    // GET BY ID
    // -------------------------------------
    public Anggota getById(int id) {
        Anggota a = new Anggota();
        ResultSet rs = DBHelper.selectQuery(
                "SELECT * FROM anggota WHERE idanggota = '" + id + "'");

        try {
            while (rs.next()) {
                a = new Anggota();
                a.setIdanggota(rs.getInt("idanggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    // -------------------------------------
    // GET ALL
    // -------------------------------------
    public ArrayList<Anggota> getAll() {
        ArrayList<Anggota> ListAnggota = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota");

        try {
            while (rs.next()) {
                Anggota a = new Anggota();
                a.setIdanggota(rs.getInt("idanggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));

                ListAnggota.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListAnggota;
    }

    // -------------------------------------
    // SEARCH
    // -------------------------------------
    public ArrayList<Anggota> search(String keyword) {
        ArrayList<Anggota> ListAnggota = new ArrayList<>();

        String sql = "SELECT * FROM anggota WHERE "
                   + " nama LIKE '%" + keyword + "%' "
                   + " OR alamat LIKE '%" + keyword + "%' "
                   + " OR telepon LIKE '%" + keyword + "%' ";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Anggota a = new Anggota();
                a.setIdanggota(rs.getInt("idanggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));

                ListAnggota.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListAnggota;
    }

    // -------------------------------------
    // SAVE (INSERT / UPDATE)
    // -------------------------------------
    public void save() {
        if (getById(idanggota).getIdanggota() == 0) {
            // INSERT
            String SQL = "INSERT INTO anggota (nama, alamat, telepon) VALUES ("
                       + " '" + this.nama + "', "
                       + " '" + this.alamat + "', "
                       + " '" + this.telepon + "' "
                       + " )";
            this.idanggota = DBHelper.insertQueryGetId(SQL);
        } else {
            // UPDATE
            String SQL = "UPDATE anggota SET "
                       + " nama = '" + this.nama + "', "
                       + " alamat = '" + this.alamat + "', "
                       + " telepon = '" + this.telepon + "' "
                       + " WHERE idanggota = '" + this.idanggota + "'";
            DBHelper.executeQuery(SQL);
        }
    }

    // -------------------------------------
    // DELETE
    // -------------------------------------
    public void delete() {
        String SQL = "DELETE FROM anggota WHERE idanggota = '" + this.idanggota + "'";
        DBHelper.executeQuery(SQL);
    }
}
