package frontend;

import backend.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmPeminjaman extends javax.swing.JFrame {

    public FrmPeminjaman() {
        initComponents();
        tampilkanData();
        kosongkanForm();
    }

    public void kosongkanForm() {
        txtIdPeminjaman.setText("0");
        txtIdAnggota.setText("");
        txtIdBuku.setText("");
        txtTanggalPinjam.setText("");
        txtTanggalKembali.setText("");
        lblNamaAnggota.setText("Nama Anggota");
        lblJudulBuku.setText("Judul Buku");
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Anggota", "Buku", "Tgl Pinjam", "Tgl Kembali"};
        ArrayList<Peminjaman> list = new Peminjaman().getAll();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, kolom);

        for (Peminjaman p : list) {
            Object[] rowData = new Object[5];
            rowData[0] = p.getIdpeminjaman();
            rowData[1] = p.getAnggota().getNama();
            rowData[2] = p.getBuku().getJudul();
            rowData[3] = p.getTanggalPinjam();
            rowData[4] = p.getTanggalKembali();
            
            model.addRow(rowData);
        }
        tblPeminjaman.setModel(model);
    }
    
    // Method Helper untuk mencari Anggota
    private void cariAnggota() {
        try {
            int id = Integer.parseInt(txtIdAnggota.getText());
            Anggota ang = new Anggota().getById(id);
            if(ang.getIdanggota() != 0) {
                lblNamaAnggota.setText(ang.getNama());
            } else {
                lblNamaAnggota.setText("Anggota tidak ditemukan!");
            }
        } catch (NumberFormatException e) {
            lblNamaAnggota.setText("ID harus angka!");
        }
    }
    
    // Method Helper untuk mencari Buku
    private void cariBuku() {
        try {
            int id = Integer.parseInt(txtIdBuku.getText());
            Buku buk = new Buku().getById(id);
            if(buk.getIdbuku() != 0) {
                lblJudulBuku.setText(buk.getJudul());
            } else {
                lblJudulBuku.setText("Buku tidak ditemukan!");
            }
        } catch (NumberFormatException e) {
            lblJudulBuku.setText("ID harus angka!");
        }
    }

    // ---------------------------------------------------
    // EVENT HANDLERS (Dihubungkan ke GUI)
    // ---------------------------------------------------
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Peminjaman p = new Peminjaman();
        
        try {
            p.setIdpeminjaman(Integer.parseInt(txtIdPeminjaman.getText()));
            
            // Set Anggota berdasarkan ID yang diketik
            p.setAnggota(new Anggota().getById(Integer.parseInt(txtIdAnggota.getText())));
            
            // Set Buku berdasarkan ID yang diketik
            p.setBuku(new Buku().getById(Integer.parseInt(txtIdBuku.getText())));
            
            p.setTanggalPinjam(txtTanggalPinjam.getText());
            p.setTanggalKembali(txtTanggalKembali.getText());
            
            p.save();
            tampilkanData();
            kosongkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data tidak valid atau format tanggal salah!");
        }
    }                                         

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {                                         
        DefaultTableModel model = (DefaultTableModel) tblPeminjaman.getModel();
        int row = tblPeminjaman.getSelectedRow();
        
        if (row >= 0) {
            Peminjaman p = new Peminjaman();
            p.setIdpeminjaman(Integer.parseInt(model.getValueAt(row, 0).toString()));
            p.delete();
            kosongkanForm();
            tampilkanData();
        }
    }                                        

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {                                              
        kosongkanForm();
    }                                             

    private void btnCariAnggotaActionPerformed(java.awt.event.ActionEvent evt) {                                               
        cariAnggota();
    }                                              

    private void btnCariBukuActionPerformed(java.awt.event.ActionEvent evt) {                                            
        cariBuku();
    }                                           

    private void tblPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {                                           
        DefaultTableModel model = (DefaultTableModel) tblPeminjaman.getModel();
        int row = tblPeminjaman.getSelectedRow();
        int idPeminjaman = Integer.parseInt(model.getValueAt(row, 0).toString());
        
        // Ambil data lengkap dari backend agar ID Anggota dan ID Buku akurat
        Peminjaman p = new Peminjaman().getById(idPeminjaman);
        
        txtIdPeminjaman.setText(String.valueOf(p.getIdpeminjaman()));
        txtIdAnggota.setText(String.valueOf(p.getAnggota().getIdanggota()));
        txtIdBuku.setText(String.valueOf(p.getBuku().getIdbuku()));
        txtTanggalPinjam.setText(p.getTanggalPinjam());
        txtTanggalKembali.setText(p.getTanggalKembali());
        
        // Refresh Label Nama & Judul
        lblNamaAnggota.setText(p.getAnggota().getNama());
        lblJudulBuku.setText(p.getBuku().getJudul());
    }                                          

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmPeminjaman().setVisible(true));
    }

    // ---------------------------------------------------
    // GENERATED GUI COMPONENTS
    // ---------------------------------------------------
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdPeminjaman = new javax.swing.JTextField();
        txtIdAnggota = new javax.swing.JTextField();
        txtIdBuku = new javax.swing.JTextField();
        txtTanggalPinjam = new javax.swing.JTextField();
        txtTanggalKembali = new javax.swing.JTextField();
        btnCariAnggota = new javax.swing.JButton();
        btnCariBuku = new javax.swing.JButton();
        lblNamaAnggota = new javax.swing.JLabel();
        lblJudulBuku = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnTambahBaru = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID");
        jLabel2.setText("ID Anggota");
        jLabel3.setText("ID Buku");
        jLabel4.setText("Tanggal Pinjam");
        jLabel5.setText("Tanggal Kembali");

        txtIdPeminjaman.setEnabled(false);

        btnCariAnggota.setText("Cari");
        btnCariAnggota.addActionListener(evt -> btnCariAnggotaActionPerformed(evt));

        btnCariBuku.setText("Cari");
        btnCariBuku.addActionListener(evt -> btnCariBukuActionPerformed(evt));

        lblNamaAnggota.setText("Nama Anggota");
        lblJudulBuku.setText("Judul Buku");

        jLabel8.setText("Format: YYYY/MM/DD");
        jLabel9.setText("Format: YYYY/MM/DD");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed(evt));

        btnTambahBaru.setText("Tambah Baru");
        btnTambahBaru.addActionListener(evt -> btnTambahBaruActionPerformed(evt));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(evt -> btnHapusActionPerformed(evt));

        tblPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Anggota", "Buku", "Tgl Pinjam", "Tgl Kembali"}
        ));
        tblPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeminjaman);

        // Layout Manual sederhana (mirip Netbeans)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(btnSimpan))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTambahBaru)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus))
                            .addComponent(txtIdPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtIdBuku, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                            .addComponent(txtIdAnggota, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnCariAnggota)
                                            .addComponent(btnCariBuku))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamaAnggota)
                                    .addComponent(lblJudulBuku)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))))
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdPeminjaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariAnggota)
                    .addComponent(lblNamaAnggota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariBuku)
                    .addComponent(lblJudulBuku))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnTambahBaru)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private javax.swing.JButton btnCariAnggota;
    private javax.swing.JButton btnCariBuku;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJudulBuku;
    private javax.swing.JLabel lblNamaAnggota;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTextField txtIdAnggota;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtIdPeminjaman;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
}