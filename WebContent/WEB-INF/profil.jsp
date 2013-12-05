<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ruserba</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginpopup.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/ajax_generic.js"></script>
	
</head>
<body onLoad="getMoreIdentity(this)">
	<header>
        <nav><div class="container">
            <span id="login"><a class="menu_cell hyperlink" href="#loginbox">Login</a></span>
            <form id="wbd_search" class="menu_cell" onSubmit="return testA()">
                <input type="text" name="search_input" placeholder="Cari disini">
                <input type="submit" name="submit" value="Cari">
            </form>
            </div>
        </nav>
        <div class="container">
            <a href="${pageContext.request.contextPath}"><img id="logo" src="${pageContext.request.contextPath}/logo.png" height="72" alt="Ruko Serba Ada"></a>
        </div>
        <div id="background_cat">
            <img class="background" id='kat1' src="${pageContext.request.contextPath}/img_style/kat1.gif" alt="Kategori 1"/>
            <img class="background" id='kat2' src="${pageContext.request.contextPath}/img_style/kat2.gif" alt="Kategori 1"/>
            <img class="background" id='kat3' src="${pageContext.request.contextPath}/img_style/kat3.gif" alt="Kategori 1"/>
            <img class="background" id='kat4' src="${pageContext.request.contextPath}/img_style/kat4.gif" alt="Kategori 1"/>
            <img class="background" id='kat5' src="${pageContext.request.contextPath}/img_style/kat5.gif" alt="Kategori 1"/>
		</div>
        <div class="kategori_group">
            <a href="${pageContext.request.contextPath}/ketegori/?id=1&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/ketegori/?id=2&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/ketegori/?id=3&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/ketegori/?id=4&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/ketegori/?id=5&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
        </div>
    </header>
    <article class="container">
		<h1>Profil Pelanggan</h1>
			<div id="identitas">
			</div>
	</article>
	<footer class="container">
        <div>Ruserba &copy; 2013</div>
    </footer>
    <script src="${pageContext.request.contextPath}/use.js"></script>
	<script src="${pageContext.request.contextPath}/validator.js"></script>
</body>
</html>