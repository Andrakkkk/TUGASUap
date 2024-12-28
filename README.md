# Panduan Aplikasi Rental Properti

Aplikasi ini adalah aplikasi berbasis Java Swing untuk mengelola daftar properti yang tersedia untuk disewakan. Dengan aplikasi ini, pengguna dapat menambahkan, memperbarui, menghapus, menyewa, mengembalikan, dan melihat detail properti, termasuk gambarnya.

---

## Fitur Utama

1. **Menambah Properti**
    - Pengguna dapat menambahkan properti baru dengan memasukkan judul, deskripsi, harga, dan gambar properti.

2. **Melihat Daftar Properti**
    - Semua properti yang tersedia akan ditampilkan dalam daftar.

3. **Menyewa Properti**
    - Properti dapat disewa dengan menentukan durasi penyewaan (dalam hari).

4. **Mengembalikan Properti Lebih Awal**
    - Penyewa dapat mengembalikan properti sebelum durasi penyewaan berakhir.

5. **Memperbarui Properti**
    - Properti dapat diperbarui untuk mengubah judul, deskripsi, harga, atau gambar.

6. **Menghapus Properti**
    - Properti yang tidak lagi tersedia dapat dihapus dari daftar.

7. **Melihat Gambar Properti**
    - Gambar properti dapat dilihat dalam ukuran yang sesuai.

---

## Cara Menggunakan

### 1. Menambah Properti
- Masukkan **judul**, **deskripsi**, **harga**, dan **path gambar** ke dalam formulir di bagian atas aplikasi.
- Klik tombol **"Add Property"** untuk menambahkan properti ke daftar.

### 2. Menyewa Properti
- Pilih properti dari daftar.
- Klik tombol **"Rent Property"**.
- Masukkan durasi penyewaan (dalam hari) saat diminta.
- Properti akan diperbarui sebagai "Rented" di daftar.

### 3. Mengembalikan Properti Lebih Awal
- Pilih properti yang sedang disewa dari daftar.
- Klik tombol **"Return Property Early"**.
- Konfirmasi pengembalian properti.

### 4. Memperbarui Properti
- Pilih properti yang ingin diperbarui dari daftar.
- Klik tombol **"Update Property"**.
- Masukkan detail baru (judul, deskripsi, harga, atau path gambar).
- Klik **OK** untuk menyimpan perubahan.

### 5. Menghapus Properti
- Pilih properti dari daftar.
- Klik tombol **"Delete Property"**.
- Konfirmasi penghapusan properti.

### 6. Melihat Gambar Properti
- Pilih properti dari daftar.
- Klik tombol **"View Image"**.
- Gambar properti akan ditampilkan dalam dialog popup.

---

## Persyaratan Sistem

- **JRE**: Java Runtime Environment versi 8 atau lebih baru.
- **OS**: Windows, macOS, atau Linux.
- **Library**: Tidak memerlukan library eksternal tambahan.

---

## Cara Menjalankan

1. Pastikan JDK/JRE sudah terinstal di komputer Anda.
2. Kompilasi file sumber menggunakan perintah berikut:
   ```bash
   javac RentalApp.java
   ```
3. Jalankan aplikasi menggunakan perintah berikut:
   ```bash
   java RentalApp
   ```

---

## Catatan Penting

1. **Format Gambar**: Pastikan gambar yang digunakan memiliki format **jpg**, **jpeg**, atau **png**.
2. **Validasi Input**:
    - Semua field wajib diisi saat menambahkan properti.
    - Harga harus berupa angka.
    - Durasi penyewaan harus berupa angka positif.
3. **Path Gambar**: Pastikan path gambar valid dan file gambar ada di lokasi yang dimasukkan.



