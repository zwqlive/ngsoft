<#macro layout root="" title="" js="">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="GENERATOR" content="MSHTML 11.00.9600.17496">
    <title>###</title>
    <link rel="stylesheet" href="${root!}/assets/style/default.css" type="text/css" />
    <script src="${root!}/assets/js/jquery/jquery-1.9.1.min.js" language="JavaScript" ></script>
    <script src="${root!}/assets/js/bootstrap/js/bootstrap.min.js" language="JavaScript"></script>
    <script src="${root!}/assets/js/avalon/avalon.js" language="JavaScript"></script>
</head>
<body>
<#nested>
    <#if js!=''>
        <script src="${root!}/${js}"></script>
    </#if>
</body>
</html>
 </#macro>