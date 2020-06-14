/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var cell = cellStartup,
SELECT = 1,
MOVE = 2,
AI = 3,
over = false,
RED = false,
x = -1,
y = -1,
preX = -1,
preY = -1,
posibleMove = [[]],
win = 0,
lose = 0,
_timer = 120,
clock1 = 15*60,
clock2 = 15*60,
ctrl = "";
$(function()
{
    $('#canvas').mousedown(function(e){
        preX = x;
        preY = y;
        x = Math.round((e.pageY - this.offsetTop - 30)/60);
        y = Math.round((e.pageX - this.offsetLeft - 30)/60);
        
        getqueryMsg();        
        if(over){
            return;
        }
        
        value = cell[x][y];
        if(value != 0 && isCheck(value))
        {
            draw(cell);
            select(x, y);
            ctrl = SELECT;
            getData('Servlet');
        }        
        else if(ctrl == SELECT)
        {
            for(var i = 0; i < posibleMove.length; i++)
            {
                if(posibleMove[i][0] == y && posibleMove[i][1] == x){
                    ctrl = MOVE;
                    getData('Servlet');
                    _timer = 120;
                    break;
                }
            }
        }
    });
    $('#canvas').mousemove(function(){
        });
    $('#canvas').mouseenter(function(){
        });
    $("#canvas").mouseup(function(){
        });
    $('#canvas').mouseleave(function(){
        });
});
function isCheck(value)
{
    if((RED && value > 14) || (!RED && value >= 8 && value <= 14))
        return true;
    return false;
}
function select(x, y)
{
    drawPiece('images/Cselect.png', y * 60, x * 60);
}
function move(x, y, preX, preY)
{
    var pictureBox = new Array('images/bk.png', 'images/ba.png', 'images/bb.png', 'images/bn.png',
        'images/br.png', 'images/bc.png', 'images/bp.png', 'images/rk.png', 'images/ra.png',
        'images/rb.png', 'images/rn.png', 'images/rr.png', 'images/rc.png', 'images/rp.png');
    drawPiece('images/Cselect.png', y * 60, x * 60);
    var canvas = document.getElementById('canvas');
    var value = cell[x][y];
    if (canvas.getContext){ 
        var ctx = canvas.getContext('2d'); 
        var imageObj = new Image(); 
        imageObj.onload = function(){
            ctx.drawImage(imageObj, preY*60 + 20, preX*60 + 20, 20, 20); 
        }; 
        imageObj.src = pictureBox[value-8]; 
    }
}

$(document).ready(function() {
    $('#send').keypress(function(event) {
        if (event.keyCode == 13) {
            getChat("Servlet");
            event.preventDefault();
        }
    });
});

function reset()
{
    cell = cellStartup;
    _select = false;
    _move = false;
    over = false;
    RED = false;
    x = -1;
    y = -1;
    preX = -1;
    preY = -1;
    posibleMove = [[]];
    _timer = 120;
    clock1 = 15*60;
    clock2 = 15*60;
    document.getElementById("alert").style.display = "none";
    document.getElementById("canvas").style.opacity = "1";
    draw(cellStartup);
}
function showWin()
{
    if(RED){
        document.getElementById("w-l").innerHTML = "You win";
        win++;
    }
    else{
        document.getElementById("w-l").innerHTML = "You lose";
        lose++;
    }
    document.getElementById("alert").style.display = "block";
    document.getElementById("canvas").style.opacity = "0.3";
            
    document.getElementById("sc").innerHTML = win;
    document.getElementById("su").innerHTML = lose;
    
    document.getElementById("timer1").innerHTML = "---";
    document.getElementById("timer2").innerHTML = "---";
    
    document.getElementById("clock1").innerHTML = "--:--";
    document.getElementById("clock2").innerHTML = "--:--";
}
function timer()
{
    if(!over){
        if(_timer == 0 || clock1 == 0 || clock2 == 0){
            over = true;
            showWin();
            return;
        }
        du = clock1 % 60;
        if(du < 10) du = "0" + du;
        document.getElementById("clock1").innerHTML = Math.floor(clock1/60) + ":" + du;        
        du=clock2%60;
        if(du < 10) du = "0" + du;
        document.getElementById("clock2").innerHTML = Math.floor(clock2/60) + ":" + du;
        if(RED){
            document.getElementById("timer2").innerHTML = "---";
            document.getElementById("timer1").innerHTML = _timer;
            clock1--;
        }
        else{
            document.getElementById("timer1").innerHTML = "---";
            document.getElementById("timer2").innerHTML = _timer;
            clock2--;
        }
        _timer--;
    }
}
setInterval("timer()",1000);