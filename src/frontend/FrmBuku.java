package frontend;

import backend.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

public class FrmBuku extends javax.swing.JFrame {

    public FrmBuku() {
        initComponents();
        tampilkanData();
        tampilkanKategori();
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
        // Mengisi ComboBox dengan Object Kategori
        // Pastikan class Kategori memiliki method toString() yang me-return nama!
        cmbKategori.setModel(new DefaultComboBoxModel());
        ArrayList<Kategori> list = new Kategori().getAll();

        for (Kategori kat : list) {
            cmbKategori.addItem(kat);
        }
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Kategori", "Judul", "Penerbit", "Penulis"};
        ArrayList<Buku> list = new Buku().getAll();
        Object rowData[] = new Object[5];

        tblBuku.setModel(new DefaultTableModel(new Object[][]{}, kolom));

        for (Buku b : list) {
            rowData[0] = b.getIdbuku(); // Sesuaikan dengan nama getter di backend
            rowData[1] = b.getKategori().getNama(); // Mengambil nama kategori lewat relasi
            rowData[2] = b.getJudul();
            rowData[3] = b.getPenerbit();
            rowData[4] = b.getPenulis();
            
            ((DefaultTableModel) tblBuku.getModel()).addRow(rowData);
        }
    }

    public void cari(String keyword) {
        String[] kolom = {"ID", "Kategori", "Judul", "Penerbit", "Penulis"};
        ArrayList<Buku> list = new Buku().search(keyword);
        Object rowData[] = new Object[5];

        tblBuku.setModel(new DefaultTableModel(new Object[][]{}, kolom));

        for (Buku b : list) {
            rowData[0] = b.getIdbuku();
            rowData[1] = b.getKategori().getNama();
            rowData[2] = b.getJudul();
            rowData[3] = b.getPenerbit();
            rowData[4] = b.getPenulis();
            
            ((DefaultTableModel) tblBuku.getModel()).addRow(rowData);
        }
    }

    // ---------------------------------------------------
    //  EVENT HANDLERS
    // ---------------------------------------------------
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Buku b = new Buku();
        b.setIdbuku(Integer.parseInt(txtIdBuku.getText()));
        
        // Casting object dari ComboBox ke Class Kategori
        b.setKategori((Kategori) cmbKategori.getSelectedItem());
        
        b.setJudul(txtJudul.getText());
        b.setPenerbit(txtPenerbit.getText());
        b.setPenulis(txtPenulis.getText());
        
        b.save();

        txtIdBuku.setText(Integer.toString(b.getIdbuku()));
        tampilkanData();
    }                                         

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {                                         
        DefaultTableModel model = (DefaultTableModel) tblBuku.getModel();
        int row = tblBuku.getSelectedRow();
        
        Buku b = new Buku();
        // Mengambil ID dari kolom ke-0 tabel
        b.setIdbuku(Integer.parseInt(model.getValueAt(row, 0).toString()));
        b.delete();
        
        kosongkanForm();
        tampilkanData();
    }                                        

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {                                              
        kosongkanForm();
    }                                             

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {                                        
        cari(txtCari.getText());
    }                                       

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {                                     
        DefaultTableModel model = (DefaultTableModel) tblBuku.getModel();
        int row = tblBuku.getSelectedRow();
        
        txtIdBuku.setText(model.getValueAt(row, 0).toString());
        
        // Menampilkan Kategori di ComboBox berdasarkan nama yang ada di tabel
        String kategoriNama = model.getValueAt(row, 1).toString();
        for(int i=0; i<cmbKategori.getItemCount(); i++){
            Kategori kat = (Kategori) cmbKategori.getItemAt(i);
            if(kat.getNama().equals(kategoriNama)){
                cmbKategori.setSelectedIndex(i);
                break;
            }
        }
        
        txtJudul.setText(model.getValueAt(row, 2).toString());
        txtPenerbit.setText(model.getValueAt(row, 3).toString());
        txtPenulis.setText(model.getValueAt(row, 4).toString());
    }                                    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBuku().setVisible(true);
            }
        });
    }

    // ---------------------------------------------------
    //  GUI COMPONENTS (GENERATED CODE)
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
        btnTambahBaru = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
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
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnTambahBaru.setText("Tambah Baru");
        btnTambahBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBaruActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        tblBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Kategori", "Judul", "Penerbit", "Penulis"
            }
        ));
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuku);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKategori, 0, 193, Short.MAX_VALUE)
                            .addComponent(txtJudul)
                            .addComponent(txtPenerbit)
                            .addComponent(txtPenulis)))
                    .addComponent(btnSimpan)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTambahBaru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
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
                .addGap(18, 18, 18)
                .addComponent(btnSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahBaru)
                    .addComponent(btnHapus)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }
    
    // Variables declaration
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JComboBox<Object> cmbKategori; // Menggunakan Object/Generic
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtJudul;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPenulis;
}