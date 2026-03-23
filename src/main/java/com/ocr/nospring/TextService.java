package com.ocr.nospring;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * 文本服務 - 無 Spring Boot
 */
public class TextService {
    
    /**
     * 生成多頁 TXT
     */
    public void generateMultiPageTxt(List<List<OcrService.TextBlock>> allTextBlocks, File outputFile) throws Exception {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int pageIndex = 0; pageIndex < allTextBlocks.size(); pageIndex++) {
                List<OcrService.TextBlock> textBlocks = allTextBlocks.get(pageIndex);
                
                // 添加頁面分隔符
                if (pageIndex > 0) {
                    writer.newLine();
                    writer.write("========================================");
                    writer.newLine();
                    writer.write("Page " + (pageIndex + 1));
                    writer.newLine();
                    writer.write("========================================");
                    writer.newLine();
                    writer.newLine();
                } else {
                    writer.write("Page " + (pageIndex + 1));
                    writer.newLine();
                    writer.newLine();
                }
                
                // 寫入文字
                for (OcrService.TextBlock block : textBlocks) {
                    String text = block.text;
                    if (text != null && !text.trim().isEmpty()) {
                        writer.write(text);
                        writer.newLine();
                    }
                }
            }
        }
    }
    
    public void generateTxt(List<OcrService.TextBlock> textBlocks, File outputFile) throws Exception {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (OcrService.TextBlock block : textBlocks) {
                String text = block.text;
                if (text != null && !text.trim().isEmpty()) {
                    writer.write(text);
                    writer.newLine();
                }
            }
        }
    }
}
