/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var cellStartup = [[19, 18, 17, 16, 15, 16, 17, 18, 19],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 20, 0, 0, 0, 0, 0, 20, 0],
    [21, 0, 21, 0, 21, 0, 21, 0, 21],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [14, 0, 14, 0, 14, 0, 14, 0, 14],
    [0, 13, 0, 0, 0, 0, 0, 13, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0],
    [12, 11, 10, 9, 8, 9, 10, 11, 12]];
function drawPlus(_x, _y, pos)
{
    var canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        ctx.strokeStyle = 'black';
        var border = 4,
                kc = 15,
                x = _x * 60 + 30,
                y = _y * 60 + 30;
        if (pos !== 1)
        {
            ctx.moveTo(x - kc, y - border);
            ctx.lineTo(x - border, y - border);
            ctx.lineTo(x - border, y - kc);
            ctx.moveTo(x - kc, y + border);
            ctx.lineTo(x - border, y + border);
            ctx.lineTo(x - border, y + kc);
        }
        if (pos !== 2)
        {
            ctx.moveTo(x + kc, y - border);
            ctx.lineTo(x + border, y - border);
            ctx.lineTo(x + border, y - kc);
            ctx.moveTo(x + kc, y + border);
            ctx.lineTo(x + border, y + border);
            ctx.lineTo(x + border, y + kc);
        }
        ctx.stroke();
    }
}
function drawPluss() {
    drawPlus(1, 2, 0);
    drawPlus(1, 7, 0);
    drawPlus(7, 7, 0);
    drawPlus(7, 2, 0);
    for (var i = 1; i < 4; i++) {
        drawPlus(i * 2, 3, 0);
        drawPlus(i * 2, 6, 0);
    }
    drawPlus(0, 3, 1);
    drawPlus(0, 6, 1);
    drawPlus(8, 3, 2);
    drawPlus(8, 6, 2);
    drawPlus(8, 6, 2);
}
function drawPiece(images, dx, dy) {
    var canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        var imageObj = new Image();
        imageObj.onload = function () {
            ctx.drawImage(imageObj, dx, dy);
        };
        imageObj.src = images;
    }
}
function drawPieces(cellStartup)
{
    var pictureBox = new Array('images/bk.png', 'images/ba.png', 'images/bb.png', 'images/bn.png',
            'images/br.png', 'images/bc.png', 'images/bp.png', 'images/rk.png', 'images/ra.png',
            'images/rb.png', 'images/rn.png', 'images/rr.png', 'images/rc.png', 'images/rp.png');

    for (var i = 0; i <= 8; i++)
    {
        for (var j = 0; j <= 9; j++)
        {
            var value = cellStartup[j][i];
            if (value !== 0)
                drawPiece(pictureBox[value - 8], 60 * i, 60 * j);
        }
    }
}
function drawBoard() {
    var canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        var img = document.getElementById("scream");
        ctx.strokeStyle = 'black';
        ctx.drawImage(img, 0, 0, 540, 600);

        //--------border-------------
        ctx.beginPath();
        ctx.moveTo(27, 27);
        ctx.lineTo(513, 27);
        ctx.lineTo(513, 573);
        ctx.lineTo(27, 573);
        ctx.closePath();

        //---------caro---------------
        for (var i = 0; i < 10; i++) {
            ctx.moveTo(30, 30 + i * 60);
            ctx.lineTo(510, 30 + i * 60);
        }
        for (i = 1; i < 8; i++) {
            ctx.moveTo(30 + i * 60, 30);
            ctx.lineTo(30 + i * 60, 270);

            ctx.moveTo(30 + i * 60, 330);
            ctx.lineTo(30 + i * 60, 570);
        }
        ctx.moveTo(30, 30);
        ctx.lineTo(30, 570);
        ctx.moveTo(510, 30);
        ctx.lineTo(510, 570);

        ctx.stroke();
    }
}

function drawX() {
    var canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        ctx.strokeStyle = 'black';
        ctx.moveTo(210, 30);
        ctx.lineTo(330, 150);
        ctx.moveTo(330, 30);
        ctx.lineTo(210, 150);

        ctx.moveTo(210, 450);
        ctx.lineTo(330, 570);
        ctx.moveTo(330, 450);
        ctx.lineTo(210, 570);

        ctx.stroke();
    }
}
function drawBoards()
{
    drawBoard();
    drawX();
    drawPluss();
}
function drawAllPossibleMove(posibleMove)
{
    var canvas = document.getElementById('canvas');
    if (canvas.getContext) {
        var ctx = canvas.getContext('2d');
        for (var i = 0; i < posibleMove.length; i++) {
            var x = posibleMove[i][0];
            var y = posibleMove[i][1];
            ctx.fillStyle = 'black';
            ctx.beginPath();
            ctx.arc(x * 60 + 30, y * 60 + 30, 5, 0, 2 * Math.PI);
            ctx.fill();
            ctx.strokeStyle = 'white';
            ctx.beginPath();
            ctx.arc(x * 60 + 30, y * 60 + 30, 6, 0, 2 * Math.PI);
            ctx.stroke();
        }
    }
}
function draw(cellStartup)
{
    drawBoards();
    drawPieces(cellStartup);
}