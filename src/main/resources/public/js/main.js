import {createTableFromJSON} from "./table-creation.js";
import {noTaxPrice, profit, productFinalPrice, productBasePrice, productLogisticCosts} from "./config.js";


$(document).ready(function () {
    main();
});


// THIS STORES DATA
let productsData;
let productsLastCalculatedPricesData = {};


function main(){

    // at the start create table from 'localhost:4567/products' data
    $.getJSON("/products", function(data) {
        productsData = data;
        createTableFromJSON(data);
    });


    $('#products-table').on('click', '.table-calculate', function () {
        let id = $(this).parents("tr")[0].id;
        calculatePrice(id);
        updateModalContent(productsLastCalculatedPricesData[id]);
    });


    $('#products-table').on('click', '.table-remove', function () {
        // TODO
        // here should be asking for confirmation and sending remove request to the database
        $(this).parents('tr').detach();
    });


    $('#usa-state-id').change(function (e) {
        // TODO
        alert($(e.target).val());
    });
}


function calculatePrice(id){
    let finalPriceValue = document.getElementById(productFinalPrice+id).value;
    $.getJSON("/calculate/" + id + "/" + finalPriceValue, function(data) {
        productsLastCalculatedPricesData[id] = data;
        console.log("asdasd", productsLastCalculatedPricesData[id]);
    });
}

function updateModalContent(data) {

    let basePriceModalCell = document.getElementById("base-price-id");
    let finalPriceModalCell = document.getElementById("final-price-id");
    let stateModalCell = document.getElementById("usa-state-id");
    let logisticCostsModalCell = document.getElementById("logistic-costs-id");
    let noTaxPriceModalCell = document.getElementById("no-tax-price-id");
    let profitModalCell = document.getElementById("profit-id");

    let stateName = stateModalCell.options[stateModalCell.selectedIndex].innerText;
    basePriceModalCell.setAttribute("placeholder", data[productBasePrice]);
    finalPriceModalCell.setAttribute("placeholder", data[productFinalPrice]);
    noTaxPriceModalCell.setAttribute("placeholder", data[noTaxPrice]);
    profitModalCell.setAttribute("placeholder", data[stateName][profit]);

    // TODO
    //logisticCostsModalCell.setAttribute("placeholder", data[productLogisticCosts]);
}



