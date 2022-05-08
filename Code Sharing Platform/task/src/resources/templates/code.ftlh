<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.5.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.5.1/build/highlight.min.js"></script>
    <script>hljs.highlightAll();
    hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#if snippet.viewRestricted><span id="views_restriction">${snippet.views} more views allowed</span></#if>
<#if snippet.timeRestricted><span id="time_restriction">The code will be available for ${snippet.time} seconds</span></#if>
<span id="load_date">${snippet.date}</span>
<pre id="code_snippet"><code>${snippet.code}</code>
</pre>
</body>
</html>