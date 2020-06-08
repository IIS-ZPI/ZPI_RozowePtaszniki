import {createTableFromJSON} from "./table-creation.js";
import {noTaxPrice, profit, productFinalPrice, productBasePrice, productLogisticCosts,
    productCategory, productID} from "./config.js";


$(document).ready(function () {
    main();

});


// THIS STORES DATA
let productsData;
let productsLastCalculatedPricesData = {};

let currentID = null;


function main(){

    // at the start create table from 'localhost:4567/products' data
    $.getJSON("/products", function(data) {
        productsData = data;
        createTableFromJSON(data);
        calculatePriceForEveryProduct();

        // THIS NEEDS TO BE CALLED ONLY AFTER COLUMNS ARE CREATED WITH createTableFromJSON() function!
        $('#products-table').DataTable({
            columnDefs: [{
                orderable: false,
                targets: [5, 6]
            }]
        });
        $('.dataTables_length').addClass('bs-select');

    });


    $('body').on('focus', '[contenteditable]', function() {
        const $this = $(this);
        $this.data('before', $this.html());
    }).on('blur keyup paste input', '[contenteditable]', function() {
        const $this = $(this);
        if ($this.data('before') !== $this.html()) {
            $this.data('before', $this.html());
            $this.trigger('change');
        }
    }).on('change', '[contenteditable]', function() {
        let id = $(this).parents("tr")[0].id;
        getPricesFromServer(id);
        console.log(id);
    });


    $('#products-table').on('click', '.table-show-prices', function () {
        let id = $(this).parents("tr")[0].id;
        currentID = id;
        updateModalContent(productsLastCalculatedPricesData[id]);
    });


    $('#products-table').on('click', '.table-remove', function () {
        // TODO
        // here should be asking for confirmation and sending remove request to the database
        $(this).parents('tr').detach();
    });


    $('#usa-state-id').change(function (e) {
        updateModalContent(productsLastCalculatedPricesData[currentID]);
    });
}


function calculatePriceForEveryProduct(){
    for (let i=0; i<productsData.length; i++){
        getPricesFromServer(productsData[i][productID]);
    }
}


function getPricesFromServer(id){
    let category = document.getElementById(productCategory+id).textContent;
    let basePriceValue = document.getElementById(productBasePrice+id).textContent;
    let finalPriceValue = document.getElementById(productFinalPrice+id).textContent;
    let request = "/calculate/" + id + "/" + category + "/" + basePriceValue + "/" + finalPriceValue;
    $.getJSON(request, function(data) {
        productsLastCalculatedPricesData[id] = data;
    });
}


function updateModalContent(data) {
    if (data === undefined)
        return;

    let basePriceModalCell = document.getElementById("base-price-id");
    let finalPriceModalCell = document.getElementById("final-price-id");
    let stateModalCell = document.getElementById("usa-state-id");
    // let logisticCostsModalCell = document.getElementById("logistic-costs-id");
    let noTaxPriceModalCell = document.getElementById("no-tax-price-id");
    let profitModalCell = document.getElementById("profit-id");

    let stateName = stateModalCell.options[stateModalCell.selectedIndex].innerText;
    basePriceModalCell.setAttribute("placeholder", data[productBasePrice]);
    finalPriceModalCell.setAttribute("placeholder", data[productFinalPrice]);
    noTaxPriceModalCell.setAttribute("placeholder", data[stateName][noTaxPrice]);
    profitModalCell.setAttribute("placeholder", data[stateName][profit]);

    // TODO
    //logisticCostsModalCell.setAttribute("placeholder", data[productLogisticCosts]);
}
