/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/* global ctrl, MOVE, RED, SELECT, AI */

//-----------Chat---------------------------------------------------------------
function getChat(strURL) {

    var form = document.forms['formMain'];
    var msg = form.msg.value;
    if (msg === "")
        return;

    var self = this;
    // Mozilla/Safari
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }
    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }
    self.xmlHttpReq.open('POST', strURL, true);
    self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=utf-8');
    self.xmlHttpReq.onreadystatechange = function () {
        if (self.xmlHttpReq.readyState === 4 && self.xmlHttpReq.status === 200) {
            updatepageChat(self.xmlHttpReq.responseText);
        }
    };
    self.xmlHttpReq.send(getquerystring());
}

function getquerystring() {
    var form = document.forms['formMain'];
    var msg = form.msg.value;
    form.msg.value = "";
    var qstr = 'msg=' + msg;
    qstr += '&yc=msg';
    return qstr;
}

function updatepageChat(xmlDoc) {
    var obj = jQuery.parseJSON(xmlDoc);
    document.getElementById("result1").innerHTML = document.getElementById("result2").innerHTML;
    var msg = obj.msg;
    if (obj.name !== "") {
        msg = '<strong>' + obj.name + '</strong>: ' + '<span style="color:brown">' + obj.msg + '</span>';
    }
    document.getElementById("result2").innerHTML = msg;
}

function getqueryMsg() {
    var form = document.forms['formMain'];
    form.msg.value = "";
    return null;
}

//-----------Data---------------------------------------------------------------
function getData(strURL) {
    var self = this;
    // Mozilla/Safari
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }
    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }
    self.xmlHttpReq.open('POST', strURL, true);
    self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    self.xmlHttpReq.onreadystatechange = function () {
        if (self.xmlHttpReq.readyState === 4 && self.xmlHttpReq.status === 200) {
            updatepageData(self.xmlHttpReq.responseText);
        }
    };
    self.xmlHttpReq.send(getqueryData());
}

function getqueryData() {
    var form = document.forms['formMain'];
    form.msg.value = "";

    var board = "";
    for (var i = 0; i <= 9; i++)
    {
        for (var j = 0; j <= 8; j++)
        {
            board += cell[i][j];
            if (i + j !== 17)
                board += ",";
        }
    }

    var qstr = 'yc=chess';
    qstr += '&cell=' + escape(board);
    qstr += '&ctrl=' + escape(ctrl);
    if (ctrl === MOVE) {
        qstr += '&red=' + escape(RED);
        qstr += '&prex=' + escape(preX);
        qstr += '&prey=' + escape(preY);
        qstr += '&x=' + escape(x);
        qstr += '&y=' + escape(y);
    } else if (ctrl === SELECT) {
        qstr += '&x=' + escape(x);
        qstr += '&y=' + escape(y);
    }
    return qstr;
}

function updatepageData(xmlDoc) {
    var obj = jQuery.parseJSON(xmlDoc);
    if (ctrl === MOVE) {
        getqueryMsg();
        over = obj.over;
        cell = obj.cell;
        draw(cell);
        move(x, y, preX, preY);
        if (over) {
            showWin();
        }
    } else if (ctrl === SELECT) {
        posibleMove = obj.allMove;
        drawAllPossibleMove(posibleMove);
    } else if (ctrl === AI) {
        preX = obj.prx;
        preY = obj.pry;
        x = obj._x;
        y = obj._y;
    }
}