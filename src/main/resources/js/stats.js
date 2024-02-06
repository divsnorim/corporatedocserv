google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(topQuantity);
google.charts.setOnLoadCallback(drawSigned);
google.charts.setOnLoadCallback(drawMod);
google.charts.setOnLoadCallback(drawNotSigned);

function topQuantity() {
    let res = [['Бизнес процесс', 'Количество документов']];

    for (let i = 0; i < topQuantityName.length; i++) {
        res.push([topQuantityName[i], topQuantityNumber[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    let options = {
        title: 'Топ-5 бизнес процессов',
        hAxis: {title: 'Бизнес процесс'},
        vAxis: {title: 'Количество документов'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };

    let chart = new google.visualization.ColumnChart(document.getElementById('topQuantity'));
    chart.draw(data, options);
}

function drawSigned() {
    let res = [['Бизнес процесс', 'Количество подписанных']];

    for (let i = 0; i < signedString.length; i++) {
        res.push([signedString[i], signedInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Количество подписанных',
        pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawSigned'));
    chart.draw(data, options);
}

function drawMod() {
    let res = [['Бизнес процесс', 'Количество на доработке']];

    for (let i = 0; i < modString.length; i++) {
        res.push([modString[i], modInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Количество на доработке',
        pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawMod'));
    chart.draw(data, options);
}

function drawNotSigned() {
    let res = [['Бизнес процесс', 'Количество не подписанных']];

    for (let i = 0; i < notSignedString.length; i++) {
        res.push([notSignedString[i], notSignedInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Количество не подписанных',
        pieHole: 0.4,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawNotSigned'));
    chart.draw(data, options);
}