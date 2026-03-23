# JPEG2PDF-OFD (No Spring Boot Edition)

> **JPEG OCR to Searchable PDF/OFD** - 無需 Spring Boot 的輕量級版本

---

## ✨ 特點

- ✅ **無 Spring Boot** - 純 Java SE 實現
- ✅ **完整功能** - OCR + PDF + TXT + OFD
- ✅ **多種分發** - JAR、jpackage
- ✅ **跨平台** - Windows、macOS、Linux
- ✅ **80+ 語言** - 支持繁體/簡體中文、英文、日文等
- ✅ **多頁支持** - 單頁或多頁模式

---

## 📊 版本對比

| 版本 | 大小 | 需要 Java | 單文件 | 測試狀態 | 推薦 |
|------|------|----------|--------|---------|------|
| **jpackage** | **181 MB** | **❌ 否** | **❌ 資料夾** | **✅ 成功** | **⭐⭐⭐⭐⭐** |
| **JAR** | **52 MB** | **✅ 是** | **✅ 是** | **✅ 成功** | **⭐⭐⭐⭐** |

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

## 💻 跨平台使用指南

### Windows

#### jpackage 版本（推薦，無需 Java）

```powershell
# 1. 下載並解壓縮 dist-jpackage-JPEG2PDF-OFD-NoSpring.zip

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 運行
.\JPEG2PDF-OFD-NoSpring.exe config-multipage-false.json

# 多頁模式
.\JPEG2PDF-OFD-NoSpring.exe config-multipage-true.json
```

#### JAR 版本（需要 Java 17+）

```powershell
# 1. 安裝 Java 17+
# 下載：https://adoptium.net/

# 2. 驗證 Java
java -version

# 3. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

---

### macOS

#### jpackage 版本（推薦，無需 Java）

```bash
# 1. 下載並解壓縮 dist-jpackage-JPEG2PDF-OFD-NoSpring.tar.gz
tar -xzf dist-jpackage-JPEG2PDF-OFD-NoSpring.tar.gz

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 添加執行權限
chmod +x JPEG2PDF-OFD-NoSpring

# 4. 運行
./JPEG2PDF-OFD-NoSpring config-multipage-false.json

# 多頁模式
./JPEG2PDF-OFD-NoSpring config-multipage-true.json
```

**注意：** 首次運行可能需要在"系統偏好設定 > 安全性與隱私"中允許執行。

#### JAR 版本（需要 Java 17+）

```bash
# 1. 安裝 Java 17+
# 使用 Homebrew
brew install openjdk@17

# 或下載：https://adoptium.net/

# 2. 驗證 Java
java -version

# 3. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

---

### Linux

#### Ubuntu / Debian

**jpackage 版本：**

```bash
# 1. 下載並解壓縮
tar -xzf JPEG2PDF-OFD-NoSpring.tar.gz

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 添加執行權限
chmod +x JPEG2PDF-OFD-NoSpring

# 4. 運行
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**JAR 版本：**

```bash
# 1. 安裝 Java 17+
sudo apt update
sudo apt install openjdk-17-jdk

# 2. 驗證
java -version

# 3. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

---

#### CentOS / RHEL / Fedora

**jpackage 版本：**

```bash
# 1. 下載並解壓縮
tar -xzf JPEG2PDF-OFD-NoSpring.tar.gz

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 添加執行權限
chmod +x JPEG2PDF-OFD-NoSpring

# 4. 運行
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**JAR 版本：**

```bash
# 1. 安裝 Java 17+
sudo yum install java-17-openjdk-devel
# 或
sudo dnf install java-17-openjdk-devel

# 2. 驗證
java -version

# 3. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

---

#### 統信 UOS（UnionTech OS）

**系統特點：**
- 基於 Deepin
- 國產操作系統
- 支持龍芯、鯤鵬、飛騰等國產 CPU

**jpackage 版本：**

```bash
# 1. 下載並解壓縮
tar -xzf JPEG2PDF-OFD-NoSpring.tar.gz

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 添加執行權限
chmod +x JPEG2PDF-OFD-NoSpring

# 4. 運行
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**JAR 版本：**

```bash
# 1. 檢查 Java 版本
java -version

# 如果沒有 Java 17+，安裝：
sudo apt install openjdk-17-jdk

# 2. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

**注意：** 
- 統信 UOS 通常預裝 Java 環境
- 如需安裝，可使用 UOS 應用商店或命令行

---

#### 麒麟 Kylin（銀河麒麟 / 中標麒麟）

**系統特點：**
- 國產操作系統
- 支持多種國產 CPU（龍芯、申威、飛騰等）
- 分為桌面版和服務器版

**jpackage 版本：**

```bash
# 1. 下載並解壓縮
tar -xzf JPEG2PDF-OFD-NoSpring.tar.gz

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 添加執行權限
chmod +x JPEG2PDF-OFD-NoSpring

# 4. 運行
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**JAR 版本：**

```bash
# 1. 檢查 Java 版本
java -version

# 如果沒有 Java 17+，安裝：
# 麒麟系統通常使用 yum 或 apt
sudo yum install java-17-openjdk-devel
# 或
sudo apt install openjdk-17-jdk

# 2. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

**注意：**
- 銀河麒麟 V10 可能使用不同的包管理器
- 建議優先使用 jpackage 版本（無需安裝 Java）

---

#### 深度 Deepin

**jpackage 版本：**

```bash
# 1. 下載並解壓縮
tar -xzf JPEG2PDF-OFD-NoSpring.tar.gz

# 2. 進入目錄
cd JPEG2PDF-OFD-NoSpring

# 3. 添加執行權限
chmod +x JPEG2PDF-OFD-NoSpring

# 4. 運行
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**JAR 版本：**

```bash
# 1. 安裝 Java 17+
sudo apt update
sudo apt install openjdk-17-jdk

# 2. 驗證
java -version

# 3. 運行
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

---

#### 其他國產 Linux 發行版

對於其他國產 Linux 系統（如中標軟件 NeoKylin、中興新支點等）：

**通用步驟：**

```bash
# 1. 檢查系統架構
uname -m
# x86_64 = Intel/AMD 64位
# aarch64 = ARM 64位（鯤鵬、飛騰）
# mips64 = 龍芯
# sw_64 = 申威

# 2. 下載對應架構的版本

# 3. 使用 jpackage 版本（推薦）
tar -xzf JPEG2PDF-OFD-NoSpring.tar.gz
cd JPEG2PDF-OFD-NoSpring
chmod +x JPEG2PDF-OFD-NoSpring
./JPEG2PDF-OFD-NoSpring config-multipage-false.json

# 或使用 JAR 版本（需要 Java 17+）
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config-multipage-false.json
```

**國產 CPU 架構支持：**

| CPU 架構 | 支持狀態 | 備註 |
|---------|---------|------|
| **x86_64** | ✅ 完全支持 | Intel/AMD 處理器 |
| **aarch64** | ✅ 完全支持 | 鯤鵬、飛騰處理器 |
| **mips64** | ⚠️ 需測試 | 龍芯處理器 |
| **sw_64** | ⚠️ 需測試 | 申威處理器 |

**注意：**
- 國產 CPU 架構建議優先使用 JAR 版本
- 需要對應架構的 JDK 17+
- jpackage 版本可能需要重新構建

---

### Docker 支持（可選）

**構建 Docker 映像：**

```dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar /app/
COPY config-multipage-false.json /app/

ENTRYPOINT ["java", "-Xmx2G", "-jar", "jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar"]
```

**使用：**

```bash
# 構建映像
docker build -t jpeg2pdf-ofd .

# 運行
docker run --rm \
  -v /path/to/input:/input \
  -v /path/to/output:/output \
  jpeg2pdf-ofd \
  -i /input \
  -o /output \
  -f all
```

---

### 性能優化建議

**內存設置：**

```bash
# 小圖片（< 2MP）
java -Xmx1G -jar jpeg2pdf-ofd-nospring.jar config.json

# 中等圖片（2-5MP）
java -Xmx2G -jar jpeg2pdf-ofd-nospring.jar config.json

# 大圖片（> 5MP）
java -Xmx4G -jar jpeg2pdf-ofd-nospring.jar config.json

# 批量處理
java -Xmx4G -jar jpeg2pdf-ofd-nospring.jar config.json
```

**多核優化：**

OCR 引擎會自動使用多核 CPU。確保：
- 系統有足夠的 CPU 核心
- 內存足夠（建議每核 512MB - 1GB）

---

### ⚠️ 中國國產 Linux 注意事項

使用中國國產 Linux 發行版時，請注意以下重要事項：

---

#### 1️⃣ CPU 架構兼容性

**支持的 CPU 架構：**

| CPU 架構 | 支持狀態 | 代表處理器 | 建議方案 |
|---------|---------|-----------|---------|
| **x86_64** | ✅ 完全支持 | Intel、AMD | 推薦使用 jpackage |
| **aarch64** | ✅ 完全支持 | 鯤鵬 920、飛騰 2000/2500/2000+ | 推薦使用 jpackage |
| **mips64** | ⚠️ 需測試 | 龍芯 2K/3A/3B | 建議使用 JAR 版本 |
| **sw_64** | ⚠️ 需測試 | 申威 1610/26010 | 建議使用 JAR 版本 |
| **loongarch64** | ⚠️ 需測試 | 龍芯 3A5000/3C5000 | 需要龍芯專版 JDK |

**重要說明：**

```
✅ x86_64 和 aarch64 架構：
   - 完全兼容標準 OpenJDK
   - jpackage 功能完整
   - 可直接使用預編譯版本

⚠️ mips64、sw_64、loongarch64 架構：
   - 需要廠商提供的專版 JDK
   - jpackage 可能不完全支持
   - 建議優先使用 JAR 版本
```

---

#### 2️⃣ JDK 版本要求

**官方推薦 JDK：**

| 系統 | 推薦 JDK | 下載來源 |
|------|---------|---------|
| **統信 UOS** | UOS JDK 17+ | 統信軟件官方源 |
| **銀河麒麟** | Kylin JDK 17+ | 麒麟軟件官方源 |
| **中標麒麟** | NeoKylin JDK 17+ | 中標軟件官方源 |
| **深度 Deepin** | OpenJDK 17+ | Deepin 官方源 |
| **龍芯系統** | Loongson JDK 17+ | 龍芯中科官網 |

**安裝示例（統信 UOS）：**

```bash
# 統信 UOS 通常使用 apt
sudo apt update
sudo apt install uos-jdk-17

# 設置 JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/uos-java-17
export PATH=$JAVA_HOME/bin:$PATH
```

**安裝示例（銀河麒麟）：**

```bash
# 銀河麒麟可能使用 yum 或 apt
sudo yum install kylin-java-17
# 或
sudo apt install kylin-jdk-17

# 設置 JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/kylin-java-17
export PATH=$JAVA_HOME/bin:$PATH
```

**⚠️ 重要提示：**

```
❌ 不要使用非官方 JDK
   - 可能導致性能問題
   - 可能有兼容性問題
   - 可能無法正常運行

✅ 使用系統自帶的應用商店或官方源
   - 確保兼容性
   - 獲得廠商支持
```

---

#### 3️⃣ 依賴庫問題

**常見問題：**

```bash
# 錯誤：找不到共享庫
error while loading shared libraries: libXXX.so.1: cannot open shared object file

# 解決：安裝缺失的庫
sudo apt install libfreetype6-dev  # UOS/Deepin
sudo yum install freetype-devel    # Kylin
```

**推薦依賴：**

```bash
# Ubuntu/Debian 系（UOS、Deepin）
sudo apt install -y \
  build-essential \
  libfreetype6-dev \
  libfontconfig1-dev \
  libpng-dev \
  libjpeg-dev

# RHEL 系（麒麟）
sudo yum install -y \
  gcc \
  gcc-c++ \
  freetype-devel \
  fontconfig-devel \
  libpng-devel \
  libjpeg-devel
```

---

#### 4️⃣ 字體支持

**問題：** 國產系統可能缺少中文字體

**解決方案：**

```bash
# 檢查可用字體
fc-list :lang=zh

# 安裝中文字體（UOS/Deepin）
sudo apt install -y fonts-wqy-zenhei fonts-wqy-microhei

# 安裝中文字體（麒麟）
sudo yum install -y wqy-zenhei-fonts wqy-microhei-fonts

# 或使用系統字體
# 配置文件中指定字體路徑：
# /usr/share/fonts/truetype/wqy/wqy-zenhei.ttc
```

**在配置文件中指定字體：**

```json
{
  "ocr": {
    "language": "chinese_cht"
  },
  "font": {
    "path": "/usr/share/fonts/truetype/wqy/wqy-zenhei.ttc"
  }
}
```

---

#### 5️⃣ jpackage 限制

**已知問題：**

```
⚠️ jpackage 在某些國產架構上可能不支持
   - mips64、sw_64、loongarch64 需要測試
   - 建議優先使用 JAR 版本

⚠️ jpackage 生成的安裝包可能無法跨架構使用
   - x86_64 的 .deb 不能在 aarch64 上安裝
   - 必須在目標架構上重新構建
```

**建議方案：**

```
✅ 優先使用 JAR 版本
   - 更好的兼容性
   - 跨架構通用
   - 只需要對應架構的 JDK

❌ 避免使用 jpackage（除非已測試）
   - 可能有不兼容問題
   - 需要特定架構的工具鏈
```

---

#### 6️⃣ 文件系統特性

**注意事項：**

```bash
# 某些國產系統使用特殊文件系統
# 可能有權限問題

# 確保輸出目錄有寫入權限
chmod -R 755 /path/to/output

# 避免使用特殊字符
# 某些系統對中文路徑支持不完善
# 建議使用英文路徑
```

**推薦路徑：**

```json
{
  "input": {
    "folder": "/opt/ocr/input"    // ✅ 推薦
    // "folder": "/home/用戶/ocr" // ⚠️ 可能問題
  },
  "output": {
    "folder": "/opt/ocr/output"  // ✅ 推薦
  }
}
```

---

#### 7️⃣ 安全策略

**國產系統安全限制：**

```bash
# 某些系統默認啟用嚴格的安全策略
# 可能阻止程序執行

# 統信 UOS
sudo setenforce 0  # 臨時關閉（測試用）
# 或添加到白名單

# 銀河麒麟
sudo chmod +x JPEG2PDF-OFD-NoSpring
./JPEG2PDF-OFD-NoSpring config.json
```

**安全建議：**

```
⚠️ 不要完全關閉安全策略
   - 只在測試時臨時關閉
   - 或添加程序到信任列表

✅ 聯繫系統管理員
   - 獲取適當的權限
   - 遵循安全規範
```

---

#### 8️⃣ 性能調優

**國產 CPU 性能特點：**

```bash
# 鯤鵬 920（aarch64）
# - 多核性能強
# - 內存帶寬大
java -Xmx4G -XX:+UseG1GC -jar jpeg2pdf-ofd-nospring.jar config.json

# 飛騰 2000+（aarch64）
# - 單核性能一般
# - 多核並行效率高
java -Xmx4G -XX:ParallelGCThreads=8 -jar jpeg2pdf-ofd-nospring.jar config.json

# 龍芯 3A5000（loongarch64）
# - 性能相對較低
# - 增加 GC 優化
java -Xmx4G -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -jar jpeg2pdf-ofd-nospring.jar config.json
```

**優化建議：**

```bash
# 通用優化參數
java \
  -Xmx4G \
  -XX:+UseG1GC \
  -XX:ParallelGCThreads=4 \
  -XX:ConcGCThreads=2 \
  -XX:MaxGCPauseMillis=200 \
  -Djava.awt.headless=true \
  -jar jpeg2pdf-ofd-nospring.jar config.json
```

---

#### 9️⃣ 常見問題排查

**問題 1：程序無法啟動**

```bash
# 檢查 JDK 版本
java -version  # 應該是 17 或更高

# 檢查架構
uname -m  # 確認 JDK 架構匹配

# 檢查權限
chmod +x JPEG2PDF-OFD-NoSpring
```

**問題 2：OCR 識別失敗**

```bash
# 檢查模型文件
ls -la models/

# 檢查內存
free -h  # 確保至少 4GB 可用

# 檢查 CPU
lscpu  # 確認指令集支持
```

**問題 3：PDF/OFD 生成失敗**

```bash
# 檢查字體
fc-list :lang=zh

# 檢查磁盤空間
df -h

# 檢查輸出目錄權限
ls -la /path/to/output
```

**問題 4：性能過慢**

```bash
# 增加 JVM 內存
java -Xmx8G -jar jpeg2pdf-ofd-nospring.jar config.json

# 優化 GC
java -XX:+UseG1GC -jar jpeg2pdf-ofd-nospring.jar config.json

# 減少並發
# 在配置中處理較小的批次
```

---

#### 🔟 獲取技術支持

**官方支持渠道：**

| 系統 | 官方支持 |
|------|---------|
| 統信 UOS | https://www.chinauos.com/ |
| 銀河麒麟 | https://www.kylinos.cn/ |
| 中標麒麟 | http://www.cs2c.com.cn/ |
| 深度 Deepin | https://www.deepin.org/ |
| 龍芯中科 | https://www.loongson.cn/ |

**社區支持：**

```
- GitHub Issues: https://github.com/brianshih04/jpeg2pdf-ofd-nospring/issues
- 提供詳細的系統信息：
  - 系統名稱和版本
  - CPU 架構
  - JDK 版本
  - 錯誤日誌
```

---

**💡 總結：**

```
✅ 優先使用 JAR 版本（更好的兼容性）
✅ 使用系統官方源安裝 JDK
✅ 確保字體支持
✅ 注意文件路徑和權限
✅ 根據 CPU 架構調整 JVM 參數
⚠️ jpackage 版本需要測試驗證
```

---

## 🔧 如何構建

### 系統需求

```
- JDK 17+
- Maven 3.6+
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

#### Windows

**前置需求：**
- JDK 17+ (包含 jpackage)
- Maven 3.6+

**構建步驟：**

```powershell
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

#### macOS

**前置需求：**
- JDK 17+ (包含 jpackage)
- Maven 3.6+
- Xcode Command Line Tools

**構建步驟：**

```bash
# 1. 安裝 JDK 17+
# 使用 Homebrew
brew install openjdk@17

# 2. 設置 JAVA_HOME
export JAVA_HOME=/usr/local/opt/openjdk@17
export PATH=$JAVA_HOME/bin:$PATH

# 3. 驗證
java -version
jpackage --version

# 4. 構建 JAR
mvn clean package -DskipTests

# 5. 構建 jpackage
mkdir -p jpackage-input
cp target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar jpackage-input/

jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type app-image \
  --dest dist-jpackage \
  --java-options -Xmx2G

# 6. 添加配置文件
cp config-multipage-false.json dist-jpackage/JPEG2PDF-OFD-NoSpring/
cp config-multipage-true.json dist-jpackage/JPEG2PDF-OFD-NoSpring/

# 輸出
# dist-jpackage/JPEG2PDF-OFD-NoSpring/ (~180 MB)
```

**使用：**

```bash
cd dist-jpackage/JPEG2PDF-OFD-NoSpring
chmod +x JPEG2PDF-OFD-NoSpring
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**創建 .app 應用程序（可選）：**

```bash
# 創建 macOS .app 應用程序
jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type app-image \
  --dest dist-jpackage \
  --java-options -Xmx2G \
  --mac-package-identifier com.ocr.jpeg2pdf \
  --mac-package-name "JPEG2PDF OFD" \
  --mac-package-signing-prefix com.ocr \
  --icon icons/icon.icns  # 可選：自定義圖標
```

**文件結構：**

```
dist-jpackage/JPEG2PDF-OFD-NoSpring/
├── JPEG2PDF-OFD-NoSpring (可執行文件)
├── app/                   (應用程序)
├── runtime/               (JRE runtime)
├── config-multipage-false.json
└── config-multipage-true.json
```

**注意：**
- 首次運行可能需要在"系統偏好設定 > 安全性與隱私"中允許執行
- 若要分發給其他用戶，需要進行代碼簽名

---

#### Linux (Ubuntu/Debian)

**前置需求：**
- JDK 17+ (包含 jpackage)
- Maven 3.6+
- build-essential (編譯工具)

**構建步驟：**

```bash
# 1. 安裝依賴
sudo apt update
sudo apt install -y openjdk-17-jdk maven build-essential

# 2. 設置 JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# 3. 驗證
java -version
jpackage --version

# 4. 構建 JAR
mvn clean package -DskipTests

# 5. 構建 jpackage
mkdir -p jpackage-input
cp target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar jpackage-input/

jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type app-image \
  --dest dist-jpackage \
  --java-options -Xmx2G

# 6. 添加配置文件
cp config-multipage-false.json dist-jpackage/JPEG2PDF-OFD-NoSpring/
cp config-multipage-true.json dist-jpackage/JPEG2PDF-OFD-NoSpring/

# 輸出
# dist-jpackage/JPEG2PDF-OFD-NoSpring/ (~180 MB)
```

**使用：**

```bash
cd dist-jpackage/JPEG2PDF-OFD-NoSpring
chmod +x JPEG2PDF-OFD-NoSpring
./JPEG2PDF-OFD-NoSpring config-multipage-false.json
```

**創建 .deb 安裝包（可選）：**

```bash
# 創建 .deb 安裝包
jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type deb \
  --dest dist-jpackage \
  --java-options -Xmx2G \
  --linux-package-name jpeg2pdf-ofd \
  --linux-deb-maintainer your-email@example.com \
  --linux-menu-group "Graphics;OCR" \
  --linux-shortcut

# 安裝
sudo dpkg -i dist-jpackage/jpeg2pdf-ofd_*.deb
```

**文件結構：**

```
dist-jpackage/JPEG2PDF-OFD-NoSpring/
├── JPEG2PDF-OFD-NoSpring (可執行文件)
├── app/                   (應用程序)
├── runtime/               (JRE runtime)
├── config-multipage-false.json
└── config-multipage-true.json
```

---

#### Linux (CentOS/RHEL/Fedora)

**構建步驟：**

```bash
# 1. 安裝依賴
sudo yum install -y java-17-openjdk-devel maven gcc

# 或使用 dnf
sudo dnf install -y java-17-openjdk-devel maven gcc

# 2. 設置 JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# 3. 構建 JAR
mvn clean package -DskipTests

# 4. 構建 jpackage
mkdir -p jpackage-input
cp target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar jpackage-input/

jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type app-image \
  --dest dist-jpackage \
  --java-options -Xmx2G

# 5. 添加配置文件
cp config-multipage-false.json dist-jpackage/JPEG2PDF-OFD-NoSpring/
cp config-multipage-true.json dist-jpackage/JPEG2PDF-OFD-NoSpring/
```

**創建 .rpm 安裝包（可選）：**

```bash
# 創建 .rpm 安裝包
jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type rpm \
  --dest dist-jpackage \
  --java-options -Xmx2G \
  --linux-package-name jpeg2pdf-ofd \
  --linux-rpm-license-type MIT \
  --linux-menu-group "Graphics;OCR" \
  --linux-shortcut

# 安裝
sudo rpm -i dist-jpackage/jpeg2pdf-ofd-*.rpm
```

---

#### 國產 Linux（統信 UOS、麒麟 Kylin 等）

**構建步驟：**

```bash
# 1. 檢查系統架構
uname -m

# 2. 根據架構選擇對應的 JDK
# x86_64: 使用標準 OpenJDK
# aarch64 (鯤鵬/飛騰): 使用 ARM64 版本 JDK
# mips64 (龍芯): 使用龍芯版本 JDK
# sw_64 (申威): 使用申威版本 JDK

# 3. 安裝依賴（根據系統包管理器）
# Debian 系（UOS、Deepin）
sudo apt install -y openjdk-17-jdk maven build-essential

# RHEL 系（麒麟）
sudo yum install -y java-17-openjdk-devel maven gcc

# 4. 設置環境變量
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH

# 5. 構建
mvn clean package -DskipTests

# 6. 創建 jpackage
mkdir -p jpackage-input
cp target/jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar jpackage-input/

jpackage \
  --name JPEG2PDF-OFD-NoSpring \
  --input jpackage-input \
  --main-jar jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar \
  --main-class com.ocr.nospring.Main \
  --type app-image \
  --dest dist-jpackage \
  --java-options -Xmx2G

# 7. 添加配置文件
cp config-multipage-false.json dist-jpackage/JPEG2PDF-OFD-NoSpring/
cp config-multipage-true.json dist-jpackage/JPEG2PDF-OFD-NoSpring/
```

**注意：**
- 國產 CPU 架構可能需要從源碼編譯 JDK
- jpackage 在某些架構上可能不完全支持
- 建議優先使用 JAR 版本（需要對應架構的 JDK）

---

## 📝 配置文件

### 單頁模式（每個圖片一個 PDF/OFD）

```json
{
  "input": {
    "folder": "C:/OCR/Watch",
    "pattern": "*.jpg"
  },
  "output": {
    "folder": "C:/OCR/Output",
    "format": "all",
    "multiPage": false
  },
  "ocr": {
    "language": "chinese_cht"
  }
}
```

### 多頁模式（所有圖片合併成一個 PDF/OFD）

```json
{
  "input": {
    "folder": "C:/OCR/Watch",
    "pattern": "*.jpg"
  },
  "output": {
    "folder": "C:/OCR/Output",
    "format": "all",
    "multiPage": true
  },
  "ocr": {
    "language": "chinese_cht"
  }
}
```

---

## 🎯 輸出模式說明

### **單頁模式** (`multiPage: false`, 默認)

```
特點：
  - 每個圖片生成一個獨立的 PDF/OFD
  - 適合獨立處理文件
  
輸出：
  image1_20260323_130000.pdf
  image1_20260323_130000.txt
  image1_20260323_130000.ofd
  
  image2_20260323_130001.pdf
  image2_20260323_130001.txt
  image2_20260323_130001.ofd
```

### **多頁模式** (`multiPage: true`)

```
特點：
  - 所有圖片合併成一個 PDF/OFD
  - 適合掃描文檔、批量報告
  
輸出：
  multipage_20260323_130000.pdf  (包含所有頁面)
  multipage_20260323_130000.txt  (包含所有頁面文字)
  multipage_20260323_130000.ofd  (包含所有頁面)
```

---

## 📋 配置參數

### **input 配置**

```json
{
  "input": {
    "file": "C:/OCR/test.jpg",      // 單個文件
    "folder": "C:/OCR/Watch",       // 資料夾
    "pattern": "*.jpg"              // 通配符（*.jpg, *.png, *.*）
  }
}
```

### **output 配置**

```json
{
  "output": {
    "folder": "C:/OCR/Output",      // 輸出目錄
    "format": "all",                // pdf, ofd, txt, all
    "multiPage": false              // true=多頁, false=單頁
  }
}
```

### **ocr 配置**

```json
{
  "ocr": {
    "language": "chinese_cht"       // OCR 語言
  }
}
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
│   ├── Main.java              # 主程序
│   ├── Config.java            # 配置類
│   ├── OcrService.java        # OCR 服務
│   ├── PdfService.java        # PDF 服務（含多頁）
│   ├── OfdService.java        # OFD 服務（含多頁）
│   └── TextService.java       # TXT 服務（含多頁）
├── dist/                       # JAR 輸出
├── dist-jpackage/              # jpackage 輸出
├── build.ps1                   # JAR 構建腳本
├── build-jpackage.ps1          # jpackage 構建腳本
├── config-single.json          # 單頁模式配置
└── config-multipage.json       # 多頁模式配置
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

## 🎯 使用場景

### **單頁模式適合：**
- ✅ 獨立處理文件
- ✅ 批量處理圖片
- ✅ 靈活的輸出管理

### **多頁模式適合：**
- ✅ 掃描文檔（多頁掃描）
- ✅ 批量報告生成
- ✅ 文檔歸檔

---

## ❓ 常見問題

### Q: 哪個版本最推薦？

**A:** 
- **無 Java 環境**: jpackage 版本（181 MB）⭐⭐⭐⭐⭐
- **有 Java 環境**: JAR 版本（52 MB）⭐⭐⭐⭐

### Q: 單頁和多頁模式有什麼區別？

**A:**
- **單頁模式**：每個圖片生成一個獨立的 PDF/OFD 文件
- **多頁模式**：所有圖片合併成一個 PDF/OFD 文件（類似掃描儀）

### Q: 如何支持更多語言？

**A:** 修改 `config.json` 中的 `ocr.language` 參數。支持 80+ 種語言。

### Q: 輸出的 PDF 可以搜索嗎？

**A:** 是的！這是本項目的核心功能。OCR 識別的文字會作為不可見文字層添加到 PDF 中，支持全文搜索。

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
- **PDFBox**: https://pdfbox.apache.org/
- **RapidOCR**: https://github.com/RapidAI/RapidOCR

---

**🎉 感謝使用 JPEG2PDF-OFD！** ✨
