/* ------------------------------------------------------------------------- */
/*                           VALIDATOR FUNCTION                              */
/* ------------------------------------------------------------------------- */
function validateUsername(fld) {
    var error="";
    var legalChars = /^[a-z]([a-z\.\_])*[a-z]+$/;

    if(fld.value.length < 5) {
        error = "Jumlah karakter kurang dari 5";
    } else if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan ada yang tidak diizinkan.\n";
    }
    return error; 
}
function validatePassword(fld) {
    var error="";
    var legalChars = /^[a-zA-Z\.\-\_]+$/;

    if(fld.value.length < 8) {
        error = "Jumlah karakter kurang dari 8";
    } else if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan ada yang tidak diizinkan.\n";
    }
    return error; 
}
function validateFullname(fld) {
    var error="";
    var legalChars = /^[A-Z][a-z]+([\ ][A-Za-z]+)+$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak valid.\n";
    }
    return error; 
}
function validateEmail(fld) {
    var error="";
    var legalChars = /^[a-z][0-9a-z]*([._][0-9a-z])*[@][a-z0-9]+([.][a-z]{2,})+$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak valid sebagai email.\n";
    }
    return error; 
}
function validateAlamat(fld) {
    var error="";
    var legalChars = /^[0-9a-zA-Z\.\ \/]+$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak valid.\n";
    }
    return error; 
}
function validateProvinsi(fld) {
    var error="";
    var legalChars = /^[A-Z][a-z]+([\ ][A-Za-z]+)*$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak valid.\n";
    }
    return error; 
}
function validateKodePOS(fld) {
    var error="";
    var legalChars = /^[0-9]{5}$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak valid.\n";
    }
    return error; 
}
function validateNoHP(fld) {
    var error="";
    var legalChars = /^[0-9]{11,13}$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak valid.\n";
    }
    return error; 
}
function validateCreditCard(fld) {
    var error = "";
    var legalChars = /^[0-9]{4}([\-][0-9]{4}){2,3}([\-][0-9]{1,4})$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak memenuhi syarat kartu kredit.\n";
    }
    return error;
}
function validateNumber(fld) {
    var error = "";
    var legalChars = /^([1-9]|[1-9][0-9]+)$/;

    if (!legalChars.test(fld.value)) {
        error = "Karakter yang dimasukkan tidak memenuhi syarat kartu kredit.\n";
    }
    return error;
}

/* ------------------------------------------------------------------------- */
/*                        IMPLEMENTATION PENDAFTARAN                         */
/* ------------------------------------------------------------------------- */
var pendaftaranAriValid = [false,false,false,false,false];
function pendaftaranSubmit(number_field, set) {
    pendaftaranAriValid[number_field] = set;
    var i;
    for(i = 0; i < pendaftaranAriValid.length; i++) {
        if(!pendaftaranAriValid[i]) break;
    }
    var child = document.getElementById('pendaftaran');
    var submit = child.getElementsByTagName("input");
    if(i < pendaftaranAriValid.length) {
        submit[submit.length - 1].disabled = true;
    } else {
        submit[submit.length - 1].disabled = false;
    }
}
function pendaftaranUsername(fld) {
    var parent = document.getElementById('pendaftaran');
    var child = parent.getElementsByTagName("input");
    if(child[1].value != "" && child[1].value == fld.value) {
        fld.style.background = "Red";
        return;
    }
    if(child[4].value != "" && child[4].value == fld.value) {
        fld.style.background = "Red";
        return;
    }
    if(validateUsername(fld) == "") {
        var bucket = {"todo":"pendaftaran", "data":[
            {"check_field":"username", "value":fld.value}
        ]};
        sendJSONType(bucket, pendaftaranUsernameAfter);
        fld.style.background = "Gray";
    } else {
        fld.style.background = "Red";
        pendaftaranSubmit(0, false);
    }
}
function pendaftaranUsernameAfter(data) {
    var child = document.getElementById('pendaftaran');
    var nama_pengguna = child.getElementsByTagName("input");

    var result = JSON.parse(data);
    if(result.status == "valid") {
        nama_pengguna[0].style.background = "White";
        pendaftaranSubmit(0, true);
    } else {
        nama_pengguna[0].style.background = "Yellow";
        pendaftaranSubmit(0, false);
    }
}
function pendaftaranPassword(fld) {
    var parent = document.getElementById('pendaftaran');
    var child = parent.getElementsByTagName("input");
    if(child[0].value != "" && child[0].value == fld.value) {
        fld.style.background = "Red";
        return;
    }
    if(child[4].value != "" && child[4].value == fld.value) {
        fld.style.background = "Red";
        return;
    }
    if(validatePassword(fld) == "") {
        fld.style.background = "White";
        pendaftaranSubmit(1, true);
    } else {
        fld.style.background = "Red";
        pendaftaranSubmit(1, false);
    }
}
function pendaftaranConfirmPassword(fld) {
    var parent = document.getElementById('pendaftaran');
    var child = parent.getElementsByTagName("input");
    if(child[1].value == "" || child[1].value != fld.value) {
        fld.style.background = "Red";
        pendaftaranSubmit(2, false);
    } else {
        fld.style.background = "White";
        pendaftaranSubmit(2, true);
    }
}
function pendaftaranFullName(fld) {
    if(validateFullname(fld) == "") {
        fld.style.background = "White";
        pendaftaranSubmit(3, true);
    } else {
        fld.style.background = "Red";
        pendaftaranSubmit(3, false);
    }
}
function pendaftaranEmail(fld) {
    var parent = document.getElementById('pendaftaran');
    var child = parent.getElementsByTagName("input");
    if(child[0].value != "" && child[0].value == fld.value) {
        fld.style.background = "Red";
        return;
    }
    if(child[1].value != "" && child[1].value == fld.value) {
        fld.style.background = "Red";
        return;
    }
    if(validateEmail(fld) == "") {
        var bucket = {"todo":"pendaftaran", "data":[
            {"check_field":"email", "value":fld.value}
        ]};
        sendJSONType(bucket, pendaftaranEmailAfter);
        fld.style.background = "Gray";
    } else {
        fld.style.background = "Red";
        pendaftaranSubmit(4, false);
    }
}
function pendaftaranEmailAfter(data) {
    var child = document.getElementById('pendaftaran');
    var nama_pengguna = child.getElementsByTagName("input");

    var result = JSON.parse(data);
    if(result.status == "valid") {
        nama_pengguna[4].style.background = "White";
        pendaftaranSubmit(4, true);
    } else {
        nama_pengguna[4].style.background = "Yellow";
        pendaftaranSubmit(4, false);
    }
}

/* ------------------------------------------------------------------------- */
/*                      IMPLEMENTATION REG CREDIT CARD                       */
/* ------------------------------------------------------------------------- */
var regCreditCard = [false, false];
function regCCSubmit(number_field, set) {
    regCreditCard[number_field] = set;
    var i;
    for(i = 0; i < regCreditCard.length; i++) {
        if(!regCreditCard[i]) break;
    }
    var parent = document.getElementById('daftar_kartukredit');
    var child = parent.getElementsByTagName("input");
    if(i < regCreditCard.length) {
        child[child.length - 1].disabled = true;
    } else {
        child[child.length - 1].disabled = false;
    }
}
function regCCNumber(fld) {
    if(validateCreditCard(fld) == "") {
        var bucket = {"todo":"checkCreditCardNumber", "data":fld.value};
        sendJSONType(bucket, regCCNumberAfter);
        fld.style.background = "Gray";
    } else {
        fld.style.background = "Yellow";
        regCCSubmit(0, false);
    }
}
function regCCNumberAfter(data) {
    var parent = document.getElementById('daftar_kartukredit');
    var child = parent.getElementsByTagName("input");
    fld = child[0];
    var result = JSON.parse(data);
    if(result.status == "valid") {
        fld.style.background = "White";
        regCCSubmit(0, true);
    } else {
        regCCSubmit(0, false);
        fld.style.background = "Yellow";
    }
}
function regCCName(fld) {
    if(validateFullname(fld) == "") {
        fld.style.background = "White";
        regCCSubmit(1, true);
    } else {
        fld.style.background = "Red";
        regCCSubmit(1, false);
    }
}

/* ------------------------------------------------------------------------- */
/*                      IMPLEMENTATION EDIT IDENTITAS                        */
/* ------------------------------------------------------------------------- */
var editIdentiasAriValid = [true,true,true,false,false,false,false,false];
function editProfilSubmit(number_field, set) {
    editIdentiasAriValid[number_field] = set;
    var i;
    for(i = 0; i < editIdentiasAriValid.length; i++) {
        if(!editIdentiasAriValid[i]) break;
    }
    var parent = document.getElementById('identitas');
    var child = parent.getElementsByTagName("input");
    if(i < editIdentiasAriValid.length) {
        child[child.length - 1].disabled = true;
    } else {
        child[child.length - 1].disabled = false;
    }
}
function editProfilFullName(fld) {
    if(validateFullname(fld) == "") {
        fld.style.background = "White";
        editProfilSubmit(0, true);
    } else {
        fld.style.background = "Red";
        editProfilSubmit(0, false);
    }
}
function editProfilPassword(fld) {
    if(fld.value == "") {
        fld.style.background = "White";
        editProfilSubmit(1, true);
    } else {
        if(validatePassword(fld) == "") {
            var bucket = {"todo":"pendaftaran", "data":[
                {"check_field":"username", "value":fld.value}
            ]};
            sendJSONType(bucket, editProfilPasswordAfter);
            fld.style.background = "Gray";
        } else {
            fld.style.background = "Red";
            editProfilSubmit(1, false);
        }
    }
}
function editProfilPasswordAfter(data) {
    var parent = document.getElementById('identitas');
    var child = parent.getElementsByTagName("input");

    var result = JSON.parse(data);
    if(result.status == "valid") {
        child[1].style.background = "White";
        editProfilSubmit(1, true);
    } else {
        child[1].style.background = "Yellow";
        editProfilSubmit(1, false);
    }
}
function editProfilConfirmPassword(fld) {
    var parent = document.getElementById('identitas');
    var child = parent.getElementsByTagName("input");
    if(child[1].value != fld.value) {
        fld.style.background = "Red";
        editProfilSubmit(2, false);
    } else {
        fld.style.background = "White";
        editProfilSubmit(2, true);
    }
}
function editProfilAlamat(fld) {
    if(validateAlamat(fld) != "") {
        fld.style.background = "Red";
        editProfilSubmit(3, false);
    } else {
        fld.style.background = "White";
        editProfilSubmit(3, true);
    }
}
function editProfilProvinsi(fld) {
    if(validateProvinsi(fld) != "") {
        fld.style.background = "Red";
        editProfilSubmit(4, false);
    } else {
        fld.style.background = "White";
        editProfilSubmit(4, true);
    }
}
function editProfilKabupaten(fld) {
    if(validateProvinsi(fld) != "") {
        fld.style.background = "Red";
        editProfilSubmit(5, false);
    } else {
        fld.style.background = "White";
        editProfilSubmit(5, true);
    }
}
function editProfilKodePOS(fld) {
    if(validateKodePOS(fld) != "") {
        fld.style.background = "Red";
        editProfilSubmit(6, false);
    } else {
        fld.style.background = "White";
        editProfilSubmit(6, true);
    }
}
function editProfilNoHP(fld) {
    if(validateNoHP(fld) != "") {
        fld.style.background = "Red";
        editProfilSubmit(7, false);
    } else {
        fld.style.background = "White";
        editProfilSubmit(7, true);
    }
}
/* ADD TO SHOPPING BAG */
function validateQty(fld) {
    var parent = fld.parentNode;
    if(validateNumber(fld) == "") {
        fld.style.background = "#99FF00";
        parent.elements[2].disabled = false;
    } else {
        fld.style.background = "Orange";
        parent.elements[2].disabled = true;
    }
}
function validateQtyBarang(fld) {
    var parent = fld.parentNode;
    if(validateNumber(fld) == "") {
        fld.style.background = "#99FF00";
        parent.lastChild.disabled = false;
    } else {
        fld.style.background = "Orange";
        parent.lastChild.disabled = true;
    }   
}
/* CHECK USER WAS REGISTERED CARD NUMBER OR NOT YET */
function checkIsCard(){
    var str = {"user_id":getItemLocalStorage("userData").user_id};
    var bucket = {"todo":"checkCard", "data":str};
    sendJSONType(bucket, checkCardAfter);
}

function checkCardAfter(data) {
    //After get data from jsonresponder
    var result = (data == 1);
    if (result){
        buy();
    } else {
        alert('Data Credit Card kosong');
        window.location.href = BASE_URL + "pendaftaran/kartu";
    }
}