---

## 📋 JSON 配置文件詳解

### **完整配置結構**

```json
{
  "input": {
    "folder": "C:/OCR/Watch",
    "pattern": "*.jpg"
  },
  "output": {
    "folder": "C:/OCR/Output",
    "formats": ["pdf", "ofd", "txt"],
    "multiPage": true
  },
  "ocr": {
    "language": "chinese_cht",
    "useGpu": false,
    "cpuThreads": 4
  },
  "font": {
    "path": "C:/Windows/Fonts/msyh.ttc"
  }
}
```

### **📖 input 配置（輸入設置）**

| 參數 | 類型 | 必填 | 默認值 | 說明 |
|------|------|------|--------|------|
| `folder` | String | ✅ | - | 輸入圖片資料夾路徑 |
| `file` | String | ⚪ | - | 單個圖片文件路徑（與 folder 二選一） |
| `pattern` | String | ⚪ | `*.jpg` | 文件過濾模式 |
| `extensions` | Array | ⚪ | `["jpg", "jpeg", "png"]` | 支持的擴展名列表 |

**使用示例：**

```json
// 示例 1：處理整個資料夾
{
  "input": {
    "folder": "C:/OCR/Watch",
    "pattern": "*.jpg"
  }
}

// 示例 2：處理單個文件
{
  "input": {
    "file": "C:/OCR/test.jpg"
  }
}

// 示例 3：支持多種格式
{
  "input": {
    "folder": "C:/OCR/Watch",
    "extensions": ["jpg", "jpeg", "png", "bmp", "gif"]
  }
}
```

### **📖 output 配置（輸出設置）**

| 參數 | 類型 | 必填 | 默認值 | 說明 |
|------|------|------|--------|------|
| `folder` | String | ✅ | - | 輸出資料夾路徑 |
| `formats` | Array | ⚪ | `["pdf"]` | 輸出格式列表 |
| `multiPage` | Boolean | ⚪ | `false` | 是否合併為多頁文檔 |

**格式說明：**
- `"pdf"` - 只生成 PDF
- `"ofd"` - 只生成 OFD（中國國家標準格式）
- `"txt"` - 只生成 TXT（純文字）
- `["pdf", "ofd"]` - 生成 PDF 和 OFD
- `["pdf", "ofd", "txt"]` - 生成所有格式

**使用示例：**

```json
// 示例 1：單頁模式（默認）
{
  "output": {
    "folder": "C:/OCR/Output",
    "formats": ["pdf", "ofd", "txt"],
    "multiPage": false
  }
}

// 示例 2：多頁模式（合併所有圖片）
{
  "output": {
    "folder": "C:/OCR/Output",
    "formats": ["pdf", "ofd", "txt"],
    "multiPage": true
  }
}

// 示例 3：只生成 PDF
{
  "output": {
    "folder": "C:/OCR/Output",
    "formats": ["pdf"]
  }
}
```

### **📖 ocr 配置（OCR 設置）**

| 參數 | 類型 | 必填 | 默認值 | 說明 |
|------|------|------|--------|------|
| `language` | String | ⚪ | `chinese_cht` | OCR 識別語言 |
| `useGpu` | Boolean | ⚪ | `false` | 是否使用 GPU 加速 |
| `cpuThreads` | Integer | ⚪ | `4` | CPU 線程數 |

**支持語言（80+ 種）：**

| 語言代碼 | 語言名稱 |
|---------|---------|
| `chinese_cht` | 繁體中文（默認） |
| `ch` | 簡體中文 |
| `en` | 英文 |
| `japan` | 日文 |
| `korean` | 韓文 |
| `french` | 法文 |
| `german` | 德文 |
| `spanish` | 西班牙文 |
| `portuguese` | 葡萄牙文 |
| `russian` | 俄文 |
| `arabic` | 阿拉伯文 |
| `hindi` | 印地文 |
| `thai` | 泰文 |
| `vietnamese` | 越南文 |
| `italian` | 意大利文 |
| `dutch` | 荷蘭文 |

**使用示例：**

```json
// 示例 1：英文文檔
{
  "ocr": {
    "language": "en"
  }
}

// 示例 2：高性能配置（有 GPU）
{
  "ocr": {
    "language": "en",
    "useGpu": true,
    "cpuThreads": 8
  }
}
```

### **📖 font 配置（字體設置）**

| 參數 | 類型 | 必填 | 默認值 | 說明 |
|------|------|------|--------|------|
| `path` | String | ⚪ | 系統默認 | TrueType 字體文件路徑 |

**使用示例：**

```json
{
  "font": {
    "path": "C:/Windows/Fonts/arial.ttf"
  }
}
```

---

### **💡 配置文件完整示例**

#### **示例 1：處理繁體中文（多頁）**
```json
{
  "input": {
    "folder": "C:/OCR/Chinese"
  },
  "output": {
    "folder": "C:/OCR/Output",
    "formats": ["pdf", "ofd", "txt"],
    "multiPage": true
  },
  "ocr": {
    "language": "chinese_cht"
  }
}
```

#### **示例 2：處理英文文檔（單頁）**
```json
{
  "input": {
    "folder": "C:/Documents/English"
  },
  "output": {
    "folder": "C:/Output",
    "formats": ["pdf", "txt"],
    "multiPage": false
  },
  "ocr": {
    "language": "en"
  }
}
```

#### **示例 3：高性能配置**
```json
{
  "input": {
    "folder": "C:/OCR/Watch",
    "pattern": "*.*"
  },
  "output": {
    "folder": "C:/OCR/Output",
    "formats": ["pdf", "ofd"],
    "multiPage": true
  },
  "ocr": {
    "language": "en",
    "useGpu": true,
    "cpuThreads": 16
  }
}
```

---

### **⚠️ 配置注意事項**

#### **1. 路徑格式**
```json
// ✅ 推薦：正斜線（跨平台）
{
  "input": {
    "folder": "C:/OCR/Watch"
  }
}

// ⚠️ 可用：雙反斜線（僅 Windows）
{
  "input": {
    "folder": "C:\\OCR\\Watch"
  }
}

// ❌ 錯誤：單反斜線（會轉義錯誤）
{
  "input": {
    "folder": "C:\OCR\Watch"  // ❌ 錯誤！
  }
}
```

#### **2. JSON 語法**
```json
// ✅ 正確示例
{
  "output": {
    "formats": ["pdf", "ofd"],  // 最後一項無逗號
    "multiPage": true            // 最後一個屬性無逗號
  }
}

// ❌ 錯誤示例
{
  "output": {
    "formats": ["pdf", "ofd",],  // ❌ 數組最後多逗號
    "multiPage": true,           // ❌ 對象最後多逗號
  }
}
```

#### **3. 性能優化建議**
```
小圖片（< 2MP）:  -Xmx1G, cpuThreads: 2
中等圖片（2-5MP）: -Xmx2G, cpuThreads: 4
大圖片（> 5MP）:   -Xmx4G, cpuThreads: 8
批量處理:         -Xmx4G, multiPage: true
```
