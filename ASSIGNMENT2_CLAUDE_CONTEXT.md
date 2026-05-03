# CME2204 Assignment 2 — Claude Context Document
## DNA Sequence Comparison using LCS (Dynamic Programming in Java)
**Due Date: 06.05.2026**

---

## GÖREV TANIMI

İnsan ve şempanze DNA dizilerini karşılaştıran bir Java programı yaz.
LCS (Longest Common Subsequence) algoritmasını Dynamic Programming ile implemente et.

---

## YAPILACAKLAR (Sırayla)

1. `homosapiens.txt` ve `chimpanzee.txt` dosyalarını oku
2. Her dosyadaki satır sonlarını kaldır → tek uzun String'e birleştir
3. LCS algoritmasını 2D DP tablosu ile yaz
4. LCS'i backtrack ile geri-iz sürerek reconstruct et ve yazdır
5. En az 5 farklı ortak subsequence bul (LCS'ten kısa, ama valid ortak subsequence)
6. 4 analiz sorusunu cevapla

---

## DNA VERİLERİ

**homosapiens.txt** → 4995 karakter (satırlar birleşince)
**chimpanzee.txt** → 5221 karakter (satırlar birleşince)
Her iki dizide sadece A, T, C, G harfleri var.

Örnek (İnsan ilk 100 char):
`GCCCCAGCCCTCCAGGACAGGCTGCATCAGAAGAGGCCATCAAGCAGGTCTGTTCCAAGGGCCTTTGCGTCAGGTGGGCTCAGGGTTCCAGGGTGGCTGG`

Örnek (Şempanze ilk 100 char):
`TCCTGGGGACAGGGGCTGGGGACAGCGGTGCAAAGAGCCCCGCCCTGCAGCCTCCAGCTGTCCTGGTCTAATGTGGAAAGTGGCCCAGGTGACGGCTTT`

---

## NOTLANDIRMA

| Kriter | Puan |
|--------|------|
| Dynamic Programming implementasyonu | 35 |
| LCS Reconstruction (backtrack) | 20 |
| .txt dosya okuma ve birleştirme | 10 |
| En az 5 farklı ortak subsequence | 15 |
| Kod kalitesi | 10 |
| Analiz soruları (4 adet) | 10 |
| **TOPLAM** | **100** |

---

## ANALİZ SORULARI (Cevaplanması gereken)

1. LCS implementasyonunun **zaman karmaşıklığı** nedir? Justify et.
2. **Alan (space) karmaşıklığı** nedir? Olası optimizasyonları tartış.
3. DNA dizisi karşılaştırması biyoloji ve tıpta neden önemlidir?
4. İnsan ve şempanze dizileri arasındaki benzerlik hakkında ne gözlemledin?

---

## JAVA PROJE YAPISI (Önerilen)

```
src/
├── Main.java              → programın giriş noktası
├── DNAReader.java         → .txt dosyalarını okur, birleştirir
├── LCS.java               → DP tablosu, LCS uzunluğu, reconstruction
├── SubsequenceFinder.java → 5+ farklı ortak subsequence bulur
└── AnalysisReport.java    → analiz sorularını print eder (isteğe bağlı)
```

---

## TEKNİK DETAYLAR

### LCS DP Tablosu
- `int[][] dp = new int[m+1][n+1]`  (m = insan uzunluğu, n = şempanze uzunluğu)
- `dp[i][j]` = s1'in ilk i karakteri ile s2'nin ilk j karakterinin LCS uzunluğu
- Tablo boyutu: 4996 × 5222 = ~26 milyon hücre
- Dikkat: bellek kullanımı yüksek olabilir, int yerine short veya space optimization düşünülebilir

### LCS Reconstruction
- DP tablosunu sağ-alttan sol-üste backtrack et
- `dp[i][j] == dp[i-1][j-1] + 1` ise karakter LCS'e dahil
- Sonucu StringBuilder ile topla, sonra reverse et

### 5 Farklı Subsequence
Önerilen yöntemler:
- LCS'in ilk N karakteri (farklı uzunluklarda substrings)
- Backtrack sırasında alternatif yollar takip et (birden fazla path)
- Belirli pozisyonlardan başlayarak greedy subsequence bul

---

## DOSYA OKUMA KODU (Java)

```java
// Dosyayı oku ve satırları birleştir
StringBuilder sb = new StringBuilder();
BufferedReader br = new BufferedReader(new FileReader("homosapiens.txt"));
String line;
while ((line = br.readLine()) != null) {
    sb.append(line.trim());
}
br.close();
String humanDNA = sb.toString();
```

---

## ÖNEMLİ NOTLAR

- Kodun plagiarism kontrolünden geçeceğini unutma — kendi yaz
- Dosya yolları: `.txt` dosyaları proje dizininde olmalı
- Output: konsola yazdır (dosya output istenmemiş)
- Rapor: cover page + Java implementasyonu açıklaması + analiz soruları cevapları

---

## SUBMISSION

- Kaynak kod: `(öğrenci_no)_sources.zip`
- Rapor: `(öğrenci_no)_report.pdf`
- Platform: online.deu.edu.tr
