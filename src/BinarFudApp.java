import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BinarFudApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Integer> pesanan = new HashMap<>();
        double totalHarga = 0;

        while (true) {
            System.out.println("==========================");
            System.out.println("Selamat Datang di BinarFud");
            System.out.println("==========================");
            System.out.println("Silahkan pilih makanan :");
            System.out.println("1. Nasi Goreng   | 15.000");
            System.out.println("2. Mie Goreng    | 13.000");
            System.out.println("3. Nasi + Ayam   | 18.000");
            System.out.println("4. Es Teh Manis  | 3.000");
            System.out.println("5. Es Jeruk      | 5.000");
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print("=> ");
            int pilihan = input.nextInt();

            if (pilihan == 0) {
                break;
            }

            if (pilihan == 99) {
                if (pesanan.isEmpty()) {
                    System.out.println("Anda belum memesan makanan.");
                    continue;
                }

                System.out.println("=======================");
                System.out.println("Konfirmasi dan Pembayaran");
                System.out.println("=======================");
                System.out.println("Silahkan pilih makanan :");

                for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                    String item = entry.getKey();
                    int qty = entry.getValue();
                    double harga = getHarga(item);
                    double subtotal = qty * harga;
                    totalHarga += subtotal;
                    System.out.println(item + "\t" + qty + "\t" + (qty * harga));
                }

                System.out.println("------------------------- +");
                System.out.println("Total\t\t" + totalHarga);

                System.out.println("1. Konfirmasi dan Bayar");
                System.out.println("2. Kembali ke menu utama");
                System.out.println("0. Keluar Aplikasi");
                System.out.print("=> ");
                int menuKonfirmasi = input.nextInt();

                if (menuKonfirmasi == 1) {
                    // Simpan struk pembayaran ke file
                    simpanStruk(pesanan, totalHarga);
                    System.out.println("==========================");
                    System.out.println("BinarFud");
                    System.out.println("==========================");
                    System.out.println("Terima Kasih sudah memesan di BinarFud");
                    System.out.println("Dibawah ini adalah pesanan anda");
                    for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                        String item = entry.getKey();
                        int qty = entry.getValue();
                        double harga = getHarga(item);
                        double subtotal = qty * harga;
                        System.out.println(item + "\t" + qty + "\t" + subtotal);
                    }
                    System.out.println("------------------------- +");
                    System.out.println("Total\t\t1" + totalHarga);
                    System.out.println("Pembayaran : BinarCash");
                    pesanan.clear();
                    totalHarga = 0;
                } else if (menuKonfirmasi == 2) {
                    pesanan.clear();
                    totalHarga = 0;
                } else if (menuKonfirmasi == 0) {
                    break;
                }
            } else {
                String makanan = getMakananByKode(pilihan);
                if (makanan != null) {
                    System.out.println("=======================");
                    System.out.println("Berapa pesanan anda");
                    System.out.println("=======================");
                    System.out.println(makanan + "\t" + getHarga(makanan));
                    System.out.println("input 0 untuk kembali");
                    System.out.print("qty => ");
                    int qty = input.nextInt();
                    if (qty > 0) {
                        pesanan.put(makanan, qty);
                    }
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            }
        }
    }

    private static String getMakananByKode(int kode) {
        switch (kode) {
            case 1:
                return "Nasi Goreng";
            case 2:
                return "Mie Goreng";
            case 3:
                return "Nasi + Ayam";
            case 4:
                return "Es Teh Manis";
            case 5:
                return "Es Jeruk";
            default:
                return null;
        }
    }

    private static double getHarga(String makanan) {
        switch (makanan) {
            case "Nasi Goreng":
                return 15000;
            case "Mie Goreng":
                return 13000;
            case "Nasi + Ayam":
                return 18000;
            case "Es Teh Manis":
                return 3000;
            case "Es Jeruk":
                return 5000;
            default:
                return 0;
        }
    }

    private static void simpanStruk(Map<String, Integer> pesanan, double totalHarga) {
        try {
            FileWriter fileWriter = new FileWriter("struk_pembayaran.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("==========================");
            printWriter.println("BinarFud");
            printWriter.println("==========================");
            printWriter.println("Terima Kasih sudah memesan di BinarFud");
            printWriter.println("Dibawah ini adalah pesanan anda");

            for (Map.Entry<String, Integer> entry : pesanan.entrySet()) {
                String item = entry.getKey();
                int qty = entry.getValue();
                double harga = getHarga(item);
                double subtotal = qty * harga;
                printWriter.println(item + "\t" + qty + "\t" + subtotal);
            }

            printWriter.println("------------------------- +");
            printWriter.println("Total\t\t" + totalHarga);
            printWriter.println("Pembayaran : BinarCash");

            printWriter.println("==========================");
            printWriter.println("Simpan struk sebagai");
            printWriter.println("bukti pembayaran");
            printWriter.println("==========================");





            printWriter.close();
            System.out.println("Struk pembayaran telah disimpan sebagai bukti pembayaran.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
