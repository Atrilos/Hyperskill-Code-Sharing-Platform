<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.5.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.5.1/build/highlight.min.js"></script>
    <script>hljs.highlightAll();
        hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list snippets as snippet>
<span id="load_date">${snippet.date}</span>
<pre id="code_snippet"><code>${snippet.code}</code>

</pre>
</#list>
</body>
</html>