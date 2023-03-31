function pageLink (url) {
    window.location.href = url;
}

function pageReplace (url) {
    window.location.replace(url);
}

function dateToString(date) {
    let year = date.getFullYear().toString();

    let month = date.getMonth() + 1;
    month = month < 10 ? '0' + month.toString() : month.toString();

    let day = date.getDate();
    day = day < 10 ? '0' + day.toString() : day.toString();

    let hour = date.getHours();
    hour = hour < 10 ? '0' + hour.toString() : hour.toString();

    let minites = date.getMinutes();
    minites = minites < 10 ? '0' + minites.toString() : minites.toString();

    let seconds = date.getSeconds();
    seconds = seconds < 10 ? '0' + seconds.toString() : seconds.toString();

    return year + month + day + hour + minites + seconds;
}