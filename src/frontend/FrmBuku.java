package frontend;

import backend.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

public class FrmBuku extends javax.swing.JFrame {

    public FrmBuku() {
        initComponents();
        tampilkanKategori();
        tampilkanData();
        kosongkanForm();
    }

    // ---------------------------------------------------
    //  FORM HANDLING
    // ---------------------------------------------------
    public void kosongkanForm() {
        txtIdBuku.setText("0");
        cmbKategori.setSelectedIndex(0);
        txtJudul.setText("");
        txtPenerbit.setText("");
        txtPenulis.setText("");
    }

    public void tampilkanKategori() {
        cmbKategori.setModel(new DefaultComboBoxModel());
        ArrayList<Kategori> list = new Kategori().getAll();

        for (Kategori kat : list) {
            cmbKategori.addItem(kat);
        }
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Kategori", "Judul", "Penerbit", "Penulis"};
        ArrayList<Buku> list = new Buku().getAll();

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, kolom);

        for (Buku b : list) {
            Object[] row = new Object[5];
            row[0] = b.getIdBuku();
            row[1] = b.getKategori().getNama();
            row[2] = b.getJudul();
            row[3] = b.getPenerbit();
            row[4] = b.getPenulis();
            model.addRow(row);
        }

        tblBuku.setModel(model);
    }

    public void cari(String keyword) {
        String[] kolom = {"ID", "Kategori", "Judul", "Penerbit", "Penulis"};
        ArrayList<Buku> list = new Buku().search(keyword);

        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, kolom);

        for (Buku b : list) {
            Object[] row = new Object[5];
            row[0] = b.getIdBuku();
            row[1] = b.getKategori().getNama();
            row[2] = b.getJudul();
            row[3] = b.getPenerbit();
            row[4] = b.getPenulis();
            model.addRow(row);
        }

        tblBuku.setModel(model);
    }

    // ---------------------------------------------------
    //  GUI COMPONENTS
    // ---------------------------------------------------
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        txtIdBuku = new javax.swing.JTextField();
        cmbKategori = new javax.swing.JComboBox<>();
        txtJudul = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();

        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnTambahBaru = new javax.swing.JButton();

        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Buku");
        jLabel2.setText("Kategori");
        jLabel3.setText("Judul");
        jLabel4.setText("Penerbit");
        jLabel5.setText("Penulis");

        txtIdBuku.setEnabled(false);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed(evt));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(evt -> btnHapusActionPerformed(evt));

        btnTambahBaru.setText("Tambah Baru");
        btnTambahBaru.addActionListener(evt -> btnTambahBaruActionPerformed(evt));

        btnCari.setText("Cari");
        btnCari.addActionListener(evt -> btnCariActionPerformed(evt));

        tblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Kategori", "Judul", "Penerbit", "Penulis"}
        ));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuku);

        // Layout NetBeans (langsung paste, aman)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTambahBaru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJudul)
                            .addComponent(txtPenerbit)
                            .addComponent(txtPenulis)
                            .addComponent(cmbKategori, 0, 200, Short.MAX_VALUE)))
                    .addComponent(btnSimpan))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtJudul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(btnSimpan)
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahBaru)
                    .addComponent(btnHapus)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addGap(20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // ---------------------------------------------------
    // EVENT HANDLER
    // ---------------------------------------------------
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        Buku b = new Buku();
        b.setIdBuku(Integer.parseInt(txtIdBuku.getText()));
        b.setKategori((Kategori) cmbKategori.getSelectedItem());
        b.setJudul(txtJudul.getText());
        b.setPenerbit(txtPenerbit.getText());
        b.setPenulis(txtPenulis.getText());
        b.save();

        txtIdBuku.setText("" + b.getIdBuku());
        tampilkanData();
    }

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {
        kosongkanForm();
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblBuku.getSelectedRow();
        int id = Integer.parseInt(tblBuku.getValueAt(row, 0).toString());

        Buku b = new Buku().getById(id);
        b.delete();

        kosongkanForm();
        tampilkanData();
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        cari(txtCari.getText());
    }

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblBuku.getSelectedRow();
        txtIdBuku.setText(tblBuku.getValueAt(row, 0).toString());
        txtJudul.setText(tblBuku.getValueAt(row, 2).toString());
        txtPenerbit.setText(tblBuku.getValueAt(row, 3).toString());
        txtPenulis.setText(tblBuku.getValueAt(row, 4).toString());

        // set kategori berdasarkan nama
        String namaKategori = tblBuku.getValueAt(row, 1).toString();
        for (int i = 0; i < cmbKategori.getItemCount(); i++) {
            Kategori kat = (Kategori) cmbKategori.getItemAt(i);
            if (kat.getNama().equals(namaKategori)) {
                cmbKategori.setSelectedIndex(i);
                break;
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmBuku().setVisible(true));
    }

    // VARIABLES
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JComboBox<Kategori> cmbKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
}
