<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ruserba</title>
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/favicon.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginpopup.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/ajax_generic.js"></script>
	
</head>
<body>
	<header>
        <nav><div class="container">
            <span id="login"><a class="menu_cell hyperlink" href="#loginbox">Login</a></span>
            <form id="wbd_search" class="menu_cell" onSubmit="return testA()">
                <input type="text" name="search_input" placeholder="Cari disini">
                <input type="submit" name="submit" value="Cari">
            </form>
            <a id="keranjang_belanja" class="menu_cell hyperlink" href="keranjang/">Keranjang Belanja <span id="total_keranjang"></span></a>
            <a href="admin" class="menu_cell hyperlink" id="admin">Admin </a>
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
            <a href="${pageContext.request.contextPath}/kategori?id=1&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/kategori?id=2&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/kategori?id=3&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/kategori?id=4&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
            <a href="${pageContext.request.contextPath}/kategori?id=5&page=1"><img src="${pageContext.request.contextPath}/img_style/klik.gif" alt="Klik"/></a>
        </div>
    </header>
    <article class="container">
		<h1>Edit Barang</h1>
		<form id="pendaftaran" method="post" action="edit_barang" enctype="multipart/form-data">
		    <h2>Umum</h2>
		    <input hidden type="text" name="id" size="40" value="${id}" >
		    <p><label>Nama</label>: <input type="text" name="nama" size="40" value="${nama}"></p>
		    <p><label>Deskripsi</label>: <input type="text" name="deskripsi" size="40" value="${deskripsi}"></p>
		    <p><label>Harga</label>: <input type="text" name="harga" size="40" value="${harga}"></p>
		    <p><label>Stock</label>: <input type="text" name="stock " size="40" value="${stock}"></p>
		    <p><label>Kategori</label>: 
		    <select name="kategori">
			  <option value=1>Makanan & Minuman</option>
			  <option value=2>Alat Tulis</option>
			  <option value=3>Elektronik</option>
			  <option value=4>Perabotan</option>
			  <option value=5>Alat Musik</option>
			</select> </p>
		    <h2>Media</h2>
		    <p><label>Gambar</label>: <input type="file" name="nama_lengkap" size="40"></p>
		    <p><label>&nbsp;</label>&nbsp; <input type="submit" name="tambah_submit" value="Simpan"></p>
		</form>
		<form onsubmit="return hapusBarang(this)">
		 	<input hidden type="text" name="id" size="40" value="${id}" >
			<p><label>&nbsp;</label>&nbsp; <input type="submit" name="delete" value="Hapus" ></p>
		</form>
	</article>
	<footer class="container">
        <div>Ruserba &copy; 2013</div>
    </footer>
    <script src="${pageContext.request.contextPath}/use.js"></script>
	<script src="${pageContext.request.contextPath}/validator.js"></script>
</body>
</html>