# JPEG2PDF-OFD (No Spring Boot Edition)

> **JPEG OCR to Searchable PDF/OFD** - 無需 Spring Boot 的輕量級版本

---

## ✨ 特點

- ✅ **無 Spring Boot** - 純 Java SE 實現
- ✅ **完整功能** - OCR + PDF + TXT + OFD
- ✅ **多種分發** - JAR、jpackage、Native Image
- ✅ **跨平台** - Windows、macOS、Linux
- ✅ **80+ 語言** - 支持繁體/簡體中文、英文、日文等

---

## 📊 版本對比

| 版本 | 大小 | 需要 Java | 單文件 | 測試狀態 | 推薦 |
|------|------|----------|--------|---------|------|
| **jpackage** | **181 MB** | **❌ 否** | **❌ 資料夾** | **✅ 成功** | **⭐⭐⭐⭐⭐** |
| **JAR** | **52 MB** | **✅ 是** | **✅ 是** | **✅ 成功** | **⭐⭐⭐⭐** |
| **Native Image** | **55 MB** | **❌ 否** | **✅ 是** | **⚠️ JNI 問題** | **⚠️ 實驗性** |

---

## 🚀 快速開始

### 使用 jpackage 版本（推薦，無需 Java）

```powershell
cd dist-jpackage\JPEG2PDF-OFD-NoSpring
.\JPEG2PDF-OFD-NoSpring.exe config.json
```

### 使用 JAR 版本（需要 Java 17+）

```powershell
cd dist
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config.json
```

---

## 🔧 如何構建

### 系統需求

```
- JDK 17+
- Maven 3.6+
- (可選) GraalVM CE 17+ (Native Image)
- (可選) Visual Studio Build Tools (Windows Native Image)
```

---

### 1️⃣ 構建 JAR 版本

```powershell
# 克隆倉庫
git clone https://github.com/brianshih04/jpeg2pdf-ofd-nospring.git
cd jpeg2pdf-ofd-nospring

# 構建
mvn clean package

# 輸出
# target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar (52 MB)
```

**使用：**

```powershell
java -Xmx2G -jar target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar config.json
```

---

### 2️⃣ 構建 jpackage 版本（無需 Java）

**前置需求：**
- JDK 17+ (包含 jpackage)
- Maven 3.6+

**構建步驟：**

```powershell
# 切換到主分支
git checkout master

# 運行構建腳本
.\build-jpackage.ps1

# 輸出
# dist-jpackage\JPEG2PDF-OFD-NoSpring\ (181 MB)
```

**使用：**

```powershell
cd dist-jpackage\JPEG2PDF-OFD-NoSpring
.\JPEG2PDF-OFD-NoSpring.exe config.json
```

**文件結構：**

```
dist-jpackage\JPEG2PDF-OFD-NoSpring\
├── JPEG2PDF-OFD-NoSpring.exe  (主執行檔)
├── app\                        (應用程序)
├── runtime\                    (JRE runtime)
└── config.json                 (配置文件)
```

---

### 3️⃣ 構建 Native Image 版本（實驗性）

> ⚠️ **注意：** Native Image 版本目前有 RapidOCR JNI 問題，建議使用 jpackage 版本

**前置需求：**

**Windows:**
```
1. GraalVM CE 17+
   - 下載：https://github.com/graalvm/graalvm-ce-builds/releases
   - 設置 JAVA_HOME 環境變量

2. Visual Studio Build Tools
   - 下載：https://visualstudio.microsoft.com/downloads/
   - 勾選："使用 C++ 的桌面開發" + "Windows 10/11 SDK"

3. Native Image 組件
   gu install native-image
```

**macOS:**
```bash
# 安裝 Xcode Command Line Tools
xcode-select --install

# 安裝 GraalVM CE
# 下載對應架構版本 (ARM64 或 x64)
```

**構建步驟：**

```powershell
# 切換到 native-image 分支
git checkout native-image

# 設置 GraalVM 環境
$env:JAVA_HOME = "C:\graalvm\graalvm-community-openjdk-17.0.9+9.1"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

# 在 x64 Native Tools Command Prompt 中運行
# (搜尋 "x64 Native Tools Command Prompt for VS")
mvn clean package

# 輸出
# target\jpeg2pdf-ofd-native.exe (55 MB)
```

**使用 Tracing Agent（推薦）：**

```powershell
# 1. 運行 JAR 並收集配置
java -agentlib:native-image-agent=config-merge-dir=target\native-image-agent ^
     -jar target\jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar ^
     test-config.json

# 2. 複製配置文件
Copy-Item target\native-image-agent\*.json src\main\resources\META-INF\native-image\

# 3. 重新構建
mvn clean package
```

---

## 📝 配置文件

### config.json (JSON 格式)

```json
{
  "input": {
    "folder": "C:/OCR/Watch",
    "pattern": "*.jpg"
  },
  "output": {
    "folder": "C:/OCR/Output",
    "format": "all"
  },
  "ocr": {
    "language": "chinese_cht"
  }
}
```

### 命令行參數 (Native Image 版本)

```powershell
# 顯示幫助
.\jpeg2pdf-ofd-native.exe --help

# 單個文件
.\jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -f pdf

# 資料夾
.\jpeg2pdf-ofd-native.exe -i images/ -o output/ -f all

# 指定語言
.\jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l en -f pdf

# 通配符
.\jpeg2pdf-ofd-native.exe -i *.jpg -o output/ -f all
```

**參數說明：**

```
-i, --input <path>    輸入文件/資料夾/通配符 (必須)
-o, --output <path>   輸出資料夾 (默認: 當前目錄)
-l, --lang <lang>     OCR 語言 (默認: chinese_cht)
-f, --format <fmt>    輸出格式 (默認: pdf)
-h, --help            顯示幫助
-v, --version         顯示版本
```

**輸出格式：**

```
pdf  - 可搜索 PDF
ofd  - 可搜索 OFD
txt  - 純文本
all  - 所有格式
```

**支持語言：**

```
chinese_cht - 繁體中文 (默認)
ch          - 簡體中文
en          - 英文
japan       - 日文
korean      - 韓文
(80+ 種語言)
```

---

## 🧪 測試結果

**測試圖片：** 856x875 pixels

**測試輸出：**

```
✅ OCR: 101 個文字方塊
✅ PDF: 428.2 KB
✅ TXT: 1.24 KB
✅ OFD: 427.92 KB
✅ 處理時間: ~2 秒
```

---

## 📦 項目結構

```
jpeg2pdf-ofd-nospring/
├── src/main/java/com/ocr/nospring/
│   ├── Main.java              # JSON 配置主類
│   ├── NativeCli.java         # 命令行主類
│   ├── Config.java            # 配置類
│   ├── OcrService.java        # OCR 服務
│   ├── PdfService.java        # PDF 服務
│   ├── OfdService.java        # OFD 服務
│   └── TextService.java       # TXT 服務
├── src/main/resources/
│   └── META-INF/native-image/ # Native Image 配置
├── dist/                       # JAR 輸出
├── dist-jpackage/              # jpackage 輸出
├── build.ps1                   # JAR 構建腳本
├── build-jpackage.ps1          # jpackage 構建腳本
└── build-native-image.ps1      # Native Image 構建腳本
```

---

## 🔨 技術棧

| 組件 | 版本 | 用途 |
|------|------|------|
| **Java** | 17+ | 運行環境 |
| **PDFBox** | 2.0.29 | PDF 生成 |
| **RapidOCR** | 0.0.7 | OCR 引擎 |
| **ofdrw** | 2.3.8 | OFD 生成 |
| **Jackson** | 2.15.3 | JSON 解析 |

---

## 📖 相關文檔

- **構建指南**: `NATIVE_IMAGE_BUILD_GUIDE.md`
- **CLI 文檔**: `NATIVE_IMAGE_CLI.md`
- **分支說明**: `NATIVE_IMAGE_BRANCH.md`

---

## 🐛 已知問題

### Native Image JNI 問題

```
⚠️ RapidOCR JNI 在 Native Image 中崩潰
   - 錯誤: Segfault in OcrEngine.initModels()
   - 原因: JNI 調用不兼容
   - 解決方案: 使用 jpackage 版本
```

---

## 🤝 貢獻

歡迎提交 Issue 和 Pull Request！

---

## 📄 授權

MIT License

---

## 🔗 相關連結

- **GitHub**: https://github.com/brianshih04/jpeg2pdf-ofd-nospring
- **原項目**: https://github.com/brianshih04/jpeg2pdf_ofd_cmd
- **GraalVM**: https://www.graalvm.org/
- **PDFBox**: https://pdfbox.apache.org/
- **RapidOCR**: https://github.com/RapidAI/RapidOCR

---

## 📊 性能對比

### 啟動時間

| 版本 | 啟動時間 | 首次運行 | 後續運行 |
|------|---------|---------|---------|
| **jpackage** | ~1s | ~3s | ~2s |
| **JAR** | ~1s | ~3s | ~2s |
| **Native Image** | <1s | - | - |

### 內存使用

| 版本 | 空閒 | 運行時 | 峰值 |
|------|------|--------|------|
| **jpackage** | ~80 MB | ~150 MB | ~200 MB |
| **JAR** | ~80 MB | ~150 MB | ~200 MB |
| **Native Image** | ~50 MB | ~100 MB | ~150 MB |

---

## ❓ 常見問題

### Q: 哪個版本最推薦？

**A:** 
- **無 Java 環境**: jpackage 版本（181 MB）
- **有 Java 環境**: JAR 版本（52 MB）

### Q: Native Image 為什麼不能用？

**A:** RapidOCR 使用 JNI 調用原生庫，與 GraalVM Native Image 不完全兼容。建議使用 jpackage 版本。

### Q: 如何支持更多語言？

**A:** 修改 `config.json` 中的 `ocr.language` 參數。支持 80+ 種語言，包括：
- `chinese_cht` (繁體中文)
- `ch` (簡體中文)
- `en` (英文)
- `japan` (日文)
- `korean` (韓文)

### Q: 輸出的 PDF 可以搜索嗎？

**A:** 是的！這是本項目的核心功能。OCR 識別的文字會作為不可見文字層添加到 PDF 中，支持全文搜索。

---

**🎉 感謝使用 JPEG2PDF-OFD！** ✨
