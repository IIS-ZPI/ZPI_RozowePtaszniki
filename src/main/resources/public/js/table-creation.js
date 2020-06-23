import {
    productBasePrice, productCalculatePrice,
    productCategory,
    productFinalPrice,
    productID,
    productName, productRemove
} from "./config.js";


export function createTableFromJSON(data) {

    // CREATE COLUMNS
    let columnsBody = document.getElementById('columnsBody');
    let htmlString = `<tr>`;
    htmlString += `<th class="text-center">Id</th>`;
    htmlString += `<th class="text-center">Nazwa</th>`;
    htmlString += `<th class="text-center">Kategoria</th>`;
    htmlString += `<th class="text-center">Cena podstawowa</th>`;
    htmlString += `<th class="text-center">Cena końcowa</th>`;
    htmlString += `<th class="text-center"></th>`;
    htmlString += `<th class="text-center"></th>`;
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
        htmlString += `<td id="${productFinalPrice + id}" class="final-price" contenteditable="true">0</td>`;
        htmlString += createShowPricesButton(id);
        htmlString += createRemoveButton(id);
        htmlString += `</tr>`;
    }
    tableBody.innerHTML = htmlString;
}

export function createRemoveButton(id) {
    let htmlString = `<td id="${productRemove+id}" class="table-remove p-0 py-1">`;
    htmlString += `<button type="button" class="btn btn-sm btn-danger waves-effect btn-in-table">${productRemove}</button></td>`;
    return htmlString;
}

export function createShowPricesButton(id) {
    let htmlString = `<td id="${productCalculatePrice+id}" class="table-show-prices p-0 py-1">`;
    htmlString += `<button type="button" class="btn btn-sm btn-dark-green waves-effect btn-in-table" data-toggle="modal" data-target="#show-prices-modal">`;
    htmlString += `${productCalculatePrice}</button></td>`;
    return htmlString;
}
