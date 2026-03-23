package com.ocr.nospring;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

/**
 * 純 Java SE 主程序 - 無 Spring Boot
 * 
 * 支持兩種輸出模式：
 * 1. perPage (默認) - 每個圖片生成一個 PDF/OFD
 * 2. multiPage - 所有圖片合併成一個多頁 PDF/OFD
 */
public class Main {
    
    private static final String VERSION = "3.0.0 (No Spring Boot)";
    
    public static void main(String[] args) {
        try {
            System.setProperty("java.awt.headless", "true");
            
            if (args.length == 0) {
                printUsage();
                System.exit(0);
            }
            
            String configFile = args[0];
            
            if (configFile.equals("--help") || configFile.equals("-h")) {
                printUsage();
                System.exit(0);
            }
            
            if (configFile.equals("--version") || configFile.equals("-v")) {
                System.out.println("JPEG2PDF-OFD v" + VERSION);
                System.exit(0);
            }
            
            // 創建配置
            Config config = new Config();
            
            // 加載配置文件
            File file = new File(configFile);
            if (!file.exists()) {
                System.err.println("ERROR: Config file not found: " + configFile);
                System.exit(1);
            }
            
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> configMap = mapper.readValue(file, Map.class);
            
            System.out.println("========================================");
            System.out.println("  JPEG2PDF-OFD v" + VERSION);
            System.out.println("========================================");
            System.out.println();
            System.out.println("Config: " + configFile);
            System.out.println("OK: Config loaded");
            
            // 創建 Service 實例
            OcrService ocrService = new OcrService();
            PdfService pdfService = new PdfService(config);
            TextService textService = new TextService();
            OfdService ofdService = new OfdService();
            
            // 獲取輸入配置
            Map<String, Object> inputConfig = (Map<String, Object>) configMap.get("input");
            Map<String, Object> outputConfig = (Map<String, Object>) configMap.get("output");
            Map<String, Object> ocrConfig = (Map<String, Object>) configMap.get("ocr");
            
            // 獲取輸入文件
            List<File> inputFiles = getInputFiles(inputConfig);
            System.out.println("Found " + inputFiles.size() + " file(s)");
            
            if (inputFiles.isEmpty()) {
                System.err.println("ERROR: No files found");
                return;
            }
            
            // 獲取輸出配置
            String outputFolder = getOutputFolder(outputConfig);
            String format = getOutputFormat(outputConfig);
            String language = getOcrLanguage(ocrConfig);
            boolean multiPage = getMultiPageMode(outputConfig);
            
            // 創建輸出目錄
            File outputDir = new File(outputFolder);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            System.out.println("Output: " + outputFolder);
            System.out.println("Mode: " + (multiPage ? "Multi-Page" : "Per-Page"));
            System.out.println();
            
            if (multiPage) {
                // 多頁模式：所有圖片合併成一個 PDF/OFD
                processMultiPage(inputFiles, outputDir, format, language, 
                               ocrService, pdfService, textService, ofdService);
            } else {
                // 單頁模式：每個圖片一個 PDF/OFD
                processPerPage(inputFiles, outputDir, format, language,
                             ocrService, pdfService, textService, ofdService);
            }
            
            System.out.println("========================================");
            System.out.println("  Done!");
            System.out.println("========================================");
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * 多頁模式：所有圖片合併成一個 PDF/OFD
     */
    private static void processMultiPage(List<File> inputFiles, File outputDir, 
                                        String format, String language,
                                        OcrService ocrService, PdfService pdfService,
                                        TextService textService, OfdService ofdService) {
        try {
            System.out.println("Processing " + inputFiles.size() + " images into multi-page document...");
            System.out.println();
            
            // 存儲所有頁面的數據
            List<BufferedImage> images = new ArrayList<>();
            List<List<OcrService.TextBlock>> allTextBlocks = new ArrayList<>();
            
            // 處理每個圖片
            for (int i = 0; i < inputFiles.size(); i++) {
                File inputFile = inputFiles.get(i);
                System.out.println("[" + (i+1) + "/" + inputFiles.size() + "] " + inputFile.getName());
                
                try {
                    // 加載圖片
                    BufferedImage image = ImageIO.read(inputFile);
                    if (image == null) {
                        System.err.println("  ERROR: Cannot read image");
                        continue;
                    }
                    
                    System.out.println("  Image size: " + image.getWidth() + "x" + image.getHeight());
                    
                    // OCR 識別
                    System.out.println("  Running OCR...");
                    List<OcrService.TextBlock> textBlocks = ocrService.recognize(image, language);
                    System.out.println("  OK: OCR completed (" + textBlocks.size() + " blocks)");
                    
                    // 保存數據
                    images.add(image);
                    allTextBlocks.add(textBlocks);
                    
                } catch (Exception e) {
                    System.err.println("  ERROR: " + e.getMessage());
                }
            }
            
            if (images.isEmpty()) {
                System.err.println("ERROR: No valid images processed");
                return;
            }
            
            System.out.println();
            System.out.println("Generating multi-page output...");
            
            // 生成輸出文件名
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String outputFilename = "multipage_" + timestamp;
            
            // 生成多頁 PDF
            if (format.contains("pdf") || format.contains("all")) {
                File pdfFile = new File(outputDir, outputFilename + ".pdf");
                pdfService.generateMultiPagePdf(images, allTextBlocks, pdfFile);
                System.out.println("  OK: Multi-page PDF -> " + pdfFile.getName());
            }
            
            // 生成 TXT（所有頁面的文字）
            if (format.contains("txt") || format.contains("all")) {
                File txtFile = new File(outputDir, outputFilename + ".txt");
                textService.generateMultiPageTxt(allTextBlocks, txtFile);
                System.out.println("  OK: TXT -> " + txtFile.getName());
            }
            
            // 生成多頁 OFD
            if (format.contains("ofd") || format.contains("all")) {
                File ofdFile = new File(outputDir, outputFilename + ".ofd");
                ofdService.generateMultiPageOfd(images, allTextBlocks, ofdFile);
                System.out.println("  OK: Multi-page OFD -> " + ofdFile.getName());
            }
            
            System.out.println();
            System.out.println("Total pages: " + images.size());
            
        } catch (Exception e) {
            System.err.println("ERROR in multi-page processing: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 單頁模式：每個圖片生成一個 PDF/OFD
     */
    private static void processPerPage(List<File> inputFiles, File outputDir,
                                      String format, String language,
                                      OcrService ocrService, PdfService pdfService,
                                      TextService textService, OfdService ofdService) {
        int processed = 0;
        int failed = 0;
        
        for (File inputFile : inputFiles) {
            processed++;
            System.out.println("[" + processed + "/" + inputFiles.size() + "] Processing: " + inputFile.getName());
            
            try {
                // 加載圖片
                BufferedImage image = ImageIO.read(inputFile);
                if (image == null) {
                    System.err.println("  ERROR: Cannot read image");
                    failed++;
                    continue;
                }
                
                System.out.println("  Image size: " + image.getWidth() + "x" + image.getHeight());
                
                // OCR 識別
                System.out.println("  Running OCR...");
                List<OcrService.TextBlock> textBlocks = ocrService.recognize(image, language);
                System.out.println("  OK: OCR completed (" + textBlocks.size() + " blocks)");
                
                // 生成輸出
                String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String baseName = getBaseName(inputFile.getName());
                String outputFilename = baseName + "_" + timestamp;
                
                // 導出文件
                if (format.contains("pdf") || format.contains("all")) {
                    File pdfFile = new File(outputDir, outputFilename + ".pdf");
                    pdfService.generatePdf(image, textBlocks, pdfFile);
                    System.out.println("  OK: PDF -> " + pdfFile.getName());
                }
                
                if (format.contains("txt") || format.contains("all")) {
                    File txtFile = new File(outputDir, outputFilename + ".txt");
                    textService.generateTxt(textBlocks, txtFile);
                    System.out.println("  OK: TXT -> " + txtFile.getName());
                }
                
                if (format.contains("ofd") || format.contains("all")) {
                    File ofdFile = new File(outputDir, outputFilename + ".ofd");
                    ofdService.generateOfd(image, textBlocks, ofdFile);
                    System.out.println("  OK: OFD -> " + ofdFile.getName());
                }
                
                System.out.println();
                
            } catch (Exception e) {
                System.err.println("  ERROR: " + e.getMessage());
                e.printStackTrace();
                failed++;
            }
        }
        
        System.out.println();
        System.out.println("========================================");
        System.out.println("  Summary");
        System.out.println("========================================");
        System.out.println("Processed: " + processed);
        System.out.println("Failed:    " + failed);
        System.out.println();
        
        if (failed == 0) {
            System.out.println("SUCCESS: All files processed");
        } else {
            System.out.println("WARNING: Some files failed");
        }
    }
    
    private static List<File> getInputFiles(Map<String, Object> inputConfig) {
        List<File> files = new ArrayList<>();
        
        if (inputConfig == null) return files;
        
        if (inputConfig.containsKey("file")) {
            String filePath = (String) inputConfig.get("file");
            File file = new File(filePath);
            if (file.exists()) files.add(file);
            return files;
        }
        
        if (inputConfig.containsKey("folder")) {
            String folderPath = (String) inputConfig.get("folder");
            String pattern = inputConfig.containsKey("pattern") 
                ? (String) inputConfig.get("pattern") 
                : "*.jpg";
            
            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                findFiles(folder, pattern, files);
            }
        }
        
        return files;
    }
    
    private static void findFiles(File folder, String pattern, List<File> files) {
        File[] fileList = folder.listFiles();
        if (fileList == null) return;
        
        for (File file : fileList) {
            if (file.isDirectory()) {
                findFiles(file, pattern, files);
            } else if (file.isFile() && matchesPattern(file.getName(), pattern)) {
                files.add(file);
            }
        }
    }
    
    private static boolean matchesPattern(String filename, String pattern) {
        if (pattern.equals("*") || pattern.equals("*.*")) return true;
        if (pattern.startsWith("*.")) {
            String ext = pattern.substring(1).toLowerCase();
            return filename.toLowerCase().endsWith(ext);
        }
        return filename.equals(pattern);
    }
    
    private static String getOutputFolder(Map<String, Object> outputConfig) {
        if (outputConfig != null && outputConfig.containsKey("folder")) {
            return (String) outputConfig.get("folder");
        }
        return ".";
    }
    
    private static String getOutputFormat(Map<String, Object> outputConfig) {
        if (outputConfig != null && outputConfig.containsKey("format")) {
            Object format = outputConfig.get("format");
            if (format instanceof String) return (String) format;
            if (format instanceof List) return String.join(",", (List<String>) format);
        }
        return "pdf";
    }
    
    private static String getOcrLanguage(Map<String, Object> ocrConfig) {
        if (ocrConfig != null && ocrConfig.containsKey("language")) {
            return (String) ocrConfig.get("language");
        }
        return "chinese_cht";
    }
    
    /**
     * 獲取多頁模式配置
     * 默認為 false（單頁模式）
     */
    private static boolean getMultiPageMode(Map<String, Object> outputConfig) {
        if (outputConfig != null && outputConfig.containsKey("multiPage")) {
            Object multiPage = outputConfig.get("multiPage");
            if (multiPage instanceof Boolean) {
                return (Boolean) multiPage;
            }
            if (multiPage instanceof String) {
                return Boolean.parseBoolean((String) multiPage);
            }
        }
        return false; // 默認為單頁模式
    }
    
    private static String getBaseName(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return dotIndex > 0 ? filename.substring(0, dotIndex) : filename;
    }
    
    private static void printUsage() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("  JPEG2PDF-OFD v" + VERSION);
        System.out.println("  Pure Java SE - No Spring Boot");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Usage:");
        System.out.println("  java -jar jpeg2pdf-ofd-nospring.jar config.json");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  --help     Show help");
        System.out.println("  --version  Show version");
        System.out.println();
        System.out.println("Config example (per-page mode):");
        System.out.println("  {");
        System.out.println("    \"input\": {");
        System.out.println("      \"folder\": \"C:/OCR/Watch\",");
        System.out.println("      \"pattern\": \"*.jpg\"");
        System.out.println("    },");
        System.out.println("    \"output\": {");
        System.out.println("      \"folder\": \"C:/OCR/Output\",");
        System.out.println("      \"format\": \"pdf\",");
        System.out.println("      \"multiPage\": false");
        System.out.println("    },");
        System.out.println("    \"ocr\": {");
        System.out.println("      \"language\": \"chinese_cht\"");
        System.out.println("    }");
        System.out.println("  }");
        System.out.println();
        System.out.println("Config example (multi-page mode):");
        System.out.println("  {");
        System.out.println("    \"input\": {");
        System.out.println("      \"folder\": \"C:/OCR/Watch\",");
        System.out.println("      \"pattern\": \"*.jpg\"");
        System.out.println("    },");
        System.out.println("    \"output\": {");
        System.out.println("      \"folder\": \"C:/OCR/Output\",");
        System.out.println("      \"format\": \"all\",");
        System.out.println("      \"multiPage\": true");
        System.out.println("    },");
        System.out.println("    \"ocr\": {");
        System.out.println("      \"language\": \"chinese_cht\"");
        System.out.println("    }");
        System.out.println("  }");
        System.out.println();
        System.out.println("Output modes:");
        System.out.println("  multiPage: false - Each image generates one PDF/OFD (default)");
        System.out.println("  multiPage: true  - All images merged into one multi-page PDF/OFD");
        System.out.println();
    }
}
