const Y_MAX = 5;
const Y_MIN = -3;

function validateY(y) {
    return (Number.isInteger(value) && value > Y_MIN && value < Y_MAX);
}

function validateR(value) {
    if (!Number.isInteger(value)) return false;
    let values = [2, 2.75,  3.5, 4.25, 5];
    for (let i = 0; values.length; i++) {
        if (value === values[i]) return values[i];
    }
    return "R";
}