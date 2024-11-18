import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        int userAge; // Kullanıcının yaşı
        int debtInstallments; // Kullanıcının sahip olduğu borcunun taksiti
        int choosenInstallments; // Kullanıcının kredisi için seçtiği taksit
        double userSalary; // Kullanıcının Maaşı
        double maxMonthlyLoanAmount; // Kullanıcının aylık kredi limiti
        double choosenLoan; // Kullanıcının talep ettiği kredi miktarı
        double userDebt; // Kullanıcının sahip olduğu borç miktarı
        double monthlyPayment; // Aylık kredi ödeme miktarını geçici olarak hesaplamak için kullanılan değişken
        double finalMonthlyPayment; // Kullanıcının kredisi için aylık ödeyeceği tutar
        double interestRate; // Kullanıcının faiz oranı
        boolean isEligible = false; // Kullanıcının krediye uygun olup olmadığını belirten flag

        // -------------------- Kullanıcı Bilgilerini Alma Başlangıç --------------------
        System.out.println("Please enter your name:");
        String userName = scan.nextLine(); // Kullanıcının ismini alır ve sonuçlarda kullanır
        System.out.println("Hi " + userName + "! Please answer the questions below");
        Thread.sleep(500);
        // -------------------- Kullanıcı Bilgilerini Alma Bitiş --------------------

        // -------------------- Yaş Kontrolü Başlangıç --------------------
        System.out.println("Please enter your age:");
        // Yaşın geçerli olup olmadığını kontrol etmek için döngü
        while (true) {
            if (scan.hasNextInt()) {
                userAge = scan.nextInt(); // Kullanıcının yaşını alır
                scan.nextLine();
                if (userAge < 0) { // Negatif yaş girişi kontrol edilir
                    System.out.println("Please enter valid age:");
                    scan.nextLine();
                } else if (userAge < 18 || userAge > 65) { // Yaşın 18-65 arasında olup olmadığını kontrol eder
                    System.out.println("Hi " + userName + ", unfortunately you cannot get a loan because people under 18 and over 65 cannot get a loan.");
                    System.exit(0); // Kredi başvurusu reddedilir
                } else break;// Yaş geçerliyse döngüden çıkılır
            } else {
                System.out.println("Oops! Looks like you entered something wrong. Please provide a valid number.");
                scan.nextLine();  // Hatalı giriş sonrası giriş temizlenir
            }
        }
        // -------------------- Yaş Kontrolü Bitiş --------------------

        // -------------------- Maaş Bilgisi Kontrolü Başlangıç --------------------
        System.out.println("Please enter your salary:");
        // Maaşın geçerli olup olmadığını kontrol etmek için döngü
        while (true) {
            if (scan.hasNextDouble()) {
                userSalary = scan.nextDouble(); // Maaşı alır
                scan.nextLine();
                if (userSalary < 0) {  // Negatif maaş girişi kontrol edilir
                    System.out.println("Please enter a valid salary (greater than or equal to 0):");
                } else {
                    break;  // Maaş geçerli ise döngüden çıkılır
                }
            } else {
                System.out.println("Oops! Looks like you entered something wrong. Please provide a valid number.");
                scan.nextLine();  // Hatalı giriş sonrası giriş temizlenir
            }
        }
        // -------------------- Maaş Bilgisi Kontrolü Bitiş --------------------

        // -------------------- Borç Durumu Kontrolü Başlangıç --------------------
        while (true) {
            System.out.println("Do you have debt? (yes/no) ");
            String debtAnswer = scan.nextLine().toLowerCase(); // Kullanıcıdan borç durumu alınır

            if (debtAnswer.equals("yes")) {
                System.out.println("How much debt do you have? ");

                // Borcun geçerli olup olmadığını kontrol eder
                while (true) {
                    if (scan.hasNextDouble()) {
                        userDebt = scan.nextDouble(); // Borcu alır
                        scan.nextLine();
                        if (userDebt < 0) {
                            System.out.println("Please enter a valid debt (greater than or equal to 0):");
                        } else {
                            break;  // Borç geçerliyse döngüden çıkılır
                        }

                    } else {
                        System.out.println("Oops! Looks like you entered something wrong. Please provide a valid number.");
                        scan.nextLine(); // Hatalı giriş sonrası giriş temizlenir
                    }
                }

                System.out.println("How many installments do you have for your debt?");
                // Borcun taksit sayısını alır
                while (true) {
                    if (scan.hasNextInt()) {
                        debtInstallments = scan.nextInt();
                        scan.nextLine();
                        if (debtInstallments < 1) {
                            System.out.println("Please enter a valid debt (greater than or equal to 1):");
                        } else {
                            break; // Taksit sayısı geçerliyse döngüden çıkılır
                        }
                    } else {
                        System.out.println("Oops! Looks like you entered something wrong. Please provide a valid number.");
                        scan.nextLine(); // Hatalı giriş sonrası giriş temizlenir
                    }
                }

                double monthlyDebt = userDebt / debtInstallments; // Aylık borç ödeme miktarı hesaplanır
                // Eğer borç aylık gelirinin %20'sinden fazla ise kredi verilmez
                if (userSalary * 0.2 <= monthlyDebt) {
                    System.out.println("Unfortunately, you cannot get a loan because your debt is more than 20% of your salary.");
                    System.exit(0); // Kredi başvurusu reddedilir
                }
                break;
            } else if (!debtAnswer.equals("no")) {
                System.out.println("Oops! Looks like you entered something wrong. Please enter 'yes' or 'no'.");
                // Burada kullanıcıdan tekrar girdi alabilirsiniz
            } else break;  // Borç yoksa döngüden çıkılır
        }
        // -------------------- Borç Durumu Kontrolü Bitiş --------------------

        // -------------------- Geçmiş Kredi Sorgulama Başlangıç --------------------
        // Kullanıcının daha önce kredi çekip çekmediğini sorgular.
        // Eğer kullanıcı kredi çektiyse, ödeme geçmişine bakarak uygunluk kontrolü yapar.
        while (true) {
            System.out.println("Have you taken a loan in the past? (yes/no)");
            String lounAnswer = scan.nextLine().toLowerCase();

            if (lounAnswer.equals("yes")) {
                // Daha önce kredi çekmişse, ödeme geciktirip geciktirmediğini sorar.
                System.out.println("Have you ever delayed or skipped a loan payment? (yes/no)");
                String missedAnswer = scan.nextLine().toLowerCase();

                if (missedAnswer.equals("yes")) {
                    // Eğer kredi ödemesi geciktirilmişse, kredi verilmez ve program sonlanır.
                    System.out.println("Unfortunately , we cannot give a loan to people who missed a loan payment.");
                    System.exit(0);
                } else if (!missedAnswer.equals("no")) {
                    // Yanlış giriş yapılmışsa kullanıcı tekrar uyarılır.
                    System.out.println("Oops! Looks like you entered something wrong. Please enter 'yes' or 'no'.");
                } else break;  // Ödeme geciktirilmemişse işlem devam eder.
            } else if (!lounAnswer.equals("no")) {
                // Yanlış giriş yapılmışsa kullanıcı tekrar uyarılır.
                System.out.println("Oops! Looks like you entered something wrong. Please enter 'yes' or 'no'.");
            } else break; // Kullanıcı daha önce kredi çekmemişse işlem devam eder.
        }
        // -------------------- Geçmiş Kredi Sorgulama Bitiş --------------------

        // -------------------- Kredi Başvurusu Başlangıç --------------------
        maxMonthlyLoanAmount = userSalary * 0.4; // Kullanıcının aylık kredi limiti
        System.out.println("Your monthly loan amount is calculated as " + maxMonthlyLoanAmount + " dollars.\n"
                + "How much loan do you want to get? (you can get a max of 1 million and a min of 5 thousand dollars )");
        while (true) {
            choosenLoan = scan.nextDouble();
            scan.nextLine();
            // Kredi miktarının belirtilen aralıkta olup olmadığını kontrol eder
            if (choosenLoan < 5000 || choosenLoan > 1000000) {
                System.out.println("Invalid loan amount. Please enter between 5 thousand and 1 million.");
            } else {
                int maxInstallments = 48;
                int minInstallments = 0;
                // Kredi miktarına uygun taksit sayısını hesaplar
                for (int i = 1; i < maxInstallments; i++) {
                    monthlyPayment = choosenLoan / i;
                    if (maxMonthlyLoanAmount >= monthlyPayment) {
                        isEligible = true;
                        minInstallments = i;
                        break;
                    }
                }

                if (isEligible) {
                    System.out.println("Congratulations your loan application has been approved");
                    System.out.println("How many installments would you like to pay for your loan?" + " From " + minInstallments + " to " + maxInstallments);
                    while (true) {
                        choosenInstallments = scan.nextInt();
                        scan.nextLine();
                        // Kullanıcının istediği taksit sayısının geçerli olup olmadığını kontrol eder
                        if (choosenInstallments < minInstallments || choosenInstallments > maxInstallments) {
                            System.out.println("Please enter a value within the acceptable range.");
                        } else break;
                    }
                    break;
                } else {
                    System.out.println("Unfortunately, the loan amount you requested exceeds the allowed limit. Please request a lower amount.");
                }
            }
        }
        interestRate = (double) (choosenInstallments - 1) / 2 + 3; // Faiz oranı hesaplanır
        choosenLoan = (choosenLoan / 100 * interestRate) + choosenLoan; //  Faizli kredi hesaplanır
        finalMonthlyPayment = choosenLoan / choosenInstallments; // Son ödeme miktarı hesaplanır
        System.out.println("The loan application has been successfully approved.");
        System.out.printf("--------------------\nLoan Details:\nName: %s\nAge: %d\nLoan Amount: %.0f$\nInstallments: %d\nMonthly Payment: %.2f$\nInterest Rate: %.1f%%\n",
                userName, userAge, choosenLoan, choosenInstallments, finalMonthlyPayment, interestRate);
        // -------------------- Kredi Başvurusu Bitiş --------------------
    }
}
