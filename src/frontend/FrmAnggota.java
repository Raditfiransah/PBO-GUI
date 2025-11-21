package frontend;

import backend.Anggota;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FrmAnggota extends javax.swing.JFrame {

    public FrmAnggota() {
        initComponents();
        setCustomStyle();
        styleButtons();
        styleTable();
        getContentPane().setBackground(new java.awt.Color(245, 245, 250));
        
        tampilkanData();
        kosongkanForm();
    }

    // ---------------------------------------------------
    // 🔥 CUSTOM STYLING SECTION
    // ---------------------------------------------------

    private void setCustomStyle() {
        java.awt.Font fontLabel = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14);
        java.awt.Font fontField = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);
        java.awt.Font fontButton = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);

        jLabel1.setFont(fontLabel);
        jLabel2.setFont(fontLabel);
        jLabel3.setFont(fontLabel);
        jLabel4.setFont(fontLabel);

        txtIdAnggota.setFont(fontField);
        txtNama.setFont(fontField);
        txtAlamat.setFont(fontField);
        txtTelepon.setFont(fontField);
        txtCari.setFont(fontField);

        btnSimpan.setFont(fontButton);
        btnTambahBaru.setFont(fontButton);
        btnHapus.setFont(fontButton);
        btnCari.setFont(fontButton);

        tblAnggota.setFont(fontField);
        tblAnggota.setRowHeight(22);
    }

    private void styleButtons() {
        java.awt.Color primary = new java.awt.Color(51, 153, 255);
        java.awt.Color danger  = new java.awt.Color(255, 90, 90);
        java.awt.Color neutral = new java.awt.Color(230, 230, 230);

        btnSimpan.setBackground(primary);
        btnSimpan.setForeground(java.awt.Color.WHITE);

        btnTambahBaru.setBackground(neutral);
        btnTambahBaru.setForeground(java.awt.Color.BLACK);

        btnHapus.setBackground(danger);
        btnHapus.setForeground(java.awt.Color.WHITE);

        btnCari.setBackground(primary);
        btnCari.setForeground(java.awt.Color.WHITE);
    }

    private void styleTable() {
        tblAnggota.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        tblAnggota.getTableHeader().setOpaque(false);
        tblAnggota.getTableHeader().setBackground(new java.awt.Color(51, 153, 255));
        tblAnggota.getTableHeader().setForeground(java.awt.Color.WHITE);
        
        tblAnggota.setShowVerticalLines(false);
        tblAnggota.setIntercellSpacing(new java.awt.Dimension(0, 1));
    }

    // ---------------------------------------------------
    // FUNGSI ASLI PROGRAM
    // ---------------------------------------------------

    public void kosongkanForm() {
        txtIdAnggota.setText("0");
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Nama", "Alamat", "Telepon"};
        ArrayList<Anggota> list = new Anggota().getAll();
        Object rowData[] = new Object[4];

        tblAnggota.setModel(new DefaultTableModel(new Object[][] {}, kolom));

        for (Anggota a : list) {
            rowData[0] = a.getIdanggota();
            rowData[1] = a.getNama();
            rowData[2] = a.getAlamat();
            rowData[3] = a.getTelepon();

            ((DefaultTableModel) tblAnggota.getModel()).addRow(rowData);
        }
    }

    public void cari(String keyword) {
        String[] kolom = {"ID", "Nama", "Alamat", "Telepon"};
        ArrayList<Anggota> list = new Anggota().search(keyword);
        Object rowData[] = new Object[4];

        tblAnggota.setModel(new DefaultTableModel(new Object[][] {}, kolom));

        for (Anggota a : list) {
            rowData[0] = a.getIdanggota();
            rowData[1] = a.getNama();
            rowData[2] = a.getAlamat();
            rowData[3] = a.getTelepon();

            ((DefaultTableModel) tblAnggota.getModel()).addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        txtIdAnggota = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtTelepon = new javax.swing.JTextField();

        btnSimpan = new javax.swing.JButton();
        btnTambahBaru = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnggota = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Anggota");
        jLabel2.setText("Nama");
        jLabel3.setText("Alamat");
        jLabel4.setText("Telepon");

        txtIdAnggota.setEnabled(false);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(evt -> btnSimpanActionPerformed(evt));

        btnTambahBaru.setText("Tambah Baru");
        btnTambahBaru.addActionListener(evt -> btnTambahBaruActionPerformed(evt));

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(evt -> btnHapusActionPerformed(evt));

        btnCari.setText("Cari");
        btnCari.addActionListener(evt -> btnCariActionPerformed(evt));

        tblAnggota.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Nama", "Alamat", "Telepon"}
        ));
        tblAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAnggotaMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(tblAnggota);

        // Layout (NetBeans auto)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSimpan)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnTambahBaru)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnHapus)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCari))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtIdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtIdAnggota))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNama))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtAlamat))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtTelepon))
                    .addGap(18, 18, 18)
                    .addComponent(btnSimpan)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTambahBaru)
                        .addComponent(btnHapus)
                        .addComponent(txtCari)
                        .addComponent(btnCari))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // ---------------------------------------------------
    // EVENT HANDLERS
    // ---------------------------------------------------

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        Anggota a = new Anggota();
        a.setIdanggota(Integer.parseInt(txtIdAnggota.getText()));
        a.setNama(txtNama.getText());
        a.setAlamat(txtAlamat.getText());
        a.setTelepon(txtTelepon.getText());
        a.save();

        txtIdAnggota.setText(Integer.toString(a.getIdanggota()));
        tampilkanData();
    }

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {
        kosongkanForm();
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tblAnggota.getModel();
        int row = tblAnggota.getSelectedRow();

        Anggota a = new Anggota().getById(Integer.parseInt(model.getValueAt(row, 0).toString()));
        a.delete();
        kosongkanForm();
        tampilkanData();
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        cari(txtCari.getText());
    }

    private void tblAnggotaMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tblAnggota.getModel();
        int row = tblAnggota.getSelectedRow();

        txtIdAnggota.setText(model.getValueAt(row, 0).toString());
        txtNama.setText(model.getValueAt(row, 1).toString());
        txtAlamat.setText(model.getValueAt(row, 2).toString());
        txtTelepon.setText(model.getValueAt(row, 3).toString());
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmAnggota().setVisible(true));
    }

    // Variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAnggota;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdAnggota;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtTelepon;
}
