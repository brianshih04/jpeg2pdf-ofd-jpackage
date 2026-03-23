package com.ocr.nospring;

import java.io.File;

/**
 * 簡單配置類（無 JSON 依賴）
 * 
 * 用於 Native Image 編譯
 */
public class Config {
    
    private String fontPath;
    
    public Config() {
        this.fontPath = getDefaultFontPath();
    }
    
    public String getFontPath() {
        return fontPath;
    }
    
    public void setFontPath(String fontPath) {
        this.fontPath = fontPath;
    }
    
    private String getDefaultFontPath() {
        String os = System.getProperty("os.name", "").toLowerCase();
        
        if (os.contains("win")) {
            String[] windowsFonts = {
                "C:/Windows/Fonts/msjh.ttc",
                "C:/Windows/Fonts/msyh.ttc",
                "C:/Windows/Fonts/simhei.ttf",
                "C:/Windows/Fonts/simsun.ttc"
            };
            
            for (String font : windowsFonts) {
                if (new File(font).exists()) {
                    return font;
                }
            }
        } else if (os.contains("mac")) {
            String[] macFonts = {
                "/System/Library/Fonts/PingFang.ttc",
                "/Library/Fonts/Arial Unicode.ttf"
            };
            
            for (String font : macFonts) {
                if (new File(font).exists()) {
                    return font;
                }
            }
        } else {
            String[] linuxFonts = {
                "/usr/share/fonts/truetype/wqy/wqy-zenhei.ttc",
                "/usr/share/fonts/opentype/noto/NotoSansCJK-Regular.ttc"
            };
            
            for (String font : linuxFonts) {
                if (new File(font).exists()) {
                    return font;
                }
            }
        }
        
        return null;
    }
}
