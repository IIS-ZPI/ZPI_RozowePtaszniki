$(document).ready(function () {

    // THIS SHOULD BE THE SAME AS IN JSON RETURNED BY 'localhost:4567/products' !!!!!!!!
    const productID = "id";
    const productName = "nazwa";
    const productCategory = "kategoria";
    const productBasePrice = "cena podstawowa";

    // THIS SHOULD BE THE SAME AS IN JSON RETURNED BY 'localhost:4567/calculate' !!!!!!!!
    const stateUSA = "stan USA";
    const noTaxPrice = "cena bez podatku";
    const profit = "zysk";

    // THESE NAMES ARE INDEPENDENT FROM SERVER
    const productLogisticCosts = "koszty logistyczne";
    const productFinalPrice = "pożądana cena";
    const productCalculatePrice = "pokaż ceny";
    const productRemove = "usuń";

    // THIS STORES DATA
    let productsData;
    let productsLastCalculatedPricesData;


    // at the start create table from 'localhost:4567/products' data
    $.getJSON("/products", function(data) {
        productsData = data;
        CreateTableFromJSON(data);
    });


    $('#products-table').on('click', '.table-calculate', function () {
        let id = $(this).parents("tr")[0].id;
        console.log(id);
        calculatePrice(id);
        updateModalContent(productsLastCalculatedPricesData[id]);
    });


    $('#products-table').on('click', '.table-remove', function () {
        // here should be asking for confirmation and sending remove request to the database
        $(this).parents('tr').detach();
    });


    $('#usa-state-id').change(function (e) {
        // TODO
        alert($(e.target).val());
    });


    function CreateTableFromJSON(data) {

        // CREATE COLUMNS
        let columnsBody = document.getElementById('columnsBody');
        let htmlString = `<tr>`;
        htmlString += `<th class="text-center">${productID}</th>`;
        htmlString += `<th class="text-center">${productName}</th>`;
        htmlString += `<th class="text-center">${productCategory}</th>`;
        htmlString += `<th class="text-center">${productBasePrice}</th>`;
        htmlString += `<th class="text-center">${productLogisticCosts}</th>`;
        htmlString += `<th class="text-center">${productFinalPrice}</th>`;
        htmlString += `<th class="text-center">${productCalculatePrice}</th>`;
        htmlString += `<th class="text-center">${productRemove}</th>`;
        htmlString += `</tr>`;
        columnsBody.innerHTML = htmlString;

        // ADD JSON DATA TO THE TABLE AS ROWS.
        let tableBody = document.getElementById('tableBody');
        htmlString = "";
        for (let i = 0; i < data.length; i++) {
            let id = data[i][productID];
            htmlString += `<tr id="${id}">`;
            htmlString += `<td id="${productID + id}">${id}</td>`;
            htmlString += `<td id="${productName + id}">${data[i][productName]}</td>`;
            htmlString += `<td id="${productCategory + id}">${data[i][productCategory]}</td>`;
            htmlString += `<td id="${productBasePrice + id}">${data[i][productBasePrice]}</td>`;
            htmlString += `<td id="${productLogisticCosts + id}">0</td>`;
            htmlString += `<td id="${productFinalPrice + id}">0</td>`;
            htmlString += createCalculateButton(id);
            htmlString += createRemoveButton(id);
            htmlString += `</tr>`;
        }
        tableBody.innerHTML = htmlString;
    }

    function createRemoveButton(id) {
        let htmlString = `<td id="${productRemove+id}" class="table-remove"><button type="button" class="btn btn-rounded btn-danger btn-sm">${productRemove}</button></td>`;
        return htmlString;
    }

    function createCalculateButton(id) {
        let htmlString = `<td id="${productCalculatePrice+id}" class="table-calculate">`;
        htmlString += `<button type="button" class="btn btn-rounded btn-default btn-sm" data-toggle="modal" data-target="#calculate-modal">`;
        htmlString += `${productCalculatePrice}</button></td>`;
        return htmlString;
    }


    function calculatePrice(id){
        let finalPriceValue = document.getElementById("product-final-price" + id).value;
        $.getJSON("/calculate/" + id + "/" + finalPriceValue, function(data) {
            productsLastCalculatedPricesData[id] = data;
        });
    }


    function updateModalContent(data) {

        let basePriceModalCell = document.getElementById("base-price-id");
        let finalPriceModalCell = document.getElementById("final-price-id");
        let stateModalCell = document.getElementById("usa-state-id");
        let logisticsCostModalCell = document.getElementById("logistic-costs-id");
        let noTaxPriceModalCell = document.getElementById("no-tax-price-id");
        let profitModalCell = document.getElementById("profit-id");

        let stateName = stateModalCell.options[stateModalCell.selectedIndex].innerText;
        noTaxPriceModalCell.setAttribute("placeholder", data[noTaxPrice]);
        profitModalCell.setAttribute("placeholder", data[stateName][profit]);
    }

});
