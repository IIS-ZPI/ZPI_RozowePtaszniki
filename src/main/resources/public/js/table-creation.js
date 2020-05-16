import {
    productBasePrice, productCalculatePrice,
    productCategory,
    productFinalPrice,
    productID,
    productLogisticCosts,
    productName, productRemove
} from "./constants.js";


export function CreateTableFromJSON(data) {

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

export function createRemoveButton(id) {
    let htmlString = `<td id="${productRemove+id}" class="table-remove">`;
    htmlString += `<button type="button" class="btn btn-rounded btn-danger btn-sm">${productRemove}</button></td>`;
    return htmlString;
}

export function createCalculateButton(id) {
    let htmlString = `<td id="${productCalculatePrice+id}" class="table-calculate">`;
    htmlString += `<button type="button" class="btn btn-rounded btn-default btn-sm" data-toggle="modal" data-target="#calculate-modal">`;
    htmlString += `${productCalculatePrice}</button></td>`;
    return htmlString;
}
