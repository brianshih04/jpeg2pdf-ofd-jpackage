package com.ocr.nospring;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.RenderingMode;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

/**
 * PDF 服務 - 無 Spring Boot
 */
public class PdfService {
    
    private final Config config;
    
    public PdfService(Config config) {
        this.config = config;
    }
    
    public void generatePdf(BufferedImage image, List<OcrService.TextBlock> textBlocks, File outputFile) throws Exception {
        
        try (PDDocument document = new PDDocument()) {
            // 載入字體
            PDFont font = loadFont(document);
            
            // 建立頁面
            float width = image.getWidth();
            float height = image.getHeight();
            PDPage page = new PDPage(new PDRectangle(width, height));
            document.addPage(page);
            
            // 轉換圖片
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "PNG", baos);
            byte[] imageBytes = baos.toByteArray();
            
            PDImageXObject pdImage = PDImageXObject.createFromByteArray(
                document, imageBytes, "image"
            );
            
            // 繪製內容
            try (PDPageContentStream contentStream = new PDPageContentStream(
                document, page, 
                PDPageContentStream.AppendMode.APPEND, 
                true, 
                true
            )) {
                // 1. 繪製圖片
                contentStream.drawImage(pdImage, 0, 0, width, height);
                
                // 2. 繪製透明文字層
                contentStream.setRenderingMode(org.apache.pdfbox.pdmodel.graphics.state.RenderingMode.NEITHER);
                contentStream.setNonStrokingColor(255, 255, 255); // 白色
                
                for (OcrService.TextBlock block : textBlocks) {
                    try {
                        // Y 軸轉換（PDF 使用 Y-up）
                        float pdfY = (float) (height - block.y - block.height);
                        float fontSize = block.fontSize;
                        
                        contentStream.beginText();
                        contentStream.setFont(font, fontSize);
                        contentStream.newLineAtOffset((float) block.x, pdfY);
                        contentStream.showText(block.text);
                        contentStream.endText();
                        
                    } catch (Exception e) {
                        System.err.println("    Error drawing text: " + e.getMessage());
                    }
                }
            }
            
            // 保存
            document.save(outputFile);
        }
    }
    
    /**
     * 生成多頁 PDF
     */
    public void generateMultiPagePdf(List<BufferedImage> images, List<List<OcrService.TextBlock>> allTextBlocks, File outputFile) throws Exception {
        
        if (images.size() != allTextBlocks.size()) {
            throw new IllegalArgumentException("Images and text blocks count mismatch");
        }
        
        try (PDDocument document = new PDDocument()) {
            // 載入字體
            PDFont font = loadFont(document);
            
            // 處理每一頁
            for (int pageIndex = 0; pageIndex < images.size(); pageIndex++) {
                BufferedImage image = images.get(pageIndex);
                List<OcrService.TextBlock> textBlocks = allTextBlocks.get(pageIndex);
                
                // 建立頁面
                float width = image.getWidth();
                float height = image.getHeight();
                PDPage page = new PDPage(new PDRectangle(width, height));
                document.addPage(page);
                
                // 轉換圖片
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "PNG", baos);
                byte[] imageBytes = baos.toByteArray();
                
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(
                    document, imageBytes, "image"
                );
                
                // 繪製內容
                try (PDPageContentStream contentStream = new PDPageContentStream(
                    document, page, 
                    PDPageContentStream.AppendMode.APPEND, 
                    true, 
                    true
                )) {
                    // 1. 繪製圖片
                    contentStream.drawImage(pdImage, 0, 0, width, height);
                    
                    // 2. 繪製透明文字層
                    contentStream.setRenderingMode(org.apache.pdfbox.pdmodel.graphics.state.RenderingMode.NEITHER);
                    contentStream.setNonStrokingColor(255, 255, 255); // 白色
                    
                    for (OcrService.TextBlock block : textBlocks) {
                        try {
                            // Y 軸轉換（PDF 使用 Y-up）
                            float pdfY = (float) (height - block.y - block.height);
                            float fontSize = block.fontSize;
                            
                            contentStream.beginText();
                            contentStream.setFont(font, fontSize);
                            contentStream.newLineAtOffset((float) block.x, pdfY);
                            contentStream.showText(block.text);
                            contentStream.endText();
                            
                        } catch (Exception e) {
                            System.err.println("    Page " + (pageIndex + 1) + " - Error drawing text: " + e.getMessage());
                        }
                    }
                }
            }
            
            // 保存
            document.save(outputFile);
        }
    }
    
    private PDFont loadFont(PDDocument document) throws Exception {
        String fontPath = config.getFontPath();
        
        if (fontPath != null && new File(fontPath).exists()) {
            try {
                return PDType0Font.load(document, new File(fontPath));
            } catch (Exception e) {
                System.err.println("    Warning: Cannot load font from " + fontPath);
            }
        }
        
        // 使用默認字體
        return org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA;
    }
}
