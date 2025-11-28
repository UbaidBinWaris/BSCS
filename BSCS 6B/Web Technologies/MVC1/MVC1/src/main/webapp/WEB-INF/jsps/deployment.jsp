<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.util.stream.Collectors" %>
<%
    // Read the markdown file from webapp directory
    String mdContent = "";
    try {
        // Load server_deployment.md from webapp directory
        InputStream is = application.getResourceAsStream("/server_deployment.md");
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            mdContent = reader.lines().collect(Collectors.joining("\n"));
            reader.close();
            // Escape for JavaScript
            mdContent = mdContent.replace("\\", "\\\\")
                                 .replace("`", "\\`")
                                 .replace("$", "\\$");
        } else {
            mdContent = "Error: server_deployment.md file not found in webapp directory. Please place your deployment guide at /server_deployment.md";
        }
    } catch (Exception e) {
        mdContent = "Error loading markdown file: " + e.getMessage();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deployment Guide - MVC1 Web Application</title>
<!-- Marked.js - Markdown Parser -->
<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<!-- GitHub Markdown CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/5.5.0/github-markdown.min.css">
<!-- Highlight.js for code syntax highlighting -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/github-dark.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body {
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif;
        background-color: #0d1117;
        color: #c9d1d9;
    }

    .navbar {
        background-color: #161b22;
        padding: 15px 0;
        box-shadow: 0 2px 5px rgba(0,0,0,0.3);
        border-bottom: 1px solid #30363d;
    }

    .navbar .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .navbar h1 {
        color: #ff6600;
        font-size: 24px;
        font-weight: 600;
    }

    .navbar .nav-links a {
        color: #c9d1d9;
        text-decoration: none;
        padding: 8px 16px;
        background-color: #238636;
        border-radius: 6px;
        font-weight: 500;
        transition: background-color 0.2s;
        margin-left: 10px;
    }

    .navbar .nav-links a:hover {
        background-color: #2ea043;
    }

    .container {
        max-width: 1200px;
        margin: 30px auto;
        padding: 0 20px;
    }

    .markdown-body {
        box-sizing: border-box;
        min-width: 200px;
        max-width: 980px;
        margin: 0 auto;
        padding: 45px;
        background-color: #0d1117;
        border: 1px solid #30363d;
        border-radius: 6px;
    }

    @media (max-width: 767px) {
        .markdown-body {
            padding: 15px;
        }
    }

    /* GitHub Dark Theme Override */
    .markdown-body {
        color: #c9d1d9;
        background-color: #0d1117;
    }

    .markdown-body h1,
    .markdown-body h2,
    .markdown-body h3,
    .markdown-body h4,
    .markdown-body h5,
    .markdown-body h6 {
        color: #ff6600;
        border-bottom-color: #21262d;
    }

    .markdown-body code {
        background-color: rgba(110,118,129,0.4);
        color: #c9d1d9;
    }

    .markdown-body pre {
        background-color: #161b22;
        border: 1px solid #30363d;
    }

    .markdown-body pre code {
        background-color: transparent;
    }

    .markdown-body a {
        color: #ff6600;
    }

    .markdown-body a:hover {
        color: #ff8833;
    }

    .markdown-body blockquote {
        color: #8b949e;
        border-left-color: #ff6600;
    }

    .markdown-body table tr {
        background-color: #0d1117;
        border-top-color: #21262d;
    }

    .markdown-body table tr:nth-child(2n) {
        background-color: #161b22;
    }

    .markdown-body table th,
    .markdown-body table td {
        border-color: #30363d;
    }

    .markdown-body hr {
        background-color: #21262d;
    }

    .loading {
        text-align: center;
        padding: 50px;
        color: #8b949e;
    }

    .footer {
        text-align: center;
        padding: 30px;
        color: #8b949e;
        margin-top: 50px;
    }
</style>
</head>
<body>
    <div class="navbar">
        <div class="container">
            <h1>🚀 Deployment Guide</h1>
            <div class="nav-links">
                <a href="<%= request.getContextPath() %>/public/documentation">📚 Documentation</a>
                <a href="<%= request.getContextPath() %>/">← Main Menu</a>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="markdown-body" id="content">
            <div class="loading">Loading deployment guide...</div>
        </div>
    </div>

    <div class="footer">
        &copy; 2025 MVC1 Web Application | Created by Ubaid Bin Waris
    </div>

    <script>
        // Markdown content from JSP
        const markdownText = `<%= mdContent %>`;

        // Configure marked options
        marked.setOptions({
            highlight: function(code, lang) {
                if (lang && hljs.getLanguage(lang)) {
                    try {
                        return hljs.highlight(code, { language: lang }).value;
                    } catch (err) {
                        console.error('Highlight error:', err);
                    }
                }
                return hljs.highlightAuto(code).value;
            },
            breaks: true,
            gfm: true
        });

        // Parse and render markdown
        try {
            const htmlContent = marked.parse(markdownText);
            document.getElementById('content').innerHTML = htmlContent;

            // Apply syntax highlighting to any code blocks that weren't highlighted
            document.querySelectorAll('pre code').forEach((block) => {
                hljs.highlightElement(block);
            });
        } catch (error) {
            document.getElementById('content').innerHTML =
                '<div class="loading">Error rendering markdown: ' + error.message + '</div>';
        }
    </script>
</body>
</html>
