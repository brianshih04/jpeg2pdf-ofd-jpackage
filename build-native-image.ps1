# GraalVM Native Image 構建腳本（簡化版）

$ErrorActionPreference = "Stop"

Write-Host "========================================"
Write-Host "  GraalVM Native Image Builder"
Write-Host "  No JSON Dependencies"
Write-Host "========================================"
Write-Host ""

# 檢查 GraalVM
Write-Host "[1/6] 檢查 GraalVM 環境..."

$GRAALVM_HOME = "C:\graalvm\graalvm-community-openjdk-17.0.9+9.1"
$NATIVE_IMAGE = "$GRAALVM_HOME\lib\svm\bin\native-image.exe"

if (-not (Test-Path $NATIVE_IMAGE)) {
    Write-Host "❌ Native Image 未找到: $NATIVE_IMAGE"
    Write-Host ""
    Write-Host "請先安裝 GraalVM 和 Native Image"
    Write-Host "參考: https://www.graalvm.org/downloads/"
    exit 1
}

Write-Host "✅ Native Image: $NATIVE_IMAGE"
Write-Host ""

# 編譯 JAR
Write-Host "[2/6] 編譯 JAR..."

mvn clean package -DskipTests 2>&1 | Out-Null

if ($LASTExitCode -ne 0) {
    Write-Host "❌ Maven 編譯失敗"
    exit 1
}

Write-Host "✅ JAR 已編譯"
Write-Host ""

# 創建輸出目錄
Write-Host "[3/6] 準備輸出目錄..."

if (Test-Path "dist-native") {
    Remove-Item -Recurse -Force "dist-native"
}

New-Item -ItemType Directory -Path "dist-native" -Force | Out-Null

Write-Host "✅ 輸出目錄已創建"
Write-Host ""

# Native Image 編譯
Write-Host "[4/6] 編譯 Native Image（這需要 5-10 分鐘）..."
Write-Host ""

$jarPath = "target\jpeg2pdf-ofd-nospring-3.0.0-jar-with-dependencies.jar"

if (-not (Test-Path $jarPath)) {
    Write-Host "❌ JAR 未找到: $jarPath"
    exit 1
}

# 計算 JAR 大小
$jarSize = [math]::Round((Get-Item $jarPath).Length / 1MB, 2)
Write-Host "JAR 大小: $jarSize MB"
Write-Host ""

# Native Image 編譯
Write-Host "開始編譯..."

$env:JAVA_HOME = $GRAALVM_HOME

# 使用簡單的編譯選項
& $NATIVE_IMAGE `
  --no-fallback `
  -H:Name=jpeg2pdf-ofd-native `
  -H:Class=com.ocr.nospring.NativeCli `
  -jar $jarPath 2>&1 | Tee-Object -FilePath "native-image-build.log"

if ($LASTExitCode -ne 0) {
    Write-Host ""
    Write-Host "❌ Native Image 編譯失敗"
    Write-Host ""
    Write-Host "查看日誌："
    Get-Content "native-image-build.log" | Select-Object -Last 30
    Write-Host ""
    Write-Host "備用方案："
    Write-Host "  1. 使用 jpackage 版本（181 MB，無需 Java）"
    Write-Host "  2. 使用 JAR 版本（52 MB，需要 Java）"
    exit 1
}

Write-Host ""
Write-Host "✅ Native Image 編譯成功"
Write-Host ""

# 移動 EXE
Write-Host "[5/6] 移動輸出文件..."

Move-Item "jpeg2pdf-ofd-native.exe" "dist-native\jpeg2pdf-ofd-native.exe" -Force

# 創建 README
$readme = @'
# JPEG2PDF-OFD Native Image Edition

## 特點

- ✅ 單文件 EXE
- ✅ 無需 Java
- ✅ 無 JSON 依賴
- ✅ 極快啟動

## 使用方法

```cmd
# 單個文件
jpeg2pdf-ofd-native.exe -i image.jpg -o output/

# 資料夾
jpeg2pdf-ofd-native.exe -i images/ -o output/

# 通配符
jpeg2pdf-ofd-native.exe -i *.jpg -o output/

# 指定語言和格式
jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l chinese_cht -f all
```

## 參數

```
-i, --input <path>     輸入文件/資料夾/通配符（必須）
-o, --output <path>    輸出資料夾（默認：當前目錄）
-l, --lang <lang>      OCR 語言（默認：chinese_cht）
-f, --format <fmt>     輸出格式（默認：pdf）
-h, --help             顯示幫助
-v, --version          顯示版本
```

## 格式

- pdf  - 可搜索 PDF
- ofd  - 可搜索 OFD
- txt  - 純文本
- all  - 所有格式

## 語言

- chinese_cht - 繁體中文（默認）
- ch          - 簡體中文
- en          - 英文
- japan       - 日文
- korean      - 韓文
- （80+ 種語言）

## 對比

| 版本 | 大小 | Java | 單文件 |
|------|------|------|--------|
| Native Image | ~30-50 MB | ❌ | ✅ |
| jpackage | 181 MB | ❌ | ❌ |
| JAR | 52 MB | ✅ | ✅ |
'@

Set-Content -Path "dist-native\README.md" -Encoding UTF8

Write-Host "✅ 輸出文件已準備"
Write-Host ""

# 顯示結果
Write-Host "[6/6] 構建總結"
Write-Host ""
Write-Host "========================================"
Write-Host "  構建完成！"
Write-Host "========================================"
Write-Host ""

# 計算大小
$exeSize = [math]::Round((Get-Item "dist-native\jpeg2pdf-ofd-native.exe").Length / 1MB, 2)

Write-Host "輸出位置："
Write-Host "  dist-native\"
Write-Host ""
Write-Host "文件大小："
Write-Host "  EXE: $exeSize MB"
Write-Host ""
Write-Host "========================================"
Write-Host "  大小對比"
Write-Host "========================================"
Write-Host ""
Write-Host "| 版本 | 大小 | 改善 |"
Write-Host "|------|------|------|"
Write-Host "| Native Image | $exeSize MB | - |"
Write-Host "| jpackage | 181 MB | -$([math]::Round((181-$exeSize)/181*100, 1))% |"
Write-Host "| JAR | 52 MB | -$([math]::Round((52-$exeSize)/52*100, 1))% |"
Write-Host ""
Write-Host "========================================"
Write-Host "  使用方法"
Write-Host "========================================"
Write-Host ""
Write-Host "cd dist-native"
Write-Host ""
Write-Host "# 顯示幫助"
Write-Host "jpeg2pdf-ofd-native.exe --help"
Write-Host ""
Write-Host "# 單個文件"
Write-Host "jpeg2pdf-ofd-native.exe -i image.jpg -o output/"
Write-Host ""
Write-Host "# 資料夾"
Write-Host "jpeg2pdf-ofd-native.exe -i images/ -o output/"
Write-Host ""
Write-Host "# 指定語言和格式"
Write-Host "jpeg2pdf-ofd-native.exe -i image.jpg -o output/ -l en -f all"
Write-Host ""

pause
