# Native Image 分支 - 命令行版本（無 JSON）

## 🎯 目標

創建**真正的單一 EXE 檔案**，無需 Java、無需 JSON 依賴。

---

## ✅ 已完成

```
✅ 移除所有 JSON 依賴（Jackson）
✅ 創建簡單的命令行參數解析
✅ 簡化反射配置
✅ 優化 Native Image 編譯配置
```

---

## 📦 文件變更

### 新增文件

```
1. NativeCli.java
   - 純命令行參數解析
   - 無 JSON 依賴
   - 支持 -i/-o/-l/-f 參數

2. Config.java
   - 簡單配置類
   - 無 JSON 依賴
   - 自動檢測字體路徑
```

### 修改文件

```
1. reflect-config.json
   - 移除 Jackson 配置
   - 只保留必要的反射
   - 大幅簡化

2. native-image.properties
   - 使用新主類：NativeCli
   - 簡化編譯選項

3. build-native-image.ps1
   - 使用新主類
   - 簡化編譯流程
```

---

## 🚀 使用方法

### 基本語法

```cmd
jpeg2pdf-ofd-native.exe -i <input> -o <output> [options]
```

### 參數說明

```
必須參數：
  -i, --input <path>     輸入文件/資料夾/通配符

可選參數：
  -o, --output <path>    輸出資料夾（默認：當前目錄）
  -l, --lang <lang>      OCR 語言（默認：chinese_cht）
  -f, --format <fmt>     輸出格式（默認：pdf）
  -h, --help             顯示幫助
  -v, --version          顯示版本
```

### 使用示例

#### 1. 單個文件

```cmd
jpeg2pdf-ofd-native.exe -i scan.jpg -o output/
```

#### 2. 資料夾

```cmd
jpeg2pdf-ofd-native.exe -i images/ -o output/
```

#### 3. 通配符

```cmd
jpeg2pdf-ofd-native.exe -i *.jpg -o output/
jpeg2pdf-ofd-native.exe -i C:\OCR\*.png -o C:\OCR\Output
```

#### 4. 指定語言

```cmd
# 繁體中文（默認）
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l chinese_cht

# 簡體中文
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l ch

# 英文
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l en
```

#### 5. 指定格式

```cmd
# 只有 PDF（默認）
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -f pdf

# 所有格式
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -f all

# PDF + OFD
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -f pdf,ofd
```

#### 6. 完整示例

```cmd
# 完整參數
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l chinese_cht -f all

# 顯示幫助
jpeg2pdf-ofd-native.exe --help

# 顯示版本
jpeg2pdf-ofd-native.exe --version
```

---

## 📊 對比

### 功能對比

| 特性 | JSON 版本 | 命令行版本 |
|------|----------|----------|
| **配置方式** | JSON 文件 | 命令行參數 |
| **Jackson 依賴** | ✅ 需要 | ❌ 不需要 |
| **反射配置** | 複雜 | 簡單 |
| **Native Image 難度** | 困難 | 容易 |
| **EXE 大小** | ~50 MB | ~30-40 MB |
| **啟動時間** | ~1 秒 | <1 秒 |
| **易用性** | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |

### 大小對比

| 版本 | 大小 | Java | 單文件 |
|------|------|------|--------|
| **Native Image** | **~30-40 MB** | **❌ 否** | **✅ 是** |
| jpackage | 181 MB | ❌ 否 | ❌ 資料夾 |
| JAR | 52 MB | ✅ 是 | ✅ 是 |

---

## 🔧 構建

### 前置需求

```
1. GraalVM CE 17+
2. Native Image 組件
3. Visual Studio Build Tools（Windows）
```

### 構建步驟

```powershell
# 1. 切換分支
git checkout native-image

# 2. 運行構建腳本
.\build-native-image.ps1

# 3. 輸出
#    dist-native\jpeg2pdf-ofd-native.exe
```

### 構建時間

```
預計：5-10 分鐘（取決於電腦性能）
```

---

## ⚠️ 已知限制

### 1. RapidOCR JNI

```
⚠️ RapidOCR 使用 JNI 調用原生庫
⚠️ Native Image 需要特殊處理
⚠️ 可能需要在運行時提取 .dll 文件
```

### 2. OCR 模型

```
⚠️ 模型文件（~100 MB）需要處理
⚠️ 選項：
   - 包含在 EXE 中（EXE 變大）
   - 外部加載（需要額外文件）
```

---

## 💡 備用方案

### 如果 Native Image 編譯失敗

**使用已測試成功的版本：**

#### 1. jpackage 版本（推薦） ⭐⭐⭐⭐⭐

```
位置：dist-jpackage\JPEG2PDF-OFD-NoSpring\
大小：181 MB
需求：無需 Java
狀態：✅ 已測試成功
```

#### 2. JAR 版本 ⭐⭐⭐⭐

```
位置：dist\jpeg2pdf-ofd-nospring.jar
大小：52 MB
需求：Java 17+
狀態：✅ 已測試成功
```

---

## 📝 配置示例

### 命令行版本（當前）

```cmd
jpeg2pdf-ofd-native.exe -i C:\OCR\Watch -o C:\OCR\Output -l chinese_cht -f all
```

### JSON 版本（main 分支）

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

---

## 🎯 當前狀態

```
✅ 命令行版本已完成
✅ 無 JSON 依賴
✅ 配置文件已簡化
✅ 構建腳本已準備
⏳ 待編譯測試
```

---

## 🚀 下一步

```powershell
# 1. 切換到 native-image 分支
git checkout native-image

# 2. 運行構建腳本
.\build-native-image.ps1

# 3. 如果成功
#    - 測試 EXE
#    - 更新文檔

# 4. 如果失敗
#    - 使用 jpackage 版本（181 MB，無需 Java）
#    - 或使用 JAR 版本（52 MB，需要 Java）
```

---

## 📞 相關鏈接

- **GitHub 分支**: https://github.com/brianshih04/jpeg2pdf-ofd-nospring/tree/native-image
- **GraalVM 文檔**: https://www.graalvm.org/reference-manual/native-image/
- **Native Image 配置**: https://www.graalvm.org/reference-manual/native-image/BuildConfiguration/

---

**分支：** native-image
**狀態：** 配置完成，待構建
**預計時間：** 5-10 分鐘
