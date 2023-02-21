let canvas = document.getElementById("hit_area");
window.onload = chooseR;


//  Данные в пикселах
const HEIGHT_PIX = canvas.height;
const WIDTH_PIX = canvas.width;
const MID_WIDTH_PIX = WIDTH_PIX / 2;
const MID_HEIGHT_PIX = HEIGHT_PIX / 2;
const ARROW_SIDE_PIX = 20;
const ARROW_HEIGHT = Math.sqrt(Math.pow(ARROW_SIDE_PIX, 2) - Math.pow(ARROW_SIDE_PIX / 2, 2));
const DOT_RADIUS = 3.5;
const DOT_COUNT = 5;
const HALF_RADIUS_PIX = 25;
const RADIUS_PIX = HALF_RADIUS_PIX*2;
//  Отображение радиуса на графике
let canvasRadius = 2.00;
let canvasHalfRadius = 1;

/* Масштабирование */
let coordinatesToServer = (canvasHalfRadius / RADIUS_PIX);
let coordinatesToView = 1 / coordinatesToServer;

//  Холст
const ctx = canvas.getContext("2d");

drawGraphicsCarcas();
setRadius();

canvas.addEventListener("click", (event) => {

    console.log("Нажали на канвас");


    console.log("Радиус удовлетворительный: r=" + canvasRadius);

    coordinatesToServer = (canvasHalfRadius / RADIUS_PIX);

    let rect = canvas.getBoundingClientRect();
    let x = event.clientX - rect.left;
    let y = event.clientY - rect.top;

    let xCoordinate = (x - MID_WIDTH_PIX) * coordinatesToServer;
    let yCoordinate = (MID_HEIGHT_PIX - y) * coordinatesToServer;

    let hiddenR = document.getElementById("mainForm:r_hidden");

    console.log(xCoordinate + " " + yCoordinate + " " + hiddenR.value + " -- данные для отправки серверу");
    let sendToServerRValue;
    if (hiddenR.value == 2){
        sendToServerRValue = 200
    } else {
        sendToServerRValue = hiddenR.value;
    }
    sendToServer(xCoordinate, yCoordinate, sendToServerRValue);

})

function chooseR(){
    let inputR = document.getElementById("mainForm:r_hidden");
    let correctDivR;
    if (inputR.value == 2){
        correctDivR = inputR.value;
    } else {
        correctDivR = inputR.value/100
    }

    document.getElementById("mainForm:slider_val").innerHTML = "Parameter R = " + correctDivR;

    if (canvasRadius != correctDivR && inputR.value !== 0) {
        canvasRadius = correctDivR;
        canvasHalfRadius = canvasRadius/2;
        clearCanvas();
        drawGraphicsCarcas();
        setRadius();
        drawDots(canvasRadius);
    }
    setTimeout(chooseR, 850);
}
function clearCanvas() {
    ctx.clearRect(0, 0, WIDTH_PIX, HEIGHT_PIX);
    drawGraphicsCarcas();
    setRadius();
}

function drawGraphicsCarcas() {

    ctx.globalAlpha = 0.45;
    ctx.fillStyle = "#9966ff";
    ctx.strokeStyle = "#9966ff";

    /* Фигуры */

    /* 1 четверть - треугольник */

    ctx.beginPath();
    ctx.moveTo(MID_WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.lineTo(MID_WIDTH_PIX, MID_HEIGHT_PIX - RADIUS_PIX);
    ctx.lineTo(MID_WIDTH_PIX + 2 * RADIUS_PIX, MID_HEIGHT_PIX);
    ctx.lineTo(MID_WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.stroke();
    ctx.fill();

    /* 2 четверть - квадрат */
    ctx.beginPath();
    ctx.moveTo(MID_WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.lineTo(MID_WIDTH_PIX-2*RADIUS_PIX , MID_HEIGHT_PIX);
    ctx.lineTo(MID_WIDTH_PIX-2*RADIUS_PIX, MID_HEIGHT_PIX-2*RADIUS_PIX);
    ctx.lineTo(MID_WIDTH_PIX, MID_HEIGHT_PIX-2*RADIUS_PIX);
    ctx.lineTo(MID_WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.stroke();
    ctx.fill();

    /* 3 четверть - пусто */

    /* 4 четверть - четверть окружности */
    ctx.beginPath();
    ctx.moveTo(MID_WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.arc(MID_WIDTH_PIX, MID_HEIGHT_PIX, 2 * RADIUS_PIX, 0, Math.PI / 2, false);
    ctx.stroke();
    ctx.fill();

    /* Цвет осей и точек на графике */
    ctx.globalAlpha = 1.0;
    ctx.fillStyle = "aliceblue";
    ctx.strokeStyle = "aliceblue";

    /* Ось Ox */

    ctx.beginPath();
    ctx.moveTo(0, MID_HEIGHT_PIX);
    ctx.lineTo(WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.lineTo(WIDTH_PIX - ARROW_HEIGHT, MID_HEIGHT_PIX - ARROW_SIDE_PIX / 2);
    ctx.lineTo(WIDTH_PIX - ARROW_HEIGHT, MID_HEIGHT_PIX + ARROW_SIDE_PIX / 2);
    ctx.lineTo(WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.stroke();
    ctx.fill();

    /* Ось Oy */

    ctx.beginPath();
    ctx.moveTo(MID_WIDTH_PIX, HEIGHT_PIX);
    ctx.lineTo(MID_WIDTH_PIX, 0);
    ctx.lineTo(MID_WIDTH_PIX - ARROW_SIDE_PIX / 2, ARROW_HEIGHT);
    ctx.lineTo(MID_WIDTH_PIX + ARROW_SIDE_PIX / 2, ARROW_HEIGHT);
    ctx.lineTo(MID_WIDTH_PIX, 0);
    ctx.stroke();
    ctx.fill();

    /* Точки на графике */
    ctx.beginPath();
    /* Центр */
    ctx.moveTo(MID_WIDTH_PIX, MID_HEIGHT_PIX);
    ctx.arc(MID_WIDTH_PIX, MID_HEIGHT_PIX, DOT_RADIUS, 0, 2 * Math.PI);
    /* Горизонтальные */
    for (let count = 1; count <= DOT_COUNT; count++) {
        if (count === 3) continue;
        let dotX = RADIUS_PIX * count;
        let dotY = HEIGHT_PIX - HALF_RADIUS_PIX*6;
        let radius = DOT_RADIUS;
        ctx.moveTo(dotX, dotY);
        ctx.arc(dotX, dotY, radius, 0, 2 * Math.PI);
    }
    /* Вертикальные */
    for (let count = 1; count <= DOT_COUNT; count++) {
        if (count === 3) continue;
        let dotX = MID_WIDTH_PIX;
        let dotY = HEIGHT_PIX - RADIUS_PIX * count;
        let radius = DOT_RADIUS;
        ctx.moveTo(dotX, dotY);
        ctx.arc(dotX, dotY, radius, 0, 2 * Math.PI);
    }
    ctx.stroke();
    ctx.fill();
}



function setRadius() {
    /* Шрифт */
    ctx.font = "12px Century Schoolbook";
    /* Цвет */
    ctx.fillStyle = "aliceblue";
    ctx.strokeStyle = "aliceblue";

    /* R/2 */

    ctx.fillText(-canvasHalfRadius, MID_WIDTH_PIX + RADIUS_PIX / 6, MID_HEIGHT_PIX + RADIUS_PIX + DOT_RADIUS); //  Низ
    ctx.fillText(canvasHalfRadius, MID_WIDTH_PIX + RADIUS_PIX / 6, MID_HEIGHT_PIX - RADIUS_PIX + DOT_RADIUS); //  Верх
    ctx.fillText(-canvasHalfRadius, MID_WIDTH_PIX - RADIUS_PIX - DOT_RADIUS, MID_HEIGHT_PIX - RADIUS_PIX / 6); //  Лево
    ctx.fillText(canvasHalfRadius, MID_WIDTH_PIX + RADIUS_PIX - DOT_RADIUS, MID_HEIGHT_PIX - RADIUS_PIX / 6); //  Право

    // /* R */

    ctx.fillText(-canvasRadius, MID_WIDTH_PIX + RADIUS_PIX / 6, MID_HEIGHT_PIX + 2 * RADIUS_PIX + DOT_RADIUS); //  Низ
    ctx.fillText(canvasRadius, MID_WIDTH_PIX + RADIUS_PIX / 6, MID_HEIGHT_PIX - 2 * RADIUS_PIX + DOT_RADIUS); //  Верх
    ctx.fillText(-canvasRadius, MID_WIDTH_PIX - 2 * RADIUS_PIX - DOT_RADIUS, MID_HEIGHT_PIX - RADIUS_PIX / 6); //  Лево
    ctx.fillText(canvasRadius, MID_WIDTH_PIX + 2 * RADIUS_PIX - DOT_RADIUS, MID_HEIGHT_PIX - RADIUS_PIX / 6); //  Право
}


function drawDot(xCoordinate, yCoordinate, color) {

    coordinatesToServer = (canvasHalfRadius / RADIUS_PIX);
    coordinatesToView = 1/coordinatesToServer;

    const xCanvas = xCoordinate * coordinatesToView + MID_WIDTH_PIX;
    const yCanvas = MID_HEIGHT_PIX - yCoordinate * coordinatesToView;

    console.log(coordinatesToView)

    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.moveTo(xCanvas, yCanvas);
    ctx.arc(xCanvas, yCanvas, DOT_RADIUS, 0, 2 * Math.PI);
    ctx.fill();
    ctx.closePath();
    console.log("Нарисовали точку...");
}

function drawDots(currentRadius) {

    console.log("Рисуем точки...");

    coordinatesToServer = (canvasHalfRadius / RADIUS_PIX);
    coordinatesToView = 1/coordinatesToServer;

    let xValues = document.getElementsByClassName("x_column");
    let yValues = document.getElementsByClassName("y_column");
    let rValues = document.getElementsByClassName("r_column");
    let resValues = document.getElementsByClassName("result_column");


    console.log("Сейчас будет цикл..., где проверка на совпадение r=" + parseFloat(currentRadius));

    for (let i = 0; i < resValues.length; i++) {
        let table_x = parseFloat(xValues[i].innerHTML);
        let table_y = parseFloat(yValues[i].innerHTML);
        let table_r = parseFloat(rValues[i].innerHTML);
        let color = (resValues[i].innerHTML === "Hit" ? "#66ff99" : "red");

        if (parseFloat(currentRadius) == table_r) {
            drawDot(table_x, table_y, color);
            console.log("Нарисовали точку: " + " x=" + table_x + " y=" + table_y + " color=" + color)
        }
    }
}


function drawLastDot() {
    setTimeout(function () {
        coordinatesToServer = (canvasHalfRadius / RADIUS_PIX);
        coordinatesToView = 1 / coordinatesToServer;

        let xValues = document.getElementsByClassName("x_column");
        let yValues = document.getElementsByClassName("y_column");
        let rValues = document.getElementsByClassName("r_column");
        let resValues = document.getElementsByClassName("result_column");
        let inputR = document.getElementById("mainForm:r_hidden");

        let index = xValues.length - 1;
        if (index === -1) {
            return;
        }
        let table_x = parseFloat(xValues[index].innerHTML);
        let table_y = parseFloat(yValues[index].innerHTML);
        let table_r = parseFloat(rValues[index].innerHTML);

        let color = (resValues[index].innerHTML === "Hit" ? "#66ff99" : "red");

        if (table_r == inputR.value/100) drawDot(table_x, table_y, color);
    }, 150);
}






