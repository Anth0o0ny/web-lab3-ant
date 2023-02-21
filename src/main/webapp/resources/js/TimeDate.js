function currentTime() {
    let date = new Date();
    let hour = date.getHours();
    let min = date.getMinutes();
    let sec = date.getSeconds();
    let day = date.getDate();
    let month = date.getMonth() + 1
    let year = date.getFullYear()

    hour = updateTime(hour);
    min = updateTime(min);
    sec = updateTime(sec);
    day  = updateTime(day)
    month = updateTime(month)
    year = updateTime(year)

    document.getElementById("clock").innerText = hour + " : " + min + " : " + sec + "\n" + day + "/" + month + "/" + year;
    setTimeout(function(){ currentTime() }, 12000);
}

function updateTime(k) {
    if (k < 10) {
        return "0" + k;
    }
    else {
        return k;
    }
}

currentTime();
