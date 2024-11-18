# Loan Approval System (Kredi Onay Sistemi)

Bu Java uygulaması, bir kullanıcının kredi başvurusunu değerlendirmek için çeşitli kriterleri kontrol eden bir kredi hesaplama ve onaylama sistemidir. Sistem, kullanıcının yaşını, gelir durumunu, mevcut borçlarını ve kredi geçmişini analiz ederek uygunluk durumunu belirler.

## 🎯 Amaç

Bu proje, kredi başvurusu sürecinde belirli kurallara dayalı otomatik bir değerlendirme sistemi geliştirme amacıyla yapılmıştır. Kullanıcılar, gelir ve borçlarına göre aylık ödemelerini ve faiz oranlarını görebilir.

## 🚀 Özellikler

- **Kullanıcı bilgilerini doğrulama** (yaş, maaş, borç durumu, kredi geçmişi).
- **Kredi uygunluk kontrolü:**
  - Yaş sınırı (18-65 yaş aralığı).
  - Mevcut borcun maaşın %20'sinden fazla olmaması.
  - Geçmiş kredi ödemelerinin düzgün olması.
- **Faiz oranı ve taksit sayısına göre aylık ödeme hesaplama.**
- **Kullanıcı dostu etkileşimli terminal arayüzü.**

## 📜 Gereksinimler

- Java Development Kit (JDK) 17 veya üzeri.
- Bir IDE (IntelliJ IDEA, Eclipse vb.) veya komut satırı.

## 🚦 Çalıştırma Adımları

1. **Projeyi Klonlayın:**
   Projeyi bilgisayarınıza klonlamak için aşağıdaki komutu terminal veya komut istemcisinde çalıştırın:
   
   ```bash
   git clone https://github.com/GalipEfeOncu/LoanApprovalSystem.git
   ```

2. **Proje Klasörüne Girin ve Alt Klasöre Geçin**
   ```bash
   cd LoanApprovalSystem
   cd KrediHesaplamaVeOnaySistemi
   ```
   
3. **Programı Derleyin**
   ```bash
   javac src/Main.java
   ```
   
4. **Programı Çalıştırın**
   ```bash
   java -cp src Main
   ```


## 🔄 Geliştirme Notları

- Yeni kriterler eklemek için `Main.java` dosyasını genişletebilirsiniz.

## 📌 İletişim

Bu projeyle ilgili öneri veya sorularınız için aşağıdaki yollardan ulaşabilirsiniz:

- E-posta: galipefe75@gmail.com
- GitHub: GalipEfeOncu
