package com.example.backend.utils;

import org.jsoup.Jsoup;
//import org.jsoup.safety.Safelist;

public class MarkdownUtils {

    // 解析 Markdown 为 HTML
    public static String parseMarkdown(String markdown) {
        // 使用开源库（如 flexmark-java）解析 Markdown
        return "<html>" + markdown + "</html>"; // 示例
    }

//    // 清理 HTML 防止 XSS
//    public static String sanitizeHtml(String html) {
//        // 使用 Jsoup 的 Safelist 来清理 HTML
//        return Jsoup.clean(html, Safelist.basic());
//    }

    // 生成内容摘要
    public static String generateSummary(String content) {
        return content.length() > 100 ? content.substring(0, 100) + "..." : content;
    }
}